<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.plateplan.user.User"
    import="com.plateplan.recipes.Recipe"
    import="java.util.ArrayList"
    import= "java.util.Iterator"
    
import="java.util.List" 
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
        
            <form class="profilesearch" action="ingredientss" method="get">
            <input hidden action="username" value=<%= userName %>>
                <input required class="searchbox" placeholder="Search an ingredient to get started...." type="text" name="ingredients">
                <button class="searchbutton buttonfill" type="submit"><p class="searchbuttontext">Search</p><img class="tinyimg roundcorners" src="./images/searchicon5050.png" alt=""></button>
            </form>
            
        </div>


        <div class="userinfopane maxheightparentnav">
         
           	 <p class="centerxygrid hellousertext">Hello,<br> <%= userName %>!</p>


       
      	<a href="" class="userprofilelink centerxygrid">
				            <img class="userpic centerxygrid" src="./images/user4848.png" alt="">
				</a>     

    </div>

</div>

    
    
    	<div class="profilecontent">
    			
    			
    			
    			<div class="contentbox">
    			
<form class="overflowform" action="userhomepage.jsp" method="get">

<%

System.out.println("userhomepage");
int size = (Integer) session.getAttribute("size");
//	List<Recipe> recipes = session.getAttribute("recipes");
//recipes stored here!

List<Recipe> recipes = (List<Recipe>) session.getAttribute("recipes");

if (recipes != null)
{



for(int i=0; i<recipes.size(); i++)
{

String name = recipes.get(i).getName().toString();
String id = recipes.get(i).getMealDbId();
String img = recipes.get(i).getThumbnailUrl();
%>



<button type="submit" class="filler recipe-card" name="" value=<%out.println(id); %> ><% out.println(name);%> <img class="recipe-card-image" src=<%out.print(img); %>></button>

<% 
}
}
%>


</form>
    			</div>
    			<div class="contentbox">
    			<div class="filler">
    			
    			<% 
    			String recipe;
    			recipe = request.getParameter("recipeid"); 
    			%>
    			 <%= recipe %>
    			
    			
    			</div>
    			</div>
    			
    				
    			<div class="contentbox">
    		<div class="filler">Add to favs/etc..</div>
    			</div>
    			
    	</div>
		<div class="footerdiv">
		<footer><p>© PlatePlan 2024</p></footer></div>
	</div>
</body>
</html>