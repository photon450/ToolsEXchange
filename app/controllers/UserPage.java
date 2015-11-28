package controllers;

/**
 * Created by senoid on 11/7/2015.
 */


import models.Tool;
import models.User;
import models.UserAuth;
import play.data.DynamicForm;
import play.mvc.*;

import play.mvc.Security.Authenticated;
import play.data.Form;
import views.html.index;

import java.util.List;

import static play.data.Form.form;


@Security.Authenticated(UserAuth.class)
public class UserPage extends Controller {
    public Result getUserPage() {
        List<Tool> = f
        return ok(views.html.UserPage.render(navibar.retrieveId()));
    }

    @Security.Authenticated(UserAuth.class)
    public Result addTool() {
        //Add in some null checkers later
        Form<Tool> userForm     = form(Tool.class).bindFromRequest();
        String Tool_Name        = userForm.data().get("Tool_Name");
        String Tool_Description = userForm.data().get("Tool_Description");
        String Condition        = userForm.data().get("Condition");

        Tool tool = Tool.createNewTool(Tool_Name, Tool_Description, Condition);

        String usrIdStr = session().get("user_id");
        Long query = Long.valueOf(usrIdStr).longValue();

        User user = User.find.where().eq("id", query).findUnique();

        if(tool == null)
        {
            flash("FATALERROR");
            return redirect(routes.Application.index());
        }
        else{
            tool.user = user;
            tool.save();
            user.addTool(tool);

        }
        return getUserPage();
    }
}
