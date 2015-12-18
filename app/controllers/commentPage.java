package controllers;

import controllers.navibar;
import models.Comment;
import models.Tool;
import models.User;
import models.UserAuth;
import play.data.Form;
import play.mvc.Security;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.mvc.*;

import java.util.List;

/**
 * Created by joseu on 11/28/15.
 */
public class commentPage extends Controller {

    public Result displayTool(Long id) {
        Tool the_tool = Tool.find.byId(id);
        return ok(toolPage.render(navibar.retrieveId(), the_tool));
    }


    @Security.Authenticated(UserAuth.class)
    public Result submitPost(Long tool_id) {
        //Add in some null checkers later
        Form<Comment> userForm     = Form.form(Comment.class).bindFromRequest();
        String content             = userForm.data().get("content");
        //Procure the info from the HTML form

        String usrIdStr = session().get("user_id");

        Long query = Long.valueOf(usrIdStr).longValue();

        User the_user = User.find.byId(query);

        Tool the_tool = Tool.find.where().eq("ToolId", tool_id).findUnique();
        //We will obtain the tool via the tool id number.

        Comment comment = Comment.createNewComment(the_user.username, content, the_tool);

        if(the_tool == null || comment == null || usrIdStr == null)
        {
            flash("FATALERROR");
            return redirect(routes.Application.index());
        }
        else{
            flash("success", "Added comment successfully!");
            comment.save();
            the_tool.addComment(comment);

        }

        List<Comment> comments = the_tool.comments;

        return ok(toolPageComments.render(comments, the_tool)); //Render the tool's comments page
    }

    //Uses the tool ID to get the comments FOR that ID.
    public Result getComments(Long id) {
        Tool thetool = Tool.find.byId(id);

        List<Comment> comments;
        comments = thetool.comments;

        return ok(toolPageComments.render(comments, thetool)); //Render the tool's comments page
    }
}


