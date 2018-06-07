package com.niit.crud;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import java.util.List;

import com.niit.implementation.EmployeeDAOImpl;
import com.niit.model.*;

@WebServlet("/")
public class UserController extends HttpServlet {
	EmployeeDAOImpl emp = new EmployeeDAOImpl();
	EmployeeModel employeeModel;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userChoice = request.getServletPath();
		switch(userChoice)
		{
		case "/deleterecord":
			deleteRecord(request,response);
			break;
		case "/editform":
			editForm(request, response);
			break;
		case "/login":
		default:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/Login.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}
	
		public void printRecord(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
		{
			List<EmployeeModel> d = emp.employeeList();
			HttpSession session = request.getSession();
			
			session.setAttribute("data", d);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/ViewData.jsp");
			dispatcher.forward(request, response);
		}

		public void insertRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			try {
				//System.out.println("Hello");
				employeeModel = new EmployeeModel();
				employeeModel.seteId(Integer.parseInt(request.getParameter("eId")));
				
				employeeModel.setFirstName(request.getParameter("firstName"));
				employeeModel.setLastName(request.getParameter("lastName"));
				employeeModel.setAddress(request.getParameter("address"));
				employeeModel.setContactNo(request.getParameter("contactNo"));
				employeeModel.seteMail(request.getParameter("eMail"));
				employeeModel.setPassword(request.getParameter("password"));
				emp.insertRecord(employeeModel);
				
				printRecord(request, response);
				
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		
		public void editRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			///System.out.println("Hi");
			int eId = Integer.parseInt(request.getParameter("eId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String Address = request.getParameter("address");
			String contactNo = request.getParameter("contactNo");
			String eMail = request.getParameter("eMail");
			String password = request.getParameter("password");
			
			EmployeeModel employeeModel = new EmployeeModel(eId, firstName, lastName, Address, contactNo,eMail,password);
			emp.updateRecord(employeeModel);
			printRecord(request,response);
		}
		public void deleteRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			int eid = Integer.parseInt(request.getParameter("eid"));
			System.out.println(eid);
			emp.deleteRecord(eid);
			printRecord(request,response);
		}
		
		
		public void editForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			int eId = Integer.parseInt(request.getParameter("eid"));
			try {
				EmployeeModel employeeModel = emp.getEmployee(eId);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/ViewData.jsp");
				
				request.setAttribute("employeeModel",employeeModel);
				dispatcher.forward(request, response);
				printRecord(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userChoice = request.getServletPath();
		switch(userChoice)
		{
		
		case "/editrecord":
			editRecord(request, response);
			break;
		case "/insertrecord":
			insertRecord(request,response);
			break;
		default:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/ViewData.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}

}
