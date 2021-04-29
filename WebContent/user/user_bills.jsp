<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import ="java.time.temporal.ChronoUnit" %>
<%@ page import="java.sql.Date" %>
<%@ page import="edu.ucam.enums.SearchBy" %>
    
<%@ page import="edu.ucam.servlets.Controller" %>
<%@ page import="edu.ucam.actions.user.ReturnRental" %>

<%@ page import="edu.ucam.pojos.User" %>
<%@ page import="edu.ucam.pojos.Videogame" %>
<%@ page import="edu.ucam.pojos.Bill" %>
<%@ page import="edu.ucam.pojos.Purchase" %>
<%@ page import="edu.ucam.pojos.Rental" %>

<%@ page import="edu.ucam.daos.VideogameDAO" %>
<%@ page import="edu.ucam.daos.BillDAO" %>
<%@ page import="edu.ucam.daos.PurchaseDAO" %>
<%@ page import="edu.ucam.daos.RentalDAO" %>
<%@ page import="edu.ucam.actions.user.PayBill" %>


<!DOCTYPE html>

<% 
	User thisUser = (User)session.getAttribute(User.ATR_USER_LOGGED); 
	DecimalFormat df = new DecimalFormat();
	df.setMaximumFractionDigits(2);
%>

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
			                     	<th scope="col">Fecha de Facturaci&oacute;n</th>
			                     	<th scope="col">Hora de Facturaci&oacute;n</th>
									<th scope="col">Pagado</th>
									<th scope="col">Fecha de Pago</th>
									<th scope="col">Hora de Pago</th>
			                        <th scope="col">Pagar</th>
			                        <th scope="col">Ver Factura</th>
			                  	</tr>
			               	</thead>
						   	<tbody>
			                <% ArrayList<Bill> billsList = (new BillDAO()).listByUserId(thisUser.getId());
						  	for(int i = 0; i < billsList.size(); ++i) {
								Bill showBill = billsList.get(i); 
								
								ArrayList<Purchase> purchasesBillList = (new PurchaseDAO()).listByBillId(showBill.getId());
								ArrayList<Rental> rentalsBillList = (new RentalDAO()).listByBillId(showBill.getId());
							%>
							
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
			                        <td>
			                        	<a class="btn btn-secondary" data-toggle="collapse" href="#collapse-show-bill-<%=showBill.getId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
			                        		Ver
			                        	</a>
			                        </td>
			                	</tr>
								  
								<tr>
									<td colspan="9">
									<div class="collapse row" id="collapse-show-bill-<%=showBill.getId()%>">
									
										<% Float total = 0f; 
											
											if(purchasesBillList.size()!=0){
										%>
									
											<h4 style="padding-left: 10px">Compras</h4>													
											<table class="table">
												<thead>
													<tr>
														<th scope="col">Videojuego</th>
														<th scope="col">Coste</th>
														<th scope="col">Cantidad / D&iacute;as</th>
													</tr>
												</thead>
												
												<tbody>
												
											<!-- PURCHASES OF THE BILLL -->
											<% for(int j = 0; j < purchasesBillList.size(); ++j) { 
												Purchase showPurchase = purchasesBillList.get(j);
												Videogame showVideogame = (new VideogameDAO()).read(showPurchase.getVideogameId(), SearchBy.ID);
												total += showPurchase.getAmount() * showVideogame.getPurchasePrice();
												%>
													<tr>
														<td><%=showVideogame.getName() %></td>
														<td><%=showVideogame.getPurchasePrice() %> &euro;</td>
														<td><%=showPurchase.getAmount() %></td>
													</tr>										
											<% } %>
											
												</tbody>										
											</table>
											
											<% 
												}
											
												if(rentalsBillList.size()!=0) {
											%>
											<h4 style="padding-left: 10px">Alquileres</h4>													
											<table class="table">
												<thead>
													<tr>
														<th scope="col">Videojuego</th>
														<th scope="col">Coste / D&iacute;a</th>
														<th scope="col">Fecha alquiler</th>
														<th scope="col">Fecha devoluci&oacute;n</th>
													</tr>
												</thead>
												
												<tbody>
											<!-- RENTALS OF THE BILLL -->
											<% for(int j = 0; j < rentalsBillList.size(); ++j) { 
												Rental showRental = rentalsBillList.get(j);
												Videogame showVideogame = (new VideogameDAO()).read(showRental.getVideogameId(), SearchBy.ID);
												
												if(showRental.isReturned())
													total += showVideogame.getRentalPrice() * (1 + ChronoUnit.DAYS.between(showRental.getStartDate().toLocalDate(), showRental.getEndDate().toLocalDate()));
												
												else
													total += showVideogame.getRentalPrice() * (1 + ChronoUnit.DAYS.between(showRental.getStartDate().toLocalDate(), LocalDate.now()));
												%>
													<tr>
														<td><%=showVideogame.getName() %></td>
														<td><%=showVideogame.getRentalPrice() %> &euro; / d&iacute;a</td>
														<td><%=showRental.getStartDate() %></td>
														<%if(showRental.isReturned()) out.print("<td>" + showRental.getEndDate() + "</td>"); else { %>
															<td>
																<form action= "<%= request.getContextPath() %>/Controller" method="post">
																	<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%=ReturnRental.ATR_ACTION %>'/>
																	<input type='hidden' name='<%= Rental.ATR_RENTAL_ID %>' value='<%=showRental.getId() %>'/> 
																	<input type='submit' class='' value='Devolver'/>
																</form>
															</td>
														<% } %>
													</tr>										
											<% } %>
											
												</tbody>										
											</table>
											
											<% } %>
											<h4 style="padding-left: 10px">Total: <strong><%=df.format(total) %> &euro;</strong></h4>
										</div>
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