package example.github.actions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {

    @Test
    public void testSimple() {
        System.out.println("Hello world!");
    }

    @Test
    public void testAssert() {
        assertTrue(true, "Junit KO");
    }

    @Test
    public void testKo() {
        assertTrue(false, "test KO");
    }
}