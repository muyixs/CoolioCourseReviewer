package state;
 
import editReview.EditReviewGetCourse;
import java.io.File;


/**
 *
 * @author hasnain
 */
public class EditReviewState extends State {
File[] listOfFiles ;
  
    public void setMessageFromServer() {
        String message = "";
        
        File folder = new File(myResources+"schools/");
          listOfFiles = folder.listFiles();
          
          message +="Edit Review Page \n";
// 
//    for (int i = 0; i < listOfFiles.length; i++) {
//      if (listOfFiles[i].isDirectory()) {
//                
//               
//                message += (String.format("%d. %s\n", i,listOfFiles[i].getName() ));
//      }
//    }
    
       message += "Enter your key"; 
    
        Context.getInstance().setMessageFromServer(message);
    }

     @Override
    public void setState(String key) {
         if (key.isEmpty()) {
            Context.getInstance().setState(this);
                return;

         }
        Context.getInstance().setState(new EditReviewGetCourse(key));
//        File fileName = listOfFiles[Integer.parseInt(option)];
//        Context.getInstance().setState(new EditReviewGetCourse(fileName));
    }

}
