package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.Objects;
import java.util.LinkedHashMap;


import static hexlet.code.Formatter.chooseFormat;
public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> file1 = getData(filepath1);
        Map<String, Object> file2 = getData(filepath2);
        List<Map<String, Object>> diff = build(file1, file2);
        return chooseFormat(diff, format);
    }
    public static List<Map<String, Object>> build(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> keys = new TreeSet<>(file1.keySet());
        keys.addAll(file2.keySet());
        List<Map<String, Object>> result = new LinkedList<>();
        for (var key :keys) {
            Object value1 = file1.get(key) == null ? "null" : file1.get(key);
            Object value2 = file2.get(key) == null ? "null" : file2.get(key);
            if (Objects.equals(file1.get(key), (file2.get(key)))) {
                Map<String, Object> node = Map.of("type", "unchanged", "key", key, "newValue", value1);
                result.add(node);
            } else if (file1.containsKey(key) && !file2.containsKey(key)) {
                Map<String, Object> node = Map.of("type", "deleted", "key", key, "newValue", value1);
                result.add(node);
            } else if (file2.containsKey(key) && !file1.containsKey(key)) {
                Map<String, Object> node = Map.of("type", "added", "key", key, "newValue", value2);
                result.add(node);
            } else {
                Map<String, Object> node = Map.of("type", "changed", "key", key, "oldValue",
                        value1, "newValue", value2);
                result.add(node);
            }
        }
        return result;
    }
    public static Map<String, Object> getData(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String fileContent = Files.readString(path);
        String format = getFileFormat(filePath);
        Map<String, Object> parsedData = new LinkedHashMap<>();
        Map<String, Object> parsedMap = Parser.parser(fileContent, format);

        for (Map.Entry<String, Object> entry : parsedMap.entrySet()) {
            parsedData.put(entry.getKey(), entry.getValue());
        }

        return parsedData;
    }
    public static String getFileFormat(String path) {
        return path.substring(path.lastIndexOf('.') + 1).toLowerCase();
    }
}
