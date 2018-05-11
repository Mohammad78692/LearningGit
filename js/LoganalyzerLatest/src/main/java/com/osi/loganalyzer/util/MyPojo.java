package com.osi.loganalyzer.util;

import java.io.Serializable;

public class MyPojo
{
    private Testsuite testsuite;

    public Testsuite getTestsuite ()
    {
        return testsuite;
    }

    public void setTestsuite (Testsuite testsuite)
    {
        this.testsuite = testsuite;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [testsuite = "+testsuite+"]";
    }
}