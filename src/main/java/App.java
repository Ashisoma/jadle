import DAO.Sql2oFoodtypeDao;
import DAO.Sql2oRestaurantDao;
import DAO.Sql2oReviewDAO;
import com.google.gson.Gson;
import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;
import static spark.route.HttpMethod.post;

public class App {
    public static void main(String[] args) {
        System.out.println("Its GOing DOwn FOr REal");

        Sql2oFoodtypeDao foodtypeDao;
        Sql2oReviewDAO reviewDAO;
        Sql2oRestaurantDao restaurantDao;

        Connection con;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2~/jadle.db;INIT=RUNSCRIPT from from 'classpath:create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        restaurantDao = new Sql2oRestaurantDao(sql2o);
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        reviewDAO = new Sql2oReviewDAO(sql2o);
        con = sql2o.open();


        post("/restaurants/new", "application/json", (req, res) -> {// accept a request in format JSON from app
                Restaurant restaurant = gson.fromJson(req.body(), Restaurant.class);// make java from JSON from an app
                restaurantDao.add(restaurant);//A-OK! But why 201??
                res.status(201);
                res.type("application/json");// send it back to be displayed
                return gson.toJson(restaurant);
            });

    }
}
