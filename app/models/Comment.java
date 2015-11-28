package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by joseu on 11/28/15.
 */
@Entity
public class Comment extends Model {
    public String author;
    public Date postedAt;

    public String content;

    @ManyToOne
    public Tool tool;

    public Comment(String author, String content)
    {
        this.author = author;

    }
}
