package com.example.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * @author zhanghaoyang
 */
@ToString(exclude = "questions")
@AllArgsConstructor
@Entity
@Repository
@Getter
@Setter
public class User implements Serializable {
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User() {

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    String userName;
    @NotNull
    String passWord;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Question> questions;
}
