<div id="singleColumn">

	<h2>checkout</h2>

	<p>In order to purchase the items in your shopping cart, please provide us with the following infomation:</p>

	<form id="checkoutForm" action="<%= request.getContextPath() %>/purchase" method="post">

		<table id="checkoutTable">
			<tr>
				<td><label for="name">Name :</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="name"
                           name="name"
                           value="${param.name}">
                </td>
			</tr>
			<tr>
                <td><label for="phone">Phone :</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="phone"
                           name="phone"
                           value="${param.phone}">
                </td>
            </tr>
            <tr>
                <td><label for="address">Address :</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="address"
                           name="address"
                           value="${param.address}">
                </td>
            </tr>
            <tr>
                <td><label for="cityRegion">City :</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="cityRegion"
                           name="cityRegion"
                           value="${param.cityRegion}">
                </td>
            </tr>
            <tr>
                <td><label for="ccNumber">Post Code :</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="ccNumber"
                           name="ccNumber"
                           value="${param.ccNumber}">
                </td>
            </tr>
            <tr>
                <td><label for="creditcard">Creadit Card Number :</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="19"
                           id="creditcard"
                           name="creditcard"
                           class="creditcard"
                           value="${param.creditcard}">
                </td>
            </tr>
			<tr>
				<td><input type="submit" value="submit purchase"></td>
			</tr>

		</table>

	</form>

	<div id="infoBox">

		<ul>
            <li>A &#36; 3.00 delivery surcharge is applied to all purchase orders</li>
        </ul>

		<table id="priceBox">
		  <tr>
            <td>Subtotal:</td>
            <td class="checkoutPriceColumn">&#36; : </td>
          </tr>
          <tr>
            <td>Surcharge:</td>
            <td class="checkoutPriceColumn">&#36; : </td>
          </tr>
          <tr>
            <td class="total">Total:</td>
            <td class="total checkoutPriceColumn">&#36; : </td>
          </tr>
		</table>
	</div>
</div>

