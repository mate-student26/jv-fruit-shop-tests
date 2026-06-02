package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.operation.OperationHandler;
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
import org.mockito.Mockito;

class ShopServiceImplTest {
    @Test
    void multipleTransactions_Ok() {
        OperationStrategy operationStrategy = Mockito.mock(OperationStrategy.class);
        Storage storage = Mockito.mock(Storage.class);

        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(100);
        transaction.setOperation(FruitTransaction.Operation.RETURN);

        List<FruitTransaction> transactions = List.of(transaction);

        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);
        shopService.process(transactions);

        Mockito.verify(operationStrategy).process(transaction, storage);
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
    void nullTranscationList_notOk() {
        OperationStrategy operationStrategy = Mockito.mock(OperationStrategy.class);
        Storage storage = Mockito.mock(Storage.class);
        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);
        assertThrows(NullPointerException.class, () ->
                shopService.process(null));
    }

    @Test
    void missingHandler_notOk() {
        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        OperationStrategy operationStrategy = new OperationStrategyImpl(handlers);
        Storage storage = Mockito.mock(Storage.class);
        FruitTransaction transaction = new FruitTransaction();
        transaction.setOperation(FruitTransaction.Operation.RETURN);
        assertThrows(RuntimeException.class, () ->
                operationStrategy.process(transaction, storage));
    }
}
