<%--
  - The MIT License
  -
  - Copyright 2019 giuliobosco.
  -
  - Permission is hereby granted, free of charge, to any person obtaining a copy
  - of this software and associated documentation files (the "Software"), to deal
  - in the Software without restriction, including without limitation the rights
  - to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  - copies of the Software, and to permit persons to whom the Software is
  - furnished to do so, subject to the following conditions:
  -
  - The above copyright notice and this permission notice shall be included in
  - all copies or substantial portions of the Software.
  -
  - THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  - IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  - FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  - AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  - LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  - OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  - THE SOFTWARE.
--%>

<%--
  - Portal main page.
  -
  - @author giuliobosco (giuliobva@gmail.com)
  - @version 1.0 (2019-03-01)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>App</title>
</head>
<body>
<%
    String user = null;

    if (session.getAttribute("user") == null) {
        // if the session doesn't exist, return to login
        response.sendRedirect("login.html");
    } else {
        user = (String) session.getAttribute("user");
    }

    // checks session id
    String sessionID = null;

    // get the cookies
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        // if there are some cookies
        for (Cookie cookie : cookies) {
            // check for session id in the cookies.
            if (cookie.getName().equals("JSESSIONID")) {
                sessionID = cookie.getValue();
            }
        }
    } else {
        // if there are no cookies set the session id as the actual session
        sessionID = session.getId();
    }
%>
<h3>Hi <%=user %>, Login successful. Your Session ID=<%=sessionID %>
</h3>
<br>
User=<%=user %>
<br>
<!-- need to encode all the URLs where we want session information to be passed -->
<form action="<%=response.encodeURL("Logout") %>" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
