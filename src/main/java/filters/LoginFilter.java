package filters;

import controllers.LoginController;
import database.DBManager;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        HttpSession session = req.getSession();

        //logged user
        if (nonNull(session) && nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password")) &&
                nonNull(session.getAttribute("role"))) {

            req.getRequestDispatcher("index.jsp").forward(req, res);
        } else if (DBManager.isCorrectUser(login, password, role)) {

            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("role", role);

            req.getRequestDispatcher("index.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, res);
        }
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}