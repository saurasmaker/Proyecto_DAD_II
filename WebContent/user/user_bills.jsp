<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>
    
<%@ page import="edu.ucam.servlets.Controller" %>

<%@ page import="edu.ucam.pojos.User" %>
<%@ page import="edu.ucam.pojos.Bill" %>

<%@ page import="edu.ucam.daos.BillDAO" %>
<%@ page import="edu.ucam.actions.user.PayBill" %>


<!DOCTYPE html>

<% User thisUser = (User)session.getAttribute(User.ATR_USER_LOGGED); %>

<html>
	<head>
		<jsp:include page="../mod/head.jsp" />
		<title><%=thisUser.getUsername()%> Bills Infodeo</title>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/profile_tools.js"></script>
	</head>
	
	<body>
		<div class = "general container">
			<jsp:include page="../mod/header.jsp" />
			
			<div class = 'row content user-bills-div'>
				
				<div class = 'col-12'>
					<h3 class = 'display-2 text-center'>Facturas</h3>
					<hr width = '50%'/>
					<br/>
				</div>
				
				<div class = "col-12">
			        <div class = "table-responsive" style = " max-height: 600px !important; overflow: auto;">
			            <table class="table table-striped">
			               	<thead class = "thead-dark">
			                  	<tr>
			                     	<th scope="col">ID</th>
			                     	<th scope="col">ID Usuario</th>
			                     	<th scope="col">Fecha de Facturación</th>
			                     	<th scope="col">Hora de Facturación</th>
									<th scope="col">Pagado</th>
									<th scope="col">Fecha de Pago</th>
									<th scope="col">Hora de Pago</th>
			                        <th scope="col">Pagar</th>
			                  	</tr>
			               	</thead>
						   	<tbody>
			                <% ArrayList<Bill> billsList = (new BillDAO()).listByUserId(thisUser.getId());
						  	for(int i = 0; i < billsList.size(); ++i) {
								Bill showBill = billsList.get(i); %>
								<tr>
			                     	<td><%=showBill.getId() %></td>
			                     	<td><%=showBill.getUserId() %></td>
			                        <td><%=showBill.getBillingDate() %></td>
			                        <td><%=showBill.getBillingTime() %></td>
			                        <td><%=showBill.isPaid() %></td>
			                        <td><%=showBill.getPaidDate() %></td>
			                        <td><%=showBill.getPaidTime() %></td>
			                        <td>
			                        	<% if(!showBill.isPaid()) { %>
										<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
											<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= PayBill.ATR_ACTION %>'/>
			                           		<input type = "hidden" name = "<%=Bill.ATR_BILL_ID %>" value = "<%=showBill.getId() %>">
			                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Bill.class.getName() %>">   
			                           		<button type = "submit" class="btn btn-danger">Pagar</button>
			                        	</form>
			                        	<% } else { out.print("<p><input type='checkbox' class='form-control' onclick='return false;'checked></p>"); }%>
			                        </td>
			                	</tr>
								  
							<% } %>
							</tbody>
			            </table>
			        </div>

				</div>

			</div>
			
			<jsp:include page="../mod/footer.jsp" />
			
		</div>
	</body>
</html>