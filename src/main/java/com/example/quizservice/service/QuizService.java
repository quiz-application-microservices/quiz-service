package com.example.quizservice.service;


import com.example.quizservice.entity.Answer;
import com.example.quizservice.entity.QuestionWrapper;
import com.example.quizservice.entity.Quiz;
import com.example.quizservice.feign.QuizInterface;
import com.example.quizservice.repo.QuizRepo;
import com.example.quizservice.util.VarList;
import org.modelmapper.ModelMapper;
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
    QuizRepo quizRepo;
    @Autowired
    QuizInterface quizInterface;
    @Autowired
    ModelMapper modelMapper;


    public String saveQuiz(String category, int numQ, String title){
        List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

       Quiz quiz = new Quiz();
       quiz.setTitle(title);
       quiz.setQuestions(questionIds);

       quizRepo.save(quiz);

        return VarList.RSP_SUCCESS;
    }



    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questions = quiz.getQuestions();

        ResponseEntity<List<QuestionWrapper>> questionWrappers = quizInterface.getQuestionsFromId(questions);
        return  questionWrappers;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Answer> answers) {
        ResponseEntity<Integer> right  = quizInterface.getScore(answers);
        return right;
    }
}

