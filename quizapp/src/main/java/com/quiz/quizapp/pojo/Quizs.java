package com.quiz.quizapp.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quizs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    private String title;

    @ManyToMany
    private List<Question> question;
}
