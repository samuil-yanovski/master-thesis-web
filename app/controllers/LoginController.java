package controllers;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

import models.Credentials;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class LoginController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
	public static Result login() {
	    JsonNode json = request().body().asJson();
        String email = json.findPath("email").textValue();
        String password = json.findPath("password").textValue();

		Page<Credentials> page = Credentials.find
				.where()
				.eq("email", email)
				.eq("password", password)
				.findPagingList(1)
				.getPage(1);
		List<Credentials> list = page.getList();
		if (0 < list.size()) {
			return ok(Json.toJson(list.get(0)));
		} else {
			return badRequest();
		}
	}

	public static Result check() {
		return ok(Json.toJson("It works"));
	}
}