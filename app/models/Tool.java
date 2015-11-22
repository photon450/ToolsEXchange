package models;


import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Whale on 11/13/2015.
 */
@Entity
public class Tool extends Model {

     @Id
     public long ToolId;

     public String Tool_Name;

     @Lob
     public String  Tool_Description;

     public String Condition;

       @ManyToOne //(optional=false)
       public User user;

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
