package controllers;

import models.Tool;
import models.query;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import java.util.List;
import play.data.Form;
import static play.data.Form.form;

/**
 * Created by admin on 12/16/2015.
 */

public class searchPage extends Controller {

    public Result display_search_page()
    {
        return ok(views.html.searchPage.render(null));

    }

    public Result find_by_query()
    {
        //Ideally should return a list of tools????
        Form<query> queryForm = form(query.class).bindFromRequest();
        String the_query = queryForm.data().get("Tool_Type");
        List<Tool> tool_query = Tool.find.where().eq("tool_type", the_query).findList();
        if(tool_query == null || the_query == null)
        {
            //In case this fails to retrieve anything...
            return redirect(routes.Application.index());
        }

        flash(the_query);
        return ok(views.html.searchPage.render(tool_query));
    }
}
