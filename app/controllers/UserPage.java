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

    // Regular Userpage, key from session
    public Result getUserPage() {

        String the_id = session().get("user_id"), user_name;
        Long query = Long.valueOf(the_id);
        List<Tool> user_tool;

            query = Long.valueOf(the_id).longValue();
            User the_user = User.find.byId(query);
            user_name = the_user.username;
            if (!the_user.tools.isEmpty()) {
                user_tool = the_user.tools;
            } else {
                user_tool = null;
            }

        return ok(views.html.UserPage.render( user_name, user_tool));
    }
    /* From special event
    public Result thisUserPage( String query ) {



    }
   */

    @Security.Authenticated(UserAuth.class)
    public Result addTool() {
        //Add in some null checkers later
        Form<Tool> userForm     = form(Tool.class).bindFromRequest();
        String Tool_Name        = userForm.data().get("Tool_Name");
        String Tool_Description = userForm.data().get("Tool_Description");
        String Condition        = userForm.data().get("Condition");
        String Tool_Type        = userForm.data().get("Tool_Type");

        Tool tool = Tool.createNewTool(Tool_Name, Tool_Description, Condition, Tool_Type);

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
            tool.original_owner = user;
            tool.save();
            user.addTool(tool);

        }
        return getUserPage();
    }

    public static List<Tool> return_borrowed_Tools()
    {
        List<Tool> borrowed_tools = Tool.find.where().eq("borrowed", true).findList();//Return a list of borrowed tools

        if(borrowed_tools == null)
        {
            return null;
        }
        else{
            return borrowed_tools;
        }
    }


    public Result render_return_borrow()
    {
        return ok(views.html.return_borrow.render(return_borrowed_Tools()));
    }

}
