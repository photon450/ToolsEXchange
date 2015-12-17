package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by joseu on 11/28/15.
 */
@Entity
public class Comment extends Model {
    @Id
    public Long id;

    public String author;
    public Date postedAt;

    public String content;

    public static Finder<Long, Comment> find = new Finder<Long, Comment>(Comment.class);

    @ManyToOne
    public Tool tool;


    public static Comment createNewComment(String author, String content, Tool the_tool)
    {
        if(author == null)
        {
            return null;
        }
        else {

            Comment newComment = new Comment();
            newComment.author = author;
            newComment.content = content;
            newComment.tool = the_tool;

            return newComment;

        }
    }
}
