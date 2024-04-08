package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;


import static hexlet.code.Formatter.chooseFormat;
import static hexlet.code.TreeBuilder.build;

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
