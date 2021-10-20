package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Student;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TermCreateController", urlPatterns = "/term-create")
public class TermCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Discipline> disciplines = DBManager.getAllActiveDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.getRequestDispatcher("WEB-INF/jsp/term-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String durationTerm = req.getParameter("duration");
        String nameTerm = req.getParameter("name");
        if (durationTerm.isEmpty() || nameTerm.isEmpty()) {
            req.setAttribute("error", "1");
            req.getRequestDispatcher("WEB-INF/jsp/term-create.jsp").forward(req, resp);
            return;
        }

        DBManager.createTerm(durationTerm, nameTerm);
        req.setAttribute("durationTerm", durationTerm);
        req.setAttribute("nameTerm", nameTerm);

        String ids = req.getParameter("createTermHidden");
        String[] idsToTerm = ids.split("\\.");

        Term term = DBManager.getLastTermById();
        for (String id : idsToTerm) {
            DBManager.addDisciplinesToTerm(Integer.toString(term.getId()), id);
        }
        List<Term> terms = DBManager.getAllActiveTerms();
        req.setAttribute("terms", terms);
        resp.sendRedirect("/terms");
    }
}