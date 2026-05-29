package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.operation.OperationHandler;
import core.basesyntax.operation.ReturnOperation;
import core.basesyntax.storage.Storage;
import core.basesyntax.storage.StorageImpl;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.transactions.FruitTransaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ShopServiceImplTest {
    @Test
    void noEmptyStorage_Ok() {
        Storage expectedStorage = new StorageImpl();
        expectedStorage.put("banana", 200);
        final int expectedQuantity = expectedStorage.getQuantity("banana");

        Storage storage = new StorageImpl();
        storage.put("banana", 100);

        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(100);
        transaction.setOperation(FruitTransaction.Operation.RETURN);

        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        OperationHandler handler = new ReturnOperation();
        handlers.put(FruitTransaction.Operation.RETURN, handler);

        OperationStrategy operationStrategy = new OperationStrategyImpl(handlers);
        List<FruitTransaction> transactions;
        transactions = new ArrayList<>();
        transactions.add(transaction);
        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);
        shopService.process(transactions);
        assertEquals(expectedQuantity, storage.getQuantity("banana"));
    }

    @Test
    void emptyTransactionList_Ok() {
        Storage expectedStorage = new StorageImpl();
        Storage storage = new StorageImpl();
        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        OperationStrategy operationStrategy = new OperationStrategyImpl(handlers);
        List<FruitTransaction> transactions = new ArrayList<>();
        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);
        shopService.process(transactions);
        assertEquals(expectedStorage, storage);
    }

    @Test
    void multipleTransactions_Ok() {
        final Storage storage = new StorageImpl();

        FruitTransaction transaction1 = new FruitTransaction();
        transaction1.setFruit("banana");
        transaction1.setQuantity(100);
        transaction1.setOperation(FruitTransaction.Operation.RETURN);

        FruitTransaction transaction2 = new FruitTransaction();
        transaction2.setFruit("apple");
        transaction2.setQuantity(50);
        transaction2.setOperation(FruitTransaction.Operation.RETURN);

        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        OperationHandler handler = new ReturnOperation();
        handlers.put(FruitTransaction.Operation.RETURN, handler);

        OperationStrategy operationStrategy = new OperationStrategyImpl(handlers);
        List<FruitTransaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);
        shopService.process(transactions);
        assertEquals(100, storage.getQuantity("banana"));
        assertEquals(50, storage.getQuantity("apple"));
    }
}
