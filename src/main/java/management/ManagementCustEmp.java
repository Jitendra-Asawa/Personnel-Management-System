package management;

import java.util.ArrayList;
import java.util.List;

import dao.Operation2;
import data.CustEmp;
import data.Customer;
import data.Employee;

public class ManagementCustEmp {
	Operation2<CustEmp> opObj;

	public ManagementCustEmp() {
		opObj = new Operation2<>();
	}

	public List<CustEmp> readAll() {
		List<CustEmp> listToAdd = new ArrayList<>();
		try {
			ManagementEmployee manEmpObj = new ManagementEmployee();
			List<Employee> empList = manEmpObj.readAll();

			ManagementCustomer manCustObj = new ManagementCustomer();
			List<Customer> custList = manCustObj.readAll();
			for (int i = 0; i < empList.size(); i++) {
				CustEmp cust = new CustEmp.CustEmpBuilder(empList.get(i).getId()).setName(empList.get(i).getName()).setAmount(0).setTeam(empList.get(i).getTeam())
						.setTimeCreate(empList.get(i).getTimeCreate()).setTimeUpdate(empList.get(i).getTimeUpdate())
						.setClassName("Employee").build();
				listToAdd.add(cust);
			}

			for (int i = 0; i < custList.size(); i++) {
				CustEmp cust = new CustEmp.CustEmpBuilder(custList.get(i).getId()).setName(custList.get(i).getName()).setAmount(custList.get(i).getAmount())
						.setClassName("Customer").setTimeCreate(custList.get(i).getTimeCreate())
						.setTimeUpdate(custList.get(i).getTimeUpdate()).setTeam("-").build();
				listToAdd.add(cust);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listToAdd;
	}

	public List<List<String>> viewHistory() {
		return opObj.readAllCustEmp();
	}

	public void closeConnection() {
		opObj.connectClose();
	}
}
