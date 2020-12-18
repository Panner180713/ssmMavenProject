package controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.TestShiroService;

/**
 * @Author chenshoukai
 * @Date 2020/05/28 22:44
 */
@RequestMapping("/testShiro")
@Controller
public class TestShiroController {

    @Autowired
    private TestShiroService testShiroService;

    @RequestMapping(value = "/jumpToLoginPage")
    public ModelAndView jumpToLoginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequiresGuest
//    @RequiresUser
    @RequestMapping(value = "/jumpToListPage")
    public ModelAndView jumpToListPage(){
        ModelAndView modelAndView = testShiroService.getModelAndViewByUser();
        Subject subject = SecurityUtils.getSubject();
        modelAndView.addObject("isRememberMe",subject.isRemembered());
        return modelAndView;
    }

    @RequiresAuthentication
    @RequiresRoles(value = {"user"})
    @RequestMapping(value = "/jumpToUserPage")
    public ModelAndView jumpToUserPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @RequiresAuthentication
    @RequiresPermissions("admin:select")
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/jumpToAdminPage")
    public ModelAndView jumpToAdminPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/shiroLogin")
    public ModelAndView shiroLogin(String userName,String password){
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated() && !subject.isRemembered()){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,password);
            usernamePasswordToken.setRememberMe(true);
            try {
                subject.login(usernamePasswordToken);
            }catch (IncorrectCredentialsException e){
                System.out.println("账号或密码有误");
                modelAndView.setViewName("error");
                return modelAndView;
            }catch (AuthenticationException e){
                System.out.println("执行登录失败");
                modelAndView.setViewName("error");
                return modelAndView;
            }
        }
        modelAndView.setViewName("list");
        modelAndView.addObject("isRememberMe",subject.isRemembered());
        modelAndView.addObject("isAuthenticated",subject.isAuthenticated());
        return modelAndView;
    }
}
