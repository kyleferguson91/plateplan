<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/styles.css">
    <link rel="icon" type="image/x-icon" href="./images/favicon.ico">
    <title>User Homepage</title>
</head>
<body>
    <div class="mainlayout">
     
        <div class="navcontainer">
            <div class="titlecontainer">
                <img class="medimg" src="./images/pplogo2.png" alt="PlatePlan Logo">
                <p class="pptitle">PlatePlan</p>
            </div>

       
            <div class="searchcontainer">
                <form action="ingredientss" method="get" class="searchform">
                    <input hidden name="username" value="${user.username}">
                    <input required class="searchbox" placeholder="Search an ingredient to get started...." type="text" name="ingredients">
                    <button class="searchbutton buttonfill" type="submit">
                        <p class="searchbuttontext">Search</p>
                        <img class="tinyimg roundcorners" src="./images/searchicon5050.png" alt="Search">
                    </button>
                </form>
            </div>

      
            <div class="userinfopane">
                <p class="hellousertext">Hello, <br> ${user.username}!</p>
                <a href="#" class="userprofilelink">
                    <img class="userpic" src="./images/user4848.png" alt="User Profile">
                </a>
            </div>
        </div>

 
        <div class="profilecontent">
     
            <div class="contentbox">
                <form action="userhomepage" method="get">
                    <div class="recipe-cards">
                        <c:forEach var="recipe" items="${recipes}">
                            <button type="submit" name="recipeid" value="${recipe.mealDbId}" class="recipe-card">
                                <p>${recipe.name}</p>
                                <img class="recipe-card-image" src="${recipe.thumbnailUrl}" alt="${recipe.name}">
                            </button>
                        </c:forEach>
                    </div>
                </form>
            </div>

    
            <div class="contentbox">
                <div class="recipe-details">
                    <c:choose>
                        <c:when test="${not empty selectedRecipe}">
                            <h2>${selectedRecipe.name}</h2>
                            <img src="${selectedRecipe.thumbnailUrl}" class="recipe-card-image" alt="${selectedRecipe.name}">
                            <p><strong>Calories:</strong><br> ${selectedRecipe.calories} cal</p>
                            <p><strong>Protein:</strong><br> ${selectedRecipe.protein} g</p>
                            <p><strong>Fat:</strong><br> ${selectedRecipe.fat} g</p>
                            <p><strong>Carbs:</strong><br> ${selectedRecipe.carbs} g</p>
                        </c:when>
                        <c:otherwise>
                            <p>Search an ingredient to view recipes.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>


            <div class="fillerbox">
                <div class="filler">
                    <button class="userbuttons">Add to Favorites</button>
                    <button class="userbuttons">Option?</button>
                    <button class="userbuttons">Option?</button>
                </div>
            </div>
        </div>

   
        <div class="footerdiv">
            <footer>
                <p>© PlatePlan 2024</p>
            </footer>
        </div>
    </div>
</body>
</html>
