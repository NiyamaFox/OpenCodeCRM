package database;

import entity.Discipline;
import entity.Student;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(1);
        student.setName("Алексей");
        student.setSurname("Алексеев");
        student.setGroup("КТ-18");
        student.setDate("System.currentTimeMillis()");
        DBManager.modifyStudent(student);

    }
}
