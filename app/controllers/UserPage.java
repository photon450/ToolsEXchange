package controllers;

/**
 * Created by senoid on 11/7/2015.
 */


import models.Tool;
import models.User;
import models.UserAuth;
import play.mvc.*;

import play.mvc.Security.Authenticated;
import play.data.Form;
import views.html.index;


@Security.Authenticated(UserAuth.class)
public class UserPage extends Controller {
    public Result getUserPage() {return ok(views.html.UserPage.render()); }
/*
    @Security.Authenticated(UserAuth.class)
    public Result addTool() {
        //Add in some null checkers later
        Tool tool = Form.form(Tool.class).bindFromRequest().get();
        String usrIdStr = session().get("user_id");

        User user = User.find.where().eq("user_id", usrIdStr).findUnique();

        if(user == null)
        {
            flash("FATALERROR");
            return redirect(routes.Application.index());
        }
        else{
            tool.user = user.email;
            tool.save();
            user.addTool(tool);

        }
        return getUserPage();
    }
*/
}
