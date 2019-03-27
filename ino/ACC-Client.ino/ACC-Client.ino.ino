/*
 * The MIT License
 *
 * Copyright 2019 giuliobosco.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

#include <Bridge.h>
#include <stdio.h>

/**
 * ACC-Client.ino is the script arduino side of the ACC-Client.
 * This script will give OpenWRT the access to controll the arduino pins.
 * Input pin: a0, a1, 5, 6
 * Output pin: 9, 10, 11, 12, 13
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-03-27 - 2019-03-27)
 */

// output pin variables
char D9value[2];  // beamer
char D10value[2]; // right curtain
char D11value[2]; // left curtain
char D12value[2]; // port light
char D13value[2]; // window light

// input pin variables
char A0value[2];  // light sensor
char A1value[2];  // temperature sensor
char D5value[2];  // port light button
char D6value[2];  // window light button

/**
 * Setting up the pin of the arduino.
 */
void setup() {
    // set the memory to use - output pin
    memset(D9value, 0, 2);
    memset(D10value, 0, 2);
    memset(D11value, 0, 2);
    memset(D12value, 0, 2);
    memset(D13value, 0, 2);

    // set output mode pin
    for (int i = 9; i < 14; i++) {
        pinMode(i, OUTPUT);
    }

    // set the memory to use - input pin
    memset(A0value, 0, 2);
    memset(A1value, 0, 2);
    memset(D5value, 0, 2);
    memset(D6value, 0, 2);

    // set input mode
    pinMode(5, INPUT);
    pinMode(6, INPUT);

    // start bridge for connection with OpenWRT
    Bridge.begin();
}

void loop() {
    // update status of pin d9
    Bridge.get("D9", D9value, 2);
    int D9int = atoi(D9value);
    digitalWrite(9, D9int);

    // update status of pin d10
    Bridge.get("D10", D10value, 2);
    int D10int = atoi(D10value);
    digitalWrite(10, D10int);

    // update status of pin d11
    Bridge.get("D11", D11value, 2);
    int D11int = atoi(D11value);
    digitalWrite(11, D11int);

    // update status of pin d12
    Bridge.get("D12", D12value, 2);
    int D12int = atoi(D12value);
    digitalWrite(12, D12int);

    // update status of pin d13
    Bridge.get("D13", D13value, 2);
    int D13int = atoi(D13value);
    digitalWrite(13, D13int);

    delay(10);
}
