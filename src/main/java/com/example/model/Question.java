package com.example.model;

import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhanghaoyang
 */
@ToString(exclude = "user")
@AllArgsConstructor
@Entity
@Repository
@Getter
@Setter
public class Question implements Serializable {
    public Question() {

    }

    @Id
    @GeneratedValue
    Long id;
    String questionName;
    String questionDescribe;

    @ManyToOne(fetch = FetchType.LAZY)
    User user;
}
