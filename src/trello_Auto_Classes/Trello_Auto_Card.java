package trello_Auto_Classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trello_Auto_Card 
{
	private String address = "", sDate = "", eDate = "", currentDate = "", currentTime = "";
	private String [] boards = new String[14];
	private String [] cards = new String[11];
	private int boardIndex, cardIndex, cBoardIndex, memberSet, labelSet;
	
	public Trello_Auto_Card(final int boardIndex, final int cardIndex, final int member, final int label, final String sDate)
	{
		this.boardIndex = boardIndex;
		this.cBoardIndex = boardIndex;
		this.cardIndex = cardIndex;
		this.memberSet = member;
		this.labelSet = label;
		this.sDate = sDate;
		this.generateEndDate();
		
		int x = 1;
		for(int i = 0; i < this.boards.length; i ++)
		{
			if(i >= 4)
			{
				this.boards[i] = "*//option[text()=\"Flower Week " + x + "\"]";
				x++;
			}
			else
			{
				this.boards[i] = "*//option[text()=\"Veg Week " + (i + 1) + "\"]";
			}
		}
		
		x = 1;
		for(int i = 0; i < this.cards.length; i++)
		{
			if(i >= 8)
			{
				this.cards[i] = "//span[text()=\"B2 F" + x + "\"]";
				x++;
			}
			else
			{
				this.cards[i] = "//span[text()=\"B1 F" + (i + 1) + "\"]";
			}
			
		} 
	}
	
	public String getAddress()
	{
		this.address = cards[cardIndex];
		return this.address;
	}
	
	public String getBoardAddress()
	{
		this.address = boards[this.boardIndex];
		return this.address;
	}
	
	public void setMemberSet(final int num)
	{
		this.memberSet = num;
	}
	
	public int getMemberSet()
	{
		return this.memberSet;
	}
	
	public void setLabelSet(final int num)
	{
		this.labelSet = num;
	}
	
	public int getLabelSet()
	{
		return this.labelSet;
	}
	
	public int getBoardIndex()
	{
		return this.boardIndex;
	}
	
	public int getCurrentBoardIndex()
	{
		return this.cBoardIndex;
	}
	
	public void incrementBoardIndex()
	{
		
		this.boardIndex += 1;
	}
	
	public String getStartDate()
	{
		return this.sDate;
	}
	
	
	public String getEndDate()
	{
		return this.eDate;
	}
	
	public void setStartDate(final String date)
	{
		this.sDate = date;
	}
	
	public String getDate()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		Date date = new Date();
		this.currentDate = formatter.format(date);
		
		return this.currentDate;
	}
	
	public String getTime()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
		Date date = new Date();
		this.currentTime = formatter.format(date);
		
		return this.currentTime;
	}

	public int getCurrentDateParts(final int part)
	{
		int day, month, year, index, index2;
		String curDate = this.getDate();
		
		index = curDate.indexOf("/");
		month = Integer.parseInt(curDate.substring(0, index));
		
		index2 = curDate.indexOf("/", index + 1);
		day = Integer.parseInt(curDate.substring(index + 1, index2));
		
		year = Integer.parseInt(curDate.substring(index2 + 1, curDate.length()));
		
		if(part == 0)
		{
			return day;
		}
		else if(part == 1)
		{
			return month;
		}
		else
		{
			return year;
		}	
	}
	
	public int getStartDateParts(final int part)
	{
		int day, month, year, index, index2;
		index = this.sDate.indexOf("/");
		month = Integer.parseInt(this.sDate.substring(0, index));
		
		index2 = this.sDate.indexOf("/", index + 1);
		day = Integer.parseInt(this.sDate.substring(index + 1, index2));
		
		year = Integer.parseInt(this.sDate.substring(index2 + 1, sDate.length()));
		
		if(part == 0)
		{
			return day;
		}
		else if(part == 1)
		{
			return month;
		}
		else
		{
			return year;
		}	
	}
	
	public int getEndDateParts(final int part)
	{
		int day, month, year, index, index2;
		index = this.eDate.indexOf("/");
		month = Integer.parseInt(this.eDate.substring(0, index));
		
		index2 = this.eDate.indexOf("/", index + 1);
		day = Integer.parseInt(this.eDate.substring(index + 1, index2));
		
		year = Integer.parseInt(this.eDate.substring(index2 + 1, eDate.length()));
		
		if(part == 0)
		{
			return day;
		}
		else if(part == 1)
		{
			return month;
		}
		else
		{
			return year;
		}	
	}
	
	public void generateEndDate()
	{
		int day, month, year, index, index2, count;
		
		index = this.sDate.indexOf("/");
		month = Integer.parseInt(this.sDate.substring(0, index));
		
		index2 = this.sDate.indexOf("/", index + 1);
		day = Integer.parseInt(this.sDate.substring(index + 1, index2));
		
		year = Integer.parseInt(this.sDate.substring(index2 + 1, sDate.length()));
		
		switch(month)
		{
		
		case 1:
			day = day + 7;

			if(day > 31)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 30; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 2:
			day = day + 7;
			
			if(day > 28)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 27; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 3:
			day = day + 7;
			
			if(day > 31)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 30; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 4:
			day = day + 7;
			
			if(day > 30)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 29; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 5:
			day = day + 7;
			
			if(day > 31)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 30; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 6:
			day = day + 7;
			
			if(day > 30)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 29; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 7:
			day = day + 7;
			
			if(day > 31)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 30; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 8:
			day = day + 7;
			
			if(day > 31)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 30; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 9:
			day = day + 7;
			
			if(day > 30)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 29; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 10:
			day = day + 7;
			
			if(day > 31)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 30; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 11:
			day = day + 7;
			
			if(day > 30)
			{
				count = day;
				month = month + 1;
				
				for(int i = 0; count != 29; i++, count--)
				{
					day = i;
				}
			}
			
			break;
			
		case 12: 
			day = day + 7;
			
			if(day > 31)
			{
				count = day;
				month = 1;
				year = year + 1;
				
				for(int i = 0; count != 30; i++, count--)
				{
					day = i;
				}
			}
		
			break;
			
		}
		
		this.eDate = String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + year;
	}
}
