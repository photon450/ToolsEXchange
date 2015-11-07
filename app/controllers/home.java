package controllers;

import play.mvc.*;
import views.html.*;

/**
 * Created by admin on 11/7/2015.
 */
public class home extends Controller {

    public Result getHome() {
        return ok(Home.render());
    }
}
