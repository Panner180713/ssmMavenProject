package service.impl;

import javaBean.Student;
import mapper.StudentMapper;
import service.StudentService;
import annotation.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author chenshoukai
 * @create 2019-08-15 10:49
 */
@Component
@Fruit
//@Vegetable
public class StudentServiceImplPro implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectStudent(int ifAll) {
        return null;
    }

    @Override
    public Map<String, String> findOneStudent(String name) {
        return null;
    }

    @Override
    public Student findStudentInfoByName(String name) {
        return null;
    }

    @Override
    public List<Student> selectStudentByName(Map<String, Object> map) {
        return null;
    }

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public void addStudentList(List<Student> students) {

    }

    @Override
    public void updateStudentInfo(Student student) {

    }
}
