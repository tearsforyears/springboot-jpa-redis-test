package com.example.demo.com.example;

import com.example.DAO.QuestionDAO;
import com.example.DAO.UserDAO;
import com.example.model.User;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private QuestionDAO questionDAO;

    @Test
    public void test() {
//        userDAO.save(new User("TOMCAT2","pass"));
//        userDAO.save(new User("access","password"));
        // 测试成功 数据库添加上了如上字段
//        System.out.println(userDAO.findUsersByUserNameOrderByIdDesc("TOMCAT"));
//        Pageable pageable = PageRequest.of(0, 3);
//        Page<User> pages = userDAO.findAll(pageable);
//        pages.forEach((user) -> {
//            System.out.println(((User) user).toString());
//        });
//        System.out.println(userDAO.findAll().get(0).getQuestions());
//        System.out.println(userDAO.findUserPassByUserName("TOMCAT"));
//        System.out.println(userDAO.findUserByUserName("tomcat"));
//        System.out.println(userDAO.findQuestionsNameByUserName("TOMCAT"));
        questionDAO.findAllQuestionInfo().forEach((obj) -> {
            System.out.println(obj.getName());
            System.out.println(obj.getUsername());
            System.out.println(obj.getDescribe());
        });
    }
}
