package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionDao {


    @Autowired
    private UserDao userDao;

    @PersistenceContext
    private EntityManager entityManager;

    public  QuestionEntity getUserByQuestion(String questionId) {
        try {
            return entityManager.createNamedQuery("getQuestionByQuestionId", QuestionEntity.class).setParameter("uuid", "questionId").getSingleResult();
          } catch (NoResultException exe) {
            return null;
        }

    }

    public QuestionEntity createQuestion(QuestionEntity questionEntity) {
          entityManager.persist(questionEntity);
        return questionEntity;

    }

    public List<QuestionEntity> getAllQuestions() {

        try {
            return entityManager.createNamedQuery("getAllQuestions", QuestionEntity.class).getResultList();
        } catch (NoResultException exe) {

            return null;
        }
    }

    public QuestionEntity editQuestion(QuestionEntity questionEntity) {
        entityManager.persist(questionEntity);
        return questionEntity;
    }
}
