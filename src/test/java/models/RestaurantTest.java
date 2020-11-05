package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantTest {
    @Test
    public void RestaurantInstantiatesCorrectly(){
        Restaurant restaurant = new Restaurant("baze","kibandaski","474dh","3452");
        assertEquals(true, restaurant instanceof Restaurant);
    }

}