package models;


import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Whale on 11/13/2015.
 */
@Entity
public class Tool extends Model {

     @Id
     public long ToolId;

     public String Tool_Name;

     public String Tool_Description;

     public String Condition;

     @ManyToOne //(optional=false)
     public User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tool")
    List<Comment> comments = new ArrayList<Comment>();

    public static Finder<Long, Tool> find = new Finder<Long, Tool>(Tool.class);

    public static Tool createNewTool(String Tool_Name, String Tool_Description, String Condition)
     {
         if(Tool_Name == null || Tool_Description == null || Condition == null){
             return null;
         }
         else {
             Tool newTool = new Tool();

             newTool.Tool_Name = Tool_Name;
             newTool.Tool_Description = Tool_Description;
             newTool.Condition = Condition;

             return newTool;
         }
     }




}
