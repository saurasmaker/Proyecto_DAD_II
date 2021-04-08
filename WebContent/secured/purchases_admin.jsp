<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Purchase" %>
<%@ page import = "edu.ucam.pojos.Videogame" %>
<%@ page import = "edu.ucam.pojos.Bill" %>

<%@ page import = "edu.ucam.daos.PurchaseDAO" %>
<%@ page import = "edu.ucam.daos.VideogameDAO" %>
<%@ page import = "edu.ucam.daos.BillDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>

<div class = "col-12">
        <h3 class = "display-3">Compras</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-purchase-form" class = "form-group" action = "<%= request.getContextPath() %>/CREATE" method = "POST">
			
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Purchase.class.getName() %>" />
			
			<label for="purchase-input-id">ID: </label>
			<p><input id = "purchase-input-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Purchase.ATR_PURCHASE_ID %>" readonly></p>

		    <label for="purchase-input-amount">Cantidad: </label>
			<p><input id = "purchase-input-name" type = "number" step = "1" min = "1"  class="form-control" placeholder = "Introduce el nombre de la categoría..." name = "<%=Purchase.ATR_PURCHASE_AMOUNT %>" required></p>
		
			<label for="purchase-input-videogameid">ID Videojuego: </label>
			<p><select id = "assessment-input-videogameid" class="form-control" name = "<%=Purchase.ATR_PURCHASE_VIDEOGAMEID %>">
			  <option value="none" selected>Select a User...</option>
			  <% ArrayList<Videogame> videogamesPurchaseList = (new VideogameDAO()).list();
			  for(int i = 0; i < videogamesPurchaseList.size(); ++i) { %>
				  <option value="<%=videogamesPurchaseList.get(i).getId() %>"><%=videogamesPurchaseList.get(i).getName() %></option>
			  <% } %>
			</select></p>
			
			<label for="purchase-input-billid">ID Factura: </label>
			<p><select id = "assessment-input-billid" class="form-control" name = "<%=Purchase.ATR_PURCHASE_BILLID %>">
			  <option value="none" selected>Select a User...</option>
			  <% ArrayList<Bill> billsPurchaseList = (new BillDAO()).list();
			  for(int i = 0; i < billsPurchaseList.size(); ++i) { %>
				  <option value="<%=billsPurchaseList.get(i).getId() %>"><%=billsPurchaseList.get(i).getId() %></option>
			  <% } %>
			</select></p>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-purchase-form" class = "form-group" action = "<%= request.getContextPath() %>/UPDATE" method = "POST" style = "display: 'none';">
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Purchase.class.getName() %>" />
			
			<label for="purchase-input-update-id">ID: </label>
			<p><input id = "purchase-input-update-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Purchase.ATR_PURCHASE_ID %>" readonly></p>

		    <label for="purchase-input-update-amount">Cantidad: </label>
			<p><input id = "purchase-input-update-name" type = "number" step = "1" min = "1" class="form-control" placeholder = "Introduce el nombre de la categoría..." name = "<%=Purchase.ATR_PURCHASE_AMOUNT %>" required></p>
		
			<label for="purchase-input-update-videogameid">ID Videojuego: </label>
			<p><select id = "assessment-input-update-userid" class="form-control" name = "<%=Purchase.ATR_PURCHASE_VIDEOGAMEID %>">
			  <option value="none" selected>Select a User...</option>
			  <% for(int i = 0; i < videogamesPurchaseList.size(); ++i) { %>
				  <option value="<%=videogamesPurchaseList.get(i).getId() %>"><%=videogamesPurchaseList.get(i).getName() %></option>
			  <% } %>
			</select></p>
			
			<label for="purchase-input-update-billid">ID Factura: </label>
			<p><select id = "assessment-update-input-userid" class="form-control" name = "<%=Purchase.ATR_PURCHASE_BILLID %>">
			  <option value="none" selected>Select a User...</option>
			  <% for(int i = 0; i < billsPurchaseList.size(); ++i) { %>
				  <option value="<%=billsPurchaseList.get(i).getId() %>"><%=billsPurchaseList.get(i).getId() %></option>
			  <% } %>
			</select></p>
			
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <button id = "input-edit-send" class="btn btn-secondary" role="button" onclick = "cancelUpdatePurchase()" style = "margin-left: 10px;">Cancelar</button>
            </p>
        </form>
    </div>

    
	  
	<div class = "col-lg-8 col-md-6 col-sm-12">
        <div class = "table-responsive" style = " max-height: 600px !important; overflow: auto;">
            <table class="table table-striped">
               	<thead class = "thead-dark">
                  	<tr>
                     	<th scope="col">ID</th>
                     	<th scope="col">Cantidad</th>
                     	<th scope="col">ID Videojuego</th>
                     	<th scope="col">ID Factura</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
                <% ArrayList<Purchase> purchasesPurchaseList = (new PurchaseDAO()).list(); 
			  	for(int i = 0; i < purchasesPurchaseList.size(); ++i) {
			  		Purchase showPurchase = purchasesPurchaseList.get(i); %>
					<tr>
                     	<td><%=showPurchase.getId() %></td>
                     	<td><%=showPurchase.getAmount() %></td>
                     	<td><%=showPurchase.getVideogameId() %></td>
                     	<td><%=showPurchase.getBillId() %></td>
                     	
                        <td>
                            <button type = "submit" class="btn btn-warning" onclick = "updatePurchase(<%= showPurchase.toJavaScriptFunction()%>)">Editar</button>
                        </td>
                        <td>
							<form action = "<%= request.getContextPath() %>/DELETE" method = "POST">
                           		<input type = "hidden" name = "<%=Purchase.ATR_PURCHASE_ID %>" value = "<%=showPurchase.getId() %>">
                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Purchase.class.getName() %>">   
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