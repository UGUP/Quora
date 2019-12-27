package com.upgrad.quora.api.controller.CommonController;


import com.upgrad.quora.api.model.UserDetailsResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserProfile {


 @RequestMapping(method= RequestMethod.GET, path="/userprofile/{userId}",produces =MediaType.APPLICATION_JSON_UTF8_VALUE,consumes= MediaType.APPLICATION_JSON_UTF8_VALUE)
public ResponseEntity<UserDetailsResponse> getUserProfile()
{







}







}
