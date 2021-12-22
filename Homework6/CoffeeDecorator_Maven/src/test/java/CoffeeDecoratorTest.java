import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



class CoffeeDecoratorTest {

    @BeforeEach
    void test() {
        Coffee order = new BasicCoffee();
        assertEquals(order.makeCoffee(), 3.99);
    }

    @Test
    void test1() {
        Coffee order = new Milk(new BasicCoffee());
        assertEquals(order.makeCoffee(), 4.99);
    }

    @Test
    void test2() {
        Coffee order = new Sugar(new Milk(new BasicCoffee()));
        assertEquals(order.makeCoffee(), 5.49);
    }

    @Test
    void test3() {
        Coffee order = new Cream(new Sugar(new Milk(new BasicCoffee())));
        assertEquals(order.makeCoffee(), 5.99);
    }

    @Test
    void test4() {
        Coffee order = new ExtraShot(new Cream(new Sugar(new Milk(new BasicCoffee()))));
        assertEquals(order.makeCoffee(), 7.19);
    }

    @Test
    void test5() {
        Coffee order = new flavouredSyrup(new ExtraShot(new Cream(new Sugar(new Milk(new BasicCoffee())))));
        assertEquals(order.makeCoffee(), 7.640000000000001);
    }

    @Test
    void test6() {
        Coffee order = new flavouredSyrup(new Cream(new Sugar(new Milk(new BasicCoffee()))));
        assertEquals(order.makeCoffee(), 6.44);
    }

    @Test
    void test7() {
        Coffee order = new flavouredSyrup(new Sugar(new Milk(new BasicCoffee())));
        assertEquals(order.makeCoffee(), 5.94);
    }

    @Test
    void test8() {
        Coffee order = new flavouredSyrup(new Milk(new BasicCoffee()));
        assertEquals(order.makeCoffee(), 5.44);
    }

    @Test
    void test9() {
        Coffee order = new flavouredSyrup(new BasicCoffee());
        assertEquals(order.makeCoffee(), 4.44);
    }

    @Test
    void test10() {
        Coffee order = new flavouredSyrup(new ExtraShot(new Sugar(new Milk(new BasicCoffee()))));
        assertEquals(order.makeCoffee(), 7.140000000000001);
    }

    @Test
    void test11() {
        Coffee order = new flavouredSyrup(new ExtraShot(new Cream(new Milk(new BasicCoffee()))));
        assertEquals(order.makeCoffee(), 7.140000000000001);
    }

    @Test
    void test12() {
        Coffee order = new flavouredSyrup(new ExtraShot(new Cream(new Sugar(new BasicCoffee()))));
        assertEquals(order.makeCoffee(), 6.640000000000001);
    }
}
