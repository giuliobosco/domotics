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

# Test program for read from the arduino BridgeClient the value of the Thermistor.
# -
# @author giuliobosco
# @version 1.0 (2019-04-12 - 2019-04-12)

import sys
sys.path.insert(0, '/usr/lib/python2.7/bridge')

from time import sleep                                      # import sleep function
from bridgeclient import BridgeClient as bridgeclient       # import arduino bridge client
from math import log;                                       # import log function from math class

bridge = bridgeclient()                                     # initialize the bridge

while True:
    RT0 = 10000                                             # thermistor datasheet values
    B = 3977                                                # thermistor datasheet values
    # ---
    VCC = 5                                                 # supply voltage
    R = 10000                                               # resistor: 10kOhm
    # ---
    T0 = 25 + 273.15                                        # temperature T0 from thermistor datasheet
    # ---
    VRT = float(bridge.get('A1'))                           # read value of the pin A1 of the arduino
    VRT = (5.00 / 1023.00) * VRT                            # conversion to voltage
    VR = VCC - VRT                                          # inverse of voltage
    RT = VRT / (VR / R)                                     # resistence of RT
    # ---
    ln = math.log(RT / RT0)                                 # logarithm of RT by RT0
    TX = (1 / ((ln / B) + (1 / T0)))                        # temperature on the thermistor
    # ---
    TX = TX - 273.15                                        # conversion from kelvin to celsius
    print("Temp" + str(TX))                                 # print temperature
    sleep(0.1)                                              # wait a second
