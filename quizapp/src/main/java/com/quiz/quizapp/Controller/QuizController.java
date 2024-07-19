package com.quiz.quizapp.Controller;

import com.quiz.quizapp.pojo.QuestionWapper;
import com.quiz.quizapp.pojo.response;
import com.quiz.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    //Accpecting Urls verbiales using @RequestParam
    public ResponseEntity<String> createquiz( @RequestParam int Qnum, @RequestParam String category,@RequestParam String title){
        return quizService.createquiz(Qnum,category,title);
    }

    @GetMapping("getquiz/{id}")
    public ResponseEntity<List<QuestionWapper>> getQuiz(@PathVariable Integer id){
        return quizService.getquiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> Submit(@PathVariable int id, @RequestBody List<response> responses){

        return quizService.calresult(id,responses);
    }
}
