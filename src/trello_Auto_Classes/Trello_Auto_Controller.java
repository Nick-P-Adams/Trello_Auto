package trello_Auto_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Trello_Auto_Controller 
{	
	private WebDriver driver;
	
	public Trello_Auto_Controller(final WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void login()
	{
		//Go to url given
		this.driver.get("https://www.trello.com/login");
		
		//Enter Username and Pass then login	
		WebElement userName = this.driver.findElement(By.name("user"));
		userName.sendKeys("Omitted for security reasons");	
		WebElement userPass = this.driver.findElement(By.name("password"));
		userPass.sendKeys("Omitted for security reasons");	
		WebElement button = this.driver.findElement(By.id("login"));
		button.click();
		
		//find the correct board		
		button = this.driver.findElement(By.name("board"));
		button.click();	
		button = this.driver.findElement(By.name("search-boards"));
		button.sendKeys("State of Grow Rooms" + "\r\n");
	}
	
	public Trello_Auto_Card [] setupCards() throws FileNotFoundException
	{
		int boardIndex = 0, index, index2, label, member, i = 0;
		String sDate = "", line = "";
		Trello_Auto_Card [] cards = new Trello_Auto_Card[11];
		File inf = new File("inOutFile.txt");
		Scanner fb = new Scanner(inf);
		
		while(fb.hasNext())
		{
			line = fb.nextLine();
			index = line.indexOf(" ");
			index2 = line.indexOf(" ", index + 1);
			
			boardIndex = Integer.parseInt(line.substring(0, index).trim());
			member = Integer.parseInt(line.substring(index + 1, index2).trim());
			index = line.indexOf(" ", index2 + 1);
			label = Integer.parseInt(line.substring(index2 + 1, index).trim());
			sDate = line.substring(index).trim();
			
			cards [i] = new Trello_Auto_Card(boardIndex, i, member, label, sDate);
			i++;
		}
		
		
		fb.close();
		
		return cards;
	}
	
	public void save(final Trello_Auto_Card [] cards) throws IOException
	{
		FileWriter out = new FileWriter("inOutFile.txt");
		
		for(int i = 0; i < cards.length; i ++)
		{
			out.write(cards[i].getBoardIndex() + " " + cards[i].getMemberSet() + " " + cards[i].getLabelSet() + " " + cards[i].getStartDate() + "\r\n");
		}
		
		out.close();
	}
	
	public void moveAuto(final Trello_Auto_Card card)
	{	
		for(int i = 0; i <= 13; i ++)
		{
			int day = card.getCurrentDateParts(0), month = card.getCurrentDateParts(1), year = card.getCurrentDateParts(2),
				eDay = card.getEndDateParts(0), eMonth = card.getEndDateParts(1), eYear = card.getEndDateParts(2);
			
			if(year > eYear)
			{	
				card.incrementBoardIndex();
				
				card.setStartDate(card.getEndDate());
				card.generateEndDate();
			}
			else if (year == eYear)
			{
				if(month > eMonth)
				{
					card.incrementBoardIndex();
					
					card.setStartDate(card.getEndDate());
					card.generateEndDate();
				}
				else if(month == eMonth)
				{
					if(day >= eDay)
					{	
						card.incrementBoardIndex();
						
						card.setStartDate(card.getEndDate());
						card.generateEndDate();
					}
				}
			}
		}
		
		if(card.getCurrentBoardIndex() - card.getBoardIndex() != 0)
		{
			this.updateCard(card);
		}
	}
	
	public void updateCard(final Trello_Auto_Card card)
	{
		if(card.getBoardIndex() <= 13)
		{
			WebElement button = this.driver.findElement(By.xpath(card.getAddress()));
			button.click();
			
			if(card.getBoardIndex() >= 4 && card.getLabelSet() == 0)
			{	
				this.setCardLabel(card);
				card.setLabelSet(1);
			}
			
			if(card.getBoardIndex() < 4 && card.getLabelSet() == 1)
			{
				this.setCardLabel(card);
				card.setLabelSet(0);
			}
			
			if(card.getBoardIndex() >= 11 && card.getMemberSet() == 0)
			{
				this.addCardMember(card);
			}
			
			if(card.getBoardIndex() < 11 && card.getMemberSet() == 1)
			{
				this.removeCardMember(card);
			}
			
			if(card.getBoardIndex() >= 11)
			{
				this.setDueDate(card);
			}
			
			this.moveCard(card);
		}
	}
	
	public void moveCard(Trello_Auto_Card card)
	{
		WebElement button = this.driver.findElement(By.xpath("//span[text()=\"Move\"]"));
		button.click();
		button = this.driver.findElement(By.className("js-select-list"));
		button.click();
		button = this.driver.findElement(By.xpath(card.getBoardAddress()));
		button.click();
		button = this.driver.findElement(By.xpath("//input[@value =\"Move\"]"));
		button.click();
		button = this.driver.findElement(By.xpath("//a[@class=\"icon-md icon-close dialog-close-button js-close-window\"]"));
		button.click();
	}
	
	public void setDueDate(final Trello_Auto_Card card)
	{
		WebElement button = this.driver.findElement(By.xpath("//span[text()=\"Due Date\"]"));
		button.click();
		button = this.driver.findElement(By.xpath("//input[@class=\"datepicker-select-input js-dpicker-date-input js-autofocus\"]"));
		button.clear();
		button.sendKeys(card.getEndDate());
		button = this.driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[4]/div/div[2]/div/div/form/div[4]/input"));
		button.click();
	}
	
	public void addCardMember(final Trello_Auto_Card card)
	{
		WebElement button = this.driver.findElement(By.xpath("//span[text()=\"Join\"]"));
		button.click();
		
		card.setMemberSet(1);
	}
	
	public void removeCardMember(final Trello_Auto_Card card)
	{
		WebElement button = this.driver.findElement(By.xpath("//span[text()=\"Members\"]"));
		button.click();
		button = this.driver.findElement(By.xpath("//span[@class=\"username\"]"));
		button.click();
		button = this.driver.findElement(By.xpath("//a[@class=\"pop-over-header-close-btn icon-sm icon-close\"]"));
		button.click();
		
		card.setMemberSet(0);
	}
	
	public void setCardLabel(final Trello_Auto_Card card)
	{
		WebElement button = this.driver.findElement(By.xpath("//span[text()=\"Labels\"]"));
		button.click();
		button = this.driver.findElement(By.xpath("//span[@data-idlabel=\"5c4a43a191d0c2ddc5c3e2c5\"]"));
		button.click();
		button = this.driver.findElement(By.xpath("//span[@data-idlabel=\"5c4a43a191d0c2ddc5c3e2c7\"]"));
		button.click();
		button = this.driver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[4]/div/div[1]/a"));
		button.click();
	}
}


	

