package com.osi.loganalyzer.util;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class Failure
{
    private String content;

    private String message;

    private String type;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", message = "+message+", type = "+type+"]";
    }
}
			
			