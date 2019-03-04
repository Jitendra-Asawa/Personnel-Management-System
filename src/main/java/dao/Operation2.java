/*
 * COPY OF Operation.java for backup
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import data.CustEmp;
import data.Customer;
import data.Employee;
import interfaces.CustomerEmployee;

public class Operation2<T extends CustomerEmployee> {

	private Connection connect = null;
	private PreparedStatement prepStatement = null;

	public Operation2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/details", "root", "infoobjects");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public T objectReturn(T tempObj, int id, List<String> resultList) {
		if (tempObj instanceof Employee) {
			Employee empObj = new Employee.EmployeeBuilder(id, resultList.get(1), Long.parseLong(resultList.get(3)),
					Long.parseLong(resultList.get(4))).setTeam(resultList.get(2)).build();
			return (T) empObj;
		} else if (tempObj instanceof Customer) {
			Customer custObj = new Customer.CustomerBuilder(id, resultList.get(1), Long.parseLong(resultList.get(3)),
					Long.parseLong(resultList.get(4))).setAmount(Integer.parseInt(resultList.get(2))).build();
			return (T) custObj;
		}
		return null;
	}

	/*
	 * Returns false if id does not exist in database
	 */
	public boolean checkIdInDB(String workingClass, int id) {
		try {
			StringBuilder sql = new StringBuilder("select id from " + workingClass + " where id= ? ;");
			prepStatement = connect.prepareStatement(sql.toString());

			prepStatement.setString(1, Integer.toString(id));

			ResultSet rs = prepStatement.executeQuery();
			if (rs.next() == false) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * Closes Connection
	 */
	public void connectClose() {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Create an entry in Employee and Customer table
	 */
	public void insertInto(T tempObj) throws Exception {

		Map<String, String> map = tempObj.toMap();
		int id = tempObj.getId();
		String name = tempObj.getName();
		int mapSize = map.size();
		String workingClass = tempObj.getClass().getSimpleName();
		StringBuilder sql = new StringBuilder("insert into " + workingClass + " values ( ");
		for (int i = 0; i < mapSize - 1; i++) {
			sql.append("?, ");
		}
		sql.append("? );");
		prepStatement = connect.prepareStatement(sql.toString()); // because prepareStatement doesnt work on string
																	// builder
		int index = 1;
		for (String key : map.keySet()) {
			prepStatement.setString(index, map.get(key));
			index++;
		}
		prepStatement.executeUpdate();
		prepStatement.close();
		CustEmp cust = new CustEmp.CustEmpBuilder(id).setClassName(workingClass).setName(name).build();
		insertIntoCustEmp(cust);
	}

	public T read(T tempObj, int id) throws Exception {
		List<String> resultList = new ArrayList<>();
		try {
			String workingClass = tempObj.getClass().getSimpleName();

			if (checkIdInDB(workingClass, id) == false) {
				return null;
			}
			StringBuilder sql = new StringBuilder("select * from " + workingClass + " where id = ?;");
			prepStatement = connect.prepareStatement(sql.toString());
			prepStatement.setString(1, Integer.toString(id));
			ResultSet rs = prepStatement.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					String value = rs.getString(i);
					resultList.add(value);
				}
			}
			T typeObject = objectReturn(tempObj, id, resultList);
			if (typeObject != null) {
				return typeObject;
			}

			prepStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Returns True if Record deleted
	 */
	public void delete(T tempObj, int id) {
		try {
			String workingClass = tempObj.getClass().getSimpleName();
			StringBuilder sql = new StringBuilder("delete from " + workingClass + " where id = ?;");
			prepStatement = connect.prepareStatement(sql.toString());
			prepStatement.setString(1, Integer.toString(id));
			prepStatement.executeUpdate();
			prepStatement.close();
			CustEmp cust = new CustEmp.CustEmpBuilder(id).setClassName(workingClass).build();
			deleteRecordCustEmp(cust);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll(T tempObj) {
		try {
			String workingClass = tempObj.getClass().getSimpleName();
			StringBuilder sql = new StringBuilder("delete from " + workingClass + ";");
			prepStatement = connect.prepareStatement(sql.toString());
			prepStatement.executeUpdate();
			prepStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Returns a list of T type objects
	 */
	public List<T> readAll(T tempObj) {
		List<T> resultList = new ArrayList<T>();

		try {
			String workingClass = tempObj.getClass().getSimpleName();
			StringBuilder sql = new StringBuilder("select * from " + workingClass + " order by id;");
			prepStatement = connect.prepareStatement(sql.toString());
			ResultSet rs = prepStatement.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			while (rs.next()) {
				ArrayList<String> stringList = new ArrayList<String>();
				for (int i = 1; i <= numberOfColumns; i++) {
					stringList.add(rs.getString(i));
				}
				resultList.add(objectReturn(tempObj, Integer.parseInt(rs.getString(1)), stringList));
			}
			prepStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public void update(T tempObj, int id) throws Exception {
		String workingClass = tempObj.getClass().getSimpleName();
		Map<String, String> map = tempObj.toMap();
		String name = tempObj.getName();
		int mapSize = map.size();
		int i = 0;
		StringBuilder sql = new StringBuilder("update " + workingClass + " set ");
		for (String key : map.keySet()) {
			sql.append(key + " = ?");
			if (i < mapSize - 1) {
				sql.append(", ");
				i++;
			}
		}
		sql.append(" where id = '" + tempObj.getId() + "';");
		prepStatement = connect.prepareStatement(sql.toString());
		int index = 1;
		for (String key : map.keySet()) {
			prepStatement.setObject(index, map.get(key));
			index++;
		}
		prepStatement.executeUpdate();
		prepStatement.close();
		CustEmp cust = new CustEmp.CustEmpBuilder(id).setClassName(workingClass).setName(name).build();
		updateIntoCustEmp(cust);
	}

	public void insertIntoCustEmp(CustEmp cust) throws Exception {
		int id = cust.getId();
		String name = cust.getName();
		String type = cust.getClassName();
		StringBuilder sql = new StringBuilder("insert into CustEmp(id,name,type) values (?,?,?);");
		prepStatement = connect.prepareStatement(sql.toString());
		prepStatement.setInt(1, id);
		prepStatement.setString(2, name);
		prepStatement.setString(3, type);
		prepStatement.executeUpdate();
		prepStatement.close();
	}

	public void updateIntoCustEmp(CustEmp cust) throws Exception {
		int id = cust.getId();
		String name = cust.getName();
		String type = cust.getClassName();
		StringBuilder sql = new StringBuilder("update CustEmp set name=? where id=? and type=?;");
		prepStatement = connect.prepareStatement(sql.toString());
		prepStatement.setInt(2, id);
		prepStatement.setString(1, name);
		prepStatement.setString(3, type);
		prepStatement.executeUpdate();
		prepStatement.close();

	}

	public List<List<String>> readAllCustEmp() {
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			String sql = "select * from CustEmp;";
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			while (rs.next()) {
				List<String> stringList = new ArrayList<String>();
				for (int i = 1; i <= numberOfColumns; i++) {
					stringList.add(rs.getString(i));
				}
				list.add(stringList);
			}
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void deleteRecordCustEmp(CustEmp cust) {
		int id = cust.getId();
		String type = cust.getClassName();
		String sql = "delete from CustEmp where id=? and type=?;";
		try {
			prepStatement = connect.prepareStatement(sql);
			prepStatement.setInt(1, id);
			prepStatement.setString(2, type);
			prepStatement.executeUpdate();
			prepStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
