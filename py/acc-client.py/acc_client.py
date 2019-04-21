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
# @version 1.0.2 (2019-04-17 - 2019-04-21)

import sys
sys.path.insert(0, '/usr/lib/python2.7/bridge')

from bridgeclient import BridgeClient
# from http.server import HTTPServer
from BaseHTTPServer import HTTPServer
import time

from http_server import HttpServer
from pin_thread import PinThread

hostName = "0.0.0.0"
hostPort = 8080


class AccClient:
    @staticmethod
    def start(key_manager):
        try:
            server = HTTPServer((hostName, hostPort), HttpServer)
            server.key_manager = key_manager
            print(time.asctime(), "Server Starts - %s:%s" % (hostName, hostPort))
            buttons = {'D5', 'D6'}
            bridge = BridgeClient()
            threads = []
            for button in buttons:
                thread = PinThread(key_manager=key_manager, pin=button, bridge=bridge)
                thread.start()
                threads.append(thread)
            server.serve_forever()
        except KeyboardInterrupt:
            server.server_close()

            for thread in threads:
                thread.interrupt()
            pass

