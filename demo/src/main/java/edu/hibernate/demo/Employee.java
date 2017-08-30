package edu.hibernate.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;



@NamedQueries({
		@NamedQuery(name="ascOrderBySal", query="SELECT empName, sal, city FROM Employee ORDER BY sal")
})
@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="EMPNO")
	private int empNo;
	
	@Column(name="ENAME")
	private String empName;
	
	@Column(name="SALARY")
	private double sal;
	
	@Column(name="CITY")
	private String city;
	

	public Employee(){
		
	}
	public Employee( String empName, double sal, String city) {
		super();
		this.empName = empName;
		this.sal = sal;
		this.city=city;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}
	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", sal=" + sal + ", city=" + city + "]";
	}


	

}
