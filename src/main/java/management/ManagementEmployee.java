package management;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import dao.Operation2;
import data.Employee;

public class ManagementEmployee {
	Operation2<Employee> opObj;

	public ManagementEmployee() throws Exception {
		opObj = new Operation2<>();
	}

	// Returns true if matches
	public boolean checkID(String name) {
		return Pattern.matches("[0-9]+", name);
	}

	// Returns true if matches
	public boolean checkName(String name) {
		return Pattern.matches("[A-Za-z ]*", name);
	}

	public String create(String empId, String empName, String team) {

		try {
			while (checkID(empId) != true) {
				return "id_error";
			}
			if (opObj.checkIdInDB("Employee", Integer.parseInt(empId)) == true) {
				return "id_exists";
			}
			Employee tempObj = new Employee.EmployeeBuilder(Integer.parseInt(empId), empName,
					Calendar.getInstance().getTimeInMillis(), Calendar.getInstance().getTimeInMillis()).setTeam(team)
							.build();
			try {
				opObj.insertInto(tempObj);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public Employee read(String empId) {
		Employee tempObj = new Employee.EmployeeBuilder(Integer.parseInt(empId), null, 0L, 0L).build();
		try {
			tempObj = opObj.read(tempObj, Integer.parseInt(empId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempObj;
	}

	public String update(String empId, String name, String team) {
		try {
			Employee tempObj = new Employee.EmployeeBuilder(Integer.parseInt(empId), null, 0L, 0L).build();
			int id = Integer.parseInt(empId);
			tempObj = opObj.read(tempObj, id);
			String oldName = tempObj.getName();
			if (name.isEmpty()) {
				name = oldName;
			}
			String oldTeam = tempObj.getTeam();
			if (team.isEmpty()) {
				team = oldTeam;
			}
			Employee empObj2 = new Employee.EmployeeBuilder(Integer.parseInt(empId), name, tempObj.getTimeCreate(),
					Calendar.getInstance().getTimeInMillis()).setTeam(team).build();
			try {
				opObj.update(empObj2, Integer.parseInt(empId));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public void delete(String empId) {
		try {
			Employee emp = new Employee.EmployeeBuilder(Integer.parseInt(empId), null, 0L, 0L).build();
			opObj.delete(emp, Integer.parseInt(empId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		try {
			Employee emp = new Employee.EmployeeBuilder(0, null, 0L, 0L).build();
			opObj.deleteAll(emp);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> readAll() {
		Employee tempObj = new Employee.EmployeeBuilder(0, null, 0L, 0L).build();
		List<Employee> resultList = opObj.readAll(tempObj);
		return resultList;
	}

	public void closeConnection() {
		opObj.connectClose();
	}

}
