package com.example.DAO;

import com.example.model.Question;
import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhanghaoyang
 */
@Component
public interface UserDAO extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Page<User> findAll(Pageable page);

    /**
     * find a user by his name
     *
     * @param userName username
     * @return a user entity
     */
    List<User> findUserByUserName(String userName); // 这里方法名要和类的字段对上

    /**
     * find a user by his id
     *
     * @param id id
     * @return a user entity
     */
    User findUserById(Long id);
    // 这里方法debug了不少时间 主要是findxxxByyyy xxx可以省略或者写类名 yyy千万不能写错是字段的名字

    /**
     * @param username username
     * @param password password
     * @return is user exist
     */
    boolean existsUserByUserNameAndPassWord(String username, String password);

    /**
     * find user by userNmae
     *
     * @param userName
     * @return
     */
    List<User> findUsersByUserNameOrderByIdDesc(String userName);

    @Query(value = "select u.passWord from User as u where u.userName=:username")
    List<String> findUserPassByUserName(@Param("username") String username);

    @Query(value = "select q.question_name from question q where q.user_id in (select id from user where user_name=?1)", nativeQuery = true)
    List<String> findQuestionsNameByUserName(String userName);
}
