package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.QuestionDetailsResponse;
import com.upgrad.quora.api.model.QuestionEditResponse;
import com.upgrad.quora.api.model.QuestionRequest;
import com.upgrad.quora.api.model.QuestionResponse;
import com.upgrad.quora.service.business.QuestionBusinessService;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class QuestionController {

    @Autowired
    private QuestionBusinessService questionBusinessService;

    @RequestMapping(method = RequestMethod.POST, path = "/question/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionResponse> createQuestion(QuestionRequest questionRequest, @RequestHeader("authorization") final String accessToken) throws AuthorizationFailedException {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setUuid(UUID.randomUUID().toString());
        questionEntity.setContent(questionRequest.getContent());
        final ZonedDateTime now = ZonedDateTime.now();
        questionEntity.setDate(now);
        QuestionEntity questionCreated = questionBusinessService.createQuestion(questionEntity, accessToken);
        return new ResponseEntity<QuestionResponse>(new QuestionResponse().id(questionCreated.getUuid()).status("QUESTION CREATED"), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/question/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionDetailsResponse>> getAllQuestions(@RequestHeader("authorization") final String accessToken) throws AuthorizationFailedException {
        List<QuestionEntity> questionEntities = questionBusinessService.getAllQuestions(accessToken);
        List<QuestionDetailsResponse> questionResponseList = new ArrayList<QuestionDetailsResponse>();
        for (QuestionEntity questions : questionEntities) {
            QuestionDetailsResponse questionDetailsResponse = new QuestionDetailsResponse();
            questionDetailsResponse.id(questions.getUuid()).content(questions.getContent());
            questionResponseList.add(questionDetailsResponse);
        }
        return new ResponseEntity<List<QuestionDetailsResponse>>(questionResponseList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/question/edit/{questionId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionEditResponse> editQuestionContent(@PathVariable("questionId") String questionId, @RequestHeader("authorization") String accessToken) throws AuthorizationFailedException, InvalidQuestionException {
        QuestionEntity questionEntity = questionBusinessService.editQuestions(questionId, accessToken);
        return new ResponseEntity<QuestionEditResponse>(new QuestionEditResponse().id(questionEntity.getUuid()).status("QUESTION EDITED"), HttpStatus.OK);
    }

}


 /*   @RequestMapping(method = RequestMethod.DELETE, path = "/question/delete/{questionId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(@PathVariable("questionId") String question_id,@RequestHeader("authorization") String accessToken) {
         questionBusinessService.deleteQuestion(question_id,accessToken);

    }*/


    /*
    @RequestMapping(method = RequestMethod.GET, path = "question/all/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionDetailsResponse> getAllQuestionsByUser() {


    }


}
*/
