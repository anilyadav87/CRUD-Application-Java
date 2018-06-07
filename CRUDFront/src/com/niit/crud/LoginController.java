package com.niit.crud;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.niit.implementation.EmployeeDAOImpl;
import com.niit.model.EmployeeModel;

@WebServlet("/logincontroller")
public class LoginController extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			try {
			EmployeeModel employeeModel = new EmployeeModel();
			employeeModel.seteMail(request.getParameter("email"));
			employeeModel.setPassword(request.getParameter("password"));
			
			UserController userController = new UserController(); 
			EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
			EmployeeModel empToCheck = employeeDAOImpl.readEmployeeByEmail(employeeModel);
			if(employeeModel.getPassword().equalsIgnoreCase("admin") && employeeModel.geteMail().equalsIgnoreCase("admin@gmail.com"))
			{
				HttpSession session=request.getSession();
				session.setAttribute("user", "admin");
				userController.printRecord(request, response);
			}
			else {
				out.print("Incorrect UserName or Password");
			}
			
			if(empToCheck!=null)
			{
				if(empToCheck.getPassword().equals(employeeModel.getPassword()))
				{
					HttpSession session=request.getSession();
					session.setAttribute("user", empToCheck.getFirstName());
					session.setAttribute("userid", empToCheck.geteId());
					userController.printRecord(request, response);
					
				}
				else
				{
					out.print("Incorrect UserName or Password");
				}
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
