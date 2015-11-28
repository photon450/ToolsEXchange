package controllers;

import controllers.navibar;
import models.Tool;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created by joseu on 11/28/15.
 */
public class toolP extends Controller {

    public Result displayTool(Long id) {
        Tool the_tool = Tool.find.byId(id);
        return ok(toolPage.render(navibar.retrieveId(), the_tool));
    }
}
