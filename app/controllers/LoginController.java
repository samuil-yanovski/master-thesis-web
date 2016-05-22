package controllers;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

import models.Credentials;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import models.*;
import java.util.UUID;
import play.libs.Json;
import http.Response;
import javax.persistence.PersistenceException;
import org.joda.time.DateTime;

public class LoginController extends Controller {

	private static Contacts createContacts(String email) {
	    Contacts contacts = new Contacts();
	    contacts.setEmail(email);
	    try {
            contacts.save();
            return contacts;
        } catch (PersistenceException e) {
            return null;
        }
	}
	
	private static Credentials createCredentials(String email, String password) {
	    Credentials credentials = new Credentials();
	    credentials.setEmail(email);
	    credentials.setPassword(password);
	    try {
            credentials.save();
            return credentials;
        } catch (PersistenceException e) {
            return null;
        }
	}
	
	private static Token createToken(Credentials credentials) {
	    Token token = new Token();
	    token.setAuth(UUID.randomUUID().toString());
	    token.setRefresh(UUID.randomUUID().toString());
	    token.setExpirationDate(DateTime.now().plusMonths(1));
	    token.setOwner(credentials);
	    try {
            token.save();
            return token;
        } catch (PersistenceException e) {
            return null;
        }
	}
	
	@BodyParser.Of(BodyParser.Json.class)
    public static Result register() {
        Response<Token> response = new Response<Token>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String email = json.findPath("email").textValue();
            String password = json.findPath("password").textValue();
            String name = json.findPath("name").textValue();
            String image = json.findPath("image").textValue();
            String type = json.findPath("type").textValue();
            
            if (null == email || null == password) {
                response.message = "Missing parameter [email] or [password]";
                return badRequest(Json.toJson(response));
            } else {
                Credentials credentials = createCredentials(email, password);
                if (null == credentials) {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                }
                Token token = createToken(credentials);
                if (null == token) {
                    response.message = "Invalid or duplicate user data";
                    return badRequest(Json.toJson(response));
                }
                response.data = token;
                Contacts contacts = createContacts(email);
                
                AccountTypes accountType = AccountTypes.valueOf(type);
                
                switch(accountType) {
                    case TEACHER: {
                        Teacher teacher = new Teacher();
                        teacher.setAvatar(image);
                        teacher.setContacts(contacts);
                        teacher.setName(name);
                        teacher.setCredentials(credentials);
                        try {
                            teacher.save();
                            credentials.setTeacher(teacher);
                            return ok(Json.toJson(response));
                        } catch (PersistenceException e) {
                            response.message = "Invalid or duplicate user data";
                            return badRequest(Json.toJson(response));
                        }
                    }
                    case STUDENT: {
                        Student student = new Student();
                        student.setAvatar(image);
                        student.setName(name);
                        student.setContacts(contacts);
                        student.setCredentials(credentials);
                        try {
                            student.save();
                            credentials.setStudent(student);
                            return ok(Json.toJson(response));
                        } catch (PersistenceException e) {
                            response.message = "Invalid or duplicate user data";
                            return badRequest(Json.toJson(response));
                        }
                    }
                }
                
                return ok(Json.toJson(response));
            }
        }
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public static Result login() {
        Response<Token> response = new Response<Token>();
        JsonNode json = request().body().asJson();
        if (null == json) {
            response.message = "Missing data";
            return badRequest(Json.toJson(response));
        } else {
            String email = json.findPath("email").textValue();
            String password = json.findPath("password").textValue();
            
            if (null == email || null == password) {
                response.message = "Missing parameter [email] or [password]";
                return badRequest(Json.toJson(response));
            } else {
                Credentials credentials = Credentials.find.where()
                                .eq("email", email)
                                .eq("password", password)
                                .findUnique();
                Student student = Student.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
                if (null != student) {
                    credentials.setStudent(student);
                } else {
                    Teacher teacher = Teacher.find.where()
                                .eq("credentials", credentials)
                                .findUnique();
                    credentials.setTeacher(teacher);
                }
                
                if (null == credentials) {
                    response.message = "Invalid [email] or [password]";
                    return forbidden(Json.toJson(response));
                } else {
                    Token token = createToken(credentials);
                    
                    if (null == token) {
                        response.message = "Invalid user data";
                        return badRequest(Json.toJson(response));
                    }
                    response.data = token;
                    return ok(Json.toJson(response));
                }
            }
        }
    }

	public static Result check() {
		return ok(Json.toJson("It works"));
	}
}