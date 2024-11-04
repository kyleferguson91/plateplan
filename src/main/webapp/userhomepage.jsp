<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.plateplan.user.User"
    import="com.plateplan.recipes.Recipe"
    %>




<%
String userName = "";
    User user = (User) session.getAttribute("user");

    // Check if the user object is not null
    if (user != null) {

  userName = user.getUsername();
  

    }
%>






<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/styles.css">
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<title>Home Page</title>
</head>
<body>
	
<div class="mainlayout">
    <div class="navcontainer">
        <div class="titlecontainer maxheightparentnav">
            
            <img class=" medimg" src="./images/pplogo2.png" alt="">
        
            <p class="pptitle ">PlatePlan</p>
        </div>


        <div class="searchcontainer maxheightparentnav">
            <form class="profilesearch" action="">
                <input class="searchbox" placeholder="Search a recipe to get started...." type="text">
                <button class="searchbutton buttonfill"><p class="searchbuttontext">Search</p><img class="tinyimg roundcorners" src="./images/searchicon5050.png" alt=""></button>
            </form>
            
        </div>
<% System.out.println("get username @ userhomepage.jsp = " +  user.getUsername()); %>

        <div class="userinfopane maxheightparentnav">
         
           	 <p class="centerxygrid hellousertext">Hello,<br> <%= userName %>!</p>


       
      	<a href="" class="userprofilelink centerxygrid">
				            <img class="userpic centerxygrid" src="./images/user4848.png" alt="">
				</a>     

    </div>
    
    
    	<div class="profilecontent">
    			
    			<div class="">
    			Recipes
    			</div>
    			
    			<div>
    			Etc
    			</div>
    			
    				
    			<div>
    			Buttons?
    			</div>
    			
    	</div>
</div>


		<div class="footerdiv">
		<footer><p>© PlatePlan 2024</p></footer></div>
	</div>
</body>
</html>