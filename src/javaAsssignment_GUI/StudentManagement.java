/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaAsssignment_GUI;

import javax.swing.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import javax.sound.sampled.*;

/**
 *
 * @author ASUS
 */
/**
 *
 * @author User
 */
public class StudentManagement {

    static {
        Data.populateData();
    }
    // ------------------------------------------
    // Cifu part
    // ------------------------------------------
    public static ArrayList<Student> studentList = Data.studentList;

    public void studentM() {
        int choice = 0;
        String message = "Enter your options \n1.Display all students \n2.Search student by class \n3.Search student by name \n4.Student Statistic \n5.Exit";;
        String ans = "";
        String classSearch = "Enter class name to search";
        String searchTitle = "Search";
        String studentSearch = "Enter the Student name to search";
        String type = "Student Enquiry System";
        String ansClass = "";
        String ansStudent = "";
        String adminSearch = "Enter student Admin Number to search";
        do {
            //Get User Options
            ans = getUserMenuOption(message, type, 1, 5);
            if (ans == null) {
                choice = 5;
                continue;
            }
            choice = Integer.parseInt(ans);
            if (choice == 1) {
                displayAll();
            } else if (choice == 2) {
                ansClass = getStudentDetails(classSearch, searchTitle, "class", studentList);
                searchClass(ansClass);
            } else if (choice == 3) {
                ansStudent = getStudentDetails(studentSearch, searchTitle, "string", studentList);
                searchStudent(ansStudent);
            } else if (choice == 4) {
                ansStudent = getStudentDetails(adminSearch, searchTitle, "adminNoExist", studentList);
//                System.out.println(ansStudent);
                searchAdmin(ansStudent);
            } else {
                choice = 5;
            }
        } while (choice != 5);
    }

