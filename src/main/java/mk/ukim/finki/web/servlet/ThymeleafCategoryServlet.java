package mk.ukim.finki.web.servlet;

import mk.ukim.finki.service.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ThymeleafServlet",urlPatterns = "/servlet/thymeleaf/category")
public class ThymeleafCategoryServlet extends HttpServlet {
    private final CategoryService categoryService;
    private final SpringTemplateEngine springTemplateEngine;

    public ThymeleafCategoryServlet(CategoryService categoryService,  SpringTemplateEngine springTemplateEngine) {
        this.categoryService=categoryService;
        this.springTemplateEngine=springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp, req.getServletContext());
        PrintWriter writer = resp.getWriter();
        String ipClient = req.getRemoteAddr();
        String agentClient = req.getHeader("User-Agent");
        context.setVariable("ipAddress", ipClient);
        context.setVariable("clientAgent", agentClient);
        context.setVariable("categories",this.categoryService.listCategories());
        this.springTemplateEngine.process("categories.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        String desc = req.getParameter("desc");
        categoryService.create(newName, desc);
        resp.sendRedirect("/servlet/thymeleaf/category");
    }
}
