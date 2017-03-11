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

                       response.getWriter().println(log + ", your password is  invalid" + "<a href = \"Main.jsp\"> Go back </a> <br>"+

                               (5-Integer.parseInt(session.getAttribute("pcount").toString()))+" attempts remaining");
                   }
                 }
             }
             else
             { response.sendRedirect("CreateLogin.jsp");
             }

     }


