package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import play.data.format.Formats;
import play.data.validation.Constraints;
/**
 * Created by admin on 11/7/2015.
 */
    @Table(name="users")
    @Entity
    public class User extends Account {


      //  @OneToMany
    //    public List<Tool> tools;

        public static Finder<Long, User> find = new Finder<Long, User>(User.class);
        //Implement find by functions later



    }


