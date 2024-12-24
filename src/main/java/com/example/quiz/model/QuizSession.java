package com.example.quiz.model;

import java.util.HashMap;
import java.util.Map;

public class QuizSession {
    private final Map<Question, Boolean> questionAnswerMap = new HashMap<>();
    private int correctAnswers = 0;
    private int totalQuestionsAsked = 0;

    public void addQuestion(Question question) {
        questionAnswerMap.put(question, null); // Add question without answer initially
        totalQuestionsAsked++;
    }

    public void addAnswer(Question question, boolean isCorrect) {
        questionAnswerMap.put(question, isCorrect);
        if (isCorrect) correctAnswers++;
    }

    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalQuestionsAsked", totalQuestionsAsked);
        summary.put("correctAnswers", correctAnswers);
        summary.put("questionAnswerMap", questionAnswerMap);
        return summary;
    }
}
