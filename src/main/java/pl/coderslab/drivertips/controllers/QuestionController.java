package pl.coderslab.drivertips.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.QuestionsConverter;
import pl.coderslab.drivertips.dtos.QuestionDTO;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.services.QuestionService;
import pl.coderslab.drivertips.services.TrainingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/tip/{tipId}/training/{trainingId}/question")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionsConverter questionsConverter;
    private final TrainingService trainingService;

    public QuestionController(QuestionService questionService, QuestionsConverter questionsConverter, TrainingService trainingService) {
        this.questionService = questionService;
        this.questionsConverter = questionsConverter;
        this.trainingService = trainingService;
    }


    @GetMapping("")
    @ApiOperation("Show all listed Questions for each Training")
    public List<QuestionDTO> getQuestionsByTraining(@PathVariable Long tipId, @PathVariable Long trainingId){
        List<Question> questions = questionService.getQuestionByTrainingId(tipId,trainingId);

        return questions.stream().map(questionsConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Show Question")
    public QuestionDTO getQuestionById(@PathVariable Long id){
        Question question = questionService.findQuestionById(id);

        return questionsConverter.toDTO(question);
    }

    @PostMapping("")
    @ApiOperation("Create new Question")
    public ResponseEntity<QuestionDTO> createNew(@PathVariable Long tipId,@PathVariable Long trainingId, @RequestBody QuestionDTO questionDTO, UriComponentsBuilder uriComponentsBuilder){
        Training training = trainingService.findTrainingById(trainingId);
        Question question = questionsConverter.fromDTO(questionDTO);

        Question saved = questionService.createNewQuestion(question,training);

        questionsConverter.toDTO(saved);

        UriComponents uriComponents = uriComponentsBuilder.path("/app/tip/{tipId}/training/{trainingId}/question/{id}").buildAndExpand(tipId,trainingId,saved.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<QuestionDTO>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update existing Question")
    public QuestionDTO edit(@PathVariable Long id, @RequestBody QuestionDTO questionDTO){
     Question questionToUpdate = questionService.findQuestionById(id);

     questionsConverter.applyChanges(questionToUpdate, questionDTO);

     Question updatedQuestion = questionService.updateQuestion(questionToUpdate);

     return questionsConverter.toDTO(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete existing Question")
    public void delete(@PathVariable Long id){
        Question questionToDelete = questionService.findQuestionById(id);

        questionService.deleteQuestion(questionToDelete);
    }
}
