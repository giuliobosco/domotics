"""
The MIT License

Copyright 2019 giuliobosco.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
"""

# ACC-Client
# -
# @author giuliobosco
# @version 1.2 (2019-04-17 - 2019-04-21)

from bridge_global import BridgeGlobal
from BaseHTTPServer import HTTPServer
import time

from http_server import HttpServer
from pin_thread import PinThread

# server host name (accessible from any network)
hostName = "0.0.0.0"
# server port
hostPort = 8080


class AccClient:
    @staticmethod
    def start(key_manager):
        """
        Start the acc client with the key manager.
        :param key_manager: Key manager for configure acc.
        """
        try:
            # create http server with HttpServer
            server = HTTPServer((hostName, hostPort), HttpServer)
            # set http server key_manager.
            server.key_manager = key_manager
            # set ACC-Client buttons thread.
            buttons = {'D5', 'D6'}
            # create the global bridge to arduino
            bridge = BridgeGlobal()
            # set http server global bridge
            server.bridge = bridge
            # initialize buttons array
            threads = []
            # create a thread for each button
            for button in buttons:
                # create pin thread, with the KeyManager, the pin of the button and the global bridge.
                thread = PinThread(key_manager=key_manager, pin=button, bridge=bridge)
                # start the pin thread
                thread.start()
                # append the pin thread to the threads list.
                threads.append(thread)
            # print the start of the server.
            print(time.asctime(), "Server Starts - %s:%s" % (hostName, hostPort))
            # start the http server
            server.serve_forever()
        except KeyboardInterrupt:
            # on key board interrupt
            # stop http server
            server.server_close()

            # stop all threads
            for thread in threads:
                thread.interrupt()
            pass

