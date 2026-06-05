package core.basesyntax.data;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FileReaderImplTest {
    private final FileReaderImpl fileReaderImpl = new FileReaderImpl();

    @Test
    void read_nullPath_notOk() {
        String path = null;
        assertThrows(IllegalArgumentException.class,
                () -> fileReaderImpl.read(path));
    }

    @Test
    void read_emptyPath_notOk() {
        String path = "";
        assertThrows(IllegalArgumentException.class,
                () -> fileReaderImpl.read(path));
    }

    @Test
    void read_invalidPath_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                fileReaderImpl.read("src/test/java/resources_test/reportToRead.txt"));
    }
}
