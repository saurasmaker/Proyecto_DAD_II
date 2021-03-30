<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="edu.ucam.pojos.*" %> 


	<!-- SCRIPTS -->
	<script src="js/login_tools.js"></script>
    <script src="js/register_tools.js"></script>
    
    <!-- LOGIN -->
	<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <form class="modal-content" action= "<%= request.getContextPath() %>/login" method="post">
	      
	      <div class="modal-header text-center">
	        <h4 class="modal-title w-100 font-weight-bold">Login</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      
	      <div class="modal-body mx-3">
	        <div class="md-form mb-5">
	          <i class="fas fa-user prefix grey-text"></i>
	          <input type="text" id="loginForm-email" class="form-control validate" name="<%= User.ATR_USER_USERNAME %>" required>
	          <label data-error="wrong" data-success="right" for="loginForm-email">Your username</label>
	        </div>
	
	        <div class="md-form mb-4">
	          <i class="fas fa-lock prefix grey-text"></i>
	          <input type="password" id="login-form-pass" class="form-control validate" name = "<%=User.ATR_USER_PASSWORD %>" required>
	          <label data-error="wrong" data-success="right" for="login-form-pass" >Your password</label>
	        </div>
	
	        <div class="md-form mb-4">
	          <input type="checkbox" id="loginForm-check" name = "SHOWPASSWORD" onclick = "showPasswordLog()">
	          <label data-error="wrong" data-success="right" for="loginForm-check" >Show password.</label>
	       </div>
	
	      </div>
	      
	      <div class="modal-footer d-flex justify-content-center">
	        <input type = "submit" value = "Login" class="btn btn-dark">
	      </div>
	      
	       	<div class="modal-footer d-flex justify-content-center">
				<p>You do not have an account? CLICK <a href = "#" data-toggle="modal" data-target="#modalRegisterForm">HERE</a> to create one.</p>      
			</div>
	
	    </form>
	  </div>
	</div>


	<!-- SIGN UP -->
	<div class="modal fade" id="modalRegisterForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	  aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <form class="modal-content" action = "<%= request.getContextPath() %>/signup" method = "post">
	      <div class="modal-header text-center">
	        <h4 class="modal-title w-100 font-weight-bold">Sign up</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      
	      
	      <div class="modal-body mx-3">
	      	<div class="md-form mb-4">
	          <i class="fas fa-user prefix grey-text"></i>
	          <input type="text" id="defaultForm-username" name="<%=User.ATR_USER_USERNAME %>" class="form-control validate" required>
	          <label data-error="wrong" data-success="right" for="defaultForm-pass">Username</label>
	      	</div>
	      
	        <div class="md-form mb-5">
	          <i class="fas fa-envelope prefix grey-text"></i>
	          <input type="email" id="defaultForm-email" name = "<%=User.ATR_USER_EMAIL %>" class="form-control validate" required>
	          <label data-error="wrong" data-success="right" for="defaultForm-email">Email</label>
	       </div>
	
	       <div class="md-form mb-4">
	          <i class="fas fa-lock prefix grey-text"></i>
	          <input type="password" id="reg-form-pass" name = "<%=User.ATR_USER_PASSWORD %>" class="form-control validate" required>
	          <label data-error="wrong" data-success="right" for="reg-form-pass">Password</label>
	       </div>
	        
	       <div class="md-form mb-4">
	          <i class="fas fa-lock prefix grey-text"></i>
	          <input type="password" id="reg-form-repeatpass" name = "REPEATPASSWORD"class="form-control validate" required>
	          <label data-error="wrong" data-success="right" for="reg-form-repeatpass">Repeat password</label>
	       </div>
	
	       <div class="md-form mb-4">
	          <input type="checkbox" id="reg-form-check" name = "SHOWPASSWORD" onclick = "showPasswordReg()">
	          <label data-error="wrong" data-success="right" for="reg-form-check" >Show password.</label>
	       </div>
	
	      </div>
	      <div class="modal-footer d-flex justify-content-center">
	        <input type = "submit" value = "Register" class="btn btn-dark">
	      </div>
	    </form>
	  </div>
	</div>