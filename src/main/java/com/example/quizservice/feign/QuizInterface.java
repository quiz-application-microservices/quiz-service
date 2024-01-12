package com.example.quizservice.feign;

import com.example.quizservice.entity.Answer;
import com.example.quizservice.entity.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    //generate a quiz
    @GetMapping("api/v1/question/generateQuiz")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName, @RequestParam Integer numQuestions);

    //get questions
    @PostMapping("api/v1/question/getQuestionsFromIds")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    //calculate the score
    @PostMapping("api/v1/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Answer> responses);
}
