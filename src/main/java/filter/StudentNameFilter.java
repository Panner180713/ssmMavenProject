package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author chenshoukai
 * @create 2019-08-29 14:29
 */
public class StudentNameFilter implements Filter {

    private String invalidName;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        invalidName = filterConfig.getInitParameter("invalidName");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("StudentNameFilter的doFilter()方法被调用了");
        HttpServletRequest request = new filter.StudentNameWrapper((HttpServletRequest) servletRequest);
        if("fuck".equals(request.getParameter("name"))){
            servletRequest.getRequestDispatcher("error").forward(request,servletResponse);
        }else{
//            System.out.println("doFilter执行之前的response对象:" + servletResponse);
            filterChain.doFilter(request,servletResponse);
//            System.out.println("doFilter执行之后的response对象:" + servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("StudentNameFilter的destroy()方法被调用了");
    }
}