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
import org.joda.time.DateTime;


public class DatesController extends Controller {
    
    private static Credentials getUserFromContext() {
        return (Credentials) Http.Context.current().args.get("user");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result getGraduationDates() {
        Response<List<GraduationDate>> response = new Response<List<GraduationDate>>();
        
        List<GraduationDate> dates = GraduationDate.find.all();
        response.data = dates;
        return ok(Json.toJson(response));
    }

    @With(Authenticated.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result createGraduationDate() {
        Response<GraduationDate> response = new Response<GraduationDate>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String name = json.findPath("name").textValue();
            long timestamp = json.findPath("timestamp").longValue();
            
            Credentials credentials = getUserFromContext();
            Teacher teacher = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
            
            if (null == name || null == teacher || 0 == timestamp) {
                response.message = "Missing parameter [name], [timestamp] or [authorization]";
                return badRequest(Json.toJson(response));
            } else {
                GraduationDate date = new GraduationDate();
                date.setName(name);
                date.setDay(new DateTime(timestamp));
                try {
                    date.save();
                    response.data = date;
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
    public static Result updateGraduationDate(long id) {
        Response<GraduationDate> response = new Response<GraduationDate>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String name = json.findPath("name").textValue();
            long timestamp = json.findPath("timestamp").longValue();
            
            Credentials credentials = getUserFromContext();
            Teacher teacher = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
                                
            GraduationDate date = GraduationDate.find.where()
                                .eq("key", id)
                                .findUnique();
                                
            if ((null == name && 0 == timestamp) || null == teacher || null == date) {
                response.message = "Missing parameter [name], [timestamp], [id] or [authorization]";
                return badRequest(Json.toJson(response));
            } else {
                if (null != name) {
                    date.setName(name);
                }
                if (0 < timestamp) {
                    date.setDay(new DateTime(timestamp));
                }
                try {
                    date.save();
                    response.data = date;
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
    public static Result deleteGraduationDate(long id) {
        Response<GraduationDate> response = new Response<GraduationDate>();
            
            Credentials credentials = getUserFromContext();
            Teacher teacher = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
                                
            GraduationDate date = GraduationDate.find.where()
                                .eq("key", id)
                                .findUnique();
                                
            if (null == teacher || null == date) {
                response.message = "Missing parameter [id] or [authorization]";
                return badRequest(Json.toJson(response));
            } else {
                try {
                    date.delete();
                    response.data = date;
                    return ok(Json.toJson(response));
                } catch (Exception e) {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                }
            }
    }


}