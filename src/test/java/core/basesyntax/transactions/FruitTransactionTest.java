package core.basesyntax.transactions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FruitTransactionTest {
    @Test
    void setQuantity_negativeQuantity_notOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        assertThrows(IllegalArgumentException.class, () ->
                fruitTransaction.setQuantity(-100));
    }

    @Test
    void setFruit_nullFruit_notOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        assertThrows(NullPointerException.class, () ->
                fruitTransaction.setFruit(null));
    }

    @Test
    void setFruit_emptyFruit_notOk() {
        FruitTransaction fruitTransaction = new FruitTransaction();
        assertThrows(IllegalArgumentException.class, () ->
                fruitTransaction.setFruit(""));
    }
}
