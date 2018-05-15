package com.osi.engine.model;

import java.io.Serializable;

public class MyPojo implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String description;

    private String keyword;

    private String name;

    private String line;

    private String uri;

    private Elements[] elements;

    private Comments[] comments;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getKeyword ()
    {
        return keyword;
    }

    public void setKeyword (String keyword)
    {
        this.keyword = keyword;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLine ()
    {
        return line;
    }

    public void setLine (String line)
    {
        this.line = line;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    public Elements[] getElements ()
    {
        return elements;
    }

    public void setElements (Elements[] elements)
    {
        this.elements = elements;
    }

    public Comments[] getComments ()
    {
        return comments;
    }

    public void setComments (Comments[] comments)
    {
        this.comments = comments;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", description = "+description+", keyword = "+keyword+", name = "+name+", line = "+line+", uri = "+uri+", elements = "+elements+", comments = "+comments+"]";
    }
}