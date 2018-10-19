package cn.wukun.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库表格信息的获取
 * @author Admin
 *
 */
public class GeneratingTable {	
	private Connection connection;  
	private Statement statement;
	private List<Table> tableList;
	
	//获取连接
	public void initConnection(String driverClass,String dbUrl,String username,String password) throws Exception {	
		Class.forName(driverClass);
		this.connection = DriverManager.getConnection(dbUrl, username, password);
		this.statement = this.connection.createStatement();
	}
	
	//根据表名获取对应的表
	public ResultSetMetaData getMetaDataFromTable(String tableName) throws Exception {
		String sql = "SELECT * FROM " + tableName.toUpperCase() + " WHERE 1 != 1";
		ResultSet rs = this.statement.executeQuery(sql);
		return rs.getMetaData();
	}
	
	//把表格的数据获取到List集合中
	// ResultSetMetaData中有Column中想要的任何属性，这里只是不需要那么复杂
	public List<Table> displayMetaData(ResultSetMetaData metaData) throws Exception {
		tableList=new ArrayList<Table>();
		Table table=new Table();
		
		table.setCname(metaData.getTableName(1));
		table.setName(toUpperCaseFirstOne(handleUnderline(metaData.getTableName(1))));
		
		tableList.add(table);
		
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			table=new Table();
			
			table.setCname(metaData.getColumnName(i + 1));
			table.setName(handleUnderline(metaData.getColumnName(i + 1)));
			table.setCheckName(toUpperCaseFirstOne(handleUnderline(metaData.getColumnName(i + 1))));
			table.setLength(metaData.getColumnDisplaySize(i + 1)+"");
			table.setJdbcType(metaData.getColumnTypeName(i + 1));
			table.setType(metaData.getColumnTypeName(i + 1));
			
			tableList.add(table);
		}
		return tableList;
	}
	
	//处理有下划线的字符串
	public String handleUnderline(String cname){
		String[] arr=new String[]{};
		arr=cname.split("_");
		String name=arr[0];
		for(int i=1;i<arr.length;i++){
			name=name+toUpperCaseFirstOne(arr[i]);
		}
		return name;
	}
	
	//字符串首字母大写
	public String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0))){
			return s;
		}else{
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}
	
	//首字母转小写
	public String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	//处理数据库中的类型和实体类的对应
	public String handleField(String type){
		String t="";
		
		if(type.equals("VARCHAR")||type.equals("CHAR")||type.equals("TEXT")){
			t="String";
		}else if(type.equals("BLOB")){
			t="byte[]";
		}else if(type.equals("INTEGER")||type.equals("ID")||type.equals("BIGINT")){
			t="Long";
		}else if(type.equals("TINYINT")||type.equals("SMALLINT")||type.equals("MEDIUMINT")||type.equals("BOOLEAN")||type.equals("INT")){
			t="Integer";
		}else if(type.equals("BIT")){
			t="Boolean";
		}else if(type.equals("FLOAT")){
			t="Float";
		}else if(type.equals("DOUBLE")){
			t="Double";
		}else if(type.equals("DECIMAL")){
			t="java.math.BigDecimal";
		}else if(type.equals("DATE")||type.equals("YEAR")){
			t="java.sql.Date";
		}else if(type.equals("TIME")){
			t="java.sql.Time";
		}else if(type.equals("DATETIME")||type.equals("TIMESTAMP")){
			t="java.sql.Timestamp";
		}
		
		return t;
	}

	public String getJdbcType(String type){
		String result = type;
		if(type.equals("INT")){
			result = "INTEGER";
		}else if(type.equals("DATETIME")){
			result = "TIMESTAMP";
		}
		return result;
	}

}
