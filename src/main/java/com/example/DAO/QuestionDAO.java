package com.example.DAO;

import com.example.model.Question;
import com.example.model.QuestionInfo;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhanghaoyang
 */
@Component
public interface QuestionDAO extends JpaRepository<Question, Long> {

    @Query(value = "select u.userName as username,q.questionName as name,q.questionDescribe as describe from Question q left join User u on u.id=q.user.id")
    List<QuestionInfo> findAllQuestionInfo();
}
