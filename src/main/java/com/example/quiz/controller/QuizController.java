package com.example.quiz.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.model.Question;
import com.example.quiz.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService questionService;

    // 1. Start a new quiz session
    @PostMapping("/start")
    public ResponseEntity<String> startQuiz() {
        String sessionId = questionService.startSession();
        return ResponseEntity.ok("Session ID: " + sessionId);
    }

    // 2. Fetch a random question
    @GetMapping("/question/{sessionId}")
    public ResponseEntity<Question> getRandomQuestion(@PathVariable String sessionId) {
        Question question = questionService.getRandomQuestion(sessionId);
        return ResponseEntity.ok(question);
    }

    // 3. Submit an answer
    @PostMapping("/submit/{sessionId}")
    public ResponseEntity<String> submitAnswer(
            @PathVariable String sessionId,
            @RequestParam Long questionId,
            @RequestParam String chosenOption) {
        boolean isCorrect = questionService.submitAnswer(sessionId, questionId, chosenOption);
        return ResponseEntity.ok(isCorrect ? "Correct!" : "Incorrect!");
    }

    // 4. Get session summary
    @GetMapping("/summary/{sessionId}")
    public ResponseEntity<Map<String, Object>> getSummary(@PathVariable String sessionId) {
        Map<String, Object> summary = questionService.getSessionSummary(sessionId);
        return ResponseEntity.ok(summary);
    }
}
