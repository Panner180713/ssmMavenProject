package mapper;

import javaBean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chenshoukai
 * @create 2019-08-06 10:17
 */
public interface StudentMapper {

    List<Student> selectStudents(@Param("ifAll") int ifAll);

    Map<String,String> findOneStudent(@Param("name")String name);

    Student findStudentInfoByName(@Param("name") String name);

    List<Student> selectStudentByName(Map<String, Object> map);

    void addStudent(Student student);

    void updateStudentInfo(Student student);

}
