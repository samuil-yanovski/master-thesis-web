package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;
import models.*;
import java.util.List;
import play.libs.Json;
import http.Response;

public class UsersController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public static Result getTeachers() {
        Response<List<Teacher>> response = new Response<List<Teacher>>();
        
        List<Teacher> teachers = Teacher.find.all();
        response.data = teachers;
        return ok(Json.toJson(response));
    }


}