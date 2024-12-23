package com.example.quiz.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.model.Question;
import com.example.quiz.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping("/start")
    public String startSession() {
        return quizService.startSession();
    }


    @GetMapping("/question/random/{sessionId}")
    public Question getRandomQuestion(@PathVariable String sessionId) {
        return quizService.getRandomQuestion(sessionId);
    }
    @PostMapping("/answer/{sessionId}")
    public boolean submitAnswer(@PathVariable String sessionId, 
                                @RequestParam Long questionId, 
                                @RequestParam String chosenOption) {                    
        return quizService.submitAnswer(sessionId, questionId, chosenOption);
    }
    


    @GetMapping("/summary/{sessionId}")
    public Map<String, Object> getSessionSummary(@PathVariable String sessionId) {
        return quizService.getSessionSummary(sessionId);
    }


    @PostMapping("/question")
    public Question saveQuestion(@RequestBody Question question) {
        return quizService.saveQuestion(question);
    }


    @GetMapping("/question/{id}")
    public Optional<Question> getQuestionById(@PathVariable Long id) {
        return quizService.getQuestionById(id);
    }


    @DeleteMapping("/question/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        quizService.deleteQuestion(id);
    }


    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return quizService.getAllQuestions();
    }
}
