package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import play.data.Form;
import play.db.ebean.Model;
import static play.libs.Json.toJson;
import java.util.List;
import controllers.*;

public class Application extends Controller {

    public Result index() {
        return ok(Home.render(navbar.retrieveId()));
    }

}
