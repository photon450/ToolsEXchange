package models;


import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by Whale on 11/13/2015.
 */
public class Tool {

    @Id
     public long ToolId;

     public String Tool_Name;
    @Lob
     public String  Tool_Description;

    public String Conditions;

    








}
