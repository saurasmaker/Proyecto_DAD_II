<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Bill" %>
<%@ page import = "edu.ucam.pojos.User" %>

<%@ page import = "edu.ucam.daos.BillDAO" %>
<%@ page import = "edu.ucam.daos.UserDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>
<%@ page import = 'edu.ucam.actions.admin.*' %>


<%
	pageContext.setAttribute("usersList", (new UserDAO()).list());
	pageContext.setAttribute("billsList", (new BillDAO()).list());
%>

	<div id = "bills-title" class = "col-12">
        <h3 class = "display-3">Facturas</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-bill-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
			
			<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Bill.class.getName() %>" />
			
			<label for="bill-input-id">ID: </label>
			<p><input id = "bill-input-id" type = "text" class="form-control" placeholder = "Introduce el id de la factura.." name = "<%=Bill.ATR_BILL_ID %>" readonly></p>
				
			<label for="bill-input-userid">ID Usuario: </label>
			<p><select id = "bill-input-userid" class="form-control" name = "<%=Bill.ATR_BILL_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  <c:forEach var='user' items='${usersList}' varStatus=''>
			  	<option value='${user.id}'>${user.username}</option>
			  </c:forEach>
			</select></p>

			<label style = "text-decoration: underline black;">Facturación: </label>
			<div class = "row col-12">
				<div class = "col-12">
				    <label for="bill-input-billingdate">Fecha de Facturación: </label>
					<p><input id = "bill-input-billingdate" type = "date" class="form-control" name = "<%=Bill.ATR_BILL_BILLINGDATE %>" required></p>
				
					<label for="bill-input-billingtime">Hora de Facturación: </label>
					<p><input id = "bill-input-billingtime" type = "time" step = "1" class="form-control" name = "<%=Bill.ATR_BILL_BILLINGTIME %>" required></p>
				</div>
			</div>
		
			<label for="bill-input-paid">Pagado: </label>
			<p><input id = "bill-input-paid" type = "checkbox" class="form-control" name = "<%=Bill.ATR_BILL_PAID %>"></p>
				
			<label style = "text-decoration: underline black;">Pago: </label>
			<div class = "row col-12">
				<div class = "col-12">
				    <label for="bill-input-paiddate">Fecha del Pago: </label>
					<p><input id = "bill-input-paiddate" type = "date" class="form-control" name = "<%=Bill.ATR_BILL_PAIDDATE %>" required></p>
				
					<label for="bill-input-paidtime">Hora del Pago: </label>
					<p><input id = "bill-input-paidtime" type = "time" step = "1" class="form-control" name = "<%=Bill.ATR_BILL_PAIDTIME %>" required></p>
				</div>
			</div>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-bill-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST" style = "display: 'none';">
            
            <input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Update.ATR_ACTION %>'/>
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Bill.class.getName() %>" />
			
			<label for="bill-input-update-id">ID: </label>
			<p><input id = "bill-input-update-id" type = "text" class="form-control" placeholder = "Introduce el id de la factura.." name = "<%=Bill.ATR_BILL_ID %>" readonly></p>
				
			<label for="bill-input-update-userid">ID Usuario: </label>
			<p><select id = "bill-input-update-userid" class="form-control" name = "<%=Bill.ATR_BILL_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  <c:forEach var='user' items='${usersList}' varStatus=''>
			  	<option value='${user.id}'>${user.username}</option>
			  </c:forEach>
			</select></p>

		    <label style = "text-decoration: underline black;">Facturación: </label>
			<div class = "row col-12">
				<div class = "col-12">
				    <label for="bill-input-update-billingdate">Fecha de Facturación: </label>
					<p><input id = "bill-input-update-billingdate" type = "date" class="form-control" name = "<%=Bill.ATR_BILL_BILLINGDATE %>" required></p>
				
					<label for="bill-input-update-billingtime">Hora de Facturación: </label>
					<p><input id = "bill-input-update-billingtime" type = "time" step = "1" class="form-control" name = "<%=Bill.ATR_BILL_BILLINGTIME %>" required></p>
				</div>
			</div>
		
			<label for="bill-input-update-paid">Pagado: </label>
			<p><input id = "bill-input-update-paid" type = "checkbox" class="form-control" name = "<%=Bill.ATR_BILL_PAID %>"></p>
				
			<label style = "text-decoration: underline black;">Pago: </label>
			<div class = "row col-12">
				<div class = "col-12">
				    <label for="bill-input-update-paiddate">Fecha del Pago: </label>
					<p><input id = "bill-input-update-paiddate" type = "date" class="form-control" name = "<%=Bill.ATR_BILL_PAIDDATE %>" required></p>
				
					<label for="bill-input-update-paidtime">Hora del Pago: </label>
					<p><input id = "bill-input-update-paidtime" type = "time" step = "1" class="form-control" name = "<%=Bill.ATR_BILL_PAIDTIME %>" required></p>
				</div>
			</div>
            
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <a id = "input-edit-send" class="btn btn-secondary smooth-scroller" href = "#bills-title" role="button" onclick = "cancelUpdateBill()" style = "margin-left: 10px;">Cancelar</a>
            </p>
        </form>
    </div>

    
	  
	<div class = "col-lg-8 col-md-6 col-sm-12">
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
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
			   	
			   		<c:forEach var='bill' items='${billsList}' varStatus=''>
			   			
			   			<% Bill b = (Bill)pageContext.getAttribute("bill"); %>
			   			
			   			<tr>
	                     	<td>${bill.id}</td>
	                     	<td>${bill.userId}</td>
	                        <td>${bill.billingDate}</td>
	                        <td>${bill.billingTime} %></td>
	                        <td>${bill.paid}</td>
	                        <td>${bill.paidDate}</td>
	                        <td>${bill.paidTime}</td>
	                        <td>
	                            <button type = "submit" class="btn btn-warning" onclick = "updateBill(<%=b.toJavaScriptFunction() %>)">Editar</button>
	                        </td>
	                        <td>
								<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
								<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>
	                           		<input type = "hidden" name = "<%=Bill.ATR_BILL_ID %>" value = "${bill.id}">
	                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Bill.class.getName() %>">   
	                           		<button type = "submit" class="btn btn-danger">Eliminar</button>
	                        	</form>
	                        </td>
	                	</tr>
			   		
			   		</c:forEach>

				</tbody>
            </table>
        </div>
    </div>
	
	<br/>  