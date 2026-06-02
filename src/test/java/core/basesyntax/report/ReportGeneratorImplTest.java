package core.basesyntax.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.storage.Storage;
import core.basesyntax.storage.StorageImpl;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {

    @Test
    void getReport_emptyStorage_Ok() {
        Storage storage = new StorageImpl();
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);
        String report = reportGenerator.getReport();
        assertEquals("fruit,quantity\n", report);
    }

    @Test
    void getReport_correctInputData_Ok() {
        Storage storage = new StorageImpl();
        Map<String, Integer> fruits = Map.of("banana", 100, "apple", 200);
        for (var entry : fruits.entrySet()) {
            storage.add(entry.getKey(), entry.getValue());
        }

        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);
        String report = reportGenerator.getReport();
        assertEquals("fruit,quantity\nbanana,100\napple,200\n", report);
    }
}
