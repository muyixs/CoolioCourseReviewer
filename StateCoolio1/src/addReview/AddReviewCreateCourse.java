/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addReview;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import state.*;

/**
 *
 * @author hasnain
 */
public class AddReviewCreateCourse extends State {
    List<String> fileNames = new ArrayList<String>();
     private File courseName;
    File[] listOfFiles;
    GenerateKey key;
    private File school;

    public AddReviewCreateCourse(File school){
        this.setSchool(school);
        
    }

    public void setMessageFromServer() {
        String message = "Add Review Page \n";
         message += (String.format("\n\nSchool: %s  \n Key: %s \n", this.getSchool().getName(), this.generateKey()));
          message += "\nRemember this KEY! \n";
        message += "\n Enter the name of the Course\n";
      
       
        Context.getInstance().setMessageFromServer(message);
    }

    public void setState(String file) {
        key.passFile(file, this.getSchool().getPath());
        
        try {
    		 
	      File newCourse = new File(this.getSchool().getPath()+File.separator+file);
	      
	      if (newCourse.createNewFile()){
	        
                  Context.getInstance().setState(new AddReviewLeaf(newCourse,key));
	      }else{
	        
	      }
	      
    	} catch (Exception e) {
	     System.out.println(e.getMessage());
	}
       
       // File fileName = new File(file);
        
    }

    /**
     * @return the schoolName
     */
   
     public String generateKey() {
        key = new GenerateKey();

        //key.passFile(this.getCourseName().getName(), this.getCourseName().getParent());

        return key.finalKey();
    }
     public File getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(File courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the school
     */
    public File getSchool() {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(File school) {
        this.school = school;
    }

    
}
