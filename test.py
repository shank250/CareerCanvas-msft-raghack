import pandas as pd

file_path = 'job_descriptions.csv'
# Create a DataFrame
# df = pd.read_csv('job_descriptions.csv', nrows=1000)

# print(df.columns)


# Define the mapping from existing column names to the new column names
column_mapping = {
    'Job Id': 'job_id',
    'Experience': 'experience',
    'Qualifications': 'qualifications',
    'Salary Range': 'salary_range',
    'location': 'location',
    'Country': 'country',
    'latitude': 'latitude',
    'longitude': 'longitude',
    'Work Type': 'work_type',
    'Company Size': 'company_size',
    'Job Posting Date': 'job_posting_date',
    'Preference': 'preference',
    'Contact Person': 'contact_person',
    'Contact': 'contact',
    'Job Title': 'job_title',
    'Role': 'role',
    'Job Portal': 'job_portal',
    'Job Description': 'job_description',
    'Benefits': 'benefits',
    'skills': 'skills',
    'Responsibilities': 'responsibilities',
    'Company': 'company_name',
    'Company Profile': 'company_profile'
}

# Step 1: Read only the header to get current column names
df_header = pd.read_csv(file_path, nrows=0)

# Step 2: Rename the columns based on the mapping
# df_header.rename(columns=column_mapping, inplace=True)

# Print the renamed columns to verify
print(df_header.columns)

# Step 3: If needed, read the data with the new column names
# df_data = pd.read_csv(file_path, names=df_header.columns, header=0)
