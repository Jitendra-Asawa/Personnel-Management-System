package com.pms;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import management.ManagementCustomer;

/**
 * Servlet implementation class Create
 */
@WebServlet("/CreateCustomer")
public class CreateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1071011271058720840L;
	private PrintWriter out;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = (String) request.getParameter("name");
		String amount = (String) request.getParameter("amount");
		try {
			ManagementCustomer manCustObj = new ManagementCustomer();
			out = response.getWriter();
			String result = "";
			if (amount.isEmpty()) {
				result = manCustObj.create(Integer.toString(id), name, 0);
			} else {
				result = manCustObj.create(Integer.toString(id), name, Integer.parseInt(amount));
			}
			if (result.contentEquals("success")) {
				response.sendRedirect("redirectToSystem.jsp");
			} else {
				response.sendRedirect("errorId.jsp");
			}
			out.close();
			manCustObj.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
