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
public class ExitState extends State {

    @Override
    public void setMessageFromServer() {
       Context.getInstance().setMessageFromServer("\n Thank you for using Coolio Client Server\n");
    }

    @Override
    public void setState(String option) {
        Context.getInstance().getServer().shutDown();
    }
    
}
