package core.basesyntax.report;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.storage.Storage;
import core.basesyntax.storage.StorageImpl;
import org.junit.jupiter.api.Test;

class ReportGeneratorImplTest {
    private Storage storage = new StorageImpl();

    @Test
    void getReport_nullStorage_notOk() {
        storage = null;
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);
        assertThrows(IllegalArgumentException.class,
                () -> reportGenerator.getReport());
    }

    @Test
    void getReport_emptyStorage_notOk() {
        ReportGenerator reportGenerator = new ReportGeneratorImpl(storage);
        assertThrows(IllegalArgumentException.class,
                () -> reportGenerator.getReport());
    }
}
