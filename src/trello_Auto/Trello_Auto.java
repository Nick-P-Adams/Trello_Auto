package trello_Auto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import trello_Auto_Classes.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Trello_Auto 
{

	public static void main(String[] args) throws FileNotFoundException 
	{	
		Timer timer = new Timer ();
		TimerTask t = new TimerTask () 
		{
		    @Override
		    public void run() 
		    {	
				//Setting the driver executable
				System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
				//Initializing the Chrome driver
				WebDriver driver = new ChromeDriver();
				//Applied wait time 
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//Maximize Chrome window
				driver.manage().window().maximize();
				
				Trello_Auto_Controller glw = new Trello_Auto_Controller(driver);
				
				glw.login();
				
				Trello_Auto_Card[] cards;
				
				try 
				{
					cards = glw.setupCards();
					glw.moveAuto(cards[0]);
					glw.moveAuto(cards[1]);
					glw.moveAuto(cards[2]);
					glw.moveAuto(cards[3]);
					glw.moveAuto(cards[4]);
					glw.moveAuto(cards[5]);
					glw.moveAuto(cards[6]);
					glw.moveAuto(cards[7]);
					glw.moveAuto(cards[8]);
					glw.moveAuto(cards[9]);
					glw.moveAuto(cards[10]);
					glw.save(cards);
				
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Close Driver and page
				driver.close();
		    }
		};

		timer.schedule(t, 0l, (1000*60*60*24)/2);
	}

}
