package com.quiz.quizapp.dao;


import com.quiz.quizapp.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Questiondao extends JpaRepository<Question,Integer> {

    public List<Question> findByCategory(String category);

   public Optional<Question> findAllById(int id);


   public void deleteById(int id);


   @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :qnum ",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int qnum);

    // public String deleteAllById(int id);
}
