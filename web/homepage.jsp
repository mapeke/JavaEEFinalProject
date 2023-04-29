<%@ page import="entity.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All news</title>
    <%@include file="head.jsp"%>
</head>
<body>
    <%@include file="navbar.jsp"%>


    <div class="container mt-3">
        <%
            if (currentUser != null && currentUser.getRole()==1){
        %>
        <button type="button" class="btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#addNews">
            + Add News
        </button>
        <div class="modal fade" id="addNews" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="/addNews" method="post">
                                <div class="row">
                                    <div class="col-12">
                                        <label>
                                            TITLE :
                                        </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                        <input type="text" class="form-control" name="title" required
                                               placeholder="Insert title:">
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <label>
                                            CONTENT :
                                        </label>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-12">
                                                <textarea class="form-control" name="content"
                                                          placeholder="Insert content:" required
                                                          rows="10"></textarea>
                                    </div>
                                </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-success">ADD NEWS</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
        <div class="row mt-3">
            <div class="col-12">
                <%
                    ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                    if(news!=null){
                        for(News n : news){
                %>
                    <div class="p-5 mb-3" style="background-color: #dee1df;">
                        <a href="/news-details?id=<%=n.getId()%>">
                            <h3><%=n.getTitle()%></h3>
                        </a>
                        <p><%=n.getContent()%></p>
                        <p>
                            At <strong><%=n.getPostDate()%></strong>
                        </p>
                    </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
