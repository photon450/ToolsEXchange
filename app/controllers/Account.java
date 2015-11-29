package controllers;

/**
 * Created by Whale on 11/15/2015.
 */

import models.User;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.*;

import play.data.Form;

import static play.data.Form.form;

public class Account extends Controller {

    public Result getLoginPage() {
        return ok(Login.render(navibar.retrieveId()));
    }
    public Result getRegPage() { return ok(Registration.render(navibar.retrieveId()));}


// we define adduser here.
    public Result addUser() {
        Form<User> userForm = form(User.class).bindFromRequest();      //creates a DynamicForm userform we bind from the request
        String email = userForm.data().get("username");
        String password = userForm.data().get("password");
        String first_name = userForm.data().get("first_name");
        String last_name = userForm.data().get("last_name");
        String username = userForm.data().get("username");

        //returns a user
        User user = User.createNewUser(email, password, first_name, last_name, username);



        if(user == null){
            flash("error", "invalid user");
            return redirect(routes.Account.getRegPage());   // so if didnt put an email warn em witha flash message and redirect back to login page
        }
        user.save();

        flash("success", "Welcome new user " + user.email);
        session("user_id", user.id.toString());
        return redirect(routes.UserPage.getUserPage()); // leaves at their Main user page
    }

    public Result logout() {
        session().remove("user_id");
        return ok(Home.render(navibar.retrieveId()));
    }

    public Result login() {
        DynamicForm userForm = form().bindFromRequest();
        String email = userForm.data().get("Tool");
        String password = userForm.data().get("password");
        User user = User.find.where().eq("email", email).findUnique();

        if(user != null && user.authenticate(password)) {
            session("user_id", user.id.toString());
            flash("success", "Welcome back " + user.username);
        } else {
            flash("error", "Invalid login. Check your username and password.");
        }

        return redirect(routes.Application.index());

    }


}