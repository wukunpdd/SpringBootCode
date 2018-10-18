package cn.wukun.util;

public class Table {
	//表名或者字段名
	private String name;
	
	//表名或者字段名在数据库中的名字
	private String cname;
	
	//字段长度
	private String length;
	
	//字段类型
	private String type;
	
	//字段注释,保留字段，因为目前不知道获取指定列注释的方法
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
