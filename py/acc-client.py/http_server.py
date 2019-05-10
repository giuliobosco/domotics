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
        """
        Send to the client the json header, response 200, content-type
        """
        # send response code 200
        self.send_response(200)
        # send contenty-type: text/json
        self.send_header("Content-type", "text/json")
        # end the http header
        self.end_headers()

    def do_GET(self):
        """
        Do at get http request.
        """
        if "/acc?" in self.path:
            # if ACC request execute self.acc()
            self.acc()
        elif ("/alive" == self.path) or ("/" == self.path):
            # if alive request execute self.alive()
            self.alive()
        else:
            # otherwise 404 file not found
            # send response 404
            self.send_response(404, "page not found")
            # end the http header
            self.end_headers()
            #  write return response not found
            self.wfile.write(ResponseRender(self.server.key_manager, self.server.bridge).not_found(self.path))

    def acc(self):
        """
        Render acc response.
        """
        # send to client json header
        self.send_header_json()
        # explode path, http get reqeust attributes
        attributes = parse_qsl(self.path.split("?")[1])
        # create the acc response render with the http server KeyManager and the http server global bridge
        response_render = ResponseRender(self.server.key_manager, self.server.bridge)
        for attribute in attributes:
            if attribute[0] == "key":
                # set the response render key
                response_render.key = attribute[1]
            if attribute[0] == "pin":
                # set the response render pin
                response_render.pin = attribute[1]
            if attribute[0] == "set":
                # set the response render set request, if it's not setted it will be get request.
                response_render.get = False
                response_render.value = attribute[1]
        # return to client acc response
        self.wfile.write(response_render.acc())

    def alive(self):
        """
        Render alive response
        """
        # send to client json header
        self.send_header_json()
        # send to client alive response rendered from ResponseRender
        self.wfile.write(ResponseRender(self.server.key_manager, self.server.bridge).alive())
