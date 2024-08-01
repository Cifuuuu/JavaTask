/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaAsssignment_GUI;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class Person {

    // ----------------------------------------------------
    // Attributes
    // ----------------------------------------------------
    private String name = "";
    private String adminNo = "";

    // ----------------------------------------------------
    // Method
    // ----------------------------------------------------
    public Person() {
        this.name = "";
        this.adminNo = "";
    }

    public Person(String name, String adminNo) {
        this.name = name;
        this.adminNo = adminNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminCode(String adminNo) {
        this.adminNo = adminNo;
    }
    
    public void getPersonDetails(){
        JOptionPane.showMessageDialog(null,
                String.format("Name: %s\nAdmin Number: %s", getName(), getAdminNo()),
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
