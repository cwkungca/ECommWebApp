
<div id="indexLeftColumn">
	<div id="welcomeText">
		<p>[ welcome text ]</p>
	</div>
</div>

<div id="indexRightColumn">
    <c:forEach var="category" items="${categories}">
        <div class="categoryBox">
            <a href="<%= request.getContextPath() %>/category?catid=${category.id}">
                <span class="categoryLabel"></span>
                <span class="categoryLabelText">${category.name}</span>
                
                <img alt="${category.name}"
                 src="Image?part=cat&num=${category.id}" class="categoryImage">
            </a>
        </div>
    </c:forEach>
</div>

