<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Bill" %>
<%@ page import = "edu.ucam.pojos.User" %>

<%@ page import = "edu.ucam.daos.BillDAO" %>
<%@ page import = "edu.ucam.daos.UserDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>


	<div class = "col-12">
        <h3 class = "display-3">Facturas</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-bill-form" class = "form-group" action = "<%= request.getContextPath() %>/CREATE" method = "POST">
			
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Bill.class.getName() %>" />
			
			<label for="bill-input-id">ID: </label>
			<p><input id = "bill-input-id" type = "text" class="form-control" placeholder = "Introduce el id de la factura.." name = "<%=Bill.ATR_BILL_ID %>" readonly></p>
				
			<label for="bill-input-userid">ID Usuario: </label>
			<p><select id = "bill-input-userid" class="form-control" name = "<%=Bill.ATR_BILL_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  <% ArrayList<User> usersBillList = (new UserDAO()).list();
			  for(int i = 0; i < usersBillList.size(); ++i) { %>
				  <option value="<%=usersBillList.get(i).getId() %>"><%=usersBillList.get(i).getUsername() %></option>
			  <% } %>
			</select></p>

		    <label for="bill-input-purchasedate">Fecha de Compra: </label>
			<p><input id = "bill-input-purchasedate" type = "date" class="form-control" placeholder = "Introduce la fecha de compra..." name = "<%=Bill.ATR_BILL_PURCHASEDATE %>" required></p>
		
			<label for="bill-input-paid">Pagado: </label>
			<p><input id = "bill-input-paid" type = "checkbox" class="form-control" name = "<%=Bill.ATR_BILL_PAID %>" required></p>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-bill-form" class = "form-group" action = "<%= request.getContextPath() %>/UPDATE" method = "POST" style = "display: 'none';">
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Bill.class.getName() %>" />
			
			<label for="bill-input-update-id">ID: </label>
			<p><input id = "bill-input-update-id" type = "text" class="form-control" placeholder = "Introduce el id de la factura.." name = "<%=Bill.ATR_BILL_ID %>" readonly></p>
				
			<label for="bill-input-update-userid">ID Usuario: </label>
			<p><select id = "bill-input-update-userid" class="form-control" name = "<%=Bill.ATR_BILL_USERID %>">
			  <option value="none" selected>Elige un Usuario...</option>
			  <% for(int i = 0; i < usersBillList.size(); ++i) { %>
				  <option value="<%=usersBillList.get(i).getId() %>"><%=usersBillList.get(i).getUsername() %></option>
			  <% } %>
			</select></p>

		    <label for="bill-input-update-purchasedate">Fecha de Compra: </label>
			<p><input id = "bill-input-update-purchasedate" type = "date" class="form-control" placeholder = "Introduce la fecha de compra..." name = "<%=Bill.ATR_BILL_PURCHASEDATE %>" required></p>
		
			<label for="bill-input-update-paid">Pagado: </label>
			<p><input id = "bill-input-update-paid" type = "checkbox" class="form-control" name = "<%=Bill.ATR_BILL_PAID %>" required></p>
            
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <button id = "input-edit-send" class="btn btn-secondary" role="button" onclick = "cancelUpdateBill()" style = "margin-left: 10px;">Cancelar</button>
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
                     	<th scope="col">Fecha de Compra</th>
						<th scope="col">Pagado</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
                <% ArrayList<Bill> billsList = (new BillDAO()).list();
			  	for(int i = 0; i < billsList.size(); ++i) {
					Bill showBill = billsList.get(i); %>
					<tr>
                     	<td><%=showBill.getId() %></td>
                     	<td><%=showBill.getUserId() %></td>
                        <td><%=showBill.getPurchaseDate() %></td>
                        <td><%=showBill.isPaid() %></td>
                        <td>
                            <button type = "submit" class="btn btn-warning" onclick = "updateBill(<%=showBill.toJavaScriptFunction() %>)">Editar</button>
                        </td>
                        <td>
							<form action = "<%= request.getServletContext() %>/DELETE" method = "POST">
                           		<input type = "hidden" name = "<%=Bill.ATR_BILL_ID %>" value = "<%=showBill.getId() %>">
                           		<button type = "submit" class="btn btn-danger">Eliminar</button>
                        	</form>
                        </td>
                	</tr>
					  
				<% } %>
				</tbody>
            </table>
        </div>
    </div>
	
	<br/>  