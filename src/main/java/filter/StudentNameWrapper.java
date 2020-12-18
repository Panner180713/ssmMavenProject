package filter;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author chenshoukai
 * @create 2019-08-29 14:41
 */
public class StudentNameWrapper extends HttpServletRequestWrapper {
    public StudentNameWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String studentName = super.getParameter(name);
        if(StringUtils.isEmpty(studentName)){
            studentName = "";
        }else if(studentName.contains("fuck")){
            return "****";
        }
        return studentName;
    }
}
