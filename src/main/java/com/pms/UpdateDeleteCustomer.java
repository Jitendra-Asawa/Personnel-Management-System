package com.pms;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Customer;
import management.ManagementCustomer;

@WebServlet("/UpdateDeleteCustomer")
public class UpdateDeleteCustomer extends HttpServlet {

	private static final long serialVersionUID = -4278066664411494038L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String select = request.getParameter("choice");
		StringBuffer id = new StringBuffer("");
		for (int i = 0; i < select.length(); i++) {
			if (Character.isDigit(select.charAt(i))) {
				id.append(select.charAt(i));
			} else {
				break;
			}
		}
		if (select.contains("update")) {
			try {
				ManagementCustomer manCustObj = new ManagementCustomer();
				Customer custObj = manCustObj.read(id.toString());
				request.setAttribute("custObj", custObj);
				RequestDispatcher rs = request.getRequestDispatcher("updateCustomer.jsp");
				rs.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (select.contains("delete")) {
			try {
				ManagementCustomer manCustObj = new ManagementCustomer();
				manCustObj.delete(id.toString());
				manCustObj.closeConnection();
				response.sendRedirect("redirectToSystem.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String amount = request.getParameter("amount");
			if (name.isEmpty()) {
				name = "";
			}
			if (amount.isEmpty()) {
				amount = "-1";
			}
			ManagementCustomer manCustObj = new ManagementCustomer();
			manCustObj.update(id.toString(), name, Integer.parseInt(amount));
			manCustObj.closeConnection();
			response.sendRedirect("redirectToSystem.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
