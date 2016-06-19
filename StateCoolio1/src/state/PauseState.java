/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import addReview.AddReviewSelectCourse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import readReview.ReadReviewSelectCourse;

/**
 *
 * @author hasnain
 */
public class PauseState extends State {
 
 
  File[] listOfFiles ;
  private String message;
  public PauseState(String message){
      setMessage(message);
      
  }
    public void setMessageFromServer() {
         
    
        Context.getInstance().setMessageFromServer(this.getMessage());
    }

     @Override
    public void setState(String option) {
         
        Context.getInstance().setState(new MainPageState());
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
