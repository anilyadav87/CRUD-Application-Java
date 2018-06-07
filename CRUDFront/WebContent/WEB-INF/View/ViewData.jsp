<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Hello ${user}</h3>
<a href="${pageContext.request.contextPath}/login }">Logout</a>
<br/><br/>
<c:if test="${user=='admin'}">
 <div style="border:1px solid black;float:left">
        <c:if test="${employeeModel != null and employeeModel != ''}">
            <form action="editrecord" method="post">
        </c:if>
        <c:if test="${employeeModel == null or employeeModel == ''}">
            <form action="insertrecord" method="post">
        </c:if>
        <table border="1" cellpadding="8">
            <caption>
                <h2>
                    <c:if test="${employeeModel != null}">
                        Edit Employee
                    </c:if>
                    <c:if test="${employeeModel == null}">
                        Add New Employee
                    </c:if>
                </h2>
            </caption>
                <c:if test="${employeeModel != null}">
                    <input type="hidden" name="eId" value="<c:out value='${employeeModel.eId}' />" />
                </c:if>
                <tr>
                <th>Employee ID: </th>
                <td>
                    <input type="text" name="eId" size="45"
                            value="<c:out value='${employeeModel.eId}' />"
                        />
                </td>
            </tr>           
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="firstName" size="45"
                            value="<c:out value='${employeeModel.firstName}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="lastName" size="45"
                            value="<c:out value='${employeeModel.lastName}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="address" size="45"
                    	value="<c:out value='${employeeModel.address}' />"/>
                </td>
                
            </tr>
            <tr>
                <th>Contact Number: </th>
                <td>
                    <input type="text" name="contactNo" size="45"
                            value="<c:out value='${employeeModel.contactNo}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>E-Mail ID: </th>
                <td>
                    <input type="text" name="eMail" size="45"
                            value="<c:out value='${employeeModel.eMail}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="password" size="45"
                            value="<c:out value='${employeeModel.password}' />"
                    />
                </td>
            </tr>
            <tr>
            <c:if test="${employeeModel !=null or employeeModel != ''}">
					<td colspan="2"><input type="submit" value="Update"/></td>
				</c:if>
				<c:if test="${employeeModel==null or employeeModel==''}">
					<td colspan="2"><input type="submit" value="Insert"/></td>
				</c:if>
            </tr>
        </table>
        </form>
    </div>   

</c:if>
<div id="display" style="border:1px solid black; width:700px;float:left; margin-left:10px">
<table cellpadding=7>
		<tr>
		<th>Emp Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Address</th>
		<th>Contact No</th>
		<th>E-Mail ID</th>
		<th>Password</th>
		</tr>
		<c:forEach items="${data}" var="r">
		<c:if test="${userid==r.eId or userid=='' }">
				<tr>
			<td>${r.eId}</td>
			<td>${r.firstName}</td>
			<td>${r.lastName}</td>
			<td>${r.address}</td>
			<td>${r.contactNo}</td>
			<td>${r.eMail}</td>
			<td>${r.password}</td>
			<c:if test="${user=='admin'}">
			<td><a href="editform?eid=${r.eId}">Update</a></td>
			<td><a href="deleterecord?eid=${r.eId}">Delete</a></td>
			</c:if>
		</tr>
		</c:if>
		</c:forEach>
	</table>
</div>
</body>
</html>