<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page import = "java.util.ArrayList" %>

<%@ page import = "edu.ucam.pojos.Rental" %>
<%@ page import = "edu.ucam.pojos.Videogame" %>
<%@ page import = "edu.ucam.pojos.User" %>
<%@ page import = "edu.ucam.pojos.Bill" %>

<%@ page import = "edu.ucam.daos.RentalDAO" %>
<%@ page import = "edu.ucam.daos.VideogameDAO" %>
<%@ page import = "edu.ucam.daos.BillDAO" %>

<%@ page import = "edu.ucam.servlets.Controller" %>
<%@ page import = 'edu.ucam.actions.admin.*' %>


<%
	pageContext.setAttribute("rentalsList", new RentalDAO().list());
	pageContext.setAttribute("videogamesList", new VideogameDAO().list());
	pageContext.setAttribute("billsList", new BillDAO().list());
%>


	<div id = "rentals-title" class = "col-12">
        <h3 class = "display-3">Alquileres</h3>
        <hr width = "25%" align = "left"/>
        <br/>
    </div>
	  
	<div class = "col-lg-4 col-md-6 col-sm-12">
      	<form id = "create-rental-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST">
			
			<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Create.ATR_ACTION %>'/>
			<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Rental.class.getName() %>" />
			
			<label for="rental-input-id">ID: </label>
			<p><input id = "rental-input-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Rental.ATR_RENTAL_ID %>" readonly></p>

			<label for="rental-input-startdate">Fecha Inicio: </label>
			<p><input id = "rental-input-startdate" type = "date" class="form-control" name = "<%=Rental.ATR_RENTAL_STARTDATE %>" required></p>
			
			<label for="rental-input-startdate">Hora Inicio: </label>
			<p><input id = "rental-input-starttime" type = "time" step = "1" class="form-control" name = "<%=Rental.ATR_RENTAL_STARTTIME %>" required></p>
			
			<label for="rental-input-enddate">Fecha Devolución: </label>
			<p><input id = "rental-input-enddate" type = "date" class="form-control" name = "<%=Rental.ATR_RENTAL_ENDDATE %>" required></p>
			
			<label for="rental-input-enddate">Hora Devolución: </label>
			<p><input id = "rental-input-endtime" type = "time" step = "1" class="form-control" name = "<%=Rental.ATR_RENTAL_ENDTIME %>" required></p>
			
			<label for="rental-input-returned">Devuelto: </label>
			<p><input id = "rental-input-returned" type = "checkbox" class="form-control" name = "<%=Rental.ATR_RENTAL_RETURNED %>"></p>
			
			<label for="rental-input-videogameid">ID Videojuego: </label>
			<p><select id = "rental-input-videogameid" class="form-control" name = "<%=Rental.ATR_RENTAL_VIDEOGAMEID %>">
			  <option value="none" selected>Elige un Videojuego...</option>
			  <c:forEach var='videogame' items='${videogamesList}' varStatus=''>
			  	<option value="${videogame.id}">${videogame.name}</option>
			  </c:forEach>
			</select></p>
						
			<label for="rental-input-billid">ID Factura: </label>
			<p><select id = "rental-input-billid" class="form-control" name = "<%=Rental.ATR_RENTAL_BILLID %>">
			  <option value="none" selected>Elige una Factura...</option>
			  <c:forEach var='bill' items='${billsList}' varStatus=''>
			  	<option value="${bill.id}">${bill.id}</option>
			  </c:forEach>
			</select></p>
			
            <p><input id = "input-send" type = "submit" class="btn btn-primary" value = "Crear"></p>
        </form>



        <form id = "update-rental-form" class = "form-group" action = "<%= request.getContextPath() %>/Controller" method = "POST" style = "display: 'none';">
        
        	<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Update.ATR_ACTION %>'/>
            <input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Rental.class.getName() %>" />
			
			<label for="rental-input-update-id">ID: </label>
			<p><input id = "rental-input-update-id" type = "text" class="form-control" placeholder = "Identificador de la categoría" name = "<%=Rental.ATR_RENTAL_ID %>" readonly></p>

		    <label for="rental-input-update-startdate">Fecha Inicio: </label>
			<p><input id = "rental-input-update-startdate" type = "date" class="form-control" name = "<%=Rental.ATR_RENTAL_STARTDATE %>" required></p>
			
			<label for="rental-input-update-startdate">Hora Inicio: </label>
			<p><input id = "rental-input-update-starttime" type = "time" step = "1" class="form-control" name = "<%=Rental.ATR_RENTAL_STARTTIME %>" required></p>
			
			<label for="rental-input-update-enddate">Fecha Devolución: </label>
			<p><input id = "rental-input-update-enddate" type = "date" class="form-control" name = "<%=Rental.ATR_RENTAL_ENDDATE %>" required></p>
			
			<label for="rental-input-update-enddate">Hora Devolución: </label>
			<p><input id = "rental-input-update-endtime" type = "time" step = "1" class="form-control" name = "<%=Rental.ATR_RENTAL_ENDTIME %>" required></p>
			
			<label for="rental-input-update-returned">Devuelto: </label>
			<p><input id = "rental-input-update-returned" type = "checkbox" class="form-control" name = "<%=Rental.ATR_RENTAL_RETURNED %>"></p>
			
			<label for="rental-input-update-videogameid">ID Videojuego: </label>
			<p><select id = "rental-input-update-videogameid" class="form-control" name = "<%=Rental.ATR_RENTAL_VIDEOGAMEID %>">
			  <option value="none" selected>Elige un Videojuego...</option>
			  <c:forEach var='videogame' items='${videogamesList}' varStatus=''>
			  	<option value="${videogame.id}">${videogame.name}</option>
			  </c:forEach>
			</select></p>
						
			<label for="rental-input-update-billid">ID Factura: </label>
			<p><select id = "rental-input-update-billid" class="form-control" name = "<%=Rental.ATR_RENTAL_BILLID %>">
			  <option value="none" selected>Elige una Factura...</option>
			  <c:forEach var='bill' items='${billsList}' varStatus=''>
			  	<option value="${bill.id}">${bill.id}</option>
			  </c:forEach>
			</select></p>
			
            <p>
                <input id = "input-edit-send" type = "submit" class="btn btn-primary" value = "Editar">
                <a id = "input-edit-send" class="btn btn-secondary smooth-scroller" role="button" href = "#rentals-title" onclick = "cancelUpdateRental()" style = "margin-left: 10px;">Cancelar</a>
            </p>
        </form>
    </div>

    
	  
	<div class = "col-lg-8 col-md-6 col-sm-12">
        <div class = "table-responsive" style = " max-height: 600px !important; overflow: auto;">
            <table class="table table-striped">
               	<thead class = "thead-dark">
                  	<tr>
                     	<th scope="col">ID</th>
                     	<th scope="col">ID Factura</th>
                     	<th scope="col">ID Videojuego</th>
                     	<th scope="col">Fecha Inicio</th>
                        <th scope="col">Fecha Devolución</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                  	</tr>
               	</thead>
			   	<tbody>
			   	
			   		<c:forEach var='rental' items='${rentalsList}' varStatus=''>
			   		
			   			<% Rental r = (Rental) pageContext.getAttribute("rental"); %>
			   		
			   			<tr>
	                     	<td>${rental.id}</td>
	                     	<td>${rental.billId}</td>
	                     	<td>${rental.videogameId}</td>
	                     	<td>${rental.startDate}</td>
	                     	<td>${rental.endDate}</td>
	                     	
	                        <td>
	                            <button type = "submit" class="btn btn-warning" onclick = "updateRental(<%=r.toJavaScriptFunction() %>)">Editar</button>
	                        </td>
	                        <td>
								<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
									<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= Delete.ATR_ACTION %>'/>
	                           		<input type = "hidden" name = "<%=Rental.ATR_RENTAL_ID %>" value = '${rental.id}'>
	                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Rental.class.getName() %>">                
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