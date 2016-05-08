package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;
import models.*;
import java.util.List;
import play.libs.Json;
import http.Response;
import com.fasterxml.jackson.databind.JsonNode;
import javax.persistence.PersistenceException;

public class CategoriesController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public static Result getCategories() {
        Response<List<Category>> response = new Response<List<Category>>();
        
        List<Category> categories = Category.find.all();
        response.data = categories;
        return ok(Json.toJson(response));
    }
    
    private static Category createCategory(String name) {
	    Category category = new Category();
	    category.setName(name);
	    try {
            category.save();
            return category;
        } catch (PersistenceException e) {
            return null;
        }
	}
	

    @BodyParser.Of(BodyParser.Json.class)
    public static Result create() {
        Response<Category> response = new Response<Category>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String name = json.findPath("name").textValue();
            
            if (null == name) {
                response.message = "Missing parameter [name]";
                return badRequest(Json.toJson(response));
            } else {
                Category category = createCategory(name);
                if (null == category) {
                    response.message = "Invalid or duplicate data";
                    return badRequest(Json.toJson(response));
                } else {
                    response.data = category;
                    return ok(Json.toJson(response));
                }
            }
        }
    }

}