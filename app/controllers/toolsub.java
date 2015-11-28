package controllers;

import models.UserAuth;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

/**
 * Created by joseu on 11/28/15.
 */
public class toolsub extends Controller{
    @Security.Authenticated(UserAuth.class)
    public Result displayToolSub(){
        return ok(toolSubPage.render(navibar.retrieveId()));
    }
}
