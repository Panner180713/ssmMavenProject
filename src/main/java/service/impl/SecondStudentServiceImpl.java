package service.impl;

import javaBean.Student;
import mapper.StudentMapper;
import service.SecondStudentService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenshoukai
 * @create 2019-10-30 16:22
 */
@Component
public class SecondStudentServiceImpl implements SecondStudentService {

    private final StudentMapper studentMapper;

    public SecondStudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public void secondAddStudent(Student student) {
        studentMapper.addStudent(student);
        if("son".equals(student.getName())){
//            try {
//                throw new NullPointerException();
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
            throw new NullPointerException();
        }
    }
}
