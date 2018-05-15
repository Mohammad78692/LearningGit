package com.osi.engine.model;

public class Comments
{
    private String value;

    private String line;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
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
        return "ClassPojo [value = "+value+", line = "+line+"]";
    }
}
	