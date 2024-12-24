package com.example.quiz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.model.Question;
import com.example.quiz.model.QuizSession;
import com.example.quiz.repository.QuestionRepository;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    private final Map<String, QuizSession> sessions = new HashMap<>();


    public String startSession() {
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, new QuizSession());
        return sessionId;
    }


    public Question getRandomQuestion(String sessionId) {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) throw new RuntimeException("No questions available");

        QuizSession session = sessions.get(sessionId);
        if (session == null) throw new RuntimeException("Invalid session ID");

        Random random = new Random();
        Question question = questions.get(random.nextInt(questions.size()));

        session.addQuestion(question);
        return question;
    }


    public boolean submitAnswer(String sessionId, Long questionId, String chosenOption) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isEmpty()) throw new RuntimeException("Invalid question ID");
    
        Question question = optionalQuestion.get();
    
        String normalizedChosenOption = chosenOption.replace("Option", "").trim(); 
        boolean isCorrect = question.getCorrectOption().trim().equalsIgnoreCase(normalizedChosenOption);
    
        // Track the answer in the session
        sessions.get(sessionId).addAnswer(question, isCorrect);
        return isCorrect;
    }
    

    public Map<String, Object> getSessionSummary(String sessionId) {
        QuizSession session = sessions.get(sessionId);
        if (session == null) throw new RuntimeException("Invalid session ID");

        return session.getSummary();
    }




    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }


    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }


    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
