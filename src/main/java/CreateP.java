import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lizzi on 05.03.2017.
 */
public class CreateP extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Cookie[] mylastcookie = request.getCookies();
        out.println("Welcome "+mylastcookie[mylastcookie.length-1].getValue()+"<br>");
                performTask(request, response);
    }

    private void performTask(HttpServletRequest request, HttpServletResponse response)

            throws ServletException {

        //Validation.printToBrowser(response, request,session);

    }
}
