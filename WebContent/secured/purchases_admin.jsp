<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Purchase" %>
<%@ page import = "edu.ucam.pojos.Videogame" %>
<%@ page import = "edu.ucam.pojos.Bill" %>

<%@ page import = "edu.ucam.daos.PurchaseDAO" %>
<%@ page import = "edu.ucam.daos.VideogameDAO" %>
<%@ page import = "edu.ucam.daos.BillDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>
<%@ page import = 'edu.ucam.actions.admin.*' %>

	
<%
	pageContext.setAttribute("purchasesList", (new PurchaseDAO()).list());
	pageContext.setAttribute("videogamesList", (new VideogameDAO()).list());
	pageContext.setAttribute("billsList", (new BillDAO()).list());
%>
	
	
	<div id = "purchases-title" class = "col-12">
        <h3 class = "display-3">Compras</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-purchase-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
			
			<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Purchase.class.getName() %>" />
			
			<label for="purchase-input-id">ID: </label>
			<p><input id = "purchase-input-id" type = "text" class="form-control" placeholder = "ID de la Compra" name = "<%=Purchase.ATR_PURCHASE_ID %>" readonly></p>

		    <label for="purchase-input-amount">Cantidad: </label>
			<p><input id = "purchase-input-amount" type = "number" step = "1" min = "1"  class="form-control" placeholder = "Número..." name = "<%=Purchase.ATR_PURCHASE_AMOUNT %>" required></p>
		
			<label for="purchase-input-videogameid">ID Videojuego: </label>
			<p><select id = "purchase-input-videogameid" class="form-control" name = "<%=Purchase.ATR_PURCHASE_VIDEOGAMEID %>">
			  <option value="none" selected>Elige un Videojuego...</option>
			  <c:forEach var='videogame' items='${videogamesList}'>
			  	<option value='${videogame.id}'>${videogame.name}</option>  
			  </c:forEach>
			</select></p>
			
			<label for="purchase-input-billid">ID Factura: </label>
			<p><select id = "purchase-input-billid" class="form-control" name = "<%=Purchase.ATR_PURCHASE_BILLID %>">
			  <option value="none" selected>Elige una Factura...</option>
			  <c:forEach var='bill' items='${billsList}'>
			  	<option value='${bill.id}'>${bill.id}</option>  
			  </c:forEach>
			</select></p>
						
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-purchase-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST" style = "display: 'none';">
        
        	<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Update.ATR_ACTION %>'/>
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Purchase.class.getName() %>" />
			
			<label for="purchase-input-update-id">ID: </label>
			<p><input id = "purchase-input-update-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Purchase.ATR_PURCHASE_ID %>" readonly></p>

		    <label for="purchase-input-update-amount">Cantidad: </label>
			<p><input id = "purchase-input-update-amount" type = "number" step = "1" min = "1" class="form-control" placeholder = "Introduce el nombre de la categoría..." name = "<%=Purchase.ATR_PURCHASE_AMOUNT %>" required></p>
		
			<label for="purchase-input-update-videogameid">ID Videojuego: </label>
			<p><select id = "purchase-input-update-videogameid" class="form-control" name = "<%=Purchase.ATR_PURCHASE_VIDEOGAMEID %>">
			  <option value="none" selected>Elige un Videojuego...</option>
			  <c:forEach var='videogame' items='${videogamesList}'>
			  	<option value='${videogame.id}'>${videogame.name}</option>  
			  </c:forEach>
			</select></p>
			
			<label for="purchase-input-update-billid">ID Factura: </label>
			<p><select id = "purchase-input-update-billid" class="form-control" name = "<%=Purchase.ATR_PURCHASE_BILLID %>">
			  <option value="none" selected>Elige una Factura...</option>
			  <c:forEach var='bill' items='${billsList}'>
			  	<option value='${bill.id}'>${bill.id}</option>  
			  </c:forEach>
			</select></p>
			
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <a id = "input-edit-send" class="btn btn-secondary smooth-scroller" role="button" href = "#purchases-title" onclick = "cancelUpdatePurchase()" style = "margin-left: 10px;">Cancelar</a>
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
			   	
			   		<c:forEach var='purchase' items='${purchasesList}' varStatus=''>
			   		
			   			<% Purchase p = (Purchase) pageContext.getAttribute("purchase"); %>
			   		
			   			<tr>
	                     	<td>${purchase.id}</td>
	                     	<td>${purchase.amount}</td>
	                     	<td>${purchase.videogameId}</td>
	                     	<td>${purchase.billId}</td>
	                     	
	                        <td>
	                            <button type = "submit" class="btn btn-warning" onclick = "updatePurchase(<%= p.toJavaScriptFunction()%>)">Editar</button>
	                        </td>
	                        <td>
								<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
									<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%=Delete.ATR_ACTION %>'/>
	                           		<input type = "hidden" name = "<%=Purchase.ATR_PURCHASE_ID %>" value = "${purchase.id}">
	                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Purchase.class.getName() %>">   
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