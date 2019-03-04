package management;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import dao.Operation2;
import data.Customer;

public class ManagementCustomer {
	Operation2<Customer> opObj;

	public ManagementCustomer() throws Exception {
		opObj = new Operation2<>();
	}

	public String create(String custId, String custName, int amount) {

		try {
			if (opObj.checkIdInDB("Customer", Integer.parseInt(custId)) == true) {
				return "id_exists";
			}
			Customer tempObj = new Customer.CustomerBuilder(Integer.parseInt(custId), custName,
					Calendar.getInstance().getTimeInMillis(), Calendar.getInstance().getTimeInMillis())
							.setAmount(amount).build();
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

	public Customer read(String custId) {
		Customer tempObj = new Customer.CustomerBuilder(Integer.parseInt(custId), null, 0L, 0L).build();
		try {
			tempObj = opObj.read(tempObj, Integer.parseInt(custId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempObj;
	}

	public void update(String custId, String name, int amount) {
		try {
			Customer tempObj = new Customer.CustomerBuilder(Integer.parseInt(custId), null, 0L, 0L).build();
			int id = Integer.parseInt(custId);
			tempObj = opObj.read(tempObj, id);
			String oldName = tempObj.getName();
			if (name.isEmpty()) {
				name = oldName;
			}
			int oldAmount = tempObj.getAmount();
			if (amount == -1) {
				amount = oldAmount;
			}
			Customer custObj2 = new Customer.CustomerBuilder(Integer.parseInt(custId), name, tempObj.getTimeCreate(),
					Calendar.getInstance().getTimeInMillis()).setAmount(amount).build();
			try {
				opObj.update(custObj2, Integer.parseInt(custId));
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
	}

	public void delete(String empId) {
		try {
			Customer emp = new Customer.CustomerBuilder(Integer.parseInt(empId), null, 0L, 0L).build();
			opObj.delete(emp, Integer.parseInt(empId));

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		try {
			Customer emp = new Customer.CustomerBuilder(0, null, 0L, 0L).build();
			opObj.deleteAll(emp);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public List<Customer> readAll() {
		Customer tempObj = new Customer.CustomerBuilder(0, null, 0L, 0L).build();
		List<Customer> resultList = opObj.readAll(tempObj);
		return resultList;
	}

	public void closeConnection() {
		opObj.connectClose();
	}

}
