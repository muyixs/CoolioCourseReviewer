/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editReview;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import state.Context;
import state.PauseState;
import state.State;


/**
 *
 * @author hasnain
 */
public class EditReviewLeaf extends State{
    
    private File courseName;
    private String key;
    

    EditReviewLeaf(File fileName, String key) {
        setCourseName(fileName);
        
       setKey(key);
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
         
        String message = "Edit Review: \n";
        
        File school = this.getCourseName().getParentFile();
        //message += (String.format(" School: %s \n Course: %s \n Key: %s \n ", school.getName(), this.getCourseName().getName(), this.getKey().finalKey() ));
        message += "\n\nEnter your review: \n";

        Context.getInstance().setMessageFromServer(message);
    }
    
        public String printReview() {
       return null; 
    }

    @Override
    public void setState(String option) {
        //option is the review
        //reviewWriter(option);
        editReview(option);
         Context.getInstance().setState(new PauseState(""));
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    
// NEED KEY
    public void editReview(String review) {
        try {
            
            //String parentPath = courseName.getParent();
            //String tempPath = parentPath + "\\myTempFile.txt";
            //File tempFile = new File(tempPath);
            String oldreview = "";

            

            BufferedReader reader = new BufferedReader(new FileReader(courseName));

            //BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            
            

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.contains(key)) continue;

                oldreview+=currentLine;
                //writer.write(currentLine + System.getProperty("line.separator"));
            }
            String finalreview = getKey()+ ":"+ (char) 219 + review + (char) 206;
            oldreview+=finalreview;


            //+(char)219+review+(char)206

            //writer.write(key + (char) 219 + review + (char) 206);
            //writer.newLine();


            //writer.close();
            reader.close();
            FileWriter fileOut = new FileWriter(courseName);
            fileOut.write(oldreview);
            fileOut.flush();
            fileOut.close();
            //String fileName = courseName.getName();
            //Path source = tempFile.toPath();
            //Path newCourse = inputFile.toPath();
            //newCourse.resolveSibling("newname")

//            try {
//                Files.delete(courseName.toPath());
//            } catch (NoSuchFileException x) {
//                System.err.format("%s: no such" + " file or directory%n", courseName.getPath());
//            } catch (DirectoryNotEmptyException x) {
//                System.err.format("%s not empty%n", courseName.getPath());
//            } catch (Exception x) {
//                // File permission problems are caught here.
//                System.err.println(x);
//            }
//            
//            Files.move(source, source.resolveSibling(fileName));
//            //Files.move(source, newCourse.resolve(source.getFileName()), REPLACE_EXISTING);
//
//
        } catch (Exception e) {
            // TODO Auto-generated catch block
           e.printStackTrace();
       }

    }
}