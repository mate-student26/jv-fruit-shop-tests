package core.basesyntax.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.storage.Storage;
import core.basesyntax.storage.StorageImpl;
import core.basesyntax.transactions.FruitTransaction;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BalanceOperationTest {

    @Test
    void noEmptyFruitsLine_Ok() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("banana", 100);
        Storage storage = new StorageImpl();
        BalanceOperation balanceOperation = new BalanceOperation();
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(100);
        balanceOperation.operate(transaction, storage);
        assertEquals(expected, storage.getAll());
    }
}
