package core.basesyntax.storage;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StorageImplTest {

    @Test
    void validLinesInStorage_ok() {
        Storage expected = new StorageImpl();
        expected.put("banana", 100);

        Storage actual = new StorageImpl();
        actual.put("banana", 100);
        assertEquals(expected, actual);
    }

    @Test
    void removeWhenCurrentQuantityLessThanQuantity_notOk() {
        Storage actual = new StorageImpl();
        actual.put("banana", 0);
        assertThrows(IllegalArgumentException.class, () ->
                actual.remove("banana", 10));
    }

    @Test
    void removeWhenFruitNotExist_notOk() {
        Storage actual = new StorageImpl();
        assertThrows(IllegalArgumentException.class, () ->
                actual.remove("banana", 100));
    }

    @Test
    void removeExactQuantityRemoveKey_ok() {
        Storage storage = new StorageImpl();
        storage.put("banana", 100);
        storage.remove("banana", 100);
        assertEquals(0, storage.getQuantity("banana"));
        assertFalse(storage.getAll().containsKey("banana"));
    }

    @Test
    void showAllInput_ok() {
        Storage actual = new StorageImpl();
        actual.put("banana", 100);

        Storage expected = new StorageImpl();
        expected.put("banana", 100);
        assertEquals(expected.getAll(), actual.getAll());
    }

    @Test
    void getQuantity_whenFruitsNotExist_ok() {
        Storage actual = new StorageImpl();
        int result = actual.getQuantity("banana");
        assertEquals(0, result);
    }

    @Test
    void put_OverrideExistingValue_ok() {
        Storage actual = new StorageImpl();
        actual.put("banana", 100);
        actual.put("banana", 50);
        assertEquals(50, actual.getQuantity("banana"));
    }

    @Test
    void addingNotExistingFruit_Ok() {
        Storage actual = new StorageImpl();
        actual.add("banana", 100);
        assertEquals(100, actual.getQuantity("banana"));
    }

    @Test
    void put_nullFruit_notOk() {
        Storage storage = new StorageImpl();
        assertThrows(IllegalArgumentException.class, () ->
                storage.put(null, 100));
    }
}
