package com.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import data.Customer;
import data.Employee;
import html.HtmlUtility;
import management.ManagementCustomer;
import management.ManagementEmployee;

/**
 * Employee Management System is made using Servlets Customer Management System
 * is made using JSP with MVC and Servlets
 * 
 * @author Jitendra Asawa Created On: 12 Feb, 2019
 */
@WebServlet("/System")
public class System extends HttpServlet {
	private static final long serialVersionUID = -492867964522625890L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			StringBuilder page = new StringBuilder(HtmlUtility.htmlBody("EMS") + HtmlUtility.homeButton());
			String systemName = request.getParameter("ms");
			if (systemName.equals("ems")) {
				page.append("<div style=\"width:440px\">" + "<div style=\"float: left; width: 130px\">"
						+ "<form action=\"CreateEmployee\" method=\"get\"><input type=\"submit\" value=\"Create Record\"></form></div>\n");
				page.append("<br /><p>EMPLOYEE MANAGEMENT SYSTEM</p>");
				try {
					ManagementEmployee manEmpObj = new ManagementEmployee();
					StringBuilder resultString = new StringBuilder("");
					List<Employee> resultList = manEmpObj.readAll();
					int rowCount = resultList.size();
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					for (int row = 0; row < rowCount; row++) {
						Employee tempObj = resultList.get(row);
						String formatCreate = format.format(tempObj.getTimeCreate());
						String formatUpdate = format.format(tempObj.getTimeUpdate());
						String s = "<tr><td>" + tempObj.getId() + "</td><td>" + tempObj.getName() + "</td><td>"
								+ tempObj.getTeam() + "</td><td>" + formatCreate + "</td><td>" + formatUpdate
								+ "</td><td><form action=\"UpdateDelete\" method=\"get\"><select name=\"choice\"><option value=\""
								+ Integer.toString(tempObj.getId()) + "update\">Update</option><option value=\""
								+ Integer.toString(tempObj.getId())
								+ "delete\">Delete</option> </select><input type=\"submit\" value=\"Submit\"></form></td></tr>";
						resultString.append(s);
					}

					page.append(
							"<table style=\"width:115%;text-align:center;\"><tr><th>ID</th><th>NAME</th><th>TEAM</th><th>Create Time</th><th>Update Time</th><th>OPERATION</th></tr>");
					page.append(resultString.toString() + "</table>");
					manEmpObj.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
				page.append(HtmlUtility.htmlBodyClose());
				out.println(page.toString());
			} else if (systemName.equals("cms")) {
				ManagementCustomer manCustObj = new ManagementCustomer();
				List<Customer> custList = manCustObj.readAll();
				manCustObj.closeConnection();
				request.setAttribute("list", (List<Customer>) custList);
				RequestDispatcher rd = request.getRequestDispatcher("customer.jsp");
				rd.forward(request, response);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
