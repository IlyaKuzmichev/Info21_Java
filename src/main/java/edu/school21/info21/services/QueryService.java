package edu.school21.info21.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import edu.school21.info21.model.QueryItem;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class QueryService {
    private final Map<String, List<QueryItem>> queries;

    public QueryService() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        this.queries = mapper.readValue(
                new ClassPathResource("queries.yml").getInputStream(),
                new TypeReference<>() {
                }
        );
    }

    public Map<String, List<QueryItem>> getAllQueries() {
        return queries;
    }
}
