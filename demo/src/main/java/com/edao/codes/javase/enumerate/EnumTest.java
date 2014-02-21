package com.edao.codes.javase.enumerate;

/**
 * 版权所有：liushuai
 * 项目名称:leo
 * 创建者: liushuai
 * 创建日期: 2013-7-29
 * 文件说明:
 * 最近修改者：liushuai
 * 最近修改日期：2013-7-29
 */

/**
 * @author liushuai
 *
 */
public class EnumTest {
	
	public enum WEEK {
		MONDAY("Monday"),
		TUESDAY("Tuesday"),
		WEDNESDAY("Wednesday"),
		THURSDAY("Thursday"),
		FRIDAY("Friday"),
		SATURDAY("Saturday"),
		ONN("22"),
		SUNDAY("Sunday");
		
		private String name;
		
		private WEEK(String name) {
			this.name = name;
		}
		
		public String toString() {
			return this.name;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EnumTest().test();
	}
	public void test() {
		//WEEK day = WEEK.valueOf("MONDAY"); // MONDAY
		
		//System.out.println(day);
		
		System.out.println(DataSource.valueOf(11));
		//System.out.println(DataSource.valueOf("1")); // throw exception
		System.out.println(DataSource.valueOf("ACCESS"));
		
		String dataSource = "1";
		DataSource ds = DataSource.valueOf(Integer.valueOf(dataSource));
		switch(ds) {
		case HOMEPAGE:
			System.out.println(ds);
			break;
		case LOGON:
			System.out.println(ds);
			break;
		default:
			System.out.println(DataSource.ALERT);
			break;
		}
		System.out.println(ds.equals(DataSource.ACCESS));
		System.out.println(ds == DataSource.ACCESS);
		System.out.println(DataSource.ACCESS.equals("1"));
	}

}

enum DataSource {
	HOMEPAGE(1000),
	LOGON(0),
	ACCESS(1),
	CERT_ACTIVE(2),
	CERT_HISTORY(4),
	ATTACK(3),
	SQL(5),
	SQLTXT(6),
	ACCESS_ACTIVE(7),
	LOGON_ARCHIVE(8),
	ACCESS_ARCHIVE(9),
	ATTACK_ARCHIVE(10),
	CERT_ARCHIVE(11),
	MIDLOGON(12),
	ALERT(13);
	
	private int value;
	
	private DataSource(int value) {
		this.value = value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
	
	public static DataSource valueOf(int value) {
		for (DataSource a : values()) {
			if (value == a.value) {
				return a;
			}
		}
		throw new IllegalArgumentException();
	}
}

