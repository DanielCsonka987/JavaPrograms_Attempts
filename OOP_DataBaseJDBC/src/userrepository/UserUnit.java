package userrepository;

import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserUnit {

	private SimpleIntegerProperty id;
	private SimpleStringProperty fname;
	private SimpleStringProperty lname;
	private SimpleStringProperty email;
	private SimpleStringProperty registr;
	private Date regdate;
	
	public UserUnit(Integer id, String fname, String lname, String email, Date regdate) {
		super();
		this.id = new SimpleIntegerProperty();  setId(id);
		this.fname = new SimpleStringProperty(); 	setFname(fname);
		this.lname = new SimpleStringProperty(); 	setLname(lname);
		this.email = new SimpleStringProperty(); 	setEmail(email);
		this.registr = new SimpleStringProperty(); 	setRegDate(regdate);
	}

	public UserUnit() {
		super();
		this.id = new SimpleIntegerProperty();
		this.fname = new SimpleStringProperty();
		this.lname = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.registr = new SimpleStringProperty();
	}

	public SimpleIntegerProperty getIdProp() {
		return id;
	}
	public Integer getId() {
		return id.getValue();
	}
	public void setId(Integer idval) {
		this.id.setValue(idval);
	}


	public SimpleStringProperty getFnameProp() {
		return fname;
	}
	public String getFname() {
		return fname.getValue();
	}
	public void setFname(String fnameval) {
		this.fname.setValue(fnameval);
	}



	public SimpleStringProperty getLnameProp() {
		return lname;
	}
	public String getLname() {
		return lname.getValue();
	}
	public void setLname(String lnameval) {
		this.lname.setValue(lnameval);
	}



	public SimpleStringProperty getEmailProp() {
		return email;
	}
	public String getEmail() {
		return email.getValue();
	}
	public void setEmail(String emailval) {
		this.email.setValue(emailval);
	}



	public SimpleStringProperty getRegistrProp() {
		return registr;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegDate(Date regdateval) {
		regdate = regdateval;
		registr.setValue(regdateval.toString());
	}



	@Override
	public String toString() {
		return "UserUnit [id=" + id.getValue() + ", fname=" + fname.getValue() + ", lname=" + lname.getValue() + 
				", email=" + email.getValue() + ", regdate=" + regdate + "]";
	}
	
	public Boolean areThereAllVariable() {
		return (	(getId() != null) && (getFname() != null) && (getLname() != null) &&
				(getEmail() != null) && (getRegdate() != null)	);
	}
}
