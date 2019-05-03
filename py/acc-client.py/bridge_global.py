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

# Use of brige client syncronized for all threads of ACC-Client
# -
# @author giuliobosco
# @version 1.0 (2019-04-21 - 2019-04-21)

# import bridge from file system
import sys
sys.path.insert(0, '/usr/lib/python2.7/bridge')

from threading import Lock

from bridgeclient import BridgeClient


class BridgeGlobal:
    def __init__(self):
        """
        Create global bridge, initialize the bridge client and the lock for synchronization.
        """
        # initialize bridge
        self.bridge = BridgeClient()
        # initialize synchronization lock
        self.lock = Lock()

    def get(self, key):
        """
        Get from the bridge with synchronization.
        :param key: Key of the value (memory address).
        :return: Value of the key.
        """
        # acquire the synchronization lock
        self.lock.acquire()
        # get the value
        value = self.bridge.get(key)
        # release the synchronization lock
        self.lock.release()
        # return the value
        return value

    def put(self, key, value):
        """
        Put the value in the bridge with synchronization.
        :param key: Key of the value (memory address).
        :param value: Value to set.
        """
        # acquire the synchronization lock
        self.lock.acquire()
        # set the value
        self.bridge.put(key, value)
        # release the synchronization lock
        self.lock.release()
