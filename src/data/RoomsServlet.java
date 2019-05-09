package data;

import acc.JsonBuilder;
import jdbc.DomoticsJdbcC;
import jdbc.JdbcConnector;
import models.Arduino;
import models.Light;
import models.Room;
import models.Thermometer;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0.1 (2019-05-08 - 2019-05-08)
 */
@WebServlet(name = "RoomsServlet")
public class RoomsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseString = "";
        try {
            JdbcConnector jdbc = DomoticsJdbcC.getConnector();
            jdbc.openConnection();
            String allRoomsQuery = "SELECT * FROM room";
            List<Room> rooms = Room.getRooms(jdbc.query(allRoomsQuery));

            JSONArray roomsJson = new JSONArray();

            for (Room room : rooms) {
                roomsJson.put(getJsonRoom(room, jdbc));
            }

            responseString = new JSONObject().put("rooms", roomsJson).toString();
        } catch (SQLException | ClassNotFoundException e) {
            responseString = JsonBuilder.getJsonResponseError("MySQL Error");
        }

        response.getOutputStream().println(responseString);
        System.out.println(responseString);
    }

    private JSONObject getJsonRoom(Room room, JdbcConnector jdbc) throws SQLException, ClassNotFoundException, IOException {
        JSONObject roomJson = new JSONObject();

        JSONArray lights = Light.getJsonLights(Light.getLights(jdbc,new Room(room.getName())));

        roomJson.put("lights", lights);
        roomJson.put("name", room.getName());
        roomJson.put("temp", Thermometer.getTemperatureByRoom(room, jdbc));

        return roomJson;
    }
}
