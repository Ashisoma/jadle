package DAO;

import models.Foodtype;
import models.Restaurant;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oFoodtypeDaoTest {
//    FoodtypeDao foodtypeDao = new FoodtypeDao() {
//        @Override
//        public void add(Foodtype foodtype) {
//
//        }
//
//        @Override
//        public List<Foodtype> getAll() {
//            return null;
//        }
//
//        @Override
//        public List<Restaurant> getAllRestaurantsForAFoodtype(int id) {
//            return null;
//        }
//
//        @Override
//        public void deleteById(int id) {
//
//        }
//
//        @Override
//        public void clearAll() {
//
//        }
//    };
//    RestaurantDao restaurantDao = new RestaurantDao() {
//        @Override
//        public void add(Restaurant restaurant) {
//
//        }
//
//        @Override
//        public void addRestaurantToFoodType(Restaurant restaurant, Foodtype foodtype) {
//
//        }
//
//        @Override
//        public List<Restaurant> getAll() {
//            return null;
//        }
//
//        @Override
//        public Restaurant findById(int id) {
//            return null;
//        }
//
//        @Override
//        public List<Foodtype> getAllFoodtypesForARestaurant(int restaurantId) {
//            return null;
//        }
//
//        @Override
//        public void update(int id, String name, String address, String zipcode, String phone, String website, String email) {
//
//        }
//
//        @Override
//        public void deleteById(int id) {
//
//        }
//
//        @Override
//        public void clearAll() {
//
//        }
//    };
//
//    @Test
//    public void addFoodTypeToRestaurantAddsTypeCorrectly() throws Exception {
//
//        Restaurant testRestaurant = setupRestaurant();
//        Restaurant altRestaurant = setupAltRestaurant();
//
//        restaurantDao.add(testRestaurant);
//        restaurantDao.add(altRestaurant);
//
//        Foodtype testFoodtype = setupNewFoodtype();
//
//        foodtypeDao.add(testFoodtype);
//
////        foodtypeDao.addFoodtypeToRestaurant(testFoodtype, testRestaurant);
////
////        foodtypeDao.addFoodtypeToRestaurant(testFoodtype, altRestaurant);
//
//        assertEquals(2, foodtypeDao.getAllRestaurantsForAFoodtype(testFoodtype.getId()).size());
//
//    }

//    private Foodtype setupNewFoodtype() {
//    }
//
//    private Restaurant setupRestaurant() {
//    }

}