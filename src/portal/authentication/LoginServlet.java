/*
 * The MIT License
 *
 * Copyright 2019 giuliobosco.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package portal.authentication;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * LoginServlet to portal servlet.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-02-28)
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * On get request try to login, if right credentials login and redirect to App.jsp, other ways,
     * returns to login.html.
     *
     * @param request Http login request.
     * @param response Http login response.
     * @throws ServletException Error while processing the servlet.
     * @throws IOException Error on the system.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authenticator authenticator = new LdapAuthenticator("OU=3,OU=I,OU=IN,OU=SAM,OU=allievi");

        // analyze request get username and password
        String requestUsername = request.getParameter("username");
        String requestPassword = request.getParameter("password");

        try {
            if (authenticator.authenticate(requestUsername, requestPassword)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", requestUsername);
                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30*60);
                Cookie userName = new Cookie("user", requestUsername);
                response.addCookie(userName);
                //Get the encoded URL string
                String encodedURL = response.encodeRedirectURL("App.jsp");
                response.sendRedirect(encodedURL);
            }
        } catch (AuthenticationException ae) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        } catch (NamingException | IOException error) {
            response.sendError(500);
        }
    }

    /**
     * Returns to login.html, because password must send by HTTP POST method.
     *
     * @param request Http request.
     * @param response Http response.
     * @throws ServletException Error while processing the servlet.
     * @throws IOException Error on the system.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.html");
    }
}