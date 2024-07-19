package com.quiz.quizapp.dao;


import com.quiz.quizapp.pojo.Quizs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Quizdao extends JpaRepository<Quizs,Integer> {

    Optional<Quizs> findById(Integer id);
}
