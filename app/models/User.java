package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.mvc.*;

/**
 * Created by admin on 11/7/2015.
 */
    @Table(name="users")
    @Entity
    public class User extends Account {


     @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
      public List<Tool> tools = new ArrayList<Tool>();

      public static Finder<Long, User> find = new Finder<Long, User>(User.class);
      //Implement find by functions later


  // So check the username and password entered and see if neither is null and password is greater than 8
    public static User createNewUser(String email, String password) {
        if(password == null || email == null || password.length() < 8) {
            return null;
        }
        // if not null continue process
        // hash and salt the password

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        // proceed to create the user, dont worry inherits from Account.
        User user = new User();
        user.email = email;
        user.passwordHash = passwordHash;

        return user;
    }

    public boolean authenticate(String password)
    {
        return BCrypt.checkpw(password, this.passwordHash);
    }
    Uncomment this
  /*  public void addTool(Tool tool)
    {
        this.tools.add(tool);
        this.save();
    }  */
}


