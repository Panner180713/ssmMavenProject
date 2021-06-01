package controller;

import annotation.SecondMethodAnnotation;
import javaBean.Student;
import annotation.MethodAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.StudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 1.若希望在多个请求之间共用某个模型属性数据，则可以在控制器类上标注@SessionAttributes注解。SpringMvc会将在模型中对应的属性暂存到HttpSession中。
 * 2.可通过属性名或对象类型指定哪些模型属性需要放到session中
 * 3.当使用@SessionAttributes注解缓存对象时，handler处理类的方法中存在对应入参时，SpringMvc在implicitModel中没有找到对应对象时，
 * SpringMvc会去判断是否使用@SessionAttributes注解缓存了对象。如果缓存了，就会去查找对应的对象，如果找不到，就会抛出HttpSessionRequiredException
 */
@Controller
@SessionAttributes(value = "student03")
public class StudentController {

//    @Autowired
//    @Qualifier(value = "studentServiceImpl")
//    @Fruit
//    @Vegetable
    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    /**
     * RequestMapping注解可以通过？ * **实现模糊匹配
     */
    @RequestMapping(value = "/getTest")
    public ModelAndView getHtmlPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @RequestMapping(value = "/student/list")
    @MethodAnnotation(value = "listStudent")
//    @SecondMethodAnnotation
    public ModelAndView selectStudents(){
        ModelAndView modelAndView = new ModelAndView();
//        List<Student> studentList = studentService.selectStudent(0);

        Map<String, Object> map = new HashMap<>();
        List<String> names = Arrays.asList("tom","jerry");
        map.put("names",names);
        List<Student> studentList = studentService.selectStudentByName(map);

        modelAndView.addObject("studentList",studentList);
        modelAndView.setViewName("listStudent");
        return modelAndView;
    }

    @RequestMapping(value = "/student/findOne")
    public ModelAndView selectOneStudent(){
        ModelAndView modelAndView = new ModelAndView();
        String name = "tony";
        Map<String,String> map = studentService.findOneStudent(name);
//        List<Map<String,String>> list = studentService.findOneStudentList(name);
//        map = studentService.findOneStudent(name);
        modelAndView.addObject(map);
        modelAndView.setViewName("listStudent");
        return modelAndView;
    }

    @RequestMapping(value = "/student/add")
    public ModelAndView addStudents(Student student,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String name = request.getParameter("name");
        studentService.addStudent(student);
        List<Student> studentList = studentService.selectStudent(0);
        modelAndView.addObject("studentList",studentList);
        modelAndView.setViewName("listStudent");
        return modelAndView;
    }

    @ModelAttribute(value = "student")
    public Student getStudent(){
        Student student = new Student();
        student.setName("tony");
        return student;
    }

    @RequestMapping(value = "/student/testSameParamName")
    public ModelAndView testSameParamName(HttpServletRequest request, @RequestParam(value = "name") String name,
                                          Student<String,Integer> student, @RequestHeader(value = "Accept-Encoding") String encoding,
                                          @CookieValue(value = "sessionId",required = false) String sessionId){
        ModelAndView modelAndView = new ModelAndView();
        String name1 = request.getParameter("name");
        String name2 = name;
        String name3 = student.getName();
        System.out.println(encoding);
        System.out.println(sessionId);
        modelAndView.setViewName("listStudent");
        return modelAndView;
    }

    @SecondMethodAnnotation()
    @MethodAnnotation
    @RequestMapping(value = "/student/testTransaction")
    public ModelAndView testTransaction(){
        ModelAndView modelAndView = new ModelAndView();
        List<Student> list = new ArrayList<>();
        Student jerry = new Student("jerry",20,"qingdao",2);
        Student son = new Student("son",8,"nanjing",1);
        Student tony = new Student("tony",12,"jinan",1);
        list.add(jerry);
        list.add(son);
        list.add(tony);
        studentService.addStudentList(list);
        List<Student> studentList = studentService.selectStudent(0);
        modelAndView.addObject("studentList",studentList);
        modelAndView.setViewName("listStudent");
        return modelAndView;
    }

    @RequestMapping(value = "/student/testUpdateTransaction")
    public void testUpdateTransaction(){
        Student student = new Student();
        student.setStudentId(48);
        student.setName("chenshoukai");
        studentService.updateStudentInfo(student);
    }

    @RequestMapping(value = "/student/testModel")
    public String testModel(Map map, @ModelAttribute(value = "student") Student student02,@ModelAttribute(value = "student03") Student student03){
        Student student = new Student();
        Student student1 = (Student)map.get("student");
        student.setName("jack");
        student.setAge(22);
        student.setAddress("jinan");
        map.put("student",student);
        return "listStudent";
    }

    /**
     * @Description: 用list作为返回值，就算没有查到结果，也会封装一个空的list对象返回；
     * 用map或实体类作为返回值，如果没有查到结果，会直接返回空。
     * @Author: chenshoukai
     * @Date: 2020/12/10 13:31
     **/
    @RequestMapping(value = "/student/testReturnResult")
    public void testReturnResult(){
        Map<String,Object> paramMap = new HashMap<>(4);
        List<String> nameList = Arrays.asList("张三","李四");
        paramMap.put("names",nameList);
        List<Student> studentList = studentService.selectStudentByName(paramMap);

        Map<String,String> studentMap = studentService.findOneStudent("张三");
        Map<String,String> studentMap02 = studentService.findOneStudent("陈守凯");

        Student student = studentService.findStudentInfoByName("张三");
        Student student02 = studentService.findStudentInfoByName("陈守凯");
    }
}
