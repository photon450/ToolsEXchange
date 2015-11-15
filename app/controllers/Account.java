package controllers;

/**
 * Created by Whale on 11/15/2015.
 */

import play.*;
import play.mvc.*;

import views.html.*;

import play.data.Form;
import play.db.ebean.Model;
import static play.libs.Json.toJson;
import java.util.List;

public class Account extends Controller{

    public Result getLoginPage(){
        return ok(Login.render());
    }
}
