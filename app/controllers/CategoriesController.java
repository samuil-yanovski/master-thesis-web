package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;
import models.*;
import java.util.List;
import play.libs.Json;
import http.Response;

public class CategoriesController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public static Result getCategories() {
        Response<List<Category>> response = new Response<List<Category>>();
        
        List<Category> categories = Category.find.all();
        response.data = categories;
        return ok(Json.toJson(response));
    }


}