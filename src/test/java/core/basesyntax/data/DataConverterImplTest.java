package core.basesyntax.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.transactions.FruitTransaction;
import java.util.List;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private final DataConverterImpl dataConverter = new DataConverterImpl();

    @Test
    void validInputData_Ok() {
        List<String> inputReport = List.of(
                "fruit,quantity",
                "b,banana,100",
                "s,apple,50"
        );

        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);
        assertEquals(transactions.size(), 2);
    }

    @Test
    void convertToTransaction_invalidLineFormat_throwsException() {
        List<String> inputReport = List.of(
                "fruit,quantity",
                "b,banana"
        );

        assertThrows(RuntimeException.class, () ->
                dataConverter.convertToTransaction(inputReport)
        );
    }

    @Test
    void convertToTransaction_invalidOperationCode_throwsException() {
        List<String> inputReport = List.of(
                "fruit,quantity",
                "x,banana,100"
        );

        assertThrows(RuntimeException.class, () ->
                dataConverter.convertToTransaction(inputReport)
        );
    }

    @Test
    void emptyList_NotOk() {
        List<String> inputReport = List.of();
        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);
        assertTrue(transactions.isEmpty());
    }
}
