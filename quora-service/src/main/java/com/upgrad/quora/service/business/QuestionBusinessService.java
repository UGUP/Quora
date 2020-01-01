package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.QuestionDao;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionBusinessService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AuthorizationBusinessService authorizationBusinessService;

    @Transactional(propagation = Propagation.REQUIRED)
    public QuestionEntity createQuestion(QuestionEntity questionEntity, String accessToken) throws AuthorizationFailedException {
        UserAuthEntity userAuthEntity = authorizationBusinessService.userAuthorization(accessToken);
        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        } else if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to post a question");
        } else {
            questionEntity.setUser(userAuthEntity.getUser());
            return questionDao.createQuestion(questionEntity);

        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<QuestionEntity> getAllQuestions(String accessToken) throws AuthorizationFailedException {
        UserAuthEntity userAuthEntity = authorizationBusinessService.userAuthorization(accessToken);

        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        } else if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to get all questions");
        } else {
            return questionDao.getAllQuestions();

        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public QuestionEntity editQuestions(String questionId, String accessToken) throws AuthorizationFailedException, InvalidQuestionException {

        UserAuthEntity userAuthEntity = authorizationBusinessService.userAuthorization(accessToken);
        QuestionEntity questionEntity = questionDao.getUserByQuestion(questionId);

        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        } else if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "User is signed out.Sign in first to edit the question");
        } else if (questionEntity == null) {
            throw new InvalidQuestionException("QUES-001", "Entered question uuid does not exist");
        }

        if (userAuthEntity.getUser().getId() != questionEntity.getUser().getId()) {
            throw new AuthorizationFailedException("ATHR-003", "Only the question owner can edit the question");

        } else {
            questionEntity.setContent(questionEntity.getContent());
            return questionDao.editQuestion(questionEntity);

        }


    }

/*    @Transactional(propagation = Propagation.REQUIRED)
    public QuestionEntity deleteQuestion(String questionId,String accessToken)
    {






    }
*/


}



