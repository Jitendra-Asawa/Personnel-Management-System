package com.pms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.ManagementCustomer;

/**
 * Servlet implementation class DeleteAllCustomer
 */
public class DeleteAllCustomer extends HttpServlet {
	private static final long serialVersionUID = -7290769479399442094L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option= request.getParameter("button1");
		if(option.equals("yes")) {
		// TODO Auto-generated method stub
		try {
			ManagementCustomer manCustObj = new ManagementCustomer();
			manCustObj.deleteAll();
			manCustObj.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			
		}
		response.sendRedirect("redirectToSystem.jsp");
	}

}
