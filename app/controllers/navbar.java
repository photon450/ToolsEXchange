package controllers;

import play.mvc.Controller;

/**
 * Created by admin on 11/21/2015.
 */
public class navbar extends Controller {
    public static String retrieveId() {
        return session().get("user_id");
    }

}