    //Displays all students in tarbulated format with the use of HTML formatting
    public void displayAll() {
        int index = 0;
        int status = 0;
        do {

            String display = "<html>"
                    + "<body>"
                    + "<h2>Student Information</h2>"
                    + "<table border='1'>"
                    + "<tr><th>Name</th><th>Admin Number</th><th>Class</th><th>Modules</th><th>Scores</th></tr>";

            // Loop through the array of students to populate the table rows
//            System.out.println(index);
            for (int i = index; i < ((index + 15) < studentList.size() ? (index + 15) : studentList.size()); i++) {
                String[] modDetails = {"", ""};
                for (Module module : studentList.get(i).getModule()) {
                    modDetails = getModInfo(module, modDetails);
                }
                display += "<tr>"
                        + "<td>" + studentList.get(i).getName() + "</td>"
                        + "<td>" + studentList.get(i).getAdminNo() + "</td>"
                        + "<td>" + studentList.get(i).getClassCode() + "</td>"
                        + "<td>" + modDetails[0] + "</td>"
                        + "<td>" + modDetails[1] + "</td>"
                        + "</tr>";

            }
            display += "</table>"
                    + "</body>"
                    + "</html>";

//        JOptionPane.showMessageDialog(null, display, "Student Information", JOptionPane.INFORMATION_MESSAGE);
            String[] options = {"Previous", "Next", "Close"};
            status = JOptionPane.showOptionDialog(null,
                    display,
                    "Student Information",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[2]);
//            System.out.println(status);
            switch (status) {
                case 0:
                    if (index < 1 && studentList.size() > 15) {
                        index = studentList.size() - 15;
                    } else if (index - 15 < 0) {
                        index = 0;
                    } else {
                        index -= 15;
                    }
                    break;
                case 1:
                    if ((index + 15) >= studentList.size()) {
                        index = 0;
                    } else if ((index + 15) < studentList.size()) {
                        index += 15;
                    } else {
                        index = 0;
                    }
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        } while (status != 2 && status != -1);
    }

    //Searches class to display class size and average GPA
    public void searchClass(String ansClass) {
        String title = "Class Summary";
        ArrayList<Student> classroom = new ArrayList<>();
        double totalGPA = 0;
        double averageGPA = 0;
        if (ansClass == null) {
            return;
        } else {
            classroom = getClassroom(ansClass);
        }

        if (!classroom.isEmpty()) {
            for (Student classStu : classroom) {
                classStu.calcGPA();
                totalGPA += classStu.getGPA();
            }
            averageGPA = totalGPA / classroom.size();

            successMessage(String.format(
                    "Number of student(s) in %s: %d\n Average GPA: %.2f",
                    ansClass, classroom.size(), averageGPA
            ), title);

        } else {
            inputErrorMessageNoStudentInClass();
        }

    }

    public ArrayList<Student> getClassroom(String classCode) {
        ArrayList<Student> tempClassroom = new ArrayList<>();
        for (Student student : studentList) {
            if (classCode.equals(student.getClassCode())) {
                tempClassroom.add(student);
            }
        }
        return tempClassroom;
    }

    public String[] getModInfo(Module module, String[] modDetails) {

        String moduleInfo = modDetails[0];
        String grade = modDetails[1];
        moduleInfo += module.getModuleName() + "/" + module.getModuleCode() + "/" + module.getModuleCredit() + "<br>";
        if (module.getStudentScore() >= 80) {
            grade += "A<br>";
        } else if (module.getStudentScore() >= 70) {
            grade += "B<br>";
        } else if (module.getStudentScore() >= 60) {
            grade += "C<br>";
        } else if (module.getStudentScore() >= 50) {
            grade += "D<br>";
        } else {
            grade += "F<br>";
        }
        return new String[]{moduleInfo, grade};
    }

    public void searchAdmin(String admin) {
        if (admin == null) {
            return;
        }
        for (Student student : studentList) {
            if (student.getAdminNo().equals(admin)) {
                student.getPersonDetails();
            }
        }
    }

    public void searchStudent(String ansStudent) {
        if (ansStudent == null) {
            return;
        }
        ArrayList<Student> students = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getName().equals(ansStudent)) {
                students.add(student);
            }
        }
        if (!students.isEmpty()) {
            String display = "<html>"
                    + "<body>"
                    + "<h2>Student Information</h2>"
                    + "<table border='1'>"
                    + "<tr><th>Name</th><th>Admin Number</th><th>Class</th><th>Modules</th><th>Scores</th></tr>";

            // Loop through the array of students to populate the table rows
            for (Student student : students) {
                String[] modDetails = {"", ""};
                for (Module module : student.getModule()) {
                    modDetails = getModInfo(module, modDetails);
                }
                display += "<tr>"
                        + "<td>" + student.getName() + "</td>"
                        + "<td>" + student.getAdminNo() + "</td>"
                        + "<td>" + student.getClassCode() + "</td>"
                        + "<td>" + modDetails[0] + "</td>"
                        + "<td>" + modDetails[1] + "</td>"
                        + "</tr>";
            }
            display += "</table>"
                    + "</body>"
                    + "</html>";

            JOptionPane.showMessageDialog(null, display, "Student Search", JOptionPane.INFORMATION_MESSAGE);
        } else {
            inputErrorStudentNotFound(ansStudent);
        }

    }

    //Account Login
    public static int login() {
        String[] choice = {"Guest", "Admin", "Close"};
        String[][] adminList = Data.adminList;
        Boolean quit = false;
        Boolean userQuit = false;
        Boolean passQuit = false;
        int ans = JOptionPane.showOptionDialog(
                null,
                "Enter Login details",
                "Login",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choice,
                choice[0]
        );
        if (ans == 0) {
            return 0;
        } else if (ans == 1) {
            do {
                String user = JOptionPane.showInputDialog(null, "Enter username", "Admin Login", JOptionPane.QUESTION_MESSAGE);
                if (user == null) {
                    return 2;
                } else {
                    String password = JOptionPane.showInputDialog(null, "Enter password", "Admin Login", JOptionPane.QUESTION_MESSAGE);

                    for (String[] admin : adminList) {
                        if (admin[0].equals(user) && admin[1].equals(password)) {
                            return 1;
                        }
                    }
                    if (password != null) {
                        JOptionPane.showMessageDialog(null, "Login Details are incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } while (quit == false);
        }
        return -1;
    }

    // ------------------------------------------
    // ------------------------------------------
    public String getUserMenuOption(String message, String typeOfProgram, int start, int end) {
        String userOptionString = "";
        int userOptionInt = 0;
        boolean quit = false;

        do {
            // quit is declared false here to ensure the loop works properly
            quit = false;

            userOptionString = JOptionPane.showInputDialog(null,
                    message,
                    typeOfProgram,
                    JOptionPane.QUESTION_MESSAGE);

            if (userOptionString == null) {
                continue;
            }

            if (validateUserInputEmpty(userOptionString)) {
                inputErrorMessageEmpty();
                quit = true;
                continue;
            }

            if (validateUserInputInteger(userOptionString)) {
                inputErrorMessageInteger();
                quit = true;
                continue;
            }

            userOptionInt = Integer.parseInt(userOptionString);

            if (validateUserInputRangeInt(userOptionInt, start, end)) {
                inputErrorMessageRange();
                quit = true;
            }

        } while (quit);

        return userOptionString;
    }

    // ----------------------------------------------------
    // Validation
    // ----------------------------------------------------
    public boolean validateUserInputEmpty(String input) {
        return input.equals("");
    }

    public boolean validateUserInputRangeInt(int input, int start, int end) {
        return !(input >= start && input <= end);
    }

    public boolean validateUserInputRangeDouble(double input, int start, int end) {
        return !(input >= start && input <= end);
    }

    public boolean validateUserInputDouble(String input) {
        double tempHolder = 0;

        try {
            tempHolder = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public boolean validateUserInputInteger(String input) {
        int tempHolder = 0;

        try {
            tempHolder = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public boolean validateUserInputString(String input) {
        // need to learn
        String regex = "[^a-zA-Z\\s]";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    public boolean validateUserInputClass(String input) {
        String ClassRegex = "^[A-Z]{2,5}/(PT|FT)/[1-3][AB]/[0-9]{2}$";

        Pattern pattern = Pattern.compile(ClassRegex);

        Matcher matcher = pattern.matcher(input);

        return !matcher.matches();
    }

    public boolean validateUserInputAdminNo(String input) {
        String AdminNoRegex = "^[Pp]\\d{7}$";

        Pattern pattern = Pattern.compile(AdminNoRegex);

        Matcher matcher = pattern.matcher(input);

        return !matcher.matches();
    }

    public boolean validateUserInputCode(String input) {
        String regex = "[^a-zA-Z0-9\\s]";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    public boolean checkIfAdminNoExist(String adminNo, ArrayList<Student> studentList) {
        // if isCode is true, then it is for ModuleCode, if its false, then it is for ModuleName

        for (Student student : studentList) {
            if (student.getAdminNo().equals(adminNo)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfModuleTaken(String moduleInput, ArrayList<Module> moduleTaken, boolean isCode) {
        // if isCode is true, then it is for ModuleCode, if its false, then it is for ModuleName
        if (moduleInput == null) {
            return false;
        }

        for (Module module : moduleTaken) {
            if (isCode && module.getModuleCode().equals(moduleInput)) {
                inputErrorModuleCodeExist();
                return true;
            } else if (!isCode && module.getModuleName().equals(moduleInput)) {
                inputErrorModuleNameExist();
                return true;
            }
        }
        return false;
    }

    public boolean checkIfModuleExists(String adminNo, String moduleInput, ArrayList<Student> studentList, boolean isCode) {
        if (moduleInput == null) {
            return false;
        }

        for (Student student : studentList) {
            if (student.getAdminNo().equals(adminNo)) {
                for (Module module : student.getModule()) {
                    if (isCode && module.getModuleCode().equals(moduleInput)) {
                        inputErrorModuleCodeExist();
                        return true;
                    } else if (!isCode && module.getModuleName().equals(moduleInput)) {
                        inputErrorModuleNameExist();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // ----------------------------------------------------
    // Sound
    // ----------------------------------------------------
    public void playSound(String soundFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile));

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            // Play the audio clip
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------
    // messages
    // ----------------------------------------------------
    public void inputErrorMessage(String message, String typeOfProgram) {
        String incorrectSoundPath = "C:\\Users\\User\\OneDrive - Singapore Polytechnic\\SP Folder\\Yr2Sem1Modules\\Module - Java Programming\\NetBeansProjects\\JavaApplication2\\src\\JavaCA\\error-4-199275.wav";

        playSound(incorrectSoundPath);

        JOptionPane.showMessageDialog(
                null,
                message,
                typeOfProgram,
                JOptionPane.ERROR_MESSAGE);
    }

    public void inputErrorMessageNoStudentInClass() {
        inputErrorMessage("No Student Found from Class", "Class Summary");
    }

    public void inputErrorStudentNotFound(String student) {
        inputErrorMessage(String.format("Canot find student\"%s\"", student), "Info");
    }

    public void inputErrorMessageEmpty() {
        inputErrorMessage("Your input cannot be empty. Please try again.", "Student Admin System");
    }

    public void inputErrorMessageInteger() {
        inputErrorMessage("Your input is invalid. Please enter a number.", "Student Admin System");
    }

    public void inputErrorMessageName() {
        inputErrorMessage("Your input cannot have symbols or numbers. Please try again.", "Student Admin System");
    }

    public void inputErrorMessageAdminNo() {
        inputErrorMessage("Your input is invalid. Please try again using the proper format (p1234567).", "Student Admin System");
    }

    public void inputErrorMessageClass() {
        inputErrorMessage("Your input is in an invalid format. Please try again using the proper format (ABC/FT/1A/02).", "Student Admin System");
    }

    public void inputErrorModuleCode() {
        inputErrorMessage("Your input cannot have symbols. Please try again.", "Student Admin System");
    }

    public void inputErrorMessageRange() {
        inputErrorMessage("Your input is out of range. Please try again.", "Student Admin System");
    }

    public void inputErrorModuleCodeExist() {
        inputErrorMessage("This module has already been added under this student.", "Student Admin System");
    }

    public void inputErrorModuleNameExist() {
        inputErrorMessage("This module has already been added under this student.", "Student Admin System");
    }

    public void inputErrorAdminNoNotFound() {
        inputErrorMessage("Student not found!", "Student Admin System");
    }

    public void inputErrorAdminNoAlreadyExist() {
        inputErrorMessage("This Admin number already exist. Please try again.", "Student Admin System");
    }

    public void inputErrorOption() {
        inputErrorMessage("Your input is either empty or not an option. Please try again.", "Student Admin System");
    }

    public void successMessage(String message, String typeOfProgram) {
        JOptionPane.showMessageDialog(null, message, typeOfProgram, JOptionPane.INFORMATION_MESSAGE);
    }

    // ----------------------------------------------------
    // option methods
    // ----------------------------------------------------
    public String getStudentDetails(String message, String typeOfProgram, String modeOfValidation, ArrayList<Student> studentList) {
        String input = "";
        String trimInput = "";
        double convertedInput = 0;
        boolean quit = true;

        while (quit) {
            input = JOptionPane.showInputDialog(null,
                    message,
                    typeOfProgram,
                    JOptionPane.QUESTION_MESSAGE);

            // This is to check if the user pressed cancel to exit loop
            if (input == null) {
                return null;
            }

            trimInput = input.trim();

            switch (modeOfValidation) {
                case "string":
                    if (validateUserInputEmpty(trimInput)) {
                        inputErrorMessageEmpty();
                        break;
                    } else if (validateUserInputString(trimInput)) {
                        inputErrorMessageName();
                        break;
                    }
                    // if it reaches here, the input is valid
                    quit = false;
                    break;
                case "adminNoExist":
                    if (validateUserInputEmpty(trimInput)) {
                        inputErrorMessageEmpty();
                        break;
                    } else if (validateUserInputAdminNo(trimInput)) {
                        inputErrorMessageAdminNo();
                        break;
                    } else if (!checkIfAdminNoExist(trimInput, studentList)) {
                        inputErrorAdminNoNotFound();
                        break;
                    }

                    quit = false;
                    break;
                case "adminNoUnique":
                    if (validateUserInputEmpty(trimInput)) {
                        inputErrorMessageEmpty();
                        break;
                    } else if (validateUserInputAdminNo(trimInput)) {
                        inputErrorMessageAdminNo();
                        break;
                    } else if (checkIfAdminNoExist(trimInput, studentList)) {
                        inputErrorAdminNoAlreadyExist();
                        break;
                    }

                    quit = false;
                    break;
                case "class":
                    if (validateUserInputEmpty(trimInput)) {
                        inputErrorMessageEmpty();
                        break;
                    } else if (validateUserInputClass(trimInput)) {
                        inputErrorMessageClass();
                        break;
                    }

                    quit = false;
                    break;
                case "integer":
                    if (validateUserInputEmpty(trimInput)) {
                        inputErrorMessageEmpty();
                        break;
                    } else if (validateUserInputInteger(trimInput)) {
                        inputErrorMessageInteger();
                        break;
                    }

                    quit = false;
                    break;
                case "moduleCode":
                    if (validateUserInputEmpty(trimInput)) {
                        inputErrorMessageEmpty();
                        break;
                    } else if (validateUserInputCode(trimInput)) {
                        inputErrorModuleCode();
                        break;
                    }

                    quit = false;
                    break;
                case "score":
                    if (validateUserInputEmpty(trimInput)) {
                        inputErrorMessageEmpty();
                        break;
                    } else if (validateUserInputDouble(trimInput)) {
                        inputErrorMessageInteger();
                        break;
                    } else {
                        convertedInput = Double.parseDouble(trimInput);
                    }

                    if (validateUserInputRangeDouble(convertedInput, 0, 100)) {
                        inputErrorMessageRange();
                        break;
                    }

                    quit = false;
                    break;
                default:
                    System.out.println("This validation does not exist");
                    break;
            }
        }
        return input;
    }

    public void addStudent() {
        String messageStudentName = "Enter name:";
        String messageAdminNo = "Enter Admin:";
        String messageClass = "Enter Class:";
        String messageNoOfModulesTaken = "Enter number of Modules Taken:";

        String messageModuleCode = "Enter Module code for Module %d:";
        String messageModuleName = "Enter Module name for Module %d:";
        String messageModuleCredits = "Enter Module Credits for Module %d:";
        String messageStudentScore = "Enter Module Marks for Module %d:";
        String adminTitle = "Student Admin System";

        String studentName = "";
        String studentClass = "";
        String studentAdminNo = "";
        ArrayList<Module> moduleTaken = new ArrayList<>();

        String tempInput = "";
        String moduleName = "";
        String moduleCode = "";
        int moduleCredits = 0;
        double studentScore = 0;
        int noOfModulesTaken = 0;

        boolean quitName = true;
        boolean quitCode = true;

        // Ask all the different question
        studentName = getStudentDetails(messageStudentName, adminTitle, "string", Data.studentList);
        if (studentName == null) {
            return;
        }

        studentAdminNo = getStudentDetails(messageAdminNo, adminTitle, "adminNoUnique", Data.studentList);
        if (studentAdminNo == null) {
            return;
        }

        studentClass = getStudentDetails(messageClass, adminTitle, "class", Data.studentList);
        if (studentClass == null) {
            return;
        }

        tempInput = getStudentDetails(messageNoOfModulesTaken, adminTitle, "integer", Data.studentList);
        if (tempInput == null) {
            return;
        }

        noOfModulesTaken = Integer.parseInt(tempInput);

        for (int i = 0; i < noOfModulesTaken; i++) {

            // This loop is done as the moduleName needs to be checked if its unique
            while (quitName) {
                moduleName = getStudentDetails(String.format(messageModuleName, (i + 1)), adminTitle, "string", Data.studentList);
                quitName = checkIfModuleTaken(moduleName, moduleTaken, false);
            }
            // This is done to reset the loop if there is multiple modules taken
            quitName = true;
            if (moduleName == null) {
                noOfModulesTaken = i;
                break;
            }

            while (quitCode) {
                moduleCode = getStudentDetails(String.format(messageModuleCode, (i + 1)), adminTitle, "moduleCode", Data.studentList);
                quitCode = checkIfModuleTaken(moduleCode, moduleTaken, true);
            }
            quitCode = true;
            if (moduleCode == null) {
                noOfModulesTaken = i;
                break;
            }

            // Module Credits
            tempInput = getStudentDetails(String.format(messageModuleCredits, (i + 1)), adminTitle, "integer", Data.studentList);
            if (tempInput == null) {
                noOfModulesTaken = i;
                break;
            }
            moduleCredits = Integer.parseInt(tempInput);

            // Student Score
            tempInput = getStudentDetails(String.format(messageStudentScore, (i + 1)), adminTitle, "score", Data.studentList);
            if (tempInput == null) {
                noOfModulesTaken = i;
                break;
            }
            studentScore = Double.parseDouble(tempInput);

            Module module = new Module(moduleName, moduleCode, moduleCredits, studentScore);
            moduleTaken.add(module);
        }

        Student student = new Student(studentName, studentAdminNo, studentClass, moduleTaken);
        Data.studentList.add(student);
    }

    public void deleteStudent(String adminNo, ArrayList<Student> studentList) {
        if (adminNo == null) {
            return;
        }

        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getAdminNo().equals(adminNo)) {
                studentList.remove(i);
                successMessage("Student deleted!", "Student Admin System");
                break;
            }
        }
    }

    public void addModule(String adminNo, ArrayList<Student> studentList) {
        String messageModuleCode = "Enter Module code for Module:";
        String messageModuleName = "Enter Module name for Module:";
        String messageModuleCredits = "Enter Module Credits for Module:";
        String messageStudentScore = "Enter Module Marks for Module:";
        String adminTitle = "Student Admin System";

        String tempInput = "";
        String moduleName = "";
        String moduleCode = "";
        int moduleCredits = 0;
        double studentScore = 0;
        boolean quitName = true;
        boolean quitCode = true;

        if (adminNo == null) {
            return;
        }

        while (quitName) {
            moduleName = getStudentDetails(messageModuleName, adminTitle, "string", Data.studentList);
            quitName = checkIfModuleExists(adminNo, moduleName, studentList, false);
        }
        if (moduleName == null) {
            return;
        }

        while (quitCode) {
            moduleCode = getStudentDetails(messageModuleCode, adminTitle, "moduleCode", Data.studentList);
            quitCode = checkIfModuleExists(adminNo, moduleCode, studentList, true);
        }
        if (moduleCode == null) {
            return;
        }
        tempInput = getStudentDetails(messageModuleCredits, adminTitle, "integer", Data.studentList);
        if (tempInput == null) {
            return;
        }
        moduleCredits = Integer.parseInt(tempInput);

        tempInput = getStudentDetails(messageStudentScore, adminTitle, "score", Data.studentList);
        if (tempInput == null) {
            return;
        }
        studentScore = Double.parseDouble(tempInput);

        Module module = new Module(moduleName, moduleCode, moduleCredits, studentScore);
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getAdminNo().equals(adminNo)) {
                studentList.get(i).addModule(module);
                successMessage("Module Added!", "Student Admin System");
                break;
            }
        }
    }
//ADDED
// CHECK ADDED DATA IN DATA FILE

    public void showAllLecturerDetails() {
        Person people = null;
//        System.out.println("Hello");
//        System.out.println(Data.lecturerList.get(0));
        for (int i = 0; i < Data.lecturerList.size(); i++) {
            people = Data.lecturerList.get(i);
            people.getPersonDetails();
        }
    }

//    public void handleQuit() {
//        try {
//            // save history to history_plain.txt
//            try (BufferedWriter bw = new BufferedWriter(new FileWriter(historyPlainFile))) {
//                List<Totobet> history = bettingHistory.getBetsSortedByDate();
//
//                for (Totobet bet : history) {
//                    bw.write(bet.getDate());
//                    bw.newLine();
//                    bw.write(Arrays.toString(bet.getNumbers()));
//                    bw.newLine();
//                }
//            }
//
//            // save history to historyblob.txt (serialization)
//            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(historyBlobFile))) {
//                oos.writeObject(bettingHistory);
//            }
//
//        } catch (IOException e) {
//            inputErrorMessage("Quit error: " + e.getMessage(), "Student Admin System");
//            e.printStackTrace();
//        }
//    }
}
