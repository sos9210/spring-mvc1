package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    
    //HttpServletRequest, HttpServletResponse 구현체는 서블릿 컨테이너에서 제공하고 요청정보를 파싱한다
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        String username = req.getParameter("username");
        System.out.println("username = "+ username);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("hello " + username);
    }
}
