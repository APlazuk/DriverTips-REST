package pl.coderslab.drivertips.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.AnswerConverter;
import pl.coderslab.drivertips.dtos.AnswerDTO;
import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.services.AnswerService;
import pl.coderslab.drivertips.services.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/tip/{tipId}/training/{trainingId}/question/{questionId}/answer")
class AnswerController {

    private final AnswerService answerService;
    private final AnswerConverter answerConverter;
    private final QuestionService questionService;

    AnswerController(AnswerService answerService, AnswerConverter answerConverter, QuestionService questionService) {
        this.answerService = answerService;
        this.answerConverter = answerConverter;
        this.questionService = questionService;
    }


    @GetMapping("")
    public List<AnswerDTO> getAnswersByQuestionId(@PathVariable Long questionId) {

        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);

        return answers.stream().map(answer -> answerConverter.toDTO(answer)).collect(Collectors.toList());
    }

    @PostMapping("")
    public ResponseEntity<AnswerDTO> createNew(@PathVariable Long tipId,@PathVariable Long trainingId, @PathVariable Long questionId, @RequestBody AnswerDTO answerDTO, UriComponentsBuilder uriComponentsBuilder) {
        Question question = questionService.findQuestionById(questionId);
        Answer answer = answerConverter.fromDTO(answerDTO);

        Answer saved = answerService.createNewAnswer(question, answer);

        answerConverter.toDTO(saved);

        UriComponents uriComponents = uriComponentsBuilder.path("/app/tip/{tipId}/training/{trainingId}/question/{questionId}/answer/{id}").buildAndExpand(tipId, trainingId, questionId, saved.getId());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponents.toUri());

        return new ResponseEntity<AnswerDTO>(httpHeaders, HttpStatus.CREATED);
    }
}
