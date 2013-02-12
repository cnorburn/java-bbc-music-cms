package bbc.forge.music.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date timestamp(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
		return new Date();
	}
	
	public static Date timestamp(String date){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date _date=new Date();
		try {
			_date= (Date) dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return _date;
	}

}
