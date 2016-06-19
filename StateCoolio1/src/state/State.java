/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

/**
 *
 * @author hasnain
 */
public abstract class State {
     public  final String myResources =  this.getClass().getClassLoader().getResource("resources/").getPath();
    public  void call() {
        setMessageFromServer(); 
    }
   public abstract void setMessageFromServer(); 
   public abstract void setState(String option);
    
    
}
