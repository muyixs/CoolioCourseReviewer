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
public class AddReviewSelectCourse extends State {

    List<String> fileNames = new ArrayList<String>();
    private File schoolName;
    File[] listOfFiles;

    public AddReviewSelectCourse(File schoolName) {
        setSchoolName(schoolName);
    }

    public void setMessageFromServer() {
        String message = "Add Review Page \n";
 
        File folder = new File(this.getSchoolName().getPath());
        this.listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {

              //  fileNames.add(listOfFiles[i].getName());
                message += (String.format("%d. %s\n", i , listOfFiles[i].getName()));
            }
        }
  message += listOfFiles.length + ". Create New Course \n";
        Context.getInstance().setMessageFromServer(message);
    }

    public void setState(String option) {
        
        if (Integer.parseInt(option) == this.listOfFiles.length) 
        {
            Context.getInstance().setState(new AddReviewCreateCourse(this.getSchoolName()));
        }
        
        else {
        File fileName = listOfFiles[Integer.parseInt(option)];
        Context.getInstance().setState(new AddReviewSelectCourseCreateKey(fileName));
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
