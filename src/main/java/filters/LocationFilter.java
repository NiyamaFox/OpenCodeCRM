package filters;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class LocationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LocationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String ip = req.getHeader("X-Forwarded-For");
        InputStream is = new URL("https://ipwhois.app/json/" + ip).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonString = readAll(rd);
        JSONObject json = new JSONObject(jsonString);
        LOGGER.info("Клиент из города: " + json.get("city"));
        filterChain.doFilter(req, servletResponse);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public void destroy() {

    }
}