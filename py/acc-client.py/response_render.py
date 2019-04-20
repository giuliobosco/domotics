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
# @version 1.1.1 (2019-04-17 - 2019-04-20)

from datetime import datetime


class ResponseRender:

    def __init__(self, key_manager):
        self.key_manager = key_manager
    def alive(self):
        return bytes("{\"status\":\"OK\",\"id\":\"" + self.key_manager.id + "\",\"date\":\"" + str(datetime.now()) +
                     "\"}", "utf-8")

    def not_found(self, path):
        return bytes("{\"status\":\"ERROR\",\"message\":\"Page " + path + " not found\",\"id\":\"" + self.key_manager.id
                     + "\"}", "utf-8")
