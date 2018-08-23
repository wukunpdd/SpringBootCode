package cn.wukun.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="achievement")
public class Achievement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	//编号
	private int number;

	//姓名
	private String name;

	//科目
	private String subject;

	//成绩
	private int grade;

	//判定
	private int isQualified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(int isQualified) {
		this.isQualified = isQualified;
	}

	@Override
	public String toString() {
		return "Achievement{" +
				"id=" + id +
				", number=" + number +
				", name='" + name + '\'' +
				", subject='" + subject + '\'' +
				", grade=" + grade +
				", isQualified=" + isQualified +
				'}';
	}
}
