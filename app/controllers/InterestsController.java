package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;
import models.*;
import java.util.List;
import play.libs.Json;
import http.Response;
import com.fasterxml.jackson.databind.JsonNode;
import javax.persistence.PersistenceException;
import http.Authenticated;

public class InterestsController extends Controller {
    
    private static Credentials getUserFromContext() {
        return (Credentials) Http.Context.current().args.get("user");
    }

    @With(Authenticated.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result create() {
        Response<Interest> response = new Response<Interest>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String name = json.findPath("name").textValue();
            
            Credentials credentials = getUserFromContext();
            Teacher teacher = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
            
            if (null == name || null == teacher) {
                response.message = "Missing parameter [name] or [authorization]";
                return badRequest(Json.toJson(response));
            } else {
                Interest interest = new Interest();
                interest.setName(name);
                interest.setTeacher(teacher);
                try {
                    interest.save();
                    response.data = interest;
                    return ok(Json.toJson(response));
                } catch (Exception e) {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                }
            }
        }
    }

    @With(Authenticated.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result update(Long id) {
        Response<Interest> response = new Response<Interest>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String name = json.findPath("name").textValue();
            
            Credentials credentials = getUserFromContext();
            Teacher teacher = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
                                
            Interest interest = Interest.find.where()
                                .eq("key", id)
                                .findUnique();
            
            if (null == name || null == teacher || null == interest) {
                response.message = "Missing parameter [name], [id] or [authorization]";
                return badRequest(Json.toJson(response));
            } else {
                if (teacher.getKey().equals(interest.getTeacher().getKey())) {
                    try {
                        interest.setName(name);
                        interest.save();
                        response.data = interest;
                        return ok(Json.toJson(response));
                    } catch (Exception e) {
                        response.message = "Invalid or duplicate user data";
                        return badRequest(Json.toJson(response));
                    }
                } else {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                }
            }
        }
    }

    @With(Authenticated.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result delete(Long id) {
        Response<Interest> response = new Response<Interest>();
            Credentials credentials = getUserFromContext();
            Teacher teacher = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
                                
            Interest interest = Interest.find.where()
                                .eq("key", id)
                                .findUnique();
            
            if (null == teacher || null == interest) {
                response.message = "Missing parameter [id] or [authorization]";
                return badRequest(Json.toJson(response));
            } else {
                if (teacher.getKey().equals(interest.getTeacher().getKey())) {
                    try {
                        interest.delete();
                        response.data = interest;
                        return ok(Json.toJson(response));
                    } catch (Exception e) {
                        response.message = "Invalid or duplicate user data";
                        return badRequest(Json.toJson(response));
                    }
                } else {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                }
            }
    }

}