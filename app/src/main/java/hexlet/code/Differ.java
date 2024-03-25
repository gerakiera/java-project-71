package hexlet.code;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Differ {
    static private final String UNCHANGED = "unchaged";
    static private final String ADDED = "added";
    static private final String DELETED = "deleted";
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> file1 = getData(filepath1);
        Map<String, Object> file2 = getData(filepath2);
        TreeMap<String,String> resultFile= new TreeMap<>();
        for (var key : file1.keySet()) {
            Object value1 = file1.get(key);
            Object value2 = file2.get(key);
                if (file2.containsKey(key) && value1.equals(value2)) {
                    resultFile.put(key + ": " + value1 + "\n", UNCHANGED);
                }
                if (file2.containsKey(key) && !value1.equals(value2)) {
                    resultFile.put(key + ": " + value1 + "\n", DELETED);
                    resultFile.put(key + ": " + value2 + "\n", ADDED);
                }
                if(!file2.containsKey(key)) {
                    resultFile.put(key + ": " + value1 + "\n", DELETED);
            }
        }
        for (var key : file2.keySet()) {
            if(!file1.containsKey(key)) {
                resultFile.put(key + ": " + file2.get(key) + "\n", ADDED);
            }
        }
        return format(resultFile);
    }
    public static String format(TreeMap<String, String> resultFile) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map.Entry<String, String> entry : resultFile.entrySet()) {
            switch (entry.getValue()) {
                case UNCHANGED -> result.append(" " + " ").append(entry.getKey());
                case ADDED -> result.append("+" + " ").append(entry.getKey());
                case DELETED -> result.append("-" + " ").append(entry.getKey());
            }
        }
        result.append("}");
        return result.toString();
    }
    public static Map<String, Object> getData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        String fileContent = Files.readString(path);
        String format = getFileFormat(filePath);
        return Parser.parser(fileContent, format);
    }
    public static String getFileFormat(String path) {
        return path.substring(path.lastIndexOf('.') + 1).toLowerCase();
    }
}
