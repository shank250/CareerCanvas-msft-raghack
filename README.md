# Job Finder Chatbot with RAG

This project is a chatbot application aimed at helping users find job opportunities and get relevant answers to questions about job roles. It leverages Retrieval-Augmented Generation (RAG) to provide personalized job recommendations and answers based on a user's skills, experience, and preferences. The system uses **Azure OpenAI**, **Azure AI Search**, and **Azure PostgreSQL Vector Database** for efficient and accurate search results. The backend is built with **Java** and **Spring Boot**.

## Features

- **Job Search**: Users can search for jobs based on their experience, skills, and preferences.
- **Job Recommendations**: The system suggests relevant jobs tailored to a user’s profile.
- **Q&A System**: Users can ask questions about job postings, such as required qualifications, experience, or job responsibilities, and get instant answers.
- **RAG Integration**: Retrieval-Augmented Generation is used to enhance the relevance of search results by combining real-time search and pre-trained language models.
  
## Technology Stack

- **Azure OpenAI**: Used for generating embeddings and enhancing natural language understanding.
- **Azure AI Search**: Provides vector-based search capabilities to quickly retrieve relevant job postings based on embeddings.
- **Azure PostgreSQL Vector Database**: Stores job data with vector embeddings for efficient searching.
- **Java**: Core programming language for the backend.
- **Spring Boot**: Framework for building and managing the backend REST API.

## Requirements

- Java 21 or later
- Maven for dependency management
- Azure account with the following services:
  - Azure OpenAI
  - Azure AI Search
  - Azure PostgreSQL
- PostgreSQL with the `pgvector` extension installed

## Setup Instructions

### 1. Clone the Repository

Clone the project repository:

git clone [https://github.com/shank250/CareerCanvas-msft-raghack](https://github.com/shank250/CareerCanvas-msft-raghack)

Navigate to the project directory:

cd job-finder-chatbot

### 2. Configure Database

- Set up **Azure PostgreSQL** and enable the `pgvector` extension for handling vector-based data.
- Update your PostgreSQL credentials in the **application.properties** or **application.yml** file in your Spring Boot project.

Example properties:

spring.datasource.url=jdbc:postgresql://<db-url>:5432/<db-name>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>

### 3. Set Up Azure AI Search and OpenAI

1. Create an **Azure OpenAI** and **Azure AI Search** resource.
2. Add your **API keys** and **Endpoint URLs** to your Spring Boot application properties.

Example:

azure.openai.api-key=<your-openai-api-key>
azure.search.endpoint=<your-azure-search-endpoint>
azure.search.api-key=<your-search-api-key>

### 4. Run the Spring Boot Application

Build and run the Spring Boot application with:

mvn clean install
mvn spring-boot:run

### 5. APIs

The application exposes the following APIs:

- **/search-jobs**: Allows users to search for jobs based on keywords, experience, and qualifications.
- **/ask-question**: Users can ask questions related to specific job postings and get instant responses.

### 6. Embedding Data

- During job posting ingestion, embeddings are computed using **Azure OpenAI** and stored in the vector column in **Azure PostgreSQL**.
- Queries are transformed into embeddings by **Azure AI Search** to retrieve the most relevant job postings.

## Future Enhancements

- Support for multiple languages for job searches and queries.
- Expand the recommendation engine with more advanced ML models.
- Add user authentication for personalized job alerts.

## License

This project is licensed under the MIT License. 

Feel free to contribute and make improvements!
