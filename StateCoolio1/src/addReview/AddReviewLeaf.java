/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addReview;
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import state.Context;
import state.PauseState;
import state.State;


/**
 *
 * @author hasnain
 */
public class AddReviewLeaf extends State{
    
    private File courseName;
    private GenerateKey key;

    AddReviewLeaf(File fileName, GenerateKey key) {
        setCourseName(fileName);
       setKey( key);
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
         
        String message = "Add Review: \n";
    
        File school = this.getCourseName().getParentFile();

        message += (String.format("\n\nSchool: %s \n Course: %s \n Key: %s \n", school.getName(), this.getCourseName().getName(), this.getKey().finalKey() ));
        message += "\nEnter your review: \n";

        Context.getInstance().setMessageFromServer(message);
    }
    
        public String printReview() {
       return null; 
    }

    @Override
    public void setState(String option) {
        //option is the review
        reviewWriter(option);
        
         Context.getInstance().setState(new PauseState("\n Review Added"));
    }

    /**
     * @return the key
     */
    public GenerateKey getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(GenerateKey key) {
        this.key = key;
    }
    
    public void reviewWriter(String review) {

        
        String path = this.getCourseName().getPath();


        File reviewFile = new File(path);

        reviewFile.getParentFile().mkdirs();

        try {
            if (reviewFile.createNewFile()) {
                System.out.println("File is created!");
                BufferedWriter writer = new BufferedWriter(new FileWriter(reviewFile));

               

                writer.write(this.getKey().finalKey() + (char) 219 + review + (char) 206);
                writer.newLine();

                writer.flush();
            } else {
 
                BufferedWriter writer = new BufferedWriter(new FileWriter(reviewFile, true));

                // +(char)219+review+(char)206

                writer.write(this.getKey().finalKey() + (char) 219 + review + (char) 206);
                writer.newLine();

                writer.flush();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
