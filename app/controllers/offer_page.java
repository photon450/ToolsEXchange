package controllers;

import models.Tool;
import models.User;
import models.UserAuth;
import models.returnToolQuery;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import static play.data.Form.form;

import java.util.List;

/**
 * Created by joseu on 12/17/2015.
 */
public class offer_page extends Controller {

    @Security.Authenticated(UserAuth.class)
    public Result generate_offer_page(Long tool_id)
    {
        String usrIdStr = session().get("user_id");
        Long query = Long.valueOf(usrIdStr);

        Tool the_tool = Tool.find.byId(tool_id);

        if(usrIdStr == null)
        {
            flash("error", "User not logged in");
            return redirect(routes.home.getHome());
        }

        User the_user = User.find.byId(query);

        List<Tool> user_Tools = the_user.tools;

        return ok(views.html.offer_page.render(user_Tools, the_tool));
    }

    @Security.Authenticated(UserAuth.class)
    public Result complete_Borrow(Long tool_id)
    {

        Tool the_tool = Tool.find.byId(tool_id);

        if(!the_tool.borrowed)
        {
            flash("error", "tool is already borrowed!");
            redirect(routes.commentPage.displayTool(tool_id));
        }

        User original_owner = the_tool.user; //Obtain the original owner.

        String usrIdStr = session().get("user_id"); //Obtain the borrower's credentials.

        Long query = Long.valueOf(usrIdStr);
        User the_borrower = User.find.byId(query);

        if( original_owner.id.equals(the_borrower.id) )
        {
            flash("error", "You already own this tool!");
            redirect(routes.commentPage.displayTool(tool_id));
        }

        original_owner.tools.remove(the_tool); //The tool is removed from the owner's inventory
        the_tool.borrowed = true; //The tool is now borrowed

        //Getting the borrower

        the_tool.user =  the_borrower;

        the_borrower.tools.add(the_tool);//The tool is placed in the borrower's tool list.

        the_tool.save();

        original_owner.save();

        flash("success", "transaction complete!");

        return redirect(routes.UserPage.getUserPage());//And the user is returned to the user page.
    }

    @Security.Authenticated(UserAuth.class)
    public Result return_Tool()
    {
        Form<returnToolQuery> queryForm = form(returnToolQuery.class).bindFromRequest();

        String tool_idstr = queryForm.data().get("ToolId");

        Long tool_id = Long.valueOf(tool_idstr);

        Tool borrowed_Tool = Tool.find.byId(tool_id);

        User borrower = borrowed_Tool.user; //Define the borrower

        borrower.tools.remove(borrowed_Tool); //remove the tool from the borrower's list

        borrowed_Tool.borrowed = false; //tool is no longer borrowed.

        User original_owner = borrowed_Tool.original_owner;


        borrowed_Tool.user = borrowed_Tool.original_owner; //Ownership is returned to the original owner.

        original_owner.addTool(borrowed_Tool);

        borrowed_Tool.save();
        //Save the tool and the update user
        original_owner.save();

        return redirect(routes.UserPage.getUserPage());//Return the user to their account page.


    }
}
