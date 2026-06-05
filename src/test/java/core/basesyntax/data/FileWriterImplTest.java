package core.basesyntax.data;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FileWriterImplTest {
    private final FileWriterImpl fileWriterImpl = new FileWriterImpl();

    @Test
    void write_nullPath_notOk() {
        String path = null;
        String data = "fruit,quantity\nbanana,100";
        assertThrows(IllegalArgumentException.class,
                () -> fileWriterImpl.write(data, path));
    }

    @Test
    void write_nullData_notOk() {
        String path = "src/test/java/resources_test/finalReport.csv";
        String data = null;
        assertThrows(IllegalArgumentException.class,
                () -> fileWriterImpl.write(data, path));
    }

    @Test
    void write_emptyPath_notOk() {
        String path = "";
        String data = "fruit,quantity\nbanana,100";
        assertThrows(IllegalArgumentException.class,
                () -> fileWriterImpl.write(data, path));
    }

    @Test
    void write_emptyData_notOk() {
        String path = "src/test/java/resources_test/finalReport.csv";
        String data = "";
        assertThrows(IllegalArgumentException.class,
                () -> fileWriterImpl.write(data, path));
    }

    @Test
    void write_invalidPath_NotOk() {
        String path = "invalid/path/file.csv";
        String data = "fruit,quantity\nbanana,100";
        assertThrows(IllegalArgumentException.class, () ->
                fileWriterImpl.write(data, path)
        );
    }
}
