import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Lizzi on 05.03.2017.
 */
public class CreateP extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String pass = request.getParameter("newpassword");
        String log = request.getParameter("oldlogin");
        if (Validation.checklogin(log) == false) {
            response.getWriter().println(log + ", login doensn`t exists " + "<a href = \"CreateLogin.jsp\"> Go create</a>");
        } else {
            String logps = log + " " + pass;
            Scanner lp = new Scanner(new File("C:/Hometask2/src/main/java/users.txt"));
            try (FileWriter writer = new FileWriter("C:/Hometask2/src/main/java/users.txt", true)) {


                String pchecker[]=new String[2];
                while (lp.hasNext()) {
                    String line = lp.nextLine();
                    pchecker=line.split(" ");


                    if (pchecker[0].equals(log)){
                        pchecker[1]=pass;
                        line=pchecker[0]+" "+pchecker[1];

                writer.write("\n" + line);}}


                writer.flush();
                response.getWriter().println(log + ", your password is changed" + "<a href = \"Main.jsp\"> Go login </a>");
            }
        }
    }}