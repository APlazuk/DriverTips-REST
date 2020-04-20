package pl.coderslab.drivertips.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.drivertips.converters.QuestionsConverter;
import pl.coderslab.drivertips.dtos.QuestionDTO;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.services.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/tip/{tipId}/training/{trainingId}/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionsConverter questionsConverter;

    public QuestionController(QuestionService questionService, QuestionsConverter questionsConverter) {
        this.questionService = questionService;
        this.questionsConverter = questionsConverter;
    }

    @GetMapping("")
    public List<QuestionDTO> get(@PathVariable Long tipId, @PathVariable Long trainingId){
        List<Question> questions = questionService.getQuestionByTrainingId(tipId,trainingId);

        return questions.stream().map(questionsConverter::toDTO).collect(Collectors.toList());
    }
}
