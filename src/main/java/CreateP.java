import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                    String fileName = "C:/Hometask2/src/main/java/users.txt";
                    String search = pchecker[0]+" "+pchecker[1];
                    pchecker[1]=pass;
                    String replace = pchecker[0]+" "+pchecker[1];
                    Charset charset = StandardCharsets.UTF_8;
                    Path path = Paths.get(fileName);
                    Files.write(path,
                            new String(Files.readAllBytes(path), charset).replace(search, replace)
                                    .getBytes(charset));
                    break;
                    }}


                writer.flush();
                response.getWriter().println(log + ", your password is changed" + "<a href = \"Main.jsp\"> Go login </a>");
            }
        }
    }}