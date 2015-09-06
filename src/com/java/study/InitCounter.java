package com.java.study;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitCounter
 */
@WebServlet(description = "Test Init method of servlet", urlPatterns = { "/InitCounter.do" })
public class InitCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	int count;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String initial = config.getInitParameter("initial");
		try {
			count = Integer.parseInt(initial);
		} catch (NumberFormatException e) {
			count = 0;
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitCounter() {
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
		out.println("Since loading (and with a possible initialization");
		out.println("parameter figured in), this servlet has been accessed");
		out.println(count + " times.");
	}

}
