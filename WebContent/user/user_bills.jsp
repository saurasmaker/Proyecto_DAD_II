<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
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
	
	pageContext.setAttribute("billsList", (new BillDAO()).listByUserId(thisUser.getId()));
%>

<html>
	<head>
		<jsp:include page="../mod/head.jsp" />
		<title>${sessionScope.ATR_USER_LOGGED.username} Bills Infodeo</title>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/user_bills_tools.js"></script>
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
						   		<c:forEach var='bill' items='${billsList}'>
						   			<%	pageContext.setAttribute("purchasesBillList", (new PurchaseDAO()).listByBillId(((Bill)pageContext.getAttribute("bill")).getId())); %>
						   			<%	pageContext.setAttribute("rentalsBillList",  (new RentalDAO()).listByBillId(((Bill)pageContext.getAttribute("bill")).getId())); %>
						   			<c:set var='total' value='${0}'/>
						   			<tr>
				                     	<td>${bill.id}</td>
				                     	<td>${bill.userId}</td>
				                        <td>${bill.billingDate}</td>
				                        <td>${bill.billingTime}</td>
				                        <td>${bill.paid}</td>
				                        <td>${bill.paidDate}</td>
				                        <td>${bill.paidTime}</td>
				                        <td>
				                        	<c:choose>
				                        		<c:when test='${not bill.paid}'>
					                        		<form action = "<%= request.getContextPath() %>/Controller" method = "POST">
														<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%= PayBill.ATR_ACTION %>'/>
						                           		<input type = "hidden" name = "<%=Bill.ATR_BILL_ID %>" value = "${bill.id}">
						                           		<input type = "hidden" name = "<%=Controller.ATR_OBJECT_CLASS %>" value = "<%=Bill.class.getName() %>">   
						                           		<button type = "submit" class="btn btn-danger">Pagar</button>
						                        	</form>
					                        	</c:when>
					                        	<c:otherwise>
					                        		<p><input type='checkbox' class='form-control' onclick='return false;'checked></p>
					                        	</c:otherwise>
				                        	</c:choose>
				                        </td>
				                        <td>
				                        	<a id="buttonShowHide${bill.id}" onclick='showOrHide("buttonShowHide${bill.id}");' class="btn btn-secondary" data-toggle="collapse" href="#collapse-show-bill-${bill.id}" role="button" aria-expanded="false" aria-controls="collapseExample">
				                        		Ver
				                        	</a>
			                       		</td>
						   			</tr>
						   			
						   			
						   			<tr>
										<td colspan="9">
											<div class="collapse row" id="collapse-show-bill-${bill.id}">

												<c:if test='${purchasesBillList.size() != 0}'>
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
														
															<c:forEach var='purchaseBill' items='${purchasesBillList}' varStatus=''>
																<c:set var='videogamePurchase' value='<%=new VideogameDAO().read(((Purchase)pageContext.getAttribute("purchaseBill")).getVideogameId(), SearchBy.ID) %>'/>
																<tr>
																	<td>${videogamePurchase.name}</td>
																	<td>${videogamePurchase.purchasePrice} &euro;</td>
																	<td>${purchaseBill.amount}</td>
																</tr>	
																<c:set var='total' value='${total + videogamePurchase.purchasePrice * purchaseBill.amount}'/>
															</c:forEach>
														
														</tbody>
													</table>
												</c:if>
													
												<c:if test='${rentalsBillList.size() != 0}'>
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
													
														<c:forEach var='rentalBill' items='${rentalsBillList}' varStatus=''>
															<c:set var='videogameRental' value='<%=new VideogameDAO().read(((Rental)pageContext.getAttribute("rentalBill")).getVideogameId(), SearchBy.ID) %>'/>
															<tr>
																<td>${videogameRental.name}</td>
																<td>${videogameRental.rentalPrice} &euro;</td>
																<td>${rentalBill.startDate}</td>
																
																<c:choose>
																	<c:when test='${rentalBill.returned}'>
																		<td>${rentalBill.endDate}</td>
																	</c:when>
																	<c:otherwise>
																		<td>
																			<form action= "<%= request.getContextPath() %>/Controller" method="post">
																				<input type='hidden' name='<%= Controller.ATR_SELECT_ACTION %>' value='<%=ReturnRental.ATR_ACTION %>'/>
																				<input type='hidden' name='<%= Rental.ATR_RENTAL_ID %>' value='${rentalBill.id}'/> 
																				<input type='submit' class='' value='Devolver'/>
																			</form>
																		</td>
																	</c:otherwise>
																</c:choose>
																
															</tr>	
															
															<c:choose>
																<c:when test='${rentalBill.returned}'>
																	<% pageContext.setAttribute("rentalPrice", 
																			((Videogame)pageContext.getAttribute("videogameRental")).getRentalPrice() 
																			* (1 + ChronoUnit.DAYS.between(((Rental)pageContext.getAttribute("rentalBill")).getStartDate().toLocalDate(),
																					((Rental)pageContext.getAttribute("rentalBill")).getEndDate().toLocalDate()))); %>
																</c:when>
																<c:otherwise>
																	<% pageContext.setAttribute("rentalPrice",
																			((Videogame)pageContext.getAttribute("videogameRental")).getRentalPrice() 
																				* (1 + ChronoUnit.DAYS.between(((Rental)pageContext.getAttribute("rentalBill")).getStartDate().toLocalDate(),
																						LocalDate.now()))); %>																	
																</c:otherwise>
															</c:choose>
															<c:set var='total' value='${total + rentalPrice}'/>
														</c:forEach>
													
													</tbody>
												</table>

												</c:if>

												<h4 style="padding-left: 10px">Total: <strong><%=df.format(Float.parseFloat(pageContext.getAttribute("total").toString())) %> &euro;</strong></h4>
											</div>
										</td>
									</tr>
						   		</c:forEach>
							</tbody>
			            </table>
			        </div>

				</div>

			</div>
			
			<jsp:include page="../mod/footer.jsp" />
			
		</div>
	</body>
</html>