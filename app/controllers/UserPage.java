package controllers;

/**
 * Created by senoid on 11/7/2015.
 */


import play.*;
import play.mvc.*;

import views.html.*;

import play.data.Form;
import play.db.ebean.Model;
import static play.libs.Json.toJson;
import java.util.List;

public class UserPage extends Controller {
    public Result getUserPage() {return ok(views.html.UserPage.render()); }
}
