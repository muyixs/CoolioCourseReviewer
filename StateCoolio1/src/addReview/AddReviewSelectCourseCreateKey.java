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
public class AddReviewSelectCourseCreateKey extends State {

    GenerateKey key;
    List<String> fileNames = new ArrayList<String>();
    private File courseName;
    File[] listOfFiles;

    AddReviewSelectCourseCreateKey(File fileName) {
        this.setCourseName(fileName);
    }

    public void setMessageFromServer() {
        String message = "Add Review Page \n";

        message += "\n\nThis key will be associated with your review. Don't forget it\n";
        File school = this.getCourseName().getParentFile();
        message += (String.format(" School: %s \n Course: %s \n Key: %s \n ", school.getName(), this.getCourseName().getName(), this.generateKey()));

        message += "\n\nEnter any key to continue";
        Context.getInstance().setMessageFromServer(message);
    }

    public String generateKey() {
        key = new GenerateKey();

        key.passFile(this.getCourseName().getName(), this.getCourseName().getParent());

        return key.finalKey();
    }

    public void setState(String option) {

        File fileName = this.getCourseName();
        Context.getInstance().setState(new AddReviewLeaf(fileName, key));
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
}
