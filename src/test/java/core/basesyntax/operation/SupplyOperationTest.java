package core.basesyntax.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.storage.Storage;
import core.basesyntax.storage.StorageImpl;
import core.basesyntax.transactions.FruitTransaction;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SupplyOperationTest {
    @Test
    void addingPossitiveQuantityToStorage_Ok() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("banana", 110);
        Storage storage = new StorageImpl();
        storage.put("banana", 100);
        SupplyOperation operation = new SupplyOperation();
        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(10);
        operation.operate(transaction, storage);
        assertEquals(expected, storage.getAll());
    }
}
