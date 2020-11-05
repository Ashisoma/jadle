package DAO;

import models.Foodtype;
import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.List;

public class Sql2oRestaurantDao implements RestaurantDao {

    private final Sql2o sql2o;

    public Sql2oRestaurantDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Restaurant restaurant) {
      String sql = "INSERT INTO restaurants (name, address, zipcode, phone, email, website) VALUES (:name, :address, :zipcode, :phone, :email, :website)";
      try(Connection con = sql2o.open()){
          int id = (int) con.createQuery(sql, true)
                  .throwOnMappingFailure(false)
                  .bind(restaurant)
                  .executeUpdate()
                  .getKey();
          restaurant.setId(id);
      }catch (Sql2oException ex){
          System.out.println(ex);
      }
    }

    @Override
    public void addRestaurantToFoodType(Restaurant restaurant, Foodtype foodtype) {
     String sql = "INSERT INTO restaurants_foodtypes (restaurantId, foodtypeId) VALUES (:restaurantId, :foodtypesId)";
     try(Connection con = sql2o.open()){
         con.createQuery(sql)
                 .addParameter("restaurantId", restaurant.getId())
                 .addParameter("foodtypeId", foodtype.getId())
                 .executeUpdate();
     }catch (Sql2oException ex){
         System.out.println(ex);
     }
    }

    @Override
    public List<Restaurant> getAll() {
       String sql = "SELECT * FROM restaurants";
       try(Connection con = sql2o.open()){
           return con.createQuery(sql)
                   .throwOnMappingFailure(false)
                   .executeAndFetch(Restaurant.class);
       }
    }

    @Override
    public Restaurant findById(int id) {
        String sql = "SELECT * FROM restaurants WHERE id =:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Restaurant.class);
        }catch (Sql2oException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public List<Foodtype> getAllFoodtypesByRestaurant(int restaurantId) {
        List<Foodtype> foodtypes = new ArrayList<>();
        String joinSql = "SELECT foodtypeId FROM restaurant_foodtypes WHERE restaurantId=:restaurantId";
          try(Connection con = sql2o.open()){
             List<Integer> allFoodTypesIds = con.createQuery(joinSql)
                      .addParameter("restaurantId", restaurantId)
                      .executeAndFetch(Integer.class);
              for (Integer foodId : allFoodTypesIds) {
                  String foodtypeQuery = "SELECT * FROM foodtypes WHERE id = :foodtypesId";
                  foodtypes.add(
                          con.createQuery(foodtypeQuery)
                                  .addParameter("foodtypeId", foodId)
                                  .executeAndFetchFirst(Foodtype.class));
              }
              }catch (Sql2oException ex){
              System.out.println(ex);
          }
          return foodtypes;
    }

    @Override
    public void update(int id, String name, String address, String zipcode, String phone, String website, String email) {
        String sql = "UPDATE INTO restaurants (name, address, zipcode, phone, email, website) VALUES (:name, :address, :zipcode, :phone, :email, :website)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql, true)
                    .throwOnMappingFailure(false)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("address", address)
                    .addParameter("zipcode", zipcode)
                    .addParameter("phone", phone)
                    .addParameter("website", website)
                    .addParameter("email", email)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM restaurant WHERE id =:id";
        try (Connection con = sql2o.open()) {
             con.createQuery(sql)
                    .throwOnMappingFailure(false)
                     .addParameter("id", id)
                    .executeAndFetch(Restaurant.class);
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE * FROM restaurants";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Restaurant.class);
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
