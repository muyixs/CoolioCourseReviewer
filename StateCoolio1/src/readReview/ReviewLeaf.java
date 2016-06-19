/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readReview;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import state.Context;
import state.PauseState;
import state.State;


/**
 *
 * @author hasnain
 */
public class ReviewLeaf extends State{
    
    private File courseName;
     public ReviewLeaf (File course) {
          
         setCourseName(course);
     }

    /**
     * @return the courseName
     */
    public File getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(File courseName) {
        
        this.courseName = courseName;
    }
     
    
    public void setMessageFromServer() {
         

        Context.getInstance().setMessageFromServer(printReview());
    }
    
        public String printReview() {
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(this.getCourseName()));

            String line;
            String review ="";
            
            File school = this.getCourseName().getParentFile();
        review += (String.format(" School: %s \n Course: %s \n \n", school.getName(), this.getCourseName().getName() ));
         
            int i = 1;
            while ((line = reader.readLine()) != null) {
           
                String start  = Character.toString ((char) 219);
                String end   = Character.toString ((char) 206);
                int startIndex = line.indexOf( start );
                int endIndex = line.indexOf( end );
                line =  line.substring(startIndex + 1, endIndex);
                
                review += "Review " + i++ + ": \n";
                review += line + "\n\n";

            }
            return review; 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.toString();
        }
    }

    @Override
    public void setState(String option) {
        Context.getInstance().setState(new PauseState("Press Enter for Main menu"));
    }
    
    
}
