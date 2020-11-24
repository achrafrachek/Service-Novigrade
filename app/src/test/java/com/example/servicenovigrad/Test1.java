package com.example.servicenovigrad;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class Test1 {

    private static final String FAKE_STRING = "Login was successful";



    @Test
    public void validateName(){
        RequestModel myObj = new RequestModel("achraf", "Passport", "Approved");

        String temp = myObj.getName();
        assertThat(temp,is("achraf"));


    }

    @Test
    public void validateService(){
        RequestModel myObj = new RequestModel("achraf", "Passport", "Approved") ;
        String temp = myObj.getName();
        assertThat(temp,is("employee"));
    }




}