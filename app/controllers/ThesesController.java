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

public class ThesesController extends Controller {
    
    private static Credentials getUserFromContext() {
        return (Credentials) Http.Context.current().args.get("user");
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result getTheses() {
        Response<List<Thesis>> response = new Response<List<Thesis>>();
        
        List<Thesis> theses = Thesis.find.all();
        response.data = theses;
        return ok(Json.toJson(response));
    }

    @With(Authenticated.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result createProposal() {
        Response<Thesis> response = new Response<Thesis>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String title = json.findPath("title").textValue();
            String description = json.findPath("description").textValue();
            long authorId = json.findPath("author").longValue();
            long categoryId = json.findPath("category").longValue();
            
            Teacher author = Teacher.find.where()
                                .eq("key", authorId)
                                .findUnique();
                                
            Category category = Category.find.where()
                                .eq("key", categoryId)
                                .findUnique();
                                
            Credentials credentials = getUserFromContext();
            Student student = Student.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
            
            if (null == title || null == author || null == category || null == student) {
                response.message = "Missing parameter [title], [author] or [category]";
                return badRequest(Json.toJson(response));
            } else {
                Thesis thesis = new Thesis();
                thesis.setTitle(title);
                thesis.setDescription(description);
                thesis.setAuthor(author);
                thesis.setCategory(category);
                thesis.setGraduate(student);
                thesis.setApproved(false);
                try {
                    thesis.save();
                    response.data = thesis;
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
    public static Result addThesis() {
        Response<Thesis> response = new Response<Thesis>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String title = json.findPath("title").textValue();
            String description = json.findPath("description").textValue();
            long categoryId = json.findPath("category").longValue();
                                
            Category category = Category.find.where()
                                .eq("key", categoryId)
                                .findUnique();
                                
            Credentials credentials = getUserFromContext();
            Teacher author = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
            
            if (null == title || null == author || null == category) {
                response.message = "Missing parameter [title] or [category]" + title + " " + author + " " + categoryId;
                return badRequest(Json.toJson(response));
            } else {
                Thesis thesis = new Thesis();
                thesis.setTitle(title);
                thesis.setDescription(description);
                thesis.setAuthor(author);
                thesis.setCategory(category);
                try {
                    thesis.save();
                    response.data = thesis;
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
    public static Result approve(Long thesisId) {
        Response<Thesis> response = new Response<Thesis>();
        Thesis thesis = Thesis.find.where()
                                .eq("key", thesisId)
                                .findUnique();
                                
            Credentials credentials = getUserFromContext();
            Teacher author = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
        try {
            if (null != author && author.getKey().equals(thesis.getAuthor().getKey())) {
                thesis.setApproved(true);
                thesis.save();
                return ok(Json.toJson(response));
            } else {
                response.message = "Invalid or duplicate user data";
                return badRequest(Json.toJson(response));
            }   
        } catch (Exception e) {
            response.message = "Invalid or duplicate user data";
            return badRequest(Json.toJson(response));
        }
            
    }

    @With(Authenticated.class)
    @BodyParser.Of(BodyParser.Json.class)
    public static Result decline(Long thesisId) {
        Response<Thesis> response = new Response<Thesis>();
        Thesis thesis = Thesis.find.where()
                                .eq("key", thesisId)
                                .findUnique();
                                
            Credentials credentials = getUserFromContext();
            Teacher author = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
        try {
            if (null != author && author.getKey().equals(thesis.getAuthor().getKey())) {
                thesis.delete();
                return ok(Json.toJson(response));
            } else {
                response.message = "Invalid or duplicate user data";
                return badRequest(Json.toJson(response));
            }   
        } catch (Exception e) {
            response.message = "Invalid or duplicate user data";
            return badRequest(Json.toJson(response));
        }
            
    }

}