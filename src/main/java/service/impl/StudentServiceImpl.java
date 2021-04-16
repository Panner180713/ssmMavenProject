package service.impl;

import javaBean.Student;
import mapper.StudentMapper;
import service.SecondStudentService;
import annotation.Vegetable;
import service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用@Transactional注解声明式的管理事务
 * 1.可以在类级别上添加@Transactional注解，这个类中所有公共方法都会被定义为支持事务处理的。
 * 2.因为spring aop基于代理机制，所以@Transactional只能标注在公有方法上。
 * 3.七种事务的传播行为中最常用的两种：required(如果有事务在运行，当前方法就在这个事务内运行。否则，就创建一个事务，并在自己的事务内运行)、
 * required_new(当前方法必须启动新事务，并在他自己的事务内运行。如果有事务正在运行，应该将他挂起)。
 * 4.事务的隔离级别要得到底层数据库引擎的支持，而不是应用程序或者框架的支持。oracle支持两种事务隔离级别，read_commited、serializable。
 * mysql支持四种，read_uncommited(允许事务读取未被其他事务提交的变更。脏读、不可重复读、幻读都可能出现)、
 * read_commited(只允许事务读取已经被其他事务提交的变更。避免脏读)、
 * repeatable_read(在这个事务执行期间，禁止其他事务对这个字段进行更新。避免脏读、不可重复读)、
 * serializable(在这个事务执行期间，禁止其他事务对这个表进行插入、更新、删除操作，所有并发问题都可以避免，但效率十分低下。避免脏读、不可重复读、幻读)。
 * 对于大多数数据库来说，默认隔离级别都是read_commited，mysql默认隔离级别是repeatable_read，oracle默认隔离级别是read_commited。
 * 5.默认情况下，只有未检查异常(RuntimeException和Error类型的异常)会导致事务回滚，受检查的异常不会。事务的回滚规则可以通过
 * rollbackFor和noRollbackFor属性来定义。
 * 6.timeout事务超时属性：事务在强制回滚之前可以保持多久，可以防止长期运行的事务占用资源。因为事务可在行和表上获得锁，因此长事务会占用资源，并对整体性能产生影响。
 * 7.readOnly事务只读属性：表示这个事务只读取数据而不更新数据，这样可以帮助数据库引擎优化事务。
 */
@Component
@Vegetable
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    private final SecondStudentService secondStudentService;

    @Autowired
    @Vegetable
    private StudentService proxyStudentService;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, SecondStudentService secondStudentService) {
        this.studentMapper = studentMapper;
        this.secondStudentService = secondStudentService;
    }

    @Override
    public List<Student> selectStudent(int ifAll) {
        System.out.println("impl层的selectStudent方法执行了。。。");
        return studentMapper.selectStudents(ifAll);
    }

    @Override
    public Map<String, String> findOneStudent(String name) {
        studentMapper.findOneStudent(name);
        studentMapper.findOneStudent(name);
        return studentMapper.findOneStudent(name);
    }

    @Override
    public Student findStudentInfoByName(String name) {
        return studentMapper.findStudentInfoByName(name);
    }

    @Override
    public List<Student> selectStudentByName(Map<String, Object> map) {
        return studentMapper.selectStudentByName(map);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ)
    public void addStudentList(List<Student> students) {
        for (Student student : students) {
            if(student.getName().equals("tony")){
                throw new NullPointerException("抛出自定义异常");
            }
            proxyStudentService.addStudent(student);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
    public void updateStudentInfo(Student student) {
        studentMapper.updateStudentInfo(student);
        System.out.println("执行完毕");
    }
}
