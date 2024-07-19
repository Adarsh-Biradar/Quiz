package com.quiz.quizapp.service;

import com.quiz.quizapp.dao.Questiondao;
import com.quiz.quizapp.dao.Quizdao;
import com.quiz.quizapp.pojo.Question;
import com.quiz.quizapp.pojo.QuestionWapper;
import com.quiz.quizapp.pojo.Quizs;
import com.quiz.quizapp.pojo.response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    Quizdao quizdao;

    @Autowired
    Questiondao questiondao;

    public ResponseEntity<String> createquiz(int qnum, String category, String title) {

        List<Question> questions = questiondao.findRandomQuestionByCategory(category,qnum);
        Quizs quiz = new Quizs();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizdao.save(quiz);

        return new ResponseEntity<>("Sucess", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWapper>> getquiz(Integer id) {
       Optional<Quizs> quizs=quizdao.findById(id);
       List<Question> questionsfromdb = quizs.get().getQuestion();
       List<QuestionWapper> questionWappers = new ArrayList<>();
       for(Question q : questionsfromdb){
           QuestionWapper Qw = new QuestionWapper(q.getId(),q.getQuestion_title(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
           questionWappers.add(Qw);
       }
        return new ResponseEntity<>(questionWappers,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calresult(int id, List<response> responses) {
        Optional<Quizs> quizsOptional = quizdao.findById(id);

        if (quizsOptional.isPresent()) {
            Quizs quiz = quizsOptional.get();
            List<Question> questions = quiz.getQuestion();

            int right = 0;

            // Iterate through responses and compare with correct answers
            for (int i = 0; i < responses.size(); i++) {
                response response1 = responses.get(i);
                Question question = questions.get(i);

                String givenAnswer = response1.getRes().trim().toLowerCase(); // Trim and lowercase for comparison
                String correctAnswer = question.getRightAnswer().trim().toLowerCase();

                // Logging for debugging
                System.out.println("Given Answer: " + givenAnswer);
                System.out.println("Correct Answer: " + correctAnswer);

                // Compare lowercase trimmed versions
                if (givenAnswer.equals(correctAnswer)) {
                    right++;
                }
            }

            return new ResponseEntity<>(right, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle case where quiz with given id is not found
        }
    }

}
