package state;
 
/**
 *
 * @author hasnain
 */
public class MainPageState extends State {
    boolean flag = true; 

    children[] options = children.values();
    public enum children {

        AddReview {

                    @Override
                    public String toString() {
                        return "Add Review";
                    }

                    @Override
                    public State getState() {
                        return new AddReviewState();
                    }

                },
        EditReview {

                    @Override
                    public String toString() {
                        return "Edit Review";
                    }

                    @Override
                    public State getState() {
                        return new EditReviewState();
                    }

                },
        ReadReview {

                    @Override
                    public String toString() {
                        return "Read Review";

                    }

                    @Override
                    public State getState() {
                        return new ReadReviewState();
                    }

                },
        Exit {

                    @Override
                    public String toString() {
                        return "Exit";

                    }

                    @Override
                    public State getState() {
                        return new ExitState();
                    }

                };

        public abstract String toString();

        public abstract State getState();
    }

    public void setMessageFromServer() {
        String message = "\n\nMain Page\n";
        int i = 0;
        for (children child : options) {
            
            message += (String.format("%d. %s\n", i++, child.toString()));
        }
          
        Context.getInstance().setMessageFromServer(message);
 
    }
    
    
    public void setState(String option){
       try {
        Context.getInstance().setState(options[Integer.parseInt(option)].getState());
       }
       catch (Exception e){
           
            Context.getInstance().setState(new PauseState("Please pick a valid option"));
       }
   
    }

}
