/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaAsssignment_GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class Data {

    public static String[][] adminList = {{"cifu", "181200"}, {"austin", "aussy123"}};
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static ArrayList<Lecturer> lecturerList = new ArrayList<>();
    //HELP ADD 3 LECTURERS
    public static String[][] modules = {{"PBCA", "S14931", "4"}, {"PCDS", "S14932", "3"}, {"DF", "S14933", "3"}, {"CDA", "S14934", "1"}, {"ECG", "S14935", "5"}};
//    public static String[] courses = {"DA"};
    public static String[] courses = {"DA", "DAE", "DAA", "DADP", "DACPS", "DAFSN", "DBFS", "DBS", "DBA", "DBID", "DBIT", "DCE", "DCEM", "DCCSS", "DCDF", "DDA"};
    private static final String[] firstNames = {"James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda", "William", "Elizabeth", "David", "Susan", "Joseph", "Jessica", "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa", "Matthew", "Betty", "Anthony", "Margaret", "Donald", "Sandra", "Mark", "Ashley", "Paul", "Dorothy", "Steven", "Kimberly", "Andrew", "Emily", "Kenneth", "Donna", "Joshua", "Michelle", "Kevin", "Carol", "Brian", "Amanda", "George", "Melissa", "Edward", "Deborah", "Ronald", "Stephanie", "Timothy", "Rebecca", "Jason", "Laura", "Jeffrey", "Sharon", "Ryan", "Cynthia", "Jacob", "Kathleen", "Gary", "Amy", "Nicholas", "Shirley", "Eric", "Angela", "Jonathan", "Helen", "Stephen", "Anna", "Larry", "Brenda", "Justin", "Pamela", "Scott", "Nicole", "Brandon", "Ruth", "Benjamin", "Katherine", "Samuel", "Samantha", "Gregory", "Christine", "Frank", "Emma", "Alexander", "Catherine", "Raymond", "Debra", "Patrick", "Virginia", "Jack", "Rachel", "Dennis", "Carolyn", "Jerry", "Janet", "Tyler", "Maria", "Aaron", "Heather", "Jose", "Diane", "Henry", "Julie", "Adam", "Joyce", "Douglas", "Victoria", "Nathan", "Joan", "Peter", "Evelyn", "Zachary", "Kelly", "Kyle", "Christina", "Walter", "Lauren", "Harold", "Judith", "Jeremy", "Olivia", "Ethan", "Frances", "Carl", "Martha", "Keith", "Cheryl", "Roger", "Megan", "Gerald", "Andrea", "Christian", "Hannah", "Terry", "Jacqueline", "Sean", "Ann", "Arthur", "Jean", "Austin", "Alice", "Noah", "Kathryn", "Lawrence", "Gloria", "Jesse", "Teresa", "Joe", "Sara", "Bryan", "Janice", "Billy", "Doris", "Jordan", "Madison", "Albert", "Julia", "Dylan", "Grace", "Bruce", "Judy", "Willie", "Theresa", "Gabriel", "Beverly", "Alan", "Denise", "Juan", "Marilyn", "Logan", "Amber", "Wayne", "Danielle", "Ralph", "Abigail", "Roy", "Brittany", "Eugene", "Rose", "Randy", "Dawn", "Vincent", "Carolyn", "Russell", "Diana", "Philip", "Heather", "Bobby", "Catherine", "Johnny", "Kathleen", "Bradley", "Carrie", "Antonio", "Stacy", "Tony", "Samantha", "Louis", "Lori", "Johnny", "Edna"};
    private static final String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes"};
    //private static final String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Cifu"};
    
    public static void populateData() {
        //HashSet to add the existing admin numbers
        HashSet<String> adminNumbList = new HashSet<>();
        HashSet<String> checker = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Student creater = new Student();
            creater.setName(firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)]);
            String adminNo;
            do {
                adminNo = "p" + (random.nextInt(9000000) + 1000000);
            } while (adminNumbList.contains(adminNo));

            creater.setAdminCode(adminNo);
            adminNumbList.add(adminNo);

            String formattedNumb = String.format("%02d", random.nextInt(12) + 1);
            String sClass = courses[random.nextInt(courses.length)] + "/FT/" + (random.nextInt(2) + 1) + (char) ('A' + random.nextInt(2)) + "/" + formattedNumb;
            creater.setClassCode(sClass);
            for (int k = 0; k < 2; k++) {
                Module module = null;
                do{
                int randN = random.nextInt(modules.length);
                module = new Module(modules[randN][0], modules[randN][1], Integer.parseInt(modules[randN][2]), Double.parseDouble(String.format("%.2f", random.nextDouble() * 100)));
                    System.out.println(module.getModuleName());
                }while(checker.contains(module.getModuleName()));
                creater.addModule(module);
                checker.add(module.getModuleName());
            }
            checker.clear();

            studentList.add(creater);
        }
//        for (Student student : studentList) {
//            System.out.println(student);
//        }
        Lecturer lecturer1 = new Lecturer("James Smith", "L1234", new ArrayList<>(Arrays.asList("PBCA", "PCDS")), new ArrayList<>(Arrays.asList("DAAA/FT/2A/07", "DAE/PT/3A/02, DIT/FT/2A/05")), "james.smith@gmail.com");
        lecturerList.add(lecturer1);

        Lecturer lecturer2 = new Lecturer("Mary Johnson", "L5678", new ArrayList<>(Arrays.asList("ECG", "JP")), new ArrayList<>(Arrays.asList("DA/PT/03", "DIT/FT/1A/09")), "mary.johnson@gmail.com");
        lecturerList.add(lecturer2);
        
        
        
        
    }
}
