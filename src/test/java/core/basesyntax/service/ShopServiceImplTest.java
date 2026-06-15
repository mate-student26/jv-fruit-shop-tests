package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.operation.OperationHandler;
import core.basesyntax.storage.Storage;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.transactions.FruitTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ShopServiceImplTest {
    @Test
    void process_validTransactions_ok() {
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
    void process_emptyTransactionList_doesNotCallStrategy() {
        OperationStrategy strategy = Mockito.mock(OperationStrategy.class);
        Storage storage = Mockito.mock(Storage.class);
        ShopService service = new ShopServiceImpl(strategy, storage);
        service.process(List.of());
        Mockito.verifyNoInteractions(strategy);
    }

    @Test
    void process_nullTranscationList_notOk() {
        OperationStrategy operationStrategy = Mockito.mock(OperationStrategy.class);
        Storage storage = Mockito.mock(Storage.class);
        ShopService shopService = new ShopServiceImpl(operationStrategy, storage);
        assertThrows(IllegalArgumentException.class, () ->
                shopService.process(null));
    }

    @Test
    void process_missingHandler_notOk() {
        Map<FruitTransaction.Operation, OperationHandler> handlers = new HashMap<>();
        OperationStrategy operationStrategy = new OperationStrategyImpl(handlers);
        Storage storage = Mockito.mock(Storage.class);
        FruitTransaction transaction = new FruitTransaction();
        transaction.setOperation(FruitTransaction.Operation.RETURN);
        assertThrows(RuntimeException.class, () ->
                operationStrategy.process(transaction, storage));
    }
}
