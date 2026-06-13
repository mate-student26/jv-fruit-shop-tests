package core.basesyntax.data;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.report.ReportGenerator;
import core.basesyntax.report.ReportGeneratorImpl;
import core.basesyntax.storage.Storage;
import core.basesyntax.storage.StorageImpl;
import java.util.ArrayList;
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

    @Test
    void write_validDataOutput_ok() {
        Storage storage = new StorageImpl();
        storage.put("banana", 100);
        ReportGenerator report = new ReportGeneratorImpl(storage);
        String path = "src/main/resources/finalReport.csv";
        FileWriter writer = new FileWriterImpl();
        writer.write(report.getReport(), path);
        FileReader readFromFinalReport = new FileReaderImpl();
        ArrayList<String> expectedData = new ArrayList<>();
        expectedData.add("fruit,quantity");
        expectedData.add("banana,100");
        assertEquals(expectedData, readFromFinalReport.read(path));
    }
}
