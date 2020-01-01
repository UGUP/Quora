package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationBusinessService {

    @Autowired
    private UserDao userDao;

    public UserAuthEntity userAuthorization(String accessToken) throws AuthorizationFailedException {
        UserAuthEntity userAuthEntity = userDao.verifyToken(accessToken);
        return userAuthEntity;
    }
}
