package com.example.model;

import org.springframework.stereotype.Component;

/**
 * @author zhanghaoyang
 */
@Component
public interface QuestionInfo {
    String getName();
    String getUsername();
    String getDescribe();
}
