package org.bukkit.craftbukkit.libs.org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public interface DateParser {
  Date parse(String paramString) throws ParseException;
  
  Date parse(String paramString, ParsePosition paramParsePosition);
  
  boolean parse(String paramString, ParsePosition paramParsePosition, Calendar paramCalendar);
  
  String getPattern();
  
  TimeZone getTimeZone();
  
  Locale getLocale();
  
  Object parseObject(String paramString) throws ParseException;
  
  Object parseObject(String paramString, ParsePosition paramParsePosition);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\apache\commons\lang3\time\DateParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */