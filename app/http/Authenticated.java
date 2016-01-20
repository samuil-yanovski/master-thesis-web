/*
 * Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com>
 */
package http;

import play.libs.F;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.lang.annotation.*;
import java.util.Date;
import models.Token;
import org.joda.time.DateTime;

/**
 * Defines several security helpers.
 */
public class Authenticated extends Action.Simple {
    
    private static final String AUTHORIZATION = "authorization";

    public F.Promise<Result> call(Http.Context context) throws Throwable {

        String authHeader = context.request().getHeader(AUTHORIZATION);
        if (authHeader == null) {
            return F.Promise.pure(unauthorized());
        }

        Token token = Token.find.where().eq("auth", authHeader).findUnique();
        DateTime now = DateTime.now();

        if (null != token && now.isBefore(token.getExpirationDate())) {
            context.args.put("user", token.getOwner());
            return delegate.call(context);
        } else {
            Response<String> response = new Response();
            response.message = "Invalid or missing token";
            return F.Promise.pure(forbidden(Json.toJson(response)));
        }
    }
    
}