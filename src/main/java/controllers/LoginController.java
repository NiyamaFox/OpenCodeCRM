package controllers;

import database.DBManager;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if (login.isEmpty() || password.isEmpty() || role.isEmpty()) {
            req.setAttribute("error", 1);
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
        }

        if (DBManager.isCorrectUser(login, password, role)) {
            req.getSession().setAttribute("isLogin", "true");
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("login", login);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("error", 2);
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
        }

    }
}