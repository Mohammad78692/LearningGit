package com.osi.loganalyzer.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Testsuite
{
    private String failures;

    private String time;

    private String errors;

    private String skipped;

    private String tests;

    private String name;

   // private Properties properties;

    private Testcase[] testcase;

    public String getFailures ()
    {
        return failures;
    }

    public void setFailures (String failures)
    {
        this.failures = failures;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getErrors ()
    {
        return errors;
    }

    public void setErrors (String errors)
    {
        this.errors = errors;
    }

    public String getSkipped ()
    {
        return skipped;
    }

    public void setSkipped (String skipped)
    {
        this.skipped = skipped;
    }

    public String getTests ()
    {
        return tests;
    }

    public void setTests (String tests)
    {
        this.tests = tests;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    /*public Properties getProperties ()
    {
        return properties;
    }

    public void setProperties (Properties properties)
    {
        this.properties = properties;
    }*/

    public Testcase[] getTestcase ()
    {
        return testcase;
    }

    public void setTestcase (Testcase[] testcase)
    {
        this.testcase = testcase;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [failures = "+failures+", time = "+time+", errors = "+errors+", skipped = "+skipped+", tests = "+tests+", name = "+name+", testcase = "+testcase+"]";
    }
}
			
		