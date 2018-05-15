package com.osi.engine.model;

public class Steps
{
    private String keyword;

    private String name;

    private String line;

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

    @Override
    public String toString()
    {
        return "ClassPojo [keyword = "+keyword+", name = "+name+", line = "+line+"]";
    }
}