/*
 * The MIT License
 *
 * Copyright 2018 giuliobosco.
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
 
 /**
  * Test of the webserver for Arduino Mega 2560 with the Arduino Ethernet Shield.
  * 
  * @author mattiaruberto (mattia.ruberto@samtrevano.ch)
  * @author giuliobosco (giuliobva@gmail.com)
  * @version 1.0.1 (2019-02-22 - 2019-03-07 fix comments)
  */
 
// include arduino serial library
#include <SPI.h>
// include arduino ethernet library
#include <Ethernet.h>

// set the mac address of the arduino ethernet shield
byte mac[] = {0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED};

// create an Ethernet web server listening on the port 80
EthernetServer server(80);

<<<<<<< HEAD
// Variable to store the HTTP request
String header;

=======
// temp variable, for read lines
>>>>>>> d15728ef3bd7f2edf7c73237247a122c9678a637
String readString;

/**
 * Setup the Arduino.
 */
void setup() {
  // configure the serial port at 9600 bit/s
  Serial.begin(9600);
  // print on the serial the string
  Serial.println("Ethernet WebServer Example");

  // Start the ethernet shield with the mac address.
  Ethernet.begin(mac);

  // check if the ethernet shield is connected to the Arduino
  if (Ethernet.hardwareStatus() == EthernetNoHardware) {
    Serial.println("Ethernet shield was not found.  Sorry, can't run without hardware. :(");
    while (true) {
      delay(1); // do nothing, no point running without Ethernet hardware
    }
  }
  
  // check if the cable is connected to the ethernet shield
  if (Ethernet.linkStatus() == LinkOFF) {
    Serial.println("Ethernet cable is not connected.");
  }

  // start the server
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());
}

/*
 * Run the server.
 */
void loop() {
  // listen for clients requests
  EthernetClient client = server.available();
  
  // on client connected
  if (client) {
    Serial.println("new client");
    // an http request ends with a blank line
    bool currentLineIsBlank = true;
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();             // read a byte, then
        Serial.write(c);                    // print it out the serial monitor
        header += c;
        if (c == '\n') {
          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
          client.println("Content-Type: text/html");
          client.println("Connection: close");  // the connection will be closed after completion of the response
          //client.println("Refresh: 5");  // refresh the page automatically every 5 sec
          client.println();
          client.println("<!DOCTYPE HTML>");
          client.println("<html>");
          client.println("<head>");
          client.println("<title>Web Server</title>");
          client.println("</head>");
          client.println("<body>");  
          if (header.indexOf("GET /on") >= 0) {
            client.println("<h1>On</h1>");
          } else if (header.indexOf("GET /off") >= 0) {
            client.println("<h1>Off</h1>");
          }else{
            client.println("<h1>Undefined</h1>");
          }
          client.println("</body>");
          client.println("</html>");
          
          delay(1);
          client.stop();
          Serial.println("client disconnected");
        }
      }
    }
  }
}
