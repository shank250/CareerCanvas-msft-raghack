import openai
import psycopg2
import pandas as pd
from psycopg2 import sql
from dotenv import load_dotenv
import os
# from openai import OpenAI
# Load environment variables from .env file
load_dotenv()
# Azure OpenAI setup
# Replace with your Azure OpenAI endpoint and key

openai.api_base = os.getenv('AZURE_OPENAI_ENDPOINT')
openai.api_key = os.getenv('OPENAI_API_KEY')

client = openai.AzureOpenAI(
    api_version="2023-05-15",
    api_key=os.getenv('OPENAI_API_KEY'),
    azure_deployment=os.getenv('AZURE_OPENAI_ENDPOINT')
)

# Database connection parameters
DB_PARAMS = {
    'dbname': 'postgres',
    'user': 'shankrag',
    'password':  os.getenv('DB_PASSWORD'),
    'host': os.getenv('DB_HOST'),
    'port': 5432  # If needed
}

# CSV file path
CSV_FILE_PATH = 'job_descriptions.csv'

# Function to generate embedding using Azure OpenAI Ada model
def generate_embedding(text):
    try:
        # response = openai.Embedding.create(
        #     input=text,
        #     model="text-embedding-ada-002",  # Use the correct model ID if different
        #     api_version="2023-05-15"  # Adjust API version based on Azure's documentation
        # )
        # return response['data'][0]['embedding']
        return client.embeddings.create(input = [text], model='text-embedding-ada-002').data[0].embedding
    except Exception as e:
        print(f"Error generating embedding: {e}")
        return None

# Function to insert data into PostgreSQL
def insert_data(cursor, row, embedding):
    try:
        # query = sql.SQL("""
        #     INSERT INTO job_postings (
        #         experience, qualifications, salary_range, location, country, latitude, 
        #         longitude, work_type, company_size, job_posting_date, preference, 
        #         contact_person, contact, job_title, role, job_portal, job_description, 
        #         benefits, skills, responsibilities, company_name, company_profile, embedding
        #     ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        # """)

        # cursor.execute(query, (
        #     row['Experience'], row['Qualifications'], row['Salary Range'], row['location'], 
        #     row['Country'], row['latitude'], row['longitude'], row['Work Type'], 
        #     row['Company Size'], row['Job Posting Date'], row['Preference'], 
        #     row['Contact Person'], row['Contact'], row['Job Title'], row['Role'], 
        #     row['Job Portal'], row['Job Description'], row['Benefits'], row['skills'], 
        #     row['Responsibilities'], row['Company'], row['Company Profile'], embedding
        # ))
        
        query = sql.SQL("""
            INSERT INTO job_postings (
                experience, qualifications, salary_range, location, country, work_type, company_size, job_posting_date, preference, 
                contact_person, contact, job_title, role, job_portal, job_description, 
                benefits, skills, responsibilities, company_name, company_profile, embedding
            ) VALUES (%s, %s, %s, %s,  %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        """)

        cursor.execute(query, (
            row['Experience'], row['Qualifications'], row['Salary Range'], row['location'], 
            row['Country'], row['Work Type'], 
            row['Company Size'], row['Job Posting Date'], row['Preference'], 
            row['Contact Person'], row['Contact'], row['Job Title'], row['Role'], 
            row['Job Portal'], row['Job Description'], row['Benefits'], row['skills'], 
            row['Responsibilities'], row['Company'], row['Company Profile'], embedding
        ))
        
    except Exception as e:
        print(f"Error inserting row: {e}")
        return False
    return True

# Main ingestion function with chunk processing
def ingest_data_in_chunks(chunk_size=1000):
    cursor = None
    conn = None
    try:
        # Connect to the PostgreSQL database
        conn = psycopg2.connect(**DB_PARAMS)
        cursor = conn.cursor()
        
        # Read the CSV file in chunks
        chunk_iter = pd.read_csv(CSV_FILE_PATH, chunksize=chunk_size)

        for chunk_num, chunk in enumerate(chunk_iter):
            print(f"Processing chunk {chunk_num+1}")
            
            # Process each row in the chunk
            for index, row in chunk.iterrows():
                print(f"Processing row {index+1} in chunk {chunk_num+1}")
                
                # Generate the embedding for the 'job_description' + 'skills' + 'responsibilities'
                combined_text = f"{row['Job Description']} {row['skills']} {row['Responsibilities']}"
                embedding = generate_embedding(combined_text)
                
                # Retry logic for embedding generation failure
                if embedding is None:
                    print(f"Skipping row {index+1} in chunk {chunk_num+1} due to embedding failure.")
                    continue

                # Insert the data into PostgreSQL
                success = insert_data(cursor, row, embedding)
                
                if not success:
                    print(f"Failed to insert row {index+1} in chunk {chunk_num+1}.")
                    continue
            
            # Commit the transaction for the chunk
            conn.commit()
            print(f"Chunk {chunk_num+1} committed successfully.")
        
    except Exception as e:
        print(f"Error in ingestion process: {e}")
    finally:
        # Close the database connection
        if cursor:
            cursor.close()
        if conn:
            conn.close()

# Run the data ingestion with chunking
if __name__ == "__main__":
    ingest_data_in_chunks(chunk_size=10)  # Adjust chunk size based on your needs
