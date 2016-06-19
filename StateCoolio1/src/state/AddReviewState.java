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
public class AddReviewState extends State {
 
 
  File[] listOfFiles ;
  
    public void setMessageFromServer() {
        String message = "";
        
        File folder = new File(myResources+"schools/");
          listOfFiles = folder.listFiles();
          
          message +="Add Review Page \n";
 
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isDirectory()) {
                
               
                message += (String.format("%d. %s\n", i,listOfFiles[i].getName() ));
      }
    }

    
        Context.getInstance().setMessageFromServer(message);
    }

     @Override
    public void setState(String option) {
         if (option.isEmpty()) {
            Context.getInstance().setState(this);
                return;

         }
        
        File fileName = listOfFiles[Integer.parseInt(option)];
        Context.getInstance().setState(new AddReviewSelectCourse(fileName));
    }

}
 