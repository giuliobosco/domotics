#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address for your controller below.
// Newer Ethernet shields have a MAC address printed on a sticker on the shield
byte mac[] = {  
  0x00, 0xAA, 0xBB, 0xCC, 0xDE, 0x02 };

// Initialize the Ethernet client library
// with the IP address and port of the server 
// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;

EthernetServer server(80);

String readString;

void setup() {
  // start the serial library:
  Serial.begin(9600);
  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    for(;;)
      ;
  }
  // print your local IP address:
  Serial.println(Ethernet.localIP());
  server.begin();

  pinMode(7,OUTPUT);
  digitalWrite(7, LOW);
  pinMode(6,OUTPUT);
  digitalWrite(6,HIGH);
  pinMode(5,OUTPUT);
}

void loop() {
  EthernetClient httpClient = server.available();
  if (httpClient) {
    while(httpClient.connected()) {
      if (httpClient.available()) {
        char c = httpClient.read();

        if (readString.length() < 100) {
          readString += c;
        }

        if (c == 0x0D) {
          httpClient.println("HTTP/1.1 200 OK");
          delay(10);
          httpClient.stop();

          if (readString.indexOf("?pin1=1") > -1) {
            digitalWrite(5, HIGH);
            Serial.println("yep");
          } else {
            if (readString.indexOf("?pin1=0") > -1) {
              Serial.println("nop");
              digitalWrite(5, LOW);
            }
          }

          readString = "";
        }
      }
    }
  }
}
