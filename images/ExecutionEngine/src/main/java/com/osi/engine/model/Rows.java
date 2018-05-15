package com.osi.engine.model;

public class Rows
{
    private String[] cells;

    private String line;

    public String[] getCells ()
    {
        return cells;
    }

    public void setCells (String[] cells)
    {
        this.cells = cells;
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
        return "ClassPojo [cells = "+cells+", line = "+line+"]";
    }
}
			