
<form action="login" method=post>
    <div id="loginBox">
        <p><strong>Account:</strong>
            <input type="text" size="20" name="account"></p>

        <p><strong>Password:</strong>
            <input type="password" size="20" name="password"></p>
            
        <c:if test="${message.isPresent()}">
            <p class="error">${message.get()}</p>
        </c:if>

        <p><input type="submit" value="submit"></p>
    </div>
</form>