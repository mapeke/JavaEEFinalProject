<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register</title>
  <%@include file="head.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container mt-3">
  <div class="row">
    <div class="col-6 mx-auto">
      <%
        String emailerror = request.getParameter("emailerror");
        if(emailerror!=null){
      %>
      <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Email is busy!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <%
        }
      %>
      <%
        String passworderror = request.getParameter("passworderror");
        if(passworderror!=null){
      %>
      <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Password are not same!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <%
        }
      %>
      <%
        String success = request.getParameter("success");
        if(success!=null){
      %>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        User created successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <%
        }
      %>
      <form action="/register" method="post">
        <div class="row">
          <div class="col-12">
            <label>EMAIL : </label>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col-12">
            <input type="email" class="form-control" name="email" required placeholder="Insert Email">
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-12">
            <label>PASSWORD : </label>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col-12">
            <input type="password" class="form-control" name="password" required placeholder="Insert Password">
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-12">
            <label>REPEAT PASSWORD : </label>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col-12">
            <input type="password" class="form-control" name="re_password" required placeholder="Repeat Password">
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-12">
            <label>FULL NAME : </label>
          </div>
        </div>
        <div class="row mt-2">
          <div class="col-12">
            <input type="text" class="form-control" name="full_name" required placeholder="Full Name">
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-12">
            <button class="btn btn-success">SIGN UP</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
