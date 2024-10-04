package com.amextra.io.Request;

import java.io.Serializable;

public class Political implements Serializable {

    private boolean isPolitical;

    private String jobDescription = "";

    public boolean isPolitical() {
        return isPolitical;
    }

    public void setPolitical(boolean political) {
        isPolitical = political;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    @Override
    public String toString() {
        return "Political{" +
                "isPolitical=" + isPolitical +
                ", jobDescription='" + jobDescription + '\'' +
                '}';
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
