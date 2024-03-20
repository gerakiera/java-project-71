package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> file1 = getData(filepath1);
        Map<String, Object> file2 = getData(filepath2);
        StringBuilder resultFile = new StringBuilder("{\n");
        for (Map.Entry<String, Object> entry : file1.entrySet()) {
            for (Map.Entry<String, Object> entry2 : file2.entrySet()) {
                if (entry.getKey().equals(entry2.getKey()) && entry.getValue().equals(entry2.getValue())) {
                    resultFile.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                if (entry.getKey().equals(entry2.getKey()) && !entry.getValue().equals(entry2.getValue())) {
                    resultFile.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                    resultFile.append("+ ").append(entry2.getKey()).append(": ").append(entry2.getValue()).append("\n");
                }
                if(!file1.containsKey(entry2.getKey())) {
                    resultFile.append("+ ").append(entry2.getKey()).append(": ").append(entry2.getValue()).append("\n");
                }
                if(!file2.containsKey(entry.getKey())) {
                    resultFile.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
            }
        }
        resultFile.append("}");
        return resultFile.toString();
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
