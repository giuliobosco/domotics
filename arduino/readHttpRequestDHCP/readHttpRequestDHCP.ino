/*
 * Copyright 2018 Giulio Bosco (giuliobva@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright
 * notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/*
 * project: <project-name> (https://github.com/giuliobosco/domotics.git)
 * description: Home made domotics with control by web interface and arduino.
 *
 * author: Giulio Bosco (giuliobva@gmail.com)
 * version: 1.0
 * date: 29.05.2018
 *
 * file: readHttpRequestDHCP.ino
 */

#include <SPI.h>
#include <Ethernet.h>

/**
 * Ehternet Shield Mac Address.
 */
byte mac[] = {  
  0x00, 0xAA, 0xBB, 0xCC, 0xDE, 0x02 };

/**
 * Initialize Ethernet DHCP Client.
 */
EthernetClient client;

/**
 * Initialize Ethernet Web Server.
 */
EthernetServer server(80);

/**
 * String used for the http request from the client.
 */
String readString;

/**
 * Setup Method.
 */
void setup() {
  // Begin the Serial port for tests.
  Serial.begin(9600);
  
  // Start Ethernet Client.
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    for(;;)
      ;
  }

  // Print on serial Ip address.
  Serial.println(Ethernet.localIP());
  // Start web Server.
  server.begin();

  // Setup Test relay.
  // Setup GND Pin of the relay.
  pinMode(7,OUTPUT);
  digitalWrite(7, LOW);
  // Setup +5V Pin of the relay.
  pinMode(6,OUTPUT);
  digitalWrite(6,HIGH);
  // Setup Data Pin of the relay.
  pinMode(5,OUTPUT);
}

/**
 * Main method.
 * Used for the operation of the webserver and controll lights.
 */
void loop() {
  // Initialize web Client.
  EthernetClient httpClient = server.available();

  // Check if the web client is aveable.
  if (httpClient) {
    // On client connected
    while(httpClient.connected()) {
      // Check if the web client is connected.
      if (httpClient.available()) {
        // char of the request
        char c = httpClient.read();

        // Add char to the http request if request no complet.
        if (readString.length() < 100) {
          readString += c;
        }

        // On client http request return.
        if (c == 0x0D) {
          // Returns to client HTTP response.
          httpClient.println("HTTP/1.1 200 OK");
          httpClient.println("Content-Type: text/html"); 
          httpClient.println();
          
          // Delay 10 milliseconds.
          delay(10);
          // End client connection.
          httpClient.stop();

          // Analize request string for get options.
          // Check for relay on pin 5.
          if (readString.indexOf("?pin5=1") > -1) {
            digitalWrite(5, HIGH);
          } else {
            if (readString.indexOf("?pin5=0") > -1) {
              digitalWrite(5, LOW);
            }
          }

          // Empty request string.
          readString = "";
        }
      }
    }
  }
}
