package controllers;

/**
 * Created by Whale on 11/15/2015.
 */

import models.User;
import play.*;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.*;

import play.data.Form;
import play.db.ebean.Model;
import static play.libs.Json.toJson;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import static play.data.Form.form;
import play.api.data.Forms;

public class Account extends Controller {

    public Result getLoginPage() {
        return ok(Login.render());
    }
    public Result getRegPage() { return ok(Registration.render());}


// we define adduser here.
    public Result addUser() {
        Form<User> userForm = form(User.class).bindFromRequest();      //creates a DynamicForm userform we bind from the request
        String email = userForm.data().get("username");
        String password = userForm.data().get("password");

        //returns a user
        User user = User.createNewUser(email, password);



        if(user == null){
            flash("error", "invalid user");
            return redirect(routes.Account.getRegPage());   // so if didnt put an email warn em witha flash message and redirect back to login page
        }
        user.save();

        flash("success", "Welcome new user " + user.email);
        session("user_id", user.id.toString());
        return redirect(routes.UserPage.getUserPage()); // leaves at their Main user page
    }


}