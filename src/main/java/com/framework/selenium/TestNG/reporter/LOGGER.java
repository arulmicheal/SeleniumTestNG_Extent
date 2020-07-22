package com.framework.selenium.TestNG.reporter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.framework.selenium.TestNG.main.General;


public class LOGGER {
	static Logger logger = Logger.getLogger("TestNgLog");  
    static FileHandler fh;  
	public synchronized static void info(String strMessage)
	{
	    logger.log(Level.INFO,strMessage);  
	}
	public synchronized static void warn(String strMessage)
	{
	    logger.log(Level.WARNING, strMessage);
	}
	public synchronized static void error(Exception ex)
	{
	    logger.log(Level.SEVERE,ex.getMessage(),ex);  
	}
	public static void initializeLog(String strFilePath) throws Exception {
		General.createFilePath(strFilePath);
		fh = new FileHandler(strFilePath);  
        logger.addHandler(fh);
        fh.setFormatter(new SimpleFormatter(){
        	public synchronized String format(LogRecord lRecord)
        	{
        		StringBuffer strBuffer= new StringBuffer();
        		strBuffer.append(String.format("[%1$tF %1$tT %2$s]",new Date(lRecord.getMillis()),lRecord.getLevel()));
        		
        		Throwable throwable = lRecord.getThrown();
			      if (throwable != null)
			        {
			    	  StringWriter strWriter = new StringWriter();
			    	  strBuffer.append(throwable.getStackTrace()[1].getClassName());
			    	  strBuffer.append(": ");
			    	  strBuffer.append("\n");
			          throwable.printStackTrace(new PrintWriter(strWriter, true));
			          strBuffer.append(strWriter);
			       }else
			       {
			    	   //strBuffer.append(lRecord.getClass().getCanonicalName());
			    	   strBuffer.append(lRecord.getMessage());
			       }
			      strBuffer.append("\n");
        		  return strBuffer.toString();
        	}
        });
        logger.setUseParentHandlers(false);
	}
	public static void closeLog() throws Exception {
		fh.close();
	}
}
