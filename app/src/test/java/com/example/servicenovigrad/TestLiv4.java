package com.example.servicenovigrad;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@RunWith(MockitoJUnitRunner.class)

public class TestLiv4 {
    @Test
    public void validateZone(){
        SuccursaleModel myObj = new SuccursaleModel ("Rideau" , "90 University Private" , "Mon-9:00 am" , "Driving license") ;
        String temp = myObj.getZone() ;
        assertThat(temp,is("Rideau") ) ;


    }
    @Test
    public void validateAdress(){
        SuccursaleModel myObj = new SuccursaleModel ("Rideau" , "90 University Private" , "Mon-9:00 am" , "Driving license" ) ;
        String temp = myObj.getAdresse() ;
        assertThat(temp,is("90 University Private") ) ;



    }
    @Test
    public void validateHoraire(){
        SuccursaleModel myObj = new SuccursaleModel ("Rideau" , "90 University Private" , "Mon-9:00 am" , "Driving license") ;
        String temp = myObj.getHoraire() ;
        assertThat(temp,is("Mon-9:00 am") ) ;



    }
    @Test
    public void validateService(){
        SuccursaleModel myObj = new SuccursaleModel ("Rideau" , "90 University Private" , "Mon-9:00 am" , "Driving license") ;
        String temp = myObj.getService() ;
        assertThat(temp,is("Driving license") ) ;



    }

    @Test
    public void validateRating(){
        SuccursaleModel myObj = new SuccursaleModel ("Rideau" , "90 University Private" , "Mon-9:00 am" , "Driving license" , "0") ;
        String temp = myObj.getRating() ;
        assertThat(temp,is("0") ) ;



    }
}
