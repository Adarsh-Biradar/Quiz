package com.quiz.quizapp.service;


import com.quiz.quizapp.dao.Questiondao;
import com.quiz.quizapp.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

@Autowired
Questiondao questiondao;

    public ResponseEntity<List<Question>> allquestions() {
        try {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getquestionbyCategory(String category) {
        try {
            return new ResponseEntity<>( questiondao.findByCategory(category),HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getquestionbyId(int id) {
        try {
           Optional<Question> optionalQuestion =questiondao.findAllById(id);
        if(optionalQuestion.isPresent()){
           Question question= optionalQuestion.get();
           List<Question> questions = new ArrayList<>();
           questions.add(question);
            return new ResponseEntity<>(questions, HttpStatus.FOUND);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addque(Question question){
         try{
             questiondao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
         }catch (Exception e){
             e.printStackTrace();
         }
        return new ResponseEntity<>("Failed Cannot, add",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(int id,Question updatequestion){
      Optional<Question> optionalQuestion= questiondao.findAllById(id);
      try {
          Question exitingquestion = optionalQuestion.get();
          exitingquestion.setQuestion_title(updatequestion.getQuestion_title());
          exitingquestion.setOption1(updatequestion.getOption1());
          exitingquestion.setOption2(updatequestion.getOption2());
          exitingquestion.setOption3(updatequestion.getOption3());
          exitingquestion.setOption4(updatequestion.getOption4());
          questiondao.save(exitingquestion);
          return new ResponseEntity<>("Updated",HttpStatus.OK);

      }catch (Exception e){
    e.printStackTrace();
      }
      return new ResponseEntity<>("Not updated",HttpStatus.BAD_REQUEST);
    }




    public ResponseEntity<String> deletequestion(int id) {
       try {
           questiondao.deleteById(id);
           return new ResponseEntity<>("Deleted",HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }
}