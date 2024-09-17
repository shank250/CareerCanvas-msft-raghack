package com.example.raghack.service;
// package com.example.demo.service;
// package com.example.demo.service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OpenAISearchService {

    private final OpenAIClient client;

    public OpenAISearchService() {
        String endpoint = System.getenv("OPENAI_ENDPOINT");
        String apiKey = System.getenv("OPENAI_API_KEY");
        
        // Initialize the OpenAIClient using the builder pattern
        client = new OpenAIClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(apiKey))
                .buildClient();
    }

    public List<Float> generateEmbedding(String query) {
        // Use the constructor to set input directly
        EmbeddingsOptions options = new EmbeddingsOptions(Collections.singletonList(query))
                .setModel("text-embedding-ada-002")
                .setInputType("text"); // Assuming input type should be text

        // Make the API call and extract the embedding from the response
        return client.getEmbeddings("text-embedding-ada-002",options)
                     .getData()
                     .get(0)
                     .getEmbedding();
    }
}
