package controllers;

/**
 * Created by senoid on 11/7/2015.
 */


import models.UserAuth;
import play.mvc.*;

import play.mvc.Security.Authenticated;

@Security.Authenticated(UserAuth.class)
public class UserPage extends Controller {
    public Result getUserPage() {return ok(views.html.UserPage.render()); }
}
