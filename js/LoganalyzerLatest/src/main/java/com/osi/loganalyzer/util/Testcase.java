package com.osi.loganalyzer.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Testcase
{
    private String systemout;

    private String time;

    private Error error;

    private String name;

    private String classname;
    private Failure failure;
    
    public void setFailure(Failure failure) {
		this.failure = failure;
	}
    public Failure getFailure() {
		return failure;
	}
    public String getSystemout ()
    {
        return systemout;
    }

    public void setSystemout (String systemout)
    {
        this.systemout = systemout;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public Error getError ()
    {
        return error;
    }

    public void setError (Error error)
    {
        this.error = error;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getClassname ()
    {
        return classname;
    }

    public void setClassname (String classname)
    {
        this.classname = classname;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [system-out = "+systemout+", time = "+time+", error = "+error+", name = "+name+", classname = "+classname+"]";
    }
}
			
		