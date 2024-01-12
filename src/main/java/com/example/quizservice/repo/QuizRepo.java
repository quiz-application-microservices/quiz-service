package com.example.quizservice.repo;

import com.example.quizservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
