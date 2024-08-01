/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaAsssignment_GUI;

/**
 *
 * @author ASUS
 */

public class Module {
    // --------------------------------------------------
    // Attribute
    // --------------------------------------------------
    private String moduleName = "";
    private String moduleCode = "";
    private int moduleCredit = 0;
    private double studentScore = 0;
    
    // --------------------------------------------------
    // Methods
    // --------------------------------------------------
    public Module(){
        
        this.moduleName = "";
        this.moduleCode = "";
        this.moduleCredit = 0;
        this.studentScore = 0;
    }
    
    public Module(String moduleName, String moduleCode, int moduleCredit, double studentScore){
        
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.moduleCredit = moduleCredit;
        this.studentScore = studentScore;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public int getModuleCredit() {
        return moduleCredit;
    }

    public void setModuleCredit(int moduleCredit) {
        this.moduleCredit = moduleCredit;
    }

    public double getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(double studentScore) {
        this.studentScore = studentScore;
    }
    
    
}