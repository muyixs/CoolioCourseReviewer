package state;
import readReview.ReadReviewSelectCourse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hasnain
 */
public class ReadReviewState extends State {

   List<String> fileNames = new ArrayList<String>();
  //children[] options = children.values();
  File[] listOfFiles ;
   
  
    public void setMessageFromServer() {
        String message = "";
        
        File folder = new File(myResources+"schools/");
          listOfFiles = folder.listFiles();
 
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isDirectory()) {
                
                fileNames.add(listOfFiles[i].getName());
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
        Context.getInstance().setState(new ReadReviewSelectCourse(fileName));
    }

}
