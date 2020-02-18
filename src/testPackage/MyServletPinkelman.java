package testPackage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UtilFile;

@WebServlet("/MyServletPinkelman")
public class MyServletPinkelman extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletPinkelman() {
      super();
   }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) //
         throws ServletException, IOException {
	  int[] arr = new int[10];
	  int min = 0;
	  int minval = 999999999;
      response.setContentType("text/html");
      String filename = "/WEB-INF/numbers.csv";
      List<String> contents = UtilFile.readFile(getServletContext(), filename);
      for (String iLine : contents) {
         System.out.println(iLine);
         System.out.println(arr.length);
         if(Integer.parseInt(iLine) > minval && !(contains(arr, Integer.parseInt(iLine))))
    	 {
    		 System.out.printf("%d replaced by %d\n", arr[min], Integer.parseInt(iLine));
    		 arr[min] = Integer.parseInt(iLine);
    	 }
         minval = arr[min];
         for (int i = 0; i < arr.length; i++)
         {
        	 if( arr[i] < minval )
        	 {
        		 minval = arr[i];
        		 min = i;
        	 }
         }
      }
   PrintWriter out = response.getWriter();
   out.print("Top 10 values are: ");
   for (int val : arr)
   {
	   out.println(val); 
   }
   }
   public static boolean contains(int[] arr, int item) {
	      for (int n : arr) {
	         if (item == n) {
	            return true;
	         }
	      }
	      return false;
	  }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
