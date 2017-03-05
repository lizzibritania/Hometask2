import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Lizzi on 03.03.2017.
 */
public class Creation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String pass=request.getParameter("newpassword");
        String log=request.getParameter("newlogin");
        if(Validation.checklogin(log)==true){
            response.getWriter().println(log + ", login already exists " + "<a href = \"CreateLogin.jsp\"> Go back </a>");
        }
        else{
        String logps=log+" "+pass;
            Scanner lp = new Scanner(new File("C:/Hometask2/src/main/java/users.txt"));
        try (FileWriter writer = new FileWriter("C:/Hometask2/src/main/java/users.txt", true)) {


            writer.write("\n"+logps);


            writer.flush();
                response.getWriter().println(log+ ", your user is created"+"<a href = \"Main.jsp\"> Go login </a>" );}


        }}



}
