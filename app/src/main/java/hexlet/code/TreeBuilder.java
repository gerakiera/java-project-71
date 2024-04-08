package hexlet.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.util.Map;

public class TreeBuilder {
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
}
