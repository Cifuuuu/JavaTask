/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaAsssignment_GUI;

import java.io.File;
//import static finaltest.StudentManagement.login;

/**
 *
 * @author User
 */
public class StudentUser {

        public static void main(String args[]){
        AdminView view = new AdminView();
        
        view.setController(controller);
        
//        controller.start();
        
        view.setVisible(true);
    }
    
//    public static void main(String[] args) {
//        StudentManagement management = new StudentManagement();
//        int account = 0;
//        String welcomeSound = "C:\\Users\\User\\OneDrive - Singapore Polytechnic\\SP Folder\\Yr2Sem1Modules\\Module - Java Programming\\NetBeansProjects\\JavaApplication2\\src\\JavaCA\\ding-36029.wav";
//        do {
//            management.playSound(welcomeSound);
//            account = StudentManagement.login();
//            System.out.println(account);
//            if (account == 0) {
//                //guest function
//                management.studentM();
//            } else if (account == 1) {
//                //admin function
//                // ----------------------------------------------------------------
//                // Variables
//                // ----------------------------------------------------------------
//                String messageDeleteOrAdd = "Enter admin no of student:";
//                String messageClass = "Enter class:";
//                String adminTitle = "Student Admin System";
//                String studentClass = "";
//                String studentAdminNo = "";
//
//                boolean quit = true;
//                String userOption = "";
//                // ----------------------------------------------------------------
//                // Configuration
//                // ----------------------------------------------------------------
//                do {
//                    management.playSound(welcomeSound);
//                    userOption = management.getUserMenuOption("Enter Your Option: \n\n1.Add new student\n2.Delete student\n3.Add new module for student\n4.Show All Lecturer Details\n5.Exit", "Student Admin System", 1, 5);
//
//                    if (userOption == null) {
//                        quit = false;
//                        management.successMessage("Program will now close...", "Student Admin System");
//                        continue;
//                    }
//                    switch (userOption) {
//                        case "1":
//                            management.addStudent();
//                            System.out.println(Data.studentList);
//                            break;
//                        case "2":
//                            studentAdminNo = management.getStudentDetails(messageDeleteOrAdd, adminTitle, "adminNoExist", Data.studentList);
//
//                            management.deleteStudent(studentAdminNo, Data.studentList);
//                            break;
//                        case "3":
//                            studentAdminNo = management.getStudentDetails(messageDeleteOrAdd, adminTitle, "adminNoExist", Data.studentList);
//
//                            management.addModule(studentAdminNo, Data.studentList);
//                            break;
//                        case "4":
//                            management.showAllLecturerDetails();
//                            break;
//                        case "5":
//                            quit = false;
//                            management.successMessage("Program will now close...", "Student Admin System");
//                            break;
//                    }
//                } while (quit);
//            } else {
//
//            }
//
//        } while (account != -1);
//    }
}
