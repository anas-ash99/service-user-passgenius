package com.passgenius.serviceuser.exceptions;

public class InvalidAuthorizationHeaderException extends Exception{

    public InvalidAuthorizationHeaderException(String message){
        super(message);
    }
}
