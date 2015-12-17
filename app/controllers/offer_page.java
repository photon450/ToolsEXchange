package controllers;

import models.Tool;
import models.User;
import models.UserAuth;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

/**
 * Created by admin on 12/17/2015.
 */
public class offer_page extends Controller {

    @Security.Authenticated(UserAuth.class)
    public Result generate_offer_page()
    {
        String usrIdStr = session().get("user_id");
        Long query = Long.valueOf(usrIdStr);

        if(usrIdStr == null)
        {
            flash("error", "User not logged in");
            return redirect(routes.home.getHome());
        }

        User the_user = User.find.byId(query);

        List<Tool> user_Tools = the_user.tools;

        return ok(views.html.offer_page.render(user_Tools));
    }


}
