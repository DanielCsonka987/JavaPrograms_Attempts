package presenter;

import java.util.List;

import progoperations.DBSources;
import userrepository.UserUnit;

public interface IPresenterFrame {

	DBSources getChosenDBSource();

	String getFieldId();
	String getFieldFname();
	String getFieldLname();
	String getFieldEmail();
	String getFieldDate();
	
	void setFieldId(String id);
	void setFieldFname(String first);
	void setFieldLname(String last);
	void setFieldEmail(String email);
	void setFieldDate(String reg);

	void setListContent(List<UserUnit> datas);
	Integer getChosenId();
	DBSources getChosenDB();
	
}
