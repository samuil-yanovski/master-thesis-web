package controllers;

import play.mvc.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;
import models.*;
import java.util.List;
import play.libs.Json;
import http.Response;
import com.fasterxml.jackson.databind.JsonNode;

public class UsersController extends Controller {
        
    private static Credentials getUserFromContext() {
        return (Credentials) Http.Context.current().args.get("user");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result getTeachers() {
        Response<List<Teacher>> response = new Response<List<Teacher>>();
        
        List<Teacher> teachers = Teacher.find.all();
        response.data = teachers;
        return ok(Json.toJson(response));
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result getStudents() {
        Response<List<Student>> response = new Response<List<Student>>();
        
        List<Student> students = Student.find.all();
        response.data = students;
        return ok(Json.toJson(response));
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateTeacher() {
        Response<Teacher> response = new Response<Teacher>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String name = json.findPath("name").textValue();
            String image = json.findPath("image").textValue();
            
                Credentials credentials = getUserFromContext();
                Teacher teacher = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
                
                if (null == teacher) {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                } else {
                    teacher.setAvatar(image);
                    teacher.setName(name);
                    
                    try {
                        teacher.save();
                        response.data = teacher;
                        return ok(Json.toJson(response));
                    } catch (Exception e) {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                    }
                }
        }
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result updateStudent() {
        Response<Student> response = new Response<Student>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String name = json.findPath("name").textValue();
            String image = json.findPath("image").textValue();
            
                Credentials credentials = getUserFromContext();
                Student student = Student.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
                
                if (null == student) {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                } else {
                    student.setAvatar(image);
                    student.setName(name);
                    
                    try {
                        student.save();
                        response.data = student;
                        return ok(Json.toJson(response));
                    } catch (Exception e) {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                    }
                }
        }
    }


}