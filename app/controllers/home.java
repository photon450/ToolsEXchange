package controllers;

import play.mvc.*;
import views.html.*;

/**
 * Created by admin on 11/7/2015.
 */
public class home extends Controller {
    //Will send back a render flag on whether or not to display a logged in user's navibar

    public Result getHome() {
        return ok(Home.render(navibar.retrieveId()));
    }
}
