/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaAsssignment_GUI;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Student extends Person {

    // ------------------------------------------------------
    // Attributes
    // ------------------------------------------------------
    private String classCode = "";
    private ArrayList<Module> module;
    private double gpa = 0;

    // ------------------------------------------------------
    // Methods
    // ------------------------------------------------------
    public Student() {
        this.classCode = "";
        this.module = new ArrayList<>();

        this.gpa = 0;
    }

    public Student(String name, String adminNo, String classCode, ArrayList<Module> moduleTaken) {
        super(name, adminNo);
        this.classCode = classCode;
        this.module = moduleTaken;

        this.gpa = 0;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public ArrayList<Module> getModule() {
        return module;
    }

    public void setModule(ArrayList<Module> module) {
        this.module = module;
    }

    public Module getModArrayValue(int index) {
        return this.module.get(index);
    }

    public void addModule(Module module) {
        this.module.add(module);
    }

    public double getGPA() {
        return this.gpa;
    }

    public void calcGPA() {
        int moduleCount = 0;
        double[][] moduleScore = new double[getModule().size()][2];
        for (Module module : getModule()) {
            double grade = 0;
            if (module.getStudentScore() >= 80) {
                grade = 4;
            } else if (module.getStudentScore() >= 70) {
                grade = 3;
            } else if (module.getStudentScore() >= 60) {
                grade = 2;
            } else if (module.getStudentScore() >= 50) {
                grade = 1;
            } else {
                grade = 0;
            }
            moduleScore[moduleCount][0] = grade * module.getModuleCredit();
            moduleScore[moduleCount][1] = module.getModuleCredit();
            moduleCount++;
        }
        double totalScore = 0;
        double totalCredit = 0;
        for (double[] mod : moduleScore) {
            totalScore += mod[0];
            totalCredit += mod[1];
        }
        this.gpa = totalScore / totalCredit;

    }
   
    @Override
    public void getPersonDetails(){
        String classList = "";
        String adminNumb ="";
        String moduleList="";
        StringBuilder moduleListBuilder = new StringBuilder();
        for(Module mods : this.module){
            if(!this.module.isEmpty()){
                moduleListBuilder.append("Module Name: ").append(mods.getModuleName()).append("\n")
                .append("Module Code: ").append(mods.getModuleCode()).append("\n")              
                .append("Module Credit: ").append(mods.getModuleCredit()).append("\n")
                .append("Score: ").append(mods.getStudentScore()).append("\n");
                
            }else{
                moduleListBuilder.append("No modules");
            }
        }
          
        moduleList = moduleListBuilder.toString();
        JOptionPane.showMessageDialog(null, String.format("Name: %s\nAdmin Number: %s\nClass: %s\n\tModule Information\n\n%s",super.getName(),super.getAdminNo(),getClassCode(),moduleList),"General Statistics",JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
//    @Override
//    public String toString() {
//        for (Module modul : module) {
//            System.out.println("- " + modul.getModuleName() + " (" + modul.getModuleCode() + ") (" + modul.getModuleCredit() + ") (" + modul.getStudentScore() + ")");
//        }
//        return "Student{"
//                + "name='" + getName() + '\''
//                + ", id=" + getAdminNo()
//                + ", class=" + classCode
//                + '}';
//    }
}
