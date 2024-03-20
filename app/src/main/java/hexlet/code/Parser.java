package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String fileContent, String format) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(fileContent, new TypeReference<Map<String, Object>>() {});
    }
}
