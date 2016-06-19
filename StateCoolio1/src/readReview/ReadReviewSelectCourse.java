/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readReview;

 
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import state.Context;
import state.MainPageState;
import state.PauseState;
import state.ReadReviewState;
import state.State;

/**
 *
 * @author hasnain
 */
public class ReadReviewSelectCourse extends State {
    
     List<String> fileNames = new ArrayList<String>();
     private File schoolName;
       File[] listOfFiles ;
     public ReadReviewSelectCourse(File schoolName) {
    
         setSchoolName(schoolName);
         
         
     }
     
     
    public void setMessageFromServer() {
        String message = "";
        
        File folder = new File(this.getSchoolName().getPath());
         this.listOfFiles = folder.listFiles();
         
         if (this.listOfFiles.length == 0) {
             message += "No courses added";
         }
 
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
                
              //  fileNames.add(listOfFiles[i].getName());
             
                message += (String.format("%d. %s\n", i,listOfFiles[i].getName() ));
      }
    }

        Context.getInstance().setMessageFromServer(message);
    }

    
    public void setState(String option) {
            
        try {
         if (listOfFiles.length == 0) {
            Context.getInstance().setState(new PauseState("Press Enter for Main Page" ));
                return;
         }
         
          if (option.isEmpty()) {
            Context.getInstance().setState(this);
                return;

         }
         
         if ( Integer.parseInt(option) <0 || Integer.parseInt(option) > listOfFiles.length) {
            Context.getInstance().setState(this);
                return;

         }
         
         
        File fileName = listOfFiles[Integer.parseInt(option)];
        Context.getInstance().setState(new ReviewLeaf(fileName));
       
        
        
        }
        catch (Exception e ) // Empty School diretory
        {
         Context.getInstance().setState(new PauseState("Incorrect Entry: Press Enter to go back to main" ));

        }
    }

    /**
     * @return the schoolName
     */
    public File getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName the schoolName to set
     */
    public void setSchoolName(File schoolName) {
        this.schoolName = schoolName;
    }

   

}