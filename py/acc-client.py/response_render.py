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

# ACC-Client response render
# -
# @author giuliobosco
# @version 1.2.9 (2019-04-17 - 2019-04-20)

import sys
sys.path.insert(0, '/usr/lib/python2.7/bridge')

from datetime import datetime
from bridgeclient import BridgeClient
from thermistor import get_celsius


class ResponseRender:
    error = "ERROR"
    ok = "OK"
    ok_msg = "ok"
    warring = "WARRING"

    def __init__(self, key_manager):
        self.key_manager = key_manager
        self.key = ''
        self.pin = ''
        self.value = ''
        self.get = True
        self.bridge = BridgeClient()

    def acc(self):
        if not self.key_manager.check_key(self.key):
            return self.build(self.error, "Wrong key")
        try:
            self.check_parameters()
        except Exception as e:
            return self.build(self.error, e.message)

        if self.get:
            pin = self.get_pin()
            if pin == 'A1':
                voltage = self.bridge.get(pin)
                return self.build(self.ok, str(get_celsius(voltage)))

            value = self.bridge.get(pin)

            return self.build(self.ok, str(value))

        return self.build(self.ok, self.value)

    def check_parameters(self):
        if not len(self.pin) > 0:
            raise Exception("No pin in request")
        if not self.is_pin(self.pin):
            raise Exception("Pin not valid")
        if not self.get:
            if not len(self.value) > 0:
                raise Exception("No value to set in request")

    def get_pin(self):
        pin = str(self.pin)
        pin = pin.upper()
        if not pin.startswith('A'):
            pin = 'D' + pin
        return pin

    def is_pin(self, pin):
        try:
            pin = str(pin).upper()
            if pin.startswith("A") and len(pin) == 2:
                pin = pin.replace("A", "")
                pin = int(pin)
                if 0 <= pin <= 5:
                    return True
                else:
                    return False
            pin = int(pin)
            if 0 <= pin <= 13:
                return True
        except Exception as e:
            return False
        return False

    def alive(self):
        other = [("date", str(datetime.now())), ("id", self.key_manager.id)]
        return self.build(self.ok, self.ok_msg, other)

    def not_found(self, path):
        return self.build("ERROR", "Page " + path + " not found")

    def build(self, status, message, other=[]):
        string = "{\"status\":\"" + status + "\""
        string += ",\"message\":\"" + message + "\""
        for item in other:
            if len(item) == 2:
                string += ",\"" + item[0] + "\":\"" + item[1] + "\""
        string += "}"
        return bytes(string)
