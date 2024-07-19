package com.quiz.quizapp.Controller;

import com.quiz.quizapp.pojo.Question;
import com.quiz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.allquestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getquestionbyCategory(@PathVariable String category){
        return questionService.getquestionbyCategory(category);
    }
    @GetMapping("allquestions/{id}")
    public ResponseEntity<List<Question>> getquestionbyId(@PathVariable int id){
        return questionService.getquestionbyId(id);
    }

    @PostMapping("addquestion")
    public ResponseEntity<String> addquestion(@RequestBody Question question){
        return questionService.addque(question);
    }

    @PutMapping("updatequestion/{id}")
    public ResponseEntity<String> update(@PathVariable int id,@RequestBody Question question){
        return questionService.updateQuestion(id,question);
}

    @DeleteMapping("deletequestions/{id}")
    public ResponseEntity<String> deletequestion(@PathVariable int id){
        return questionService.deletequestion(id);
    }


}
