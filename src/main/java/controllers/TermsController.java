package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TermsController", urlPatterns = "/terms")
public class TermsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Term> terms = DBManager.getAllActiveTerms();
        req.setAttribute("terms", terms);
        String idSelected = req.getParameter("idSelected");
        if (idSelected != null) {
            Term selectedTerm = DBManager.getTermById(idSelected);
            req.setAttribute("selectedTerm", selectedTerm);
            List<Discipline> disciplines = DBManager.getAllActiveDisciplinesByTerm(selectedTerm.getId() + "");
            req.setAttribute("disciplines", disciplines);
        } else {
            Term selectedTerm = terms.get(0);
            req.setAttribute("selectedTerm", selectedTerm);
            List<Discipline> disciplines = DBManager.getAllActiveDisciplinesByTerm(selectedTerm.getId() + "");
            req.setAttribute("disciplines", disciplines);
        }
        req.getRequestDispatcher("WEB-INF/jsp/terms.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}