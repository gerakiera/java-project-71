import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class DiffTest {
    public String fileToString(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(path);
    }
    @Test
    public void testDifferJson() throws Exception {
        String result = Differ.generate("src/main/resources/file1.json", "src/main/resources/file2.json");
        String filePathJson = "src/test/resources/resultJson.json";
        assertThat(fileToString(filePathJson)).isEqualTo(result);
    }
}
