package controllers;

import play.mvc.*;

import views.html.*;



public class Application extends Controller {

    public Result index() {
        return ok(Home.render());
    }
    public Result getAbout() {return ok(About.render());}

}
