package core.basesyntax.data;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class FileReaderImplTest {
    private final FileReaderImpl fileReaderImpl = new FileReaderImpl();

    @Test
    void read_validPath_Ok() {
        String path = "src/test/java/resources_test/finalReport.csv";
        fileReaderImpl.read(path);
        assertTrue(Files.exists(Paths.get(path)));
    }

    @Test
    void read_invalidPath_throwsException() {
        assertThrows(RuntimeException.class, () ->
                fileReaderImpl.read("src/test/java/resources_test/reportToRead.txt"));
    }
}
