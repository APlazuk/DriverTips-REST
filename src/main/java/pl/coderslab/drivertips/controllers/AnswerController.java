package pl.coderslab.drivertips.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.drivertips.converters.AnswerConverter;
import pl.coderslab.drivertips.dtos.AnswerDTO;
import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.services.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/tip/{tipId}/training/{trainingId}/question/{questionId}/answer")
class AnswerController {

    private final AnswerService answerService;
    private final AnswerConverter answerConverter;

    AnswerController(AnswerService answerService, AnswerConverter answerConverter) {
        this.answerService = answerService;
        this.answerConverter = answerConverter;
    }


    @GetMapping("")
    public List<AnswerDTO> getAnswersByQuestionId(@PathVariable Long questionId) {

        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);

        return answers.stream().map(answer -> answerConverter.toDTO(answer)).collect(Collectors.toList());
    }
}
