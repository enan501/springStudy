package com.backdev.happy.wblserver.global.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userEmail){
        super(userEmail + " NotFoundException");
    }

}