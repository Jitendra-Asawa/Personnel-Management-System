package com.pms;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import html.HtmlUtility;
import management.ManagementEmployee;

/**
 * Servlet implementation class Create
 */
@WebServlet("/CreateEmployee")
public class CreateEmployee extends HttpServlet {
	private static final long serialVersionUID = -5076086663262287675L;
	private PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter()
				.println(HtmlUtility.htmlBody("Create Employee") + HtmlUtility.backButton()
						+ "<p>Enter Employee Details</p><form action=\"CreateEmployee\" method =\"post\">\n"
						+ "		ID: <input type=\"number\" name=\"id\" pattern=\"[0-9]+\" required>\n"
						+ "		Name: <input type=\"text\" name=\"name\" pattern=\"[A-Za-z ]+\" required> Team:\n"
						+ "		<input type=\"text\" name=\"team\" pattern=\"[A-Za-z ]+\" > \n"
						+ "		<input type=\"submit\" value=\"Submit Employee Detail\">\n" + "	</form>"
						+ HtmlUtility.htmlBodyClose());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		insert(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = (String) request.getParameter("name");
		String team = (String) request.getParameter("team");
		StringBuilder page = new StringBuilder(HtmlUtility.htmlBody("Record Status")
				+ "<form style=\"text-align:left; padding-left:10px\" action=\"System\" method=\"get\"><button type=\"submit\" name=\"ms\" value=\"ems\" onclick=\"System\"><-Back</button></form>");
		try {
			ManagementEmployee manEmpObj = new ManagementEmployee();
			out = response.getWriter();
			String result = manEmpObj.create(Integer.toString(id), name, team);

			if (result.equals("id_error")) {
				page.append("<h1>Invalid ID</h1><h1>ENTER POSITIVE INTEGER ID</h1>");
			} else if (result.equals("name_error")) {
				page.append("<h1>Invalid NAME</h1><h1>ENTER ALPHABETS AND SPACE ONLY</h1>");
			} else if (result.equals("team_error")) {
				page.append("<h1>Invalid TEAM NAME</h1><h1>ENTER ALPHABETS AND SPACE ONLY</h1>");
			} else if (result.equals("id_exists")) {
				page.append("<h1>Record for this ID Already Exists</h1>");
			} else {
				page.append("<h1>Record Created Successfully</h1>");
			}
			page.append(HtmlUtility.htmlBodyClose());
			out.println(page);
			out.close();
			manEmpObj.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
