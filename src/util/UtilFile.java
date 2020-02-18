package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;

public class UtilFile {
   public static List<String> readFile(ServletContext context, String filename) {
      List<String> contents = new ArrayList<String>();
      try {
         InputStream is = context.getResourceAsStream(filename);
         if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text;
            while ((text = reader.readLine()) != null) {
               contents.add(text);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return contents;
   }

   public static List<String> readFile(String filePath) {
      List<String> contents = new ArrayList<String>();
      File file = new File(filePath);
      Scanner scanner = null;
      try {
         scanner = new Scanner(file);
         while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            contents.add(line);
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } finally {
         if (scanner != null)
            scanner.close();
      }
      return contents;
   }
}/*package coreservlets;

import javax.servlet.http.*;

/** Some simple time savers. Static methods. 

public class ServletUtilities {
  public static String headWithTitle(String title) {
    return("<!DOCTYPE html>\n" +
           "<html>\n" +
           "<head><title>" + title + "</title></head>\n");
  }

  /** Read a parameter with the specified name, convert it
   *  to an int, and return it. Return the designated default
   *  value if the parameter doesn't exist or if it is an
   *  illegal integer format.
   
  
  public static int getIntParameter(HttpServletRequest request,
                                    String paramName,
                                    int defaultValue) {
    String paramString = request.getParameter(paramName);
    int paramValue;
    try {
      paramValue = Integer.parseInt(paramString);
    } catch(Exception nfe) { // null or bad format
      paramValue = defaultValue;
    }
    return(paramValue);
  }

  /** Reads param and converts to double. Default if problem. 
  
  public static double getDoubleParameter(HttpServletRequest request,
                                          String paramName,
                                          double defaultValue) {
    String paramString = request.getParameter(paramName);
    double paramValue;
    try {
      paramValue = Double.parseDouble(paramString);
    } catch(Exception nfe) { // null or bad format
      paramValue = defaultValue;
    }
    return(paramValue);
  }

  /** Replaces characters that have special HTML meanings
   *  with their corresponding HTML character entities.
   *  Specifically, given a string, this method replaces all 
   *  occurrences of  
   *  {@literal
   *  '<' with '&lt;', all occurrences of '>' with
   *  '&gt;', and (to handle cases that occur inside attribute
   *  values), all occurrences of double quotes with
   *  '&quot;' and all occurrences of '&' with '&amp;'.
   *  Without such filtering, an arbitrary string
   *  could not safely be inserted in a Web page.
   *  }
   

  public static String filter(String input) {
    if (!hasSpecialChars(input)) {
      return(input);
    }
    StringBuilder filtered = new StringBuilder(input.length());
    char c;
    for(int i=0; i<input.length(); i++) {
      c = input.charAt(i);
      switch(c) {
        case '<': filtered.append("&lt;"); break;
        case '>': filtered.append("&gt;"); break;
        case '"': filtered.append("&quot;"); break;
        case '&': filtered.append("&amp;"); break;
        default: filtered.append(c);
      }
    }
    return(filtered.toString());
  }

  private static boolean hasSpecialChars(String input) {
    boolean flag = false;
    if ((input != null) && (input.length() > 0)) {
      char c;
      for(int i=0; i<input.length(); i++) {
        c = input.charAt(i);
        switch(c) {
          case '<': flag = true; break;
          case '>': flag = true; break;
          case '"': flag = true; break;
          case '&': flag = true; break;
        }
      }
    }
    return(flag);
  }
  
  private ServletUtilities() {} // Uninstantiatable class
}

*/