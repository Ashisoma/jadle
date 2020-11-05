package DAO;

import models.Foodtype;
import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oFoodtypeDao implements FoodtypeDao{
    private final Sql2o sql2o;

    public Sql2oFoodtypeDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(Foodtype foodtype) {
            String sql = "INSERT INTO foodtypes (name) VALUES (:name)";
            try (Connection con = sql2o.open()){
                int id = (int) con.createQuery(sql, true)
                        .bind(foodtype)
                        .executeUpdate()
                        .getKey();
                foodtype.setId(id);
            }catch (Sql2oException ex){
                System.out.println(ex);
            }

    }

    @Override
    public List<Foodtype> getAll() {
        try (Connection con = sql2o.open()){
        String sql = "SELECT * FROM foodtype";
        con.createQuery(sql)
                .executeAndFetch(Foodtype.class);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurantsForAFoodtype(int id) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
      String joinSql = "SELECT restaurantId FROM restaurant_foodtypes WHERE foodtypesId=:foodtypeId";
      try (Connection con = sql2o.open()){
          List<Integer> allRestaurantsIds = con.createQuery(joinSql)
               .executeAndFetch(Integer.class);
          for(Integer restaurantId: allRestaurantsIds){
              String restaurantQuery = "SELECT * FROM restaurants WHERE id = :restaurantId";
              restaurants.add(
                      con.createQuery(restaurantQuery)
                      .addParameter("restaurantId", restaurantId)
                      .executeAndFetchFirst(Restaurant.class)
              );
          }
      }catch (Sql2oException ex){
          System.out.println(ex);
      }
      return restaurants;
    }

    @Override
    public void addFoodtypeToRestaurant(Foodtype foodtype, Restaurant restaurant) {
        String sql = "INSERT INTO restaurants_foodtypes (restaurantid, foodtypeid) VALUES (:restaurantId, :foodtypeId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("restaurantId", restaurant.getId())
                    .addParameter("foodtypeId", foodtype.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM foodtype WHERE id=:id ";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Foodtype.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void clearAll() {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE * FROM foodtype";
            con.createQuery(sql)
                    .executeAndFetch(Foodtype.class);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
