package com.example.raghack.service;
// package com.example.demo.service;

import com.example.raghack.model.JobPosting;
import com.example.raghack.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostgresSearchService {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<JobPosting> searchByEmbedding(float[] queryEmbedding) {
        // SQL query to perform vector search in PostgreSQL
        String sql = """
            SELECT * FROM job_postings
            ORDER BY embedding <-> ?::vector
            LIMIT 10;
        """;

        return jdbcTemplate.query(
                sql,
                new Object[]{queryEmbedding},
                (rs, rowNum) -> new JobPosting(
                    rs.getLong("job_id"),
                    rs.getInt("experience"),
                    rs.getString("job_title"),
                    rs.getString("job_description"),
                    rs.getString("skills")
                )
        );
    }
}
