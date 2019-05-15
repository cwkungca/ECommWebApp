
<div id="singleColumn">

	<c:choose>
	   <c:when test="${cart.numOfItems > 1}">
	       <p>Your shopping cart contains ${cart.numOfItems} items.</p>
	   </c:when>
	   <c:when test="${cart.numOfItems == 1}">
           <p>Your shopping cart contains ${cart.numOfItems} item.</p>
       </c:when>
       <c:otherwise>
           <p>Your shopping cart Empty.</p>
       </c:otherwise>
	</c:choose>

	<div id="actionBar">
	   <%-- clear cart widget --%>
	   <c:if test="${!empty cart && cart.numOfItems != 0}">
	       <a href="<%= request.getContextPath() %>/viewCart?clear=true" class="bubble hMargin">
	           clear cart
	       </a>
	   </c:if>
	   
	   <%-- continue shopping widget --%>
	   
	   <%-- checkout widget --%>
	   <c:if test="${!empty cart && cart.numOfItems != 0}">
           <a href="<%= request.getContextPath() %>/checkout" class="bubble hMargin">
                proceed to checkout
           </a>
       </c:if>
	</div>
	
	<c:if test="${!empty cart && cart.numOfItems != 0}">
        <h4 id="subtotal">
            subtotal:&#36;${cart.subtotal}
        </h4>
        
        <table id="cartTable">
            <tr class="header">
                <th>product</th>
                <th>name</th>
                <th>price</th>
                <th>quantity</th>
            </tr>
            
            <c:forEach var="cartItem" items="${cart.items}" varStatus="iter">
                <c:set var="product" value="${cartItem.product}" />
                
                <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                    <td>
                        <img src="<%= request.getContextPath() %>/Image?part=pro&num=${product.id}" 
                            alt="${product.name}" >
                    </td>
                    
                    <td>${product.name}</td>
                    
                    <td>
                        &#36;${cartItem.total}
                        <br>
                        (&#36;${product.price} / unit)
                    </td>
                    
                    <td>
                        <form action="<%= request.getContextPath() %>/updateCart" method="post">
                            <input type="hidden"
                                    name="productId"
                                    value="${product.id}">
                            <input type="text"
                                    maxlength="2"
                                    size="2"
                                    value="${cartItem.quantity}"
                                    name="quantity"
                                    style="margin:5px">
                            <input type="submit"
                                    name="submit"
                                    value="update">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>	
	</c:if>
	
</div>
