package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

 @Test
 public void ReviewInsantiatesCorrectly(){
     Review review = new Review("HI","him",5,2);
     assertEquals(true, review instanceof Review);
 }


}