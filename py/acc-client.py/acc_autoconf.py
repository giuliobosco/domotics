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

# ACC-Client autoconfiguration
# -
# @author giuliobosco
# @version 1.2 (2019-04-21 - 2019-04-21)


from urllib2 import urlopen
import json

from key_manager import KeyManager


def acc_autoconf(id, server_address):
    try:
        request = "http://" + server_address + "/acc?autoconf=request&id=" + id
        response = urlopen(request).read()
        data = json.loads(response)
        if 'key' in data and 'id' in data and 'server_address' in data:
            return KeyManager(id=data['id'], key=data['key'], server_address=data['server_address'])
    except:
        pass
    return KeyManager(id=id)