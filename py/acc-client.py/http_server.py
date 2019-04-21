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

# Base http server
# -
# @author giuliobosco
# @version 13 (2019-04-17 - 2019-04-21)

from BaseHTTPServer import BaseHTTPRequestHandler
from urlparse import parse_qsl

from response_render import ResponseRender


class HttpServer(BaseHTTPRequestHandler):
    def send_header_json(self):
        self.send_response(200)
        self.send_header("Content-type", "text/json")
        self.end_headers()

    def do_GET(self):
        if "/acc?" in self.path:
            self.acc()
        elif ("/alive" == self.path) or ("/" == self.path):
            self.alive()
        else:
            self.send_response(404, "page not found")
            self.end_headers()
            self.wfile.write(ResponseRender(self.server.key_manager, self.server.bridge).not_found(self.path))

    def acc(self):
        self.send_header_json()
        attributes = parse_qsl(self.path.split("?")[1])
        response_render = ResponseRender(self.server.key_manager, self.server.bridge)
        for attribute in attributes:
            if attribute[0] == "key":
                response_render.key = attribute[1]
            if attribute[0] == "pin":
                response_render.pin = attribute[1]
            if attribute[0] == "set":
                response_render.get = False
                response_render.value = attribute[1]
        self.wfile.write(response_render.acc())

    def alive(self):
        self.send_header_json()
        self.wfile.write(ResponseRender(self.server.key_manager, self.server.bridge).alive())
