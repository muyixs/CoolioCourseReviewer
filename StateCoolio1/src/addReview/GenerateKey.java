package addReview;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

public class GenerateKey {

	int randomNum;
	int min = 100;
	int max = 999;
	String monthString;
	private String finalKey;
	
	
	 public GenerateKey(){
		 
		 random();
		 getMonth();
		 finalKey();

		 

	}

	public   void random() {
	    Random rand = new Random();
		randomNum = rand.nextInt((max - min) + 1) + min;
		
	}
	
	public void getMonth() {
	
	Calendar now = Calendar.getInstance();
	int month = now.get(Calendar.MONTH) +1 ;
	 
	
	  
     
    switch (month) {
        case 1:  monthString = "January";
                 break;
        case 2:  monthString = "February";
                 break;
        case 3:  monthString = "March";
                 break;
        case 4:  monthString = "April";
                 break;
        case 5:  monthString = "May";
                 break;
        case 6:  monthString = "June";
                 break;
        case 7:  monthString = "July";
                 break;
        case 8:  monthString = "August";
                 break;
        case 9:  monthString = "September";
                 break;
        case 10: monthString = "October";
                 break;
        case 11: monthString = "November";
                 break;
        case 12: monthString = "December";
                 break;
        default: monthString = "Invalid month";
                 break;
    }
   // System.out.println(monthString);
}
	

	public String finalKey() {
		finalKey = monthString+randomNum;
		return finalKey;
		
	}
	
	public void passFile(String courseName, String schoolName) {
		 String myResources =  this.getClass().getClassLoader().getResource("resources/").getPath();
		File passFile = new File(myResources + "password/passFile");
	      
	      try {
			if (passFile.createNewFile()){
			    System.out.println("File is created!");
			    BufferedWriter writer = new BufferedWriter(new FileWriter(passFile));

			     
			    
			        writer.write(finalKey+":"+schoolName+"\\"+courseName);
			        writer.newLine();
			   
			    writer.close();
			}
			    
			    
			    
			  else{
				  
				  
			   
			    
			    BufferedWriter writer = new BufferedWriter(new FileWriter(passFile, true));

			     
			    
		        writer.write(finalKey+":"+schoolName+"\\"+courseName);
		        writer.newLine();
		   
		    writer.close();
			    
			  }
		} catch (Exception e) {
			 
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	public String pathForKey(String key) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("passFile"));
			String line;
			String path;
			String toReplace = finalKey+":";
			while ((line = reader.readLine()) !=null) {
				
				if (line.contains(finalKey)){
					path = line.replaceAll(toReplace,"");
					 
					return path;
				}
			
				
			}
			  
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 
		return "No Key in PassFile";
	}
	
}
