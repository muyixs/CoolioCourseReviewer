/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editReview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import state.Context;
import state.PauseState;
import state.State;

/**
 *
 * @author hasnain
 */
public class EditReviewGetCourse extends State {

    List<String> fileNames = new ArrayList<String>();
    private File schoolName;

    private String key;
    private String schoolAndCourse;
    private String filePathAndCourse;

    public EditReviewGetCourse(String keyIn) {
        key = keyIn;
        

    }

    private int getCoursePathFromPassFile() {
        try {

            String pathToPassFile = (myResources + "password/passFile");

            BufferedReader reader = new BufferedReader(new FileReader(pathToPassFile));

            String line;
            String path;

            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    if (key.isEmpty())
                        break;
                    int index = line.indexOf("schools");
                    schoolAndCourse = line.substring(index);   //.split("\"")[0];
                    index = schoolAndCourse.indexOf("\\");
                    String school = schoolAndCourse.substring(0, index);
                    String course = schoolAndCourse.substring(index + 1);

                    filePathAndCourse = (myResources + school + File.separator + course);
                    
                    return 1;
                }

            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return 0;
    }
    
    String getReview(){
        
         try {
            
            BufferedReader reader = new BufferedReader(new FileReader(filePathAndCourse));

            String line;
            String review ="";
            
            
       
         
            int i = 1;
            while ((line = reader.readLine()) != null) {
            if (line.contains(key)) {
                String start  = Character.toString ((char) 219);
                String end   = Character.toString ((char) 206);
                int startIndex = line.indexOf( start );
                int endIndex = line.indexOf( end );
                line =  line.substring(startIndex + 1, endIndex);
                
                review += "Orignal Review: \n";
                review += line + "\n";
            }
            }
            reader.close();
            return review; 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.toString();
        }
    }

    public void setMessageFromServer() {
        String message ="";
        int bool = getCoursePathFromPassFile();
        if (bool == 1){
            
            message += getReview();
        }
        else { 
         message += "Press Enter to continue...";
        }
        
        Context.getInstance().setMessageFromServer(message);
    }

    public void setState(String option) {
        int bool = getCoursePathFromPassFile();
        if (bool != 1)
            Context.getInstance().setState(new PauseState("Incorrect Key"));
        else if (bool == 1) {
           File fileName = new File(filePathAndCourse);
        
        Context.getInstance().setState(new EditReviewLeaf(fileName, key)); 
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
