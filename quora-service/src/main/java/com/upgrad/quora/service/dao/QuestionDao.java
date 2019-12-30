package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class QuestionDao {

    @Autowired
    private UserDao userDao;

    @PersistenceContext
    EntityManager entityManager;


    public UserAuthEntity validateAccessToken(String accessToken) {
       return userDao.verifyToken(accessToken);

    }


    public QuestionEntity createQuestion(QuestionEntity questionEntity) {
       entityManager.persist(questionEntity);
       return questionEntity;

    }
}
