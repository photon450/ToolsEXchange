package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import play.data.format.Formats;
import play.data.validation.Constraints;

//import models.auth.Hash;
/**
 * Created by Whale on 11/13/2015.
 */


@MappedSuperclass
public class Account  extends Model {
    @Id
    public Long id;

    public String username;

    public String password;

    public String first_name;

    public String last_name;

    @Constraints.Required
    public String email;

    @Constraints.Required
    public String passwordHash;

    @Formats.DateTime(pattern ="yyyy-MM-dd HH:MM:SS")
    public Date dateCreated;



}
