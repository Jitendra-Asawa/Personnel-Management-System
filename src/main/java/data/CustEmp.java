package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import interfaces.CustomerEmployee;

public class CustEmp implements CustomerEmployee {
//	private int autoIncrId = 0;
	private int id;
	private String name;
	private String team;
	private int amount;
	private long timeCreate;
	private long timeUpdate;
	private String className;
	private String formatCreate;
	private String formatUpdate;
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private CustEmp(CustEmpBuilder builder) {
//		this.autoIncrId=builder.autoIncrId;
		this.id = builder.id;
		this.name = builder.name;
		this.className = builder.className;
		this.amount = builder.amount;
		this.team = builder.team;
		this.timeCreate = builder.timeCreate;
		this.timeUpdate = builder.timeUpdate;
		this.formatCreate=format.format(timeCreate);
		this.formatUpdate=format.format(timeUpdate);
	}

	public String getTeam() {
		return team;
	}

	public int getAmount() {
		return amount;
	}

	public long getTimeCreate() {
		return timeCreate;
	}

	public long getTimeUpdate() {
		return timeUpdate;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public String getFormatCreate() {
		return formatCreate;
	}
	
	public String getFormatUpdate() {
		return formatUpdate;
	}
	public static class CustEmpBuilder {
//		private int autoIncrId = 0;
		private int id;
		private String name;
		private String className;
		private String team;
		private int amount;
		private long timeCreate;
		private long timeUpdate;

		public CustEmpBuilder(int id) {
			this.id = id;
		}

//		public CustEmpBuilder setId(int id) {
//			this.id = id;
//			return this;
//		}
//
		public CustEmpBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public CustEmpBuilder setClassName(String className) {
			this.className = className;
			return this;
		}

		public CustEmpBuilder setTeam(String team) {
			this.team = team;
			return this;
		}

		public CustEmpBuilder setAmount(int amount) {
			this.amount = amount;
			return this;
		}

		public CustEmpBuilder setTimeCreate(long timeCreate) {
			this.timeCreate = timeCreate;
			return this;
		}

		public CustEmpBuilder setTimeUpdate(long timeUpdate) {
			this.timeUpdate = timeUpdate;
			return this;
		}

		public CustEmp build() {
			return new CustEmp(this);
		}
	}

	@Override
	public Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<>();
//		map.put("autoIncrId", Integer.toString(autoIncrId));
		map.put("id", Integer.toString(id));
		map.put("name", name);
		map.put("team", team);
		map.put("amount", Integer.toString(amount));
		map.put("timeCreate", Long.toString(timeCreate));
		map.put("timeUpdate", Long.toString(timeUpdate));
		map.put("className", className);
		return map;
	}

	@Override
	public String toString() {
		return "CustEmp [id=" + id + ", name=" + name + ", team=" + team + ", amount=" + amount + ", className="
				+ className + ", formatCreate=" + formatCreate + ", formatUpdate=" + formatUpdate + ", ]";
	}


}
