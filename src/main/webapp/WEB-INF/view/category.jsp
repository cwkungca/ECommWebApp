
<div id="categoryLeftColumn">
	<c:forEach var="category" items="${categories}">
	   <a href="category?catid=${category.id}" class="categoryButton">
	       <span class="categoryText">${category.name}</span>
	   </a>
	</c:forEach>
</div>

<div id="categoryRightColumn">
	<p id="categoryTitle">
	   <span style="background-color: #f5eabe; padding: 7px;">${selectedCategory.name}</span>
	</p>

	<table id="productTable">
	   <c:forEach var="product" items="${selectedCategory.getCatProducts().get()}">
	       <tr>
	           <td>
	               <img alt="${product.name}" src="Image?part=pro&num=${product.id}" />
	           </td>
	           <td>
	               <br>
                   <span class="smallText">${product.description} Description</span>
	           </td>
	           <td>
	               <span>${product.price}</span>
	           </td>
	           <td>
	               <form method="post" action="addToCart">
	                   <input type="hidden"
                               name="productId"
                               value="${product.id}">
                       <input type="submit"
                               name="submit"
                               value="addToCart">
	               </form>
	           </td>
	       </tr>
	   </c:forEach>
	</table>
</div>

