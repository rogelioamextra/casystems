/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.viu.modelos.dtos.request;

/**
 *
 * @author rogel
 */
public class Political {
    
    private boolean isPolitical;
    
    private String  jobDescription;

    public boolean isIsPolitical() {
        return isPolitical;
    }

    public void setIsPolitical(boolean isPolitical) {
        this.isPolitical = isPolitical;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Override
    public String toString() {
        return "Political{" + "isPolitical=" + isPolitical + ", jobDescription=" + jobDescription + '}';
    }

    
    
    
}
