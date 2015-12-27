package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;
import com.fasterxml.jackson.databind.JsonNode;

public class LoginController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
	public static Result login() {
	    JsonNode json = request().body().asJson();
        String email = json.findPath("email").textValue();
        String password = json.findPath("password").textValue();

		return ok(views.html.index.render());
	}

}