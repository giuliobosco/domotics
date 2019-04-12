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

from math import log

class Thermistor:
    # thermistor datasheet values
    RT0 = 10000
    # thermistor datasheet values
    B = 3977
    # temperature T0 from thermistor datasheet
    T0 = 25 + 273.15
    def __init__(self, vcc = 5, r = 10000):
        self.vcc = vcc
        self.r = r

    def getKelvin(self, voltage):
        voltage = (5.00 / 1023.00) * voltage
        voltageReverse = self.vcc - voltage
        resistence = voltage / (voltageReverse / self.r)
        ln = log(resistence / self.RT0)
        return (1 / ((ln / self.B) + (1 / self.T0)))

    def getCelsius(self, voltage):
        return self.getKelvin(voltage) - 273.15


def getCelsius(voltage, vcc = 5, r = 10000):
    therm = Thermistor(vcc, r)
    return therm.getCelsius(voltage)

def getKelvin(voltage, vcc = 5, r = 10000):
    therm = Thermistor(vcc, r)
    return therm.getKelvin(voltage)
