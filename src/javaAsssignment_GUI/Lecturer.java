/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaAsssignment_GUI;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Lecturer extends Person {

    // ----------------------------------------------------
    // Attributes
    // ----------------------------------------------------
    private ArrayList<String> modulesInCharge = new ArrayList<>();
    private ArrayList<String> classInCharge = new ArrayList<>();
    private String contact = "";

    // ----------------------------------------------------
    // Methods
    // ----------------------------------------------------
    public Lecturer() {
        super();
        this.modulesInCharge = new ArrayList<>();
        this.classInCharge = new ArrayList<>();
        this.contact = "";
    }

    public Lecturer(String name, String adminNo, ArrayList<String> modulesInCharge, ArrayList<String> classInCharge, String contact) {
        super(name,adminNo);
        this.modulesInCharge = modulesInCharge;
        this.classInCharge = classInCharge;
        this.contact = contact;
    }

    public ArrayList<String> getClassInCharge() {
        return classInCharge;
    }

    public void setClassInCharge(ArrayList<String> classInCharge) {
        this.classInCharge = classInCharge;
    }

    public ArrayList<String> getModulesInCharge() {
        return modulesInCharge;
    }

    public void setModulesInCharge(ArrayList<String> modulesInCharge) {
        this.modulesInCharge = modulesInCharge;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public void getPersonDetails() {
        String classList = "";
        String moduleList = "";
        StringBuilder classListBuilder = new StringBuilder();
        StringBuilder moduleListBuilder = new StringBuilder();

        for (int i = 0; i < getClassInCharge().size(); i++) {
            classListBuilder.append(getClassInCharge().get(i));
            if (i < getClassInCharge().size() - 1) {
                classListBuilder.append(", ");
            }
        }

        classList = classListBuilder.toString();

        for (int i = 0; i < getModulesInCharge().size(); i++) {
            moduleListBuilder.append(getModulesInCharge().get(i));
            if (i < getModulesInCharge().size() - 1) {
                moduleListBuilder.append(", ");
            }
        }

        moduleList = moduleListBuilder.toString();
        JOptionPane.showMessageDialog(null, String.format("Lecturer: %s\nAdmin Number: %s\nClass(es): %s\nModules In Charge: %s\nContact: %s", super.getName(), super.getAdminNo(), classList, moduleList, getContact()), "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
