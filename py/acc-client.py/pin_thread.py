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

import threading
from time import sleep
from urllib2 import urlopen


# ACC-Client key manager
# -
# @author giuliobosco
# @version 1.0 (2019-04-21 - 2019-04-21)


class PinThread(threading.Thread):
    def __init__(self, key_manager, pin, bridge):
        super(PinThread, self).__init__()
        self._stop_event = threading.Event()
        self.key_manager = key_manager
        self.pin = pin
        self.status = None
        self.bridge = bridge

    def interrupt(self):
        self._stop_event.set()

    def is_interrupted(self):
        return self._stop_event.is_set()

    def is_status_changed(self):
        status = self.bridge.get(self.pin)
        if not status == self.status:
            self.status = status
            return True

        return False

    def execute_http_req(self):
        if len(self.key_manager.key) > 0:
            host = self.key_manager.server_address
            request = "http://" + host + "/acc?key=" + self.key_manager.key
            request += "&pin=" + self.pin + "&set=" + self.status
            return urlopen(request).read()

    def run(self):
        while not self.is_interrupted():
            if self.is_status_changed():
                response = self.execute_http_req()
        sleep(0.1)
