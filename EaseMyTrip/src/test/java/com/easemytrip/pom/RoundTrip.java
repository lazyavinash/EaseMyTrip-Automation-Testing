package com.easemytrip.pom;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



public class RoundTrip {
	WebDriver driver;
	 public RoundTrip(WebDriver driver) {
		 PageFactory.initElements(driver, this);
			this.driver = driver;
		}
	 
	 //By round = By.linkText("Round Trip");
	 By rt=By.xpath("//*[@id=\'frmHome\']/div[14]/div[2]/div[1]/ul/li[2]");
	 By from = By.id("FromSector_show");
	 By to = By.id("Editbox13_show");
	 By fromlist = By.xpath("/html/body/ul[1]/li[*]/div[1]");
	 By tolist = By.xpath("/html/body/ul[2]/li[*]/div[1]");
	 public RoundTrip findRoundTrip() throws InterruptedException {
		 //selecting source city
		 driver.findElement(rt).click();	
		 driver.findElement(from).click();
		 WebElement from2 = driver.findElement(By.id("FromSector_show"));
		 from2.sendKeys("bombay");
		 List<WebElement> ls = driver.findElements(fromlist);
		 System.out.println(ls.size());
		 //for(int x=0; x<ls.size();x++) {
	    	//  System.out.println(ls.get(x).getText());
	      //}
		 Random rand = new Random(); 
	        int randfrom = rand.nextInt(ls.size());
	        System.out.println(randfrom);
	        if(randfrom==0)
	        	randfrom++;
	        driver.findElement(By.xpath("/html/body/ul[1]/li["+randfrom+"]/div[1]")).click();;
	        //Select from_city = new Select(from2);
			 //from_city.selectByIndex(randfrom);
		 
	        //selecting destination city
	        driver.findElement(rt).click();	
			 driver.findElement(to).click();
			 WebElement to2 = driver.findElement(By.id("Editbox13_show"));
			 to2.sendKeys("delhi");
			 List<WebElement> ls2 = driver.findElements(tolist);
			 System.out.println(ls2.size());
			// for(int x=0; x<ls2.size();x++) {
		    	//  System.out.println(ls2.get(x).getText());
		      //}
			 Random rand2 = new Random(); 
		        int randto = rand2.nextInt(ls.size());
		        System.out.println(randto);
		        if(randto==0)
		        	randto++;
		        driver.findElement(By.xpath("/html/body/ul[2]/li["+randto+"]/div[1]")).click();
		    
		     //selecting going date
		        WebElement d =driver.findElement(By.id("ddate"));
		        Actions fly = new Actions(driver);
				 Thread.sleep(1000);
				 
		       try {
		    	   fly.moveToElement(d).click().perform();
		        }
		        catch(Exception e) {
		        	fly.moveToElement(d).click().perform();
		        }
		        
		        List<WebElement> lsrow = driver.findElements(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div[*]"));
			       System.out.println("date rows = "+lsrow.size());
			       //for(int x=0; x<lsrow.size();x++) {
				    //	  System.out.println(lsrow.get(x).getText());
				      //}
			       int a = (int) (Math.random()*(8-3+1)+3);   
			       System.out.println("random row is "+a);
			       
		       List<WebElement> ls3 = driver.findElements(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div[*]/ul/li[*]"));
		       System.out.println(ls3.size());
				 //for(int x=0; x<ls3.size();x++) {
			    //	  System.out.println(ls3.get(x).getText());
			     // }
				 int b = (int) (Math.random()*(7-1+1)+1);   
			       System.out.println("random row is "+b);
			       int newb = driver.findElements(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a+"]/ul/li[*]")).size();
			       if(newb<b)
			       {
			    	   b= newb;
			       }
			      if(a>=1 && a<=6) {
			    	  while(driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a+"]/ul/li["+b+"]")).getText() == null) {
			    		  b++;
			    		  if(b==7) {
			    			  a++;
			    			  b=1;
			    		  }
			    	  }
			      }
			      if(a>6) {
			    	  while(driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a+"]/ul/li["+b+"]")).getText() == null) {
			    		  b--;
			    		  if(b==1) {
			    			  a--;
			    			  b=7;
			    		  }
			    	  }
			      }
			      System.out.println("New date row is"+a+" and column is"+b);
			      Thread.sleep(2000);
			      if (a<5 && b<5) {
			    	  a=5;
			    	  b=5;
			      }
				 WebElement fly_up =driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a+"]/ul/li["+b+"]"));
				String date = fly_up.getText();
				 Actions up = new Actions(driver);
				 Thread.sleep(1000);
				 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", fly_up);

				 /* try {
					  up.moveToElement(fly_up).click().perform();
					     
			      Thread.sleep(1000);  
			      }
			      catch(Exception eup){
			    	  up.moveToElement(fly_up).click().perform();
					      Thread.sleep(1000);  
			      }*/
			    
				 
				 
				
				//selecting returning date
				 try {
			        driver.findElement(By.id("rdate")).click();
				 }
				 catch (Exception e) {
					 driver.findElement(By.id("rdate")).click();
				 }
				 List<WebElement> lsrow2 = driver.findElements(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div[*]"));
				       System.out.println("date rows = "+lsrow2.size());
				       
				       int a2 = (int) (Math.random()*(8-3+1)+3);   
				       System.out.println("random row is "+a2);
				       
			       List<WebElement> ls4 = driver.findElements(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div[*]/ul/li[*]"));
			       System.out.println(ls4.size());
					 
					 int b2 = (int) (Math.random()*(7-1+1)+1);   
				       System.out.println("random row is "+b2);
				      int newb2= driver.findElements(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a2+"]/ul/li[*]")).size();
					      if(newb2<b2)
					      {
					    	  b2=newb2;
					      }
				      if(a2>=1 && a2<=6) {
			    	  while(driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a2+"]/ul/li["+b2+"]")).getText() == null) {
			    		  b2++;
			    		  if(b2==7) {
			    			  a2++;
			    			  b2=1;
			    		  }
			    	  }
			      }
			      if(a2>6) {
			    	  while(driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a2+"]/ul/li["+b2+"]")).getText() == null) {
			    		  b2--;
			    		  if(b2==1) {
			    			  a2--;
			    			  b2=7;
			    		  }
			    	  }
			      }
			      
			      System.out.println("New date row is"+a2+" and column is"+b2);
			      Thread.sleep(2000);
			      if (a2<a && b2<b) {
			    	  a2=a+2;
			    	  b2=b+2;
			      }
			      try {
					 driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a2+"]/ul/li["+b2+"]")).click();
					 Thread.sleep(1000);
			      }
			      catch (Exception e) {
			    	  if(a2==3) {
			    		  a2=a2+1;}
			    	  System.out.println("New date row is"+a2+" and column is"+b2);
			    	  driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[3]/div[2]/div/div[1]/div/div["+a2+"]/ul/li["+b2+"]"));
						 Thread.sleep(1000);
			      }
				     
					 //selecting passengers
					 
			      	 Thread.sleep(4000);
			      	 driver.findElement(By.xpath("//a[@class='dropbtn_n9']")).click();
				// driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[5]/div")).click();
					 Thread.sleep(2000);
					 driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[5]/div/div/div/div[1]/div[2]/div/div[3]/input")).click();
					 Thread.sleep(2000);
					 
					 driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[5]/div/div/div/div[2]/div[2]/div/div[3]/input")).click();
					 Thread.sleep(1000);
					 driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[5]/div/div/div/div[3]/div[2]/div/div[3]/input")).click();
					 Thread.sleep(1000);
					 driver.findElement(By.id("traveLer")).click();
					 Thread.sleep(1000);
					
					 //selecting class
					 WebElement e5 = driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[6]/div/a"));
					 Actions cl = new Actions(driver);
					 Thread.sleep(1000);
					 cl.moveToElement(e5).click().perform();
					 Thread.sleep(1000);
					 int clas = (int) (Math.random()*(3-1+1)+1);  
					 driver.findElement(By.xpath("/html/body/form/div[14]/div[2]/div[3]/div[1]/div[6]/div/div/div/label["+clas+"]/input")).click();
					 Thread.sleep(1000);
					 driver.findElement(By.id("tripType")).click();  
					
				
					
					 return new RoundTrip(driver);
					 
					 
	 }
	public RoundDetails gotoDetails() {
		// TODO Auto-generated method stub
		//submitting form
		 WebElement e8 = driver.findElement(By.id("search"));
		 Actions actions = new Actions(driver);
		 actions.moveToElement(e8).click().perform();
		 
		return new RoundDetails(driver);
	}
	 
}
