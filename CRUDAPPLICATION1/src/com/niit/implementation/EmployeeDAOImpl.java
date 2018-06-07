package com.niit.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.niit.config.*;
import com.niit.model.*;

import dao.EmployeeDAO;
public class EmployeeDAOImpl implements EmployeeDAO
{

	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	Connection connection;
	
	//constructor
	public EmployeeDAOImpl() 
	{
		connection = Config.getConnection();
	}
	
	@Override
	public void insertRecord(EmployeeModel employeeModel) 
	{
		String str = "Insert into EmployeeDetails(eId,firstName,lastName,Address,contactNo,eMail,password) values (?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(str);
			preparedStatement.setInt(1, employeeModel.geteId());
			preparedStatement.setString(2, employeeModel.getFirstName());
			preparedStatement.setString(3, employeeModel.getLastName());
			preparedStatement.setString(4, employeeModel.getAddress());
			preparedStatement.setString(5, employeeModel.getContactNo());
			preparedStatement.setString(6, employeeModel.geteMail());
			preparedStatement.setString(7, employeeModel.getPassword());
			int rowInserted = preparedStatement.executeUpdate();
			//System.out.println(rowInserted);
			if(rowInserted>0)
			{
				System.out.println("Record Inserted sucessfully");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	@Override
	public void deleteRecord(int eId) {
		try {
			String query = "delete from EmployeeDetails where eId =" + eId;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("user deleted Successfully");
	}

	@Override
	public List<EmployeeModel> employeeList() {
		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();

		String sql = "SELECT * FROM EmployeeDetails";

		try {
			EmployeeModel em = new EmployeeModel();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int eId = resultSet.getInt("eId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String address = resultSet.getString("Address");
				String contactNo = resultSet.getString("contactNo");
				String eMail= resultSet.getString("eMail");
				String password = resultSet.getString("password");

				EmployeeModel employeeModel = new EmployeeModel();
				employeeModel.seteId(eId);
				employeeModel.setFirstName(firstName);
				employeeModel.setLastName(lastName);
				employeeModel.setAddress(address);
				employeeModel.setContactNo(contactNo);
				employeeModel.seteMail(eMail);
				employeeModel.setPassword(password);
				employeeList.add(employeeModel);
			}
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return employeeList;
	}

	@Override
	public void updateRecord(EmployeeModel employeeModel) {
		// TODO Auto-generated method stub
		String str =  "Update EmployeeDetails Set firstName=?, lastName=?, Address=?, contactNo = ?,eMail=?,password=? "+ "where eId =?";
		
		try {
			preparedStatement = connection.prepareStatement(str);
			preparedStatement.setString(1, employeeModel.getFirstName());
			preparedStatement.setString(2, employeeModel.getLastName());
			preparedStatement.setString(3, employeeModel.getAddress());
			preparedStatement.setString(4, employeeModel.getContactNo());
			preparedStatement.setString(5, employeeModel.geteMail());
			preparedStatement.setString(6, employeeModel.getPassword());
			preparedStatement.setInt(7, employeeModel.geteId());
			int rowUpdated = preparedStatement.executeUpdate();
			//System.out.println(rowUpdated);
			if(rowUpdated>0)
			{
				System.out.println("Record Updated sucessfully");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EmployeeModel getEmployee(int eid) throws SQLException {
        EmployeeModel employeeModel = null;
        String sql = "SELECT * FROM EmployeeDetails WHERE eId = ?";
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, eid);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	eid = resultSet.getInt("eId");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String Address = resultSet.getString("address");
            String contactNo = resultSet.getString("contactNo");
            String eMail = resultSet.getString("eMail");
            String password = resultSet.getString("password");
            employeeModel = new EmployeeModel(eid,firstName, lastName, Address, contactNo,eMail,password);
        
        employeeModel = new EmployeeModel();
        employeeModel.seteId(eid);
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setAddress(Address);
        employeeModel.setContactNo(contactNo);
        employeeModel.seteMail(eMail);
        employeeModel.setPassword(password);
        }
        resultSet.close();
        statement.close();
         
        return employeeModel;
    }
	
	public EmployeeModel readEmployeeByEmail(EmployeeModel employeeModel) throws SQLException
	{
		System.out.println("hi");
		ResultSet resultSet = null;
		String query = "Select * from EmployeeDetails where eMail=?";
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, employeeModel.geteMail());
		resultSet=preparedStatement.executeQuery();
		
		EmployeeModel employeeModel2 = null;
		if (resultSet.next()) {
        int	eid = resultSet.getInt("eId");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String Address = resultSet.getString("address");
            String contactNo = resultSet.getString("contactNo");
            String eMail = resultSet.getString("eMail");
            String password = resultSet.getString("password");
            employeeModel2 = new EmployeeModel(eid,firstName, lastName, Address, contactNo,eMail,password);
		}
		return employeeModel2;
	}


}