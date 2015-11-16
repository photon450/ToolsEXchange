package controllers;

/**
 * Created by senoid on 11/7/2015.
 */


import models.SecuredAction;
import play.mvc.*;

import play.mvc.Security.Authenticated;

@With(SecuredAction.class)
public class UserPage extends Controller {
    public Result getUserPage() {return ok(views.html.UserPage.render()); }
}
