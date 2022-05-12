
package mk.ukim.finki.web.servlet.filters;

import mk.ukim.finki.models.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("servlet")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        String path=request.getServletPath();
        if(user== null && !path.equals("/login") &&  !path.equals("/home") && !path.equals("/register")  && path.equals("/main.css")) {
            response.sendRedirect("/login");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}


