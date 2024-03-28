import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class DiffTest {
    private static String resultStylish;
    private static String resultPlain;
    private static String resultJson;
    public static String fileToString(String fileName) throws Exception {
        Path path = filePath(fileName);
        return Files.readString(path);
    }
    public static Path filePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName);
    }
    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = fileToString("resultJson.json");
        resultStylish = fileToString("resultStylish.json");
        resultPlain = fileToString("resultPlain.json");

    }
    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testDiffer(String format) throws Exception {
        String filePath1 = filePath("fileForTest1." + format).toString();
        String filePath2 = filePath("fileForTest2." + format).toString();
        assertThat(Differ.generate(filePath1, filePath2)).isEqualTo(resultStylish);
        assertThat(Differ.generate(filePath1, filePath2, "stylish")).isEqualTo(resultStylish);
        assertThat(Differ.generate(filePath1, filePath2, "plain")).isEqualTo(resultPlain);
        assertThat(Differ.generate(filePath1, filePath2, "json")).isEqualTo(resultJson);
    }
}
