package database;

import entity.Discipline;
import entity.Student;
import entity.Term;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    public static List<Discipline> getAllActiveDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discipline where status = '1'");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    public static List<Student> getAllActiveStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student where status = '1'");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getString("date"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public static Student getStudentById(String id) {
        Student student = new Student();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student where status = '1' AND id = " + id);
            while (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getString("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public static boolean isCorrectUser(String login, String password, String role) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_role as ur\n" +
                    "left join user as u on ur.id_user = u.id\n" +
                    "where u.login = '" + login + "' and u.password = '" + password + "' and ur.id_role = '" + role + "'");
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void modifyStudent(Student student) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `surname` = '"
                    + student.getSurname() + "', `name` = '"
                    + student.getName() + "', `group` = '"
                    + student.getGroup() + "', `date` = '"
                    + student.getDate() + "' WHERE (`id` = '"
                    + student.getId() + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createStudent(Student student) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `student` (`name`, `surname`, `group`, `date`) " +
                    "VALUES ('" + student.getName() + "', '"
                    + student.getSurname() + "', '"
                    + student.getGroup() + "', '" + student.getDate() + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDiscipline(String discipline) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `students`.`discipline` (`discipline`) VALUES ('" + discipline + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Discipline getDisciplineById(String id) {
        Discipline discipline = new Discipline();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discipline where status = '1' AND id = " + id);
            while (rs.next()) {
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discipline;
    }

    public static void modifyDiscipline(String id, String discipline) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `students`.`discipline` SET `discipline` = '" + discipline + "' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteDiscipline(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `status` = '0' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Term> getAllActiveTerms() {
        ArrayList<Term> terms = new ArrayList<Term>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM term where status = '1'");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setName("Семестр " + rs.getInt("name"));
                term.setDuration(rs.getInt("duration") + " недель");
                terms.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms;
    }

    public static List<Discipline> getAllActiveDisciplinesByTerm(String idTerm) {
        ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT td.id_discipline as id, d.discipline as name FROM term_discipline as td\n" +
                    "left join discipline as d on td.id_discipline = d.id\n" +
                    "WHERE td.id_term = " + idTerm + " AND d.status = '1'");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("name"));
                disciplines.add(discipline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    public static Term getTermById(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM term where status = '1'and id = " + id);
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setName("Семестр " + rs.getInt("name"));
                term.setDuration(rs.getInt("duration") + " недель");
                return term;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createTerm(String duration, String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `students`.`term` (`duration`, `name`) VALUES ('" + duration + "', '" + name + "');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDisciplinesToTerm(String term, String discipline) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `term_discipline` (`id_term`, `id_discipline`) " +
                    "VALUES ('" + term + "', '" + discipline + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Term getLastTermById() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students.term WHERE status = '1'and id=(SELECT max(id) FROM term);");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getInt("id"));
                term.setName("Семестр " + rs.getInt("name"));
                term.setDuration(rs.getInt("duration") + " недель");
                return term;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteTermWithDisciplines(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?user=root&password=c49voc8h");
            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM term_discipline WHERE (`id_term` = '" + id + "');");
            stmt.execute("DELETE FROM term_discipline WHERE (`id_term` = '" + id + "');");
            stmt.execute("DELETE FROM `students`.`term` WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}