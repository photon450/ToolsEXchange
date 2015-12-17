package controllers;

import models.Tool;
import play.mvc.Controller;

import java.util.List;

/**
 * Created by admin on 12/17/2015.
 */
public class homeTools extends Controller {
    //Should return a small number of things???
    public static List<Tool> obtainSmall_ToolList(){

        List<Tool> some_tools = Tool.find.findPagedList(0, 10).getList();

        return some_tools;
    }

}
