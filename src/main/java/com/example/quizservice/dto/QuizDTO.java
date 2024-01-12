package com.example.quizservice.dto;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizDTO {

    private Integer id;
    private String title;
    @ManyToMany
    private List<TypePatternQuestions.Question> questions;
}
