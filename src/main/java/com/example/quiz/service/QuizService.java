package com.example.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.model.Question;
import com.example.quiz.repository.QuestionRepository;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    public Question updateQuestion(Long id, Question updatedQuestion) {
        return questionRepository.findById(id)
            .map(existingQuestion -> {
                existingQuestion.setQuestionText(updatedQuestion.getQuestionText());
                existingQuestion.setOptionA(updatedQuestion.getOptionA());
                existingQuestion.setOptionB(updatedQuestion.getOptionB());
                existingQuestion.setOptionC(updatedQuestion.getOptionC());
                existingQuestion.setOptionD(updatedQuestion.getOptionD());
                existingQuestion.setCorrectOption(updatedQuestion.getCorrectOption());
                return questionRepository.save(existingQuestion);
            })
            .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }
    
    
}
