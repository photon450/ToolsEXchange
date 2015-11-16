package models;

import models.User;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.*;
import play.mvc.Result;
import play.mvc.Results;

/**
 * Created by Whale on 11/16/2015.
 */
public class SecuredAction extends Action.Simple {
    public F.Promise<Result> call(Http.Context ctx) throws Throwable {
        String token = getTokenFromHeader(ctx);
        if (token != null) {
            User user = User.find.where().eq("authToken", token).findUnique();
            if (user != null) {
                ctx.request().setUsername(user.email);
                return delegate.call(ctx);
            }
        }
        Result unauthorized = Results.unauthorized("unauthorized");
        return F.Promise.pure(unauthorized);
    }

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }
}

