package controllers;

import models.User;
import play.mvc.Controller;

/**
 * Created by senoid on 11/21/2015.
 */
public class navibar extends Controller {




    public static String retrieveId() {
        String the_id = session().get("user_id"), user_name;
        //Check if not null later
        Long query = null;
        if(the_id != null){
            query = Long.valueOf(the_id).longValue();
            User the_user = User.find.byId(query);
            user_name = the_user.username;

        }
        else {
            user_name = null;

        }
        return user_name;
    }
}