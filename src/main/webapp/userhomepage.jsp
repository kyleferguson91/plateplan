<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.plateplan.user.User"
    import="com.plateplan.recipes.Recipe"
    import="java.util.ArrayList"
    import="java.util.Iterator"
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
            <img class="medimg" src="./images/pplogo2.png" alt="">
            <p class="pptitle">PlatePlan</p>
        </div>

        <div class="searchcontainer maxheightparentnav">
            <form class="profilesearch" action="ingredientss" method="get">
                <input hidden action="username" value="<%= userName %>">
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
            // List<Recipe> recipes = session.getAttribute("recipes");
            List<Recipe> recipes = (List<Recipe>) session.getAttribute("recipes");

            if (recipes != null && recipes.size() > 1) {
                for (int i = 0; i < recipes.size(); i++) {
                    String name = recipes.get(i).getName().toString();
                    String id = recipes.get(i).getMealDbId();
                    String img = recipes.get(i).getThumbnailUrl();
                    int calories = recipes.get(i).getCalories();
                    String protein = String.valueOf(recipes.get(i).getProtein());
                    String fat = String.valueOf(recipes.get(i).getFat());
                    String carbs = String.valueOf(recipes.get(i).getCarbs());
					String imgurl = String.valueOf(recipes.get(i).getThumbnailUrl());
                    
            %>

            <form class="overflowform" action="userhomepage.jsp" method="get">
                <button type="submit" class="filler recipe-card" name="recipeid" value="<%= id %>">
                    <%= name %>
                    <img class="recipe-card-image" src="<%= img %>">
                </button>
                <input type="hidden" name="calories" value="<%= calories %>">
                 <input type="hidden" name="name" value="<%= name %>">
                 <input type="hidden" name="protein" value="<%= protein %>">
                  <input type="hidden" name="fat" value="<%= fat %>">
                   <input type="hidden" name="carbs" value="<%= carbs%>">
                   <input type="hidden" name="imgurl" value="<%= imgurl%>">
                    
            </form>

            <%
                }
            }
            %>

        </div>

        <div class="contentbox">
            <div class="filler">

            <%
            // we are passing recipe id, we can get info from this id via recipe list
            String recipe;
            recipe = request.getParameter("recipeid");
            if (recipe == null) {
                recipe = "Search for a recipe, or none have been found!";
            }

            String name;
            name = request.getParameter("name");
            if (name == null) {
                name = "";
            }
            
            
            String protein;
            protein = request.getParameter("protein");
            if (protein == null) {
            	protein = "";
            }
                
                String fat;
                fat = request.getParameter("fat");
                if (fat == null) {
                	fat = "";
                }
                    
                    String carbs;
                    carbs = request.getParameter("carbs");
                    if (carbs == null) {
                    	carbs = "";
                    
                    } 
                        String calories;
                        calories = request.getParameter("calories");
                        if (calories == null) {
                        	calories = "";
                        }
                        
                        String ingredients;
                        ingredients = request.getParameter("ingredients");
                        System.out.println("ingreds " + ingredients);
                        
                        String imageurl;
                        imageurl = request.getParameter("imgurl");
                   		String imagestring = "<img class='recipe-card-image' src="+imageurl+">";
            %>
                <%= imagestring + "<strong>"+name + "<br><br>"  + "<br><br><Strong>Calories:</strong>" + calories + "<br><br>Protein:" + protein + "<br><br>Fat:" + fat + "<br><br>Carbs" + carbs  %>

            </div>
        </div>

        <div class="fillerbox">
            <div class="filler">
                <button class="userbuttons">Add to Favorites</button>
                <button class="userbuttons">option?</button>
                <button class="userbuttons">option?</button>
            </div>
        </div>

    </div>
    <div class="footerdiv">
        <footer><p>© PlatePlan 2024</p></footer>
    </div>
</div>
</body>
</html>
