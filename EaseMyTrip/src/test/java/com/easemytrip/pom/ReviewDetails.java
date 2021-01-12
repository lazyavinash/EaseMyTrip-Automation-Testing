package com.easemytrip.pom;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class ReviewDetails {
WebDriver driver;
	public ReviewDetails(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	//Email Verification
		By e_textField = By.xpath("//div[@class='con-m']//input[@id='txtEmailId']");
		By e_continue = By.xpath("//span[@id='spnVerifyEmail']");
		By e_nextSection = By.id("divTravellerSHow");
		//Contact No Verification
		By co_textField = By.id("txtCPhone");
		By co_continue = By.xpath("//span[@id='spnTransaction']");
		By co_pay = By.id("divPaymentMode");
		//Add Seat Button
		By a_button = By.xpath("//div[@id='seatArea']//a[@class='adSeatbtn'][contains(text(),'+ Add Seat')]");
		By a_panel = By.id("mainSeatPanel");

		//Adult Details
		By t_title = By.id("titleAdult0");
		By t_first = By.id("txtFNAdult0");
		By t_last = By.id("txtLNAdult0");

		//Child Details
		By ch_title = By.id("titleChild0");
		By ch_first = By.id("txtFNChild0");
		By ch_last = By.id("txtLNChild0");

		//Infant Details
		By in_title = By.id("titleInfant0");
		By in_first = By.id("txtFNInfant0");
		By in_last = By.id("txtLNInfant0");

		//Verify Infant DOB drop downs
		By in_day = By.id("divDOBDayInfant0");
		By in_month = By.id("divDOBMonInfant0");
		By in_year = By.id("divDOBYarInfant0");


		//Add Baggage
		By b_add = By.id("baggageBtn");
		By b_disp = By.id("baggageBoxAdult0");
		By b_list = By.xpath("//body[@id='bodyMain']/form[@id='FrmEmtMdl']/div[@id='FrmTrveller']/div[@class='tr-m']/div[@class='tr-cen']/div[@id='divTrvfltDtl']/div[@class='fd-ll']/form[@class='ng-pristine ng-invalid ng-invalid-pattern ng-valid-maxlength ng-valid-required']/div[@id='divTravellerSHow']/div[@class='bor']/div[@id='divTravlerEntry']/div[@class='tr-cen-trv']/div[@class='fd-ll']/div[@id='divAdultPax']/div[@class='shd_pnl']/div[@class='ancillAdult1']/div[@id='baggageBoxAdult1']/div[@id='dbb_Adult1_DEL-BOM-8723']/div[@class='mealmenu']/label[*]/span[1]");

		//Remove adult button
		By r_adult = By.xpath("//div[@class='shd_pnl']//div[@class='ng-scope']//span[@class='cmark_cbox']");
		By r_hide = By.xpath("//div[@id='divTrvAdult0']");

		//Remove Child button
		By r_child = By.xpath("//span[@id='spnChild0']");
		By r_chhide = By.xpath("//div[@id='divTrvChild0']");

		//Remove Infant button
		By r_infant = By.xpath("//span[@id='spnInfant0']");
		By r_inhide = By.xpath("//div[@id='divTrvInfant0']");

		//Add Adult button
		By add_adult = By.xpath("//a[@class='add_adlt']");
		By add_error = By.xpath("//div[@id='errAdult']");

		//Add Child Button
		By add_child = By.xpath("//a[contains(text(),'+Add Child')]");
		By add_cherror = By.xpath("//div[@id='errChild']");

		//Add Infant Button
		By add_infant = By.xpath("//a[contains(text(),'+Add Infant')]");
		By add_inerror = By.xpath("//div[@id='errInfant']");
		
	public ReviewDetails verDetails() throws InterruptedException {
		// TODO Auto-generated method stub
		String up_date = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/span[2]")).getText();
		String up_flight = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/span[2]")).getText();
        String up_airlines = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/span[1]")).getText();
        String down_date = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/span[2]")).getText();
        String down_flight = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/span[2]")).getText(); 
        String down_airlines = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/span[1]")).getText();
        System.out.println(up_date+" "+up_flight+" "+up_airlines);
        System.out.println(down_date+" "+down_flight+" "+down_airlines);
        List<WebElement> ls =  driver.findElements(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[3]/div[1]/div[1]/div[1]/div[3]/div[6]/div[1]/div[*]/label[1]/input[1]"));
        int max= ls.size();
        int a = (int) (Math.random()*(max-1+1)+1);
        System.out.println(a);
        if(a==1)
        	a=2;
        if(a==max)
        	a=max-1;
        System.out.println(a);
        System.out.println(max);
        try {
        	Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[3]/div[1]/div[1]/div[1]/div[3]/div[6]/div[1]/div["+a+"]/label[1]/input[1]")).click();
        
        }
        catch(Exception e) {
        	Thread.sleep(1000);
        	driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[3]/div[1]/div[1]/div[1]/div[3]/div[6]/div[1]/div["+a+"]/label[1]/input[1]")).click();
            	
        }
        int b = (int) (Math.random()*(3-1+1)+1);
        if (b==2)
        	b=1;
        driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/div[1]/div[16]/div[3]/div["+b+"]/label[1]")).click();
       
        Thread.sleep(1000);
        
        driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[10]/div[6]/div[1]/div[4]/div[2]/div[1]/form[1]/div[1]/input[1]")).sendKeys("something@gmail.com");
		return new ReviewDetails(driver);
        
	}
	public boolean verifyEmail(String email) {
		boolean isEmail;
		driver.findElement(e_textField).clear();
		driver.findElement(e_textField).sendKeys(email);
		driver.findElement(e_continue).click();

		if(driver.findElement(e_nextSection).isDisplayed()) {
			isEmail = true;
		}else {
			isEmail = false;
		}
		return isEmail;
	}
	public void enterContactNo(String contact) {
		driver.findElement(co_textField).clear();
		driver.findElement(co_textField).sendKeys(contact);
	}
	public void clickPay() {
		driver.findElement(co_continue).click();
	}

	public boolean checkPaySectionDisplayed() {
		boolean isDisplayed;
		if(driver.findElement(co_pay).isDisplayed()) {
			isDisplayed = true;
		}else {
			isDisplayed = false;
		}
		return isDisplayed;
	}

	public boolean verifyAdultNameText(String type,String firstName) {
		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
			driver.findElement(t_first).clear();
			driver.findElement(t_first).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}else {
			driver.findElement(t_last).clear();
			driver.findElement(t_last).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}

		return !isAlert;
	}

	public boolean verifyChildNameText(String type,String firstName) {
		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
			driver.findElement(ch_first).clear();
			driver.findElement(ch_first).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}else {
			driver.findElement(ch_last).clear();
			driver.findElement(ch_last).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}

		return !isAlert;
	}

	public boolean verifyInfantNameText(String type,String firstName) {
		boolean isAlert = false;
		if(type.equalsIgnoreCase("first")) {
			driver.findElement(in_first).clear();
			driver.findElement(in_first).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}else {
			driver.findElement(in_last).clear();
			driver.findElement(in_last).sendKeys(firstName);
			Actions tab = new Actions(driver);
			Action pressTab = tab.sendKeys(Keys.TAB).build();
			pressTab.perform();
			if(isAlertPresent()) {
				isAlert = true;
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}else {
				isAlert = false;
			}
		}

		return !isAlert;
	}

	/*public void selectAdultTitle(String t) {
			Select title = new Select(driver.findElement(t_title));
			title.selectByVisibleText(t);

		}*/

	public boolean verifyAddBaggageButton() {
		driver.findElement(b_add).click();
		return driver.findElement(b_disp).isDisplayed();

	}

	public boolean verifyAdultTitleDropDown() {
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(t_title);
		titleDropDown.click();
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyChildTitleDropDown() {
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(ch_title);
		titleDropDown.click();
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyInfantTitleDropDown() {
		boolean isSelected= false;
		WebElement titleDropDown = driver.findElement(in_title);
		titleDropDown.click();
		if(titleDropDown.isEnabled()) {
			isSelected = true;
		}
		return isSelected;
	}

	public boolean verifyInfantDOB() {
		boolean f= false;

		if(driver.findElement(in_day).isEnabled()) {
			driver.findElement(in_day).click();

			if(driver.findElement(in_month).isEnabled()) {
				driver.findElement(in_month).click();

				if(driver.findElement(in_year).isEnabled()) {
					driver.findElement(in_year).click();
					f = true;
				}
			}else {
				f = false;
			}
		}else {
			f = false;
		}
		return f;

	}

	public String returnDay(String day) {
		String ret = null;

		if(day.equals("01")) {
			ret="1";
		}else if(day.equals("02")) {
			ret="2";
		}else if(day.equals("03")) {
			ret="3";
		}else if(day.equals("04")) {
			ret="4";
		}else if(day.equals("05")) {
			ret="5";
		}else if(day.equals("06")) {
			ret="6";
		}else if(day.equals("07")) {
			ret="7";
		}else if(day.equals("08")) {
			ret="8";
		}else if(day.equals("09")) {
			ret="9";
		}
		else {
			ret = day;
		}

		return ret;
	}
	
	//Presence of Alert check
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}// try
		catch (Exception e) {
			return false;
		}// catch
	}
	public boolean isStringOnlyAlphabet(String str) 
	{ 
		return ((!str.equals("")) 
				&& (str != null) 
				&& (str.matches("^[a-zA-Z]*$"))); 
	} 

	public boolean isValid(String no) 
	{ 
		// The given argument to compile() method  
		// is regular expression. With the help of  
		// regular expression we can validate mobile 
		// number.  
		// 1) Begins with 0 or 91 
		// 2) Then contains 7 or 8 or 9. 
		// 3) Then contains 9 digits 
		Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 

		// Pattern class contains matcher() method 
		// to find matching between given number  
		// and regular expression 
		Matcher m = p.matcher(no); 
		return (m.find() && m.group().equals(no)); 
	} 

}
