package models;

/**
 * Created by Whale on 11/13/2015.
 */

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

public class Admin extends Account{

    public static Finder<Long, Admin> find = new Finder<Long, Admin>(Admin.class);

    public static Admin createNewManager(String email, String password, String first_name, String last_name, String username, String code) {
        if(password == null || email == null || password.length() < 8 || username == null || code == null) {
            return null;
        }
        // if not null continue process
        // hash and salt the password

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        Admin Admin = new Admin();
        Admin.email = email;
        Admin.passwordHash = passwordHash;
        Admin.first_name = first_name;
        Admin.last_name = last_name;
        Admin.username = username;
        Admin.dateCreated = new java.util.Date();

        return Admin;


    }

    public boolean authenticate(String password)
    {
        return BCrypt.checkpw(password, this.passwordHash);
    }

}
