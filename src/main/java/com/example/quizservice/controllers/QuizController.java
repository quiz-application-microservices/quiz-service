package com.example.quizservice.controllers;


import com.example.quizservice.dto.ResponseDTO;
import com.example.quizservice.entity.Answer;
import com.example.quizservice.entity.CreateQuizDto;
import com.example.quizservice.entity.QuestionWrapper;
import com.example.quizservice.service.QuizService;
import com.example.quizservice.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    ResponseDTO responseDTO;

//    create a quiz
    @PostMapping("/create")
    public ResponseEntity createQuiz(@RequestBody CreateQuizDto createQuizDto){
        try{
            String res = quizService.saveQuiz(createQuizDto.getCategoryName(), createQuizDto.getNumQuestions(), createQuizDto.getTitle());

            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("success");

                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else{
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("bad request");
                responseDTO.setContent(null);

                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("bad request");
            responseDTO.setContent(null);

            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    //get quiz by id
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    //handle the response coming from the frontend
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Answer> answers){
        return quizService.calculateResult(id,answers);
    }
}






