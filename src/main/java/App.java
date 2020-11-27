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

        String connectionString = "jdbc:postgresql://localhost:5432/jadle";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

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

        get("/restaurants", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(restaurantDao.getAll());//send it back to be displayed
        });

        get("/restaurants/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int restaurantId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(restaurantDao.findById(restaurantId));
        });

    }
}
