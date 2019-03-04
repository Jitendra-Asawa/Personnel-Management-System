//package data;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import interfaces.CustomerEmployee;
//
//public class Employee implements CustomerEmployee {
//
//	private int id;
//	private String name;
//	private String team;
//	private long timeCreate;
//	private long timeUpdate;
//
//	public long getTimeCreate() {
//		return timeCreate;
//	}
//
//	public void setTimeCreate(long timeCreate) {
//		this.timeCreate = timeCreate;
//	}
//
//	public long getTimeUpdate() {
//		return timeUpdate;
//	}
//
//	public void setTimeUpdate(long timeUpdate) {
//		this.timeUpdate = timeUpdate;
//	}
//
//	public Employee() {
//
//	}
//
//	public Employee(int id) {
//		this.id = id;
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
//	public String getTeam() {
//		return team;
//	}
//
//	public void setTeam(String team) {
//		this.team = team;
//	}
//
//	public Map<String, String> toMap() {
//		Map<String, String> map = new LinkedHashMap<>();
//		map.put("id", Integer.toString(id));
//		map.put("name", name);
//		map.put("team", team);
//		map.put("timeCreate", Long.toString(timeCreate));
//		map.put("timeUpdate", Long.toString(timeUpdate));
//		return map;
//	}
//
//	@Override
//	public String toString() {
//		return "Employee ( Id=" + id + ", Name=" + name + ", Team=" + team + "timeCreate" + timeCreate + "timeUpdate"
//				+ timeUpdate + ")";
//	}
//}
package data;
import java.util.LinkedHashMap;
import java.util.Map;

import interfaces.CustomerEmployee;

public class Employee implements CustomerEmployee {
	private int id;
	private String name;
	private String team;
	private long timeCreate;
	private long timeUpdate;

	private Employee(EmployeeBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.team = builder.team;
		this.timeCreate = builder.timeCreate;
		this.timeUpdate = builder.timeUpdate;
	}

	public static class EmployeeBuilder {
		private int id;
		private String name;
		private String team;
		private long timeCreate;
		private long timeUpdate;

		public EmployeeBuilder(int id, String name, long timeCreate, long timeUpdate) {
			this.id = id;
			this.name = name;
			this.timeCreate = timeCreate;
			this.timeUpdate = timeUpdate;
		}

		public EmployeeBuilder setTeam(String team) {
			this.team = team;
			return this;
		}

		public Employee build() {
			return new Employee(this);
		}

	}

	public String getTeam() {
		return team;
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

	public Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("id", Integer.toString(id));
		map.put("name", name);
		map.put("team", team);
		map.put("timeCreate", Long.toString(timeCreate));
		map.put("timeUpdate", Long.toString(timeUpdate));
		return map;
	}

	@Override
	public String toString() {
		return "Employee ( Id=" + id + ", Name=" + name + ", Team=" + team + "timeCreate" + timeCreate + "timeUpdate"
				+ timeUpdate + ")";
	}
}
