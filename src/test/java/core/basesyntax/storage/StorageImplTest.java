package core.basesyntax.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StorageImplTest {
    @Test
    void storageExist_ok() {
        Storage expected = new StorageImpl();
        Storage actual = new StorageImpl();
        assertEquals(expected, actual);
    }

    @Test
    void validLinesInStorage_ok() {
        Storage expected = new StorageImpl();
        expected.put("banana", 100);

        Storage actual = new StorageImpl();
        actual.put("banana", 100);
        assertEquals(expected, actual);
    }

    @Test
    void removeWhenCurrentQuantityLeesThanQuantity_notOk() {
        Storage actual = new StorageImpl();
        actual.put("banana", 0);
        assertThrows(RuntimeException.class, () -> actual.remove("banana", 10));
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
    void showQuantity_ok() {
        Storage actual = new StorageImpl();
        actual.put("banana", 100);

        Storage expected = new StorageImpl();
        expected.put("banana", 100);
        assertEquals(expected.getQuantity("banana"), actual.getQuantity("banana"));
    }

    @Test
    void addQuantity_ok() {
        Storage actual = new StorageImpl();
        actual.put("banana", 100);
        actual.add("banana", 100);

        Storage expected = new StorageImpl();
        expected.put("banana", 200);
        assertEquals(expected.getQuantity("banana"), actual.getQuantity("banana"));
    }
}
