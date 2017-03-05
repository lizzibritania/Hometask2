import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;


public class Validation  extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String pass=request.getParameter("password");
        String log=request.getParameter("login");
        String checkbox=request.getParameter("cbox");
                HttpSession session = request.getSession(true);



        Cookie[] mylastcookie = request.getCookies();//хранение всех пользователей в сессии, пункт 5б
     if (session.getAttribute("login")!=null){//конец 3 пункта
         if((session.getAttribute("login").equals(log))||(log.equals("")))
         {   PrintWriter out = response.getWriter();
             out.println("Welcome "+session.getAttribute("login")+"<br>");
             print(out,request,response,session);
         }
     }
             if (checklogin(log)) {
                 if (checkp(log, pass)) {// проверка логина и пароля, пункт 1

                     if (checkbox != null) {//если отжат чекбокс (пункт 5а), то сохраняем пользователя через куки
                         Cookie logincookie = new Cookie("login", log);
                         response.addCookie(logincookie);
                         session.setAttribute("login", log);
                                            }
                     PrintWriter out = response.getWriter();
                     out.println("Welcome "+log+"<br>");
                     print(out,request,response,session);


                 } else {
                     if ( session.getAttribute("over")!=null) {
                         response.sendRedirect("NewPassword.jsp");
                     }
                     else
                   if (session.getAttribute("pcount")!=null){
                    String pcount= session.getAttribute("pcount").toString();
                    int plus=Integer.parseInt(pcount);
                    plus++;
                    session.setAttribute("pcount",plus);
                    if(plus==4){
                    session.setAttribute("over", "over");}
                     response.getWriter().println(log + ", your password is invalid" + "<a href = \"Main.jsp\"> Go back </a> <br>"+
                             (5-Integer.parseInt(session.getAttribute("pcount").toString()))+" attempts remaining");}

                     else {
                       session.setAttribute("pcount", 0);
                       response.getWriter().println(log + ", your password is invalid" + "<a href = \"Main.jsp\"> Go back </a> <br>"+
                               (5-Integer.parseInt(session.getAttribute("pcount").toString()))+" attempts remaining");
                   }
                 }
             }
             else
             { response.sendRedirect("CreateLogin.jsp");
             }

     }






    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



    public static boolean checkp(String login, String p) throws FileNotFoundException {

        boolean answer=false;
        Scanner lp = new Scanner(new File("C:/Hometask2/src/main/java/users.txt"));
        String pchecker[]=new String[2];
        while (lp.hasNext()) {
            String line = lp.nextLine();
            pchecker=line.split(" ");
            if ((pchecker[0].equals(login))&&(pchecker[1].equals(p))){
                answer=true;}}
                return answer;
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


    private static int prepareSessionCounter(

            HttpSession session) {

        Integer counter =

                (Integer)session.getAttribute("counter");

        if (counter == null) {

            session.setAttribute("counter", 1);

            return 1;

        } else {

            counter++;

            session.setAttribute("counter", counter);

            return counter;

        }

    }

    private void print(PrintWriter out, HttpServletRequest request, HttpServletResponse response,HttpSession session ){

    StringBuffer url = request.getRequestURL();

            session.setAttribute("URL", url);

            out.write("My session counter: ");


            out.write(String.valueOf(prepareSessionCounter(session)));

            out.write("<br> Creation Time : "

                    + new Date(session.getCreationTime()));

            out.write("<br> Time of last access : "

                    + new Date(session.getLastAccessedTime()));

            out.write("<br> session ID : "

                    + session.getId());

            out.write("<br> Your URL: " + url);

    int timeLive = 60 * 30;

            session.setMaxInactiveInterval(timeLive);

            out.write("<br>Set max inactive interval : "

                    + timeLive + "sec");

            out.flush();

            out.close();}
}




