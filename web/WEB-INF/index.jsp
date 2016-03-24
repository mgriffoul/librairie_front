<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Librarie en ligne</title>

    <!-- Bootstrap Core CSS -->
    <link href="inc/css/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="inc/css/css/shop-homepage.css" rel="stylesheet">
</head>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="./index">Accueil</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <!-- Collect the nav links, forms, and other content for toggling -->
                <jsp:include page="/WEB-INF/view/menu.jsp" />
                <!-- /.navbar-collapse -->

            </div>
        </div>
        <!-- /.container -->
    </nav>


    <!-- Page Content -->
    <div class="container">
        <div class="row">
            
            <!--Section index-->
            <% if (request.getParameter("section") == null) { %>
            <div class="col-md-3">
                <jsp:include page="/WEB-INF/view/sidebarCategorie.jsp"/>

            </div>

            <div class="col-md-9">
                <jsp:include page="/WEB-INF/view/carousel.jsp"/>
                <jsp:include page="/WEB-INF/view/smallBook.jsp"/>
                
            </div>  
            <%}%>

            <!--Section focus produit-->
            <% if ("focus".equals(request.getParameter("section"))) { %>
            <div class="col-md-3">
                <jsp:include page="/WEB-INF/view/sidebarCategorie.jsp"/>

            </div>

            <div class="col-md-9">
                <jsp:include page="/WEB-INF/view/focus.jsp"/>
               
                
            </div>  
            <%}%>
            
            
            <!--Section catalogue après recherche de l'utilisateur-->
             <% if ("rech".equals(request.getParameter("section"))) { %>
            <div class="col-md-3">
                <jsp:include page="/WEB-INF/view/sidebarCategorie.jsp"/>

            </div>

            <div class="col-md-9">
                <jsp:include page="/WEB-INF/view/recherche.jsp"/>
               
                
            </div>  
            <%}%>
            
            
            
            
            
            
            <!--Section catalogue categorie-->
             <% if ("cat".equals(request.getParameter("section"))) { %>
            <div class="col-md-3">
                <jsp:include page="/WEB-INF/view/sidebarCategorie.jsp"/>

            </div>

            <div class="col-md-9">
                <jsp:include page="/WEB-INF/view/catalogueCategorie.jsp"/>
               
                
            </div>  
            <%}%>
            
            
            
            
            <%if ("user".equals(request.getParameter("section"))) {%>
            <%if ("reg".equals(request.getParameter("action"))) {%>
            <jsp:include page="/WEB-INF/view/register.jsp"/>
            <%}%>
            <%}%>
            <!-- affichage compte client -->
            <!-- voir pour mettre un if cookie on renvoit vers acc -->
            <!-- si pas de cookie on renvoit vers login.jsp -->
            <%if ("acc".equals(request.getParameter("section"))) {%>
            <jsp:include page="/WEB-INF/view/client.jsp"/>
            <%}%>

        </div>





        <!--
        <div class="col-md-3">
            <p class="lead">Shop Name</p>
            <div class="list-group">
                <a href="#" class="list-group-item active">Category 1</a>
                <a href="#" class="list-group-item">Category 2</a>
                <a href="#" class="list-group-item">Category 3</a>
            </div>
        </div>
    
        <div class="col-md-9">
    
            <div class="thumbnail">
                <img class="img-responsive" src="http://placehold.it/800x300" alt="">
                <div class="caption-full">
                    <h4 class="pull-right">$24.99</h4>
                    <h4><a href="#">Product Name</a>
                    </h4>
                    <p>See more snippets like these online store reviews at <a target="_blank" href="http://bootsnipp.com">Bootsnipp - http://bootsnipp.com</a>.</p>
                    <p>Want to make these reviews work? Check out
                        <strong><a href="http://maxoffsky.com/code-blog/laravel-shop-tutorial-1-building-a-review-system/">this building a review system tutorial</a>
                        </strong>over at maxoffsky.com!</p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
                </div>
                <div class="ratings">
                    <p class="pull-right">3 reviews</p>
                    <p>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        4.0 stars
                    </p>
                </div>
            </div>
    
            <div class="well">
    
                <div class="text-right">
                    <a class="btn btn-success">Leave a Review</a>
                </div>
    
                <hr>
    
                <div class="row">
                    <div class="col-md-12">
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        Anonymous
                        <span class="pull-right">10 days ago</span>
                        <p>This product was great in terms of quality. I would definitely buy another!</p>
                    </div>
                </div>
    
                <hr>
    
                <div class="row">
                    <div class="col-md-12">
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        Anonymous
                        <span class="pull-right">12 days ago</span>
                        <p>I've alredy ordered another one!</p>
                    </div>
                </div>
    
                <hr>
    
                <div class="row">
                    <div class="col-md-12">
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        Anonymous
                        <span class="pull-right">15 days ago</span>
                        <p>I've seen some better than this, but not at this price. I definitely recommend this item.</p>
                    </div>
                </div>
    
            </div>
    
        </div>
    </div>
    !-->
    </div>
    <!-- /.container -->
    <jsp:include page="/WEB-INF/view/footer.jsp" />

    <!-- jQuery -->
   
    <script src="/librairie_git_front/inc/css/js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
   
    <script src="/librairie_git_front/inc/css/js/bootstrap.min.js"></script>
    
    
</body>

</html>



