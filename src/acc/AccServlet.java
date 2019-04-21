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
package acc;

import jdbc.DomoticsJdbcC;
import jdbc.JdbcConnector;
import models.Arduino;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Acc servlet.
 *
 * @author giuliobosco
 * @version 1.0 (2019-04-21)
 */
@WebServlet(name = "AccServlet")
public class AccServlet extends HttpServlet {

    private final String AUTOCONF = "autoconf";

    private final String REQUEST = "request";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameters = request.getParameterMap();
        try {
            JdbcConnector jdbc = DomoticsJdbcC.getConnector();
            jdbc.openConnection();
            IdManager idManager = new IdManager(jdbc);
            if (parameters.containsKey("autoconf") && parameters.containsKey("id")) {
                String arduinoId = parameters.get("id")[0];
                String arduinoIp = request.getRemoteAddr();

                Arduino arduino = new Arduino(idManager, arduinoId, arduinoIp);
                JSONObject json = new JSONObject();
                json.put("id", arduino.getId());
                json.put("key", arduino.getKey());
                json.put("server_address", request.getLocalAddr());
                response.getOutputStream().println(json.toString());
            }
        } catch (SQLException | ClassNotFoundException e) {
            response.sendError(500, "Internal Server ERROR");
            e.printStackTrace();
        }
    }
}
