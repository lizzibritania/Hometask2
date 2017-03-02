import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Lizzi on 06.02.2017.
 */
public class Validation  extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String pass=request.getParameter("password");
        String log=request.getParameter("login");

    if (checklogin(log)) {

        Scanner lp = new Scanner(new File("C:/Hometask2/src/main/java/users.txt"));
        String pchecker[]=new String[2];
        int checkps=0;
        while (lp.hasNext()) {
            String line = lp.nextLine();
            pchecker=line.split(" ");
            if ((pchecker[0].equals(log))&&(pchecker[1].equals(pass))){
                checkps=1; }}
                if (checkps==1){
            response.getWriter().println("Welcome, " + log );}
                else {
                    response.getWriter().println(log+ ", your password is invalid"+"<a href = \"Main.jsp\"> Go back </a>" );}


        }

    else
    { response.sendRedirect("CreateLogin.jsp");
    }
    }





        public static boolean checklogin(String login) {

            int counter=0;
            boolean check=false;
            try {
                Scanner scanner = new Scanner(new File("C:/Hometask2/src/main/java/users.txt"));
                String loginchecker[]=new String[2];
                int loginexists=1;
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    loginchecker=line.split(" ");
                    if (loginchecker[0].equals(login))
                    { loginexists=0;

                    }}
                if (loginexists==1)
                {check= false;}
                else check=true;

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
         return check;
        }










    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}