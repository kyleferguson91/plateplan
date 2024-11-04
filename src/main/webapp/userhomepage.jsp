<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%  

String user = session.getAttribute("user").toString();

System.out.println("current user is " + user); 


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
                <input class="searchbox roundcorners" type="text">
                <button class="searchbutton"><img class="tinyimg roundcorners" src="./images/searchicon5050.png" alt=""></button>
            </form>
            
        </div>


        <div class="userinfopane maxheightparentnav">
            <p class="centerxygrid">Hello, Username!</p>

				<a href="" class="userprofilelink">
				            <img class="userpic centerxygrid" src="./images/user4848.png" alt="">
				</a>            
        </div>

    </div>
</div>


		<div class="footerdiv">
		<footer><p>© PlatePlan 2024</p></footer></div>
	</div>
</body>
</html>