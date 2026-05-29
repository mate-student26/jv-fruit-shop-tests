package core.basesyntax.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class FileWriterImplTest {
    private final FileWriterImpl fileWriterImpl = new FileWriterImpl();

    @Test
    void write_validPath_Ok() throws IOException {
        String path = "src/test/java/resources_test/finalReport.csv";
        String data = "fruit,quantity\nbanana,100";
        fileWriterImpl.write(data, path);

        String fileContent = Files.readString(Paths.get(path));
        assertEquals(data, fileContent);
    }

    @Test
    void write_invalidPath_NotOk() {
        String path = "invalid/path/file.csv";
        String data = "fruit,quantity\nbanana,100";

        assertThrows(RuntimeException.class, () ->
                fileWriterImpl.write(data, path)
        );
    }
}
