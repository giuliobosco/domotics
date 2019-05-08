package acc;

import jdbc.DomoticsJdbcC;
import jdbc.JdbcConnector;
import models.Arduino;
import models.Light;
import models.LightButton;
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
 * Controller Servlet.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-05-08 - 2019-05-08)
 */
@WebServlet(name = "ControllerServlet")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameters = request.getParameterMap();
        String responseString = "";
        try {
            JdbcConnector jdbc = DomoticsJdbcC.getConnector();
            jdbc.openConnection();

            if (parameters.containsKey("light") && parameters.containsKey("toggle")) {
                String lightPin = parameters.get("light")[0];
                int xIndex = lightPin.indexOf('x');
                String arduinoId = lightPin.substring(0, xIndex);
                int pin = Integer.parseInt(lightPin.substring(xIndex + 1));

                Arduino arduino = new Arduino(jdbc, arduinoId);
                Light light = new Light(pin, arduino, jdbc);
                light.toggleLight();
                responseString = JsonBuilder.getJsonResponseOk("");
            } else {
                responseString = JsonBuilder.getJsonResponseError("no light");
            }

        } catch (SQLException | ClassNotFoundException e) {
            responseString = JsonBuilder.getJsonResponseError(e.toString());
        }

        response.getOutputStream().println(responseString);
    }
}
