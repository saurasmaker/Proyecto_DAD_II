window.onload = function(){
  document.getElementById("update_user_form").style.display = "none";
  document.getElementById("update_product_form").style.display = "none";
  document.getElementById("update_bill_form").style.display = "none";
};

//USERS
  function edit_user(id, username, email, password) {
    
    document.getElementById("create_user_form").style.display = "none";
    document.getElementById("update_user_form").style.display = "block";

    document.getElementById("user-input-edit-id").value = id;
    document.getElementById("user-input-edit-username").value = username;
    document.getElementById("user-input-edit-email").value = email;
    document.getElementById("user-input-edit-pass").value = password;

  }

  function cancel_edit_user(){
    document.getElementById("create_user_form").style.display = "block";
    document.getElementById("update_user_form").style.display = "none";
  }


//PRODUCTS
  function edit_product(id, name, trademark, model, description, price, stock) {
    document.getElementById("create_product_form").style.display = "none";
    document.getElementById("update_product_form").style.display = "block";

    document.getElementById("product-input-edit-id").value = id;
    document.getElementById("product-input-edit-name").value = name;
    document.getElementById("product-input-edit-trademark").value = trademark;
    document.getElementById("product-input-edit-model").value = model;
    document.getElementById("product-input-edit-description").value = description;
    document.getElementById("product-input-edit-stock").value = stock;
    document.getElementById("product-input-edit-price").value = price;

  }

  function cancel_edit_product(){
    document.getElementById("create_product_form").style.display = "block";
    document.getElementById("update_product_form").style.display = "none";
  }


//BILLS
  function edit_bill(id, datebuy, idproduct, iduser, sendingaddress) {
    document.getElementById("create_bill_form").style.display = "none";
    document.getElementById("update_bill_form").style.display = "block";

    document.getElementById("bill-input-edit-id").value = id;
    document.getElementById("bill-input-edit-datebuy").value = datebuy;
    document.getElementById("bill-input-edit-idproduct").value = idproduct;
    document.getElementById("bill-input-edit-iduser").value = iduser;
    document.getElementById("bill-input-edit-sendingaddress").value = sendingaddress;
  }

  function cancel_edit_bill(){
    document.getElementById("create_bill_form").style.display = "block";
    document.getElementById("update_bill_form").style.display = "none";
  }