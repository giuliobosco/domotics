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
import models.Light;
import models.LightButton;

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
 * @version 1.0.5 (2019-04-21 - 2019-05-10)
 */
@WebServlet(name = "AccServlet")
public class AccServlet extends HttpServlet {

    private final String AUTOCONF = "autoconf";

    private final String REQUEST = "request";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseString = "";
        Map<String, String[]> parameters = request.getParameterMap();
        try {
            JdbcConnector jdbc = DomoticsJdbcC.getConnector();
            jdbc.openConnection();
            IdManager idManager = new IdManager(jdbc);
            if (parameters.containsKey("autoconf") && parameters.containsKey("id")) {
                String arduinoId = parameters.get("id")[0];
                String arduinoIp = request.getRemoteAddr();
                String serverAddress = request.getLocalAddr();
                int serverPort = request.getServerPort();

                Autoconf autoconf = new Autoconf(idManager, arduinoId, arduinoIp, serverAddress, serverPort);
                responseString = autoconf.getJson();

            } else if (parameters.containsKey("key")
                    && parameters.containsKey("pin")
                    && parameters.containsKey("set")) {

                String arduinokey = parameters.get("key")[0];
                String arduinoIp = request.getRemoteAddr();

                String buttonPinString = parameters.get("pin")[0];
                if (buttonPinString.length() > 0) {
                    buttonPinString = buttonPinString.substring(1);
                    int buttonPin = Integer.parseInt(buttonPinString);

                    LightButton lb = new LightButton(buttonPin, arduinoIp, arduinokey, jdbc);
                    Light light = lb.getLight();

                    if (parameters.get("set")[0].equals("toggle")) {
                        light.toggleLight();
                    } else {
                        int setStatus = Integer.parseInt(parameters.get("set")[0]);

                        if (setStatus == light.LIGHT_ON) {
                            light.turnOn();
                        } else if (setStatus == light.LIGHT_OFF) {
                            light.turnOff();
                        }
                    }

                    responseString = JsonBuilder.getJsonResponseOk("" + light.getStatus());
                } else {
                    responseString = JsonBuilder.getJsonResponseError("No found pin");
                }
            } else {
                responseString = JsonBuilder.getJsonResponseError("Wrong operation");
            }
        } catch (SQLException | ClassNotFoundException e) {
            responseString = JsonBuilder.getJsonResponseError(e.toString());
        }

        response.getOutputStream().println(responseString);
    }
}
