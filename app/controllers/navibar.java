package controllers;

import play.mvc.Controller;

/**
 * Created by senoid on 11/21/2015.
 */
public class navibar extends Controller {
    public static String retrieveId() {
        return session().get("user_id");
    }
}