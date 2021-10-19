package controllers;

import database.DBManager;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(name = "StudentCreateController", urlPatterns = "/student-create")
public class StudentCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/student-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String group = req.getParameter("group");
        String dateFromUser = req.getParameter("date");

        if (surname.isEmpty() || name.isEmpty() || group.isEmpty() || dateFromUser.isEmpty()){
            req.setAttribute("error", "1");
            req.getRequestDispatcher("WEB-INF/jsp/student-create.jsp").forward(req, resp);
            return;
        }

        DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateFromUser);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatDB = new SimpleDateFormat("yyyy-MM-dd");
        String dateToDB = formatDB.format(date);

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setGroup(group);
        student.setDate(dateToDB);

        DBManager.createStudent(student);
        resp.sendRedirect("/students");
    }
}
