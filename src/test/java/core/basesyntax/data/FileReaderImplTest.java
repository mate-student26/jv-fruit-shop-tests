package core.basesyntax.data;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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
    void read_invalidPath_notOk() {
        assertThrows(IllegalArgumentException.class, () ->
                fileReaderImpl.read("src/test/java/resources_test/reportToRead.txt"));
    }

    @Test
    void read_validAllData_ok() {
        ArrayList<String> expectedData = new ArrayList<>();
        expectedData.add("type,fruit,quantity");
        expectedData.add("b,banana,100");
        String path = "src/main/resources/reportToRead.csv";
        assertEquals(expectedData, fileReaderImpl.read(path));
    }
}
