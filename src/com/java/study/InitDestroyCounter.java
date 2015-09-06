package com.java.study;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class InitDestroyCounter
 */
@WebServlet(description = "The implements method destroy with persistence", urlPatterns = { "/InitDestroyCounter.do" })
public class InitDestroyCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	int count;
	
	public void init(ServletConfig config) throws ServletException {
		// sempre chamar super.init(config) primeiro (servlet mantra #1)
		super.init(config);
		
		// tenta carregar o contador inicial salvo
		try {
			FileReader fileReader = new FileReader("InitDestroyCounter.initial");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String initial = bufferedReader.readLine();
			count = Integer.parseInt(initial);
			bufferedReader.close();
			return;
		} 
		catch (FileNotFoundException ignored) { } // no saved state
		catch (IOException ignored) { } // problem during read
		catch (NumberFormatException ignored) { } // corrupt saved state
		
		// no luck with the saved state, check for an init parameter
		String initial = getInitParameter("initial");
		try {
			count = Integer.parseInt(initial);
			return;
		} 
		catch (NumberFormatException ignored) { } // null or non-integer value
		
		// default to an initial count of "0"
		count = 0;
		
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitDestroyCounter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		count++;
		out.println("Since the beginning, this servlet has been accessed " +
				count + " times.");
	}
	
	public void destroy() {
		saveState();
		
	}
	
	public void saveState() {
		// try to save the accumulated count
		try {
			FileWriter fileWriter = new FileWriter("InitDestroyCounter.initial");
			String initial = Integer.toString(count);
			fileWriter.write(initial, 0, initial.length());
			fileWriter.close();
			return;
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
