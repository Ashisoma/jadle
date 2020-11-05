package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodtypeTest {

    @Before
    public static void setUp() throws Exception {

    }

    @After
    public static void tearDown() throws Exception {

    }


    @Test
    public void FoodtypeInstantiatesCorrectly() {
        Foodtype food = new Foodtype("ugali");
        assertEquals(true, food instanceof  Foodtype);
    }

    @Test
    public void setName() {
        Foodtype testFoodtype = setupFoodtype();
        testFoodtype.setName("breakfast");
        assertNotEquals("dessert", testFoodtype.getName());

    }

    //helper to above method
    private Foodtype setupFoodtype() {
        return new Foodtype("milk");
    }

    @Test
    public void setId() {
        Foodtype testFoodtype = setupFoodtype();
        testFoodtype.setId(5);
        assertEquals(5, testFoodtype.getId());
    }
}