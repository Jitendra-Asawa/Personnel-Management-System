//package data;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import interfaces.CustomerEmployee;
//
//public class Customer implements CustomerEmployee {
//	private int id;
//	private String name;
//	private int amount;
//
//	public Customer() {
//	}
//
//	public Customer(int id) {
//		this.id = id;
//	}
//
//	public int getAmount() {
//		return amount;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public void setAmount(int amount) {
//		this.amount = amount;
//	}
//
//	public Map<String, String> toMap() {
//		Map<String, String> map = new LinkedHashMap<>();
//		map.put("id", Integer.toString(id));
//		map.put("name", name);
//		map.put("amount", Integer.toString(amount));
//		return map;
//	}
//
//	@Override
//	public String toString() {
//		return "Customer ( Id=" + id + ", Name=" + name + ", Amount=" + amount + ")";
//	}
//
//}
package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import interfaces.CustomerEmployee;

public class Customer implements CustomerEmployee {
	private int id;
	private String name;
	private int amount;
	private long timeCreate;
	private long timeUpdate;
	private String formatCreate;
	private String formatUpdate;
	
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private Customer(CustomerBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.amount = builder.amount;
		this.timeCreate = builder.timeCreate;
		this.timeUpdate = builder.timeUpdate;
	}

	public static class CustomerBuilder {
		private int id;
		private String name;
		private int amount;
		private long timeCreate;
		private long timeUpdate;

		public CustomerBuilder(int id, String name, long timeCreate, long timeUpdate) {
			this.id = id;
			this.name = name;
			this.timeCreate = timeCreate;
			this.timeUpdate = timeUpdate;
		}

		public CustomerBuilder setAmount(int amount) {
			this.amount = amount;
			return this;
		}

		public Customer build() {
			return new Customer(this);
		}

	}

	public int getAmount() {
		return amount;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getTimeCreate() {
		return timeCreate;
	}

	public long getTimeUpdate() {
		return timeUpdate;
	}

	public String getFormatCreate() {
		formatCreate = format.format(timeCreate);
		return formatCreate;
	}
	
	public String getFormatUpdate() {
		formatUpdate = format.format(timeUpdate);
		return formatUpdate;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("id", Integer.toString(id));
		map.put("name", name);
		map.put("amount", Integer.toString(amount));
		map.put("timeCreate", Long.toString(timeCreate));
		map.put("timeUpdate", Long.toString(timeUpdate));
		return map;
	}

	@Override
	public String toString() {
		return "Customer ( Id=" + id + ", Name=" + name + ", Amount=" + amount + "timeCreate" + formatCreate
				+ "timeUpdate" + formatUpdate + ")";
	}
}
