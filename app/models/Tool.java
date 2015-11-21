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


}
