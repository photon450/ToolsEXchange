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

     public String Tool_Type;

     @ManyToOne //(optional=false)
     public User user; //the real owner

     public boolean borrowed;

     public User original_owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tool")
    public List<Comment> comments;

    public static Finder<Long, Tool> find = new Finder<Long, Tool>(Tool.class);

    public static Tool createNewTool(String Tool_Name, String Tool_Description, String Condition, String Tool_Type)
     {
         if(Tool_Name == null || Tool_Description == null || Condition == null){
             return null;
         }
         else {
             Tool newTool = new Tool();

             newTool.Tool_Name = Tool_Name;
             newTool.Tool_Description = Tool_Description;
             newTool.Condition = Condition;
             newTool.Tool_Type = Tool_Type;
             newTool.borrowed = false;

             return newTool;
         }
     }

    public void addComment(Comment newComment)
    {
        this.comments.add(newComment);
        this.save();
    }
}
