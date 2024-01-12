package com.example.quizservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuizDto {
    String categoryName;
    Integer numQuestions;
    String title;
}
