package core.basesyntax.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.transactions.FruitTransaction;
import java.util.List;
import org.junit.jupiter.api.Test;

class DataConverterImplTest {
    private final DataConverterImpl dataConverter = new DataConverterImpl();

    @Test
    void convertToTransaction_validInput_ok() {
        List<String> inputReport = List.of(
                "fruit,quantity",
                "b,banana,100",
                "s,apple,50"
        );

        List<FruitTransaction> transactions = dataConverter
                .convertToTransaction(inputReport);
        assertEquals(2, transactions.size());
    }

    @Test
    void convertToTransaction_nullInput_notOk() {
        List<String> inputReport = null;
        assertThrows(NullPointerException.class, () -> dataConverter
                .convertToTransaction(inputReport));
    }

    @Test
    void convertToTransaction_invalidLineFormat_notOk() {
        List<String> inputReport = List.of(
                "fruit,quantity",
                "b,banana"
        );

        assertThrows(IllegalArgumentException.class, () -> dataConverter
                .convertToTransaction(inputReport)
        );
    }

    @Test
    void convertToTransaction_invalidOperationCode_notOk() {
        List<String> inputReport = List.of(
                "fruit,quantity",
                "x,banana,100"
        );

        assertThrows(IllegalArgumentException.class, () ->
                dataConverter.convertToTransaction(inputReport)
        );
    }
}
