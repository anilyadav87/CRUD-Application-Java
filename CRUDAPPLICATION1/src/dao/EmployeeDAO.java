package dao;

import java.util.List;

import com.niit.model.EmployeeModel;
public interface EmployeeDAO {

	public void insertRecord(EmployeeModel employeeModel);
	public void deleteRecord(int eId);
	public List<EmployeeModel> employeeList();
	public void updateRecord(EmployeeModel employeeModel);
}
