package com.litt.micro.util.Timetable;

import java.util.Calendar;

//上课时间表
public class Timetable {
	
	
	public static String  startTime(String time)
	{
		
		Calendar cal=Calendar.getInstance();
		if(cal.get(Calendar.MONTH)+1<10)
		{
			switch(time)
			{
			  case "1": return "08:00";

			  case  "2": return "10:10";

			  case  "3": return "14:30";

			  case   "4":return "16:40";
          
			  default :return "00:00"; 
			
			}
		}
		else
		{

			switch(time)
			{
			  case "1": return "08:00";

			  case  "2": return "10:10";

			  case  "3": return "14:00";

			  case   "4":return "16:10";
          
			  default :return "00:00"; 
			
			}
		}
	}
		
		public static String endTime(String time)
		{

			
			Calendar cal=Calendar.getInstance();
			if(cal.get(Calendar.MONTH)+1<10)
			{
			
				switch(time)
				{
				  case "1": return "10:00";

				  case  "2": return "12:00";

				  case  "3": return "16:20";

				  case  "4": return "18:30";

				  default : return "00:00";
				  
				}
			}
			
			else
			{
				switch(time)
				{
				  case "1": return "10:00";

				  case  "2": return "12:00";

				  case  "3": return "15:50";

				  case  "4": return "18:00";

				  default: return "00:00";
				  
				}
				
			}
		}
	

}
