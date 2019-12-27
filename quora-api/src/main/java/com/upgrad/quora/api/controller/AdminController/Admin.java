package com.upgrad.quora.api.controller.AdminController;

import com.upgrad.quora.api.model.UserDeleteResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class Admin {


    @RequestMapping(method= RequestMethod.DELETE, path="/admin/user/{userId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDeleteResponse> deleteuser()
    {








    }







}
