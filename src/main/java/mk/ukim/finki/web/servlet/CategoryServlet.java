package mk.ukim.finki.web.servlet;

import mk.ukim.finki.models.Category;
import mk.ukim.finki.service.CategoryService;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Category-Servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;


    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String ipClient = req.getRemoteAddr();
        String agentClient = req.getHeader("User-Agent");
        writer.write("<html>");
        writer.write("<head>");
        writer.write("</head>");

        writer.write("<body>");
        writer.write("<h3>Client information:<br>");
        writer.format("%s<br>%s <br>", ipClient, agentClient);
        writer.write("<h3>Category List:<br>");
        writer.write("<ul>");
         this.categoryService.listCategories().stream().forEach(r -> writer.format("<li> %s (%s)</li>",r.getName(),r.getDescription()));
        writer.write("</ul>");
        writer.write("<h3>Add Category:<br>");
        writer.write("<form method='POST' action='/servlet/category'>");
        writer.write("<label for='name'>Name: </label>");
        writer.write("<input id='name' type='text' name='name'>");
        writer.write("<label for='desc'>Description: </label>");
        writer.write("<input id='desc' type='text' name='desc'>");

        writer.write("<input type='submit' value='Submit'>");
        writer.write("</form>");


        writer.write("</body>");

        writer.write("</html>");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        String desc = req.getParameter("desc");
        categoryService.create(newName, desc);
        resp.sendRedirect("/servlet/category");
    }


}
