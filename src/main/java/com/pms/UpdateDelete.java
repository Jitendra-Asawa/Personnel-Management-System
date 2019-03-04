package com.pms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Employee;
import html.HtmlUtility;
import management.ManagementEmployee;

@WebServlet("/UpdateDelete")
public class UpdateDelete extends HttpServlet {
	private static final long serialVersionUID = 6150736978053529980L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
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
				ManagementEmployee manEmpObj = new ManagementEmployee();
				Employee empObj = manEmpObj.read(id.toString());
				String update = updatePage(id.toString(), empObj);
				out.println(update);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (select.contains("delete")) {
			try {
				ManagementEmployee manEmpObj = new ManagementEmployee();
				manEmpObj.delete(id.toString());
				String page = HtmlUtility.htmlBody("Delete Record")
						+ "<form style=\"text-align:left;\" action=\"System\" method=\"get\"><button type=\"submit\" name=\"ms\" value=\"ems\" onclick=\"System\"><-Back</button></form>"
						+ "<h1> Record Deleted Successfully</h1>" + HtmlUtility.htmlBodyClose();
				out.println(page);
				manEmpObj.closeConnection();
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
		PrintWriter out = response.getWriter();
		StringBuilder page = new StringBuilder(HtmlUtility.htmlBody("Update Record Status")
				+ " <form style=\"text-align:left;\" action=\"System\" method=\"get\"><button type=\"submit\" name=\"ms\" value=\"ems\" onclick=\"System\"><-Back</button></form> ");
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String team = request.getParameter("team");
			ManagementEmployee manEmpObj = new ManagementEmployee();
			String result = manEmpObj.update(id.toString(), name, team);
			if (result.equals("name_error")) {
				page.append("<h1>Invalid NAME</h1><h1>ENTER ALPHABETS AND SPACE ONLY</h1>");
			} else if (result.equals("team_error")) {
				page.append("<h1>Invalid TEAM</h1><h1>ENTER ALPHABETS AND SPACE ONLY</h1>");
			} else {
				page.append("<h1>Record Updated Successfully</h1>");
			}
			page.append(HtmlUtility.htmlBodyClose());
			out.println(page);
			out.close();
			manEmpObj.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String updatePage(String id, Employee empObj) {
		String page = HtmlUtility.htmlBody("Update Record") + HtmlUtility.backButton()
				+ "<p>Update Employee Details</p><form method =\"post\">\n"
				+ "		ID: <input type=\"number\" name=\"id\" value=\"" + id + "\" readonly>\n"
				+ "		Name: <input type=\"text\" name=\"name\" value=\"" + empObj.getName() + "\" required > Team:\n"
				+ "		<input type=\"text\" name=\"team\" value=\"" + empObj.getTeam() + "\" > \n"
				+ "		<input type=\"submit\" value=\"Submit Employee Detail\" onclick=\"UpdateEmployee\">\n"
				+ "	</form>" + HtmlUtility.htmlBodyClose();
		return page;
	}
}
