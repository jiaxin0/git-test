package com.jpa;

import com.jpa.onetomany.domains.Book;
import com.jpa.onetomany.domains.Page;
import com.jpa.onetomany.repositories.BookRepository;
import com.jpa.onetomany.repositories.PageRepository;
import com.jpa.users.entities.Address;
import com.jpa.users.entities.User;
import com.jpa.users.repositories.AddressRepository;
import com.jpa.users.repositories.UserRepository;
import com.jpa.users.repositories.AddressRepository;
import com.jpa.users.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 一对一
    @Bean
    public CommandLineRunner mappingDemo(UserRepository userRepository,
                                         AddressRepository addressRepository) {
        return args -> {

            // 创建用户
            User user = new User("张三", "john.doe@qq.com", "1234abcd");

            // 创建地址
            Address address = new Address("毛大", "长沙", "长沙",
                    "410000", "湖南");

            // 设置引用
            address.setUser(user);

            // 设置引用
            user.setAddress(address);

            // 保存父对象
            // 同时也会保存子对象
            userRepository.save(user);
        };
    }

    // 一对多
    @Bean
    public CommandLineRunner mappingDemo(BookRepository bookRepository,
                                         PageRepository pageRepository) {
        return args -> {

            // 创建新书
            Book book = new Book("Java课程", "John Doe", "123456");

            // 保存书
            bookRepository.save(book);

            // 保存书的内容
            pageRepository.save(new Page(1, "介绍内容", "介绍", book));
            pageRepository.save(new Page(65, "Java 8 ", "Java 8", book));
            pageRepository.save(new Page(95, "并发", "并发", book));

           //Book book1= bookRepository.findByIsbn("123456");
           //System.out.println(book1);

        };
    }

//    // 多对多
//    @Bean
//    public CommandLineRunner mappingDemo(StudentRepository studentRepository,
//                                         CourseRepository courseRepository) {
//        return args -> {
//
//            // 创建学生
//            Student student = new Student("张三", 15, "3");
//
//            // 保存学生
//            studentRepository.save(student);
//
//            // 创建三门课
//            Course course1 = new Course("机器学习", "ML", 12, 1500);
//            Course course2 = new Course("数据库系统", "DS", 8, 800);
//            Course course3 = new Course("Web基础", "WB", 10, 0);
//
//            // 保存课程
//            courseRepository.saveAll(Arrays.asList(course1, course2, course3));
//
//            // 学生添加课程
//            student.getCourses().addAll(Arrays.asList(course1, course2, course3));
//
//            // 更新学生数据
//            studentRepository.save(student);
//        };
//    }


    // 复合主键
//    @Bean
//    public CommandLineRunner mappingDemo(AccountRepository accountRepository,
//                                         EmployeeRepository employeeRepository) {
//        return args -> {
//
//
//            // 创建新账户
//            accountRepository.save(new Account("458666", "Checking", 4588));
//            accountRepository.save(new Account("458689", "Checking", 2500));
//            accountRepository.save(new Account("424265", "Saving", 100000));
//
//            // 查找某种类型的账户
//            List<Account> accounts = accountRepository.findByAccountType("Checking");
//            accounts.forEach(System.out::println);
//
//            // 通过复合主键查找账户
//            Optional<Account> account = accountRepository.findById(new AccountId("424265", "Saving"));
//            account.ifPresent(System.out::println);
//
//            // ======= `@EmbeddedId` Annotation =======
//
//            // 创建新员工
//            employeeRepository.save(new Employee(new EmployeeId(100L, 10L),
//                    "John Doe", "john@example.com", "123456"));
//            employeeRepository.save(new Employee(new EmployeeId(101L, 20L),
//                    "Emma Ali", "emma@example.com", "654321"));
//
//            // 创建某个部门的新员工
//            List<Employee> employees = employeeRepository.findByEmployeeIdDepartmentId(20L);
//            employees.forEach(System.out::println);
//
//            // 通过复合主键查找员工
//            Optional<Employee> employee = employeeRepository.findById(new EmployeeId(100L, 10L));
//            employee.ifPresent(System.out::println);
//        };
//    }
}
