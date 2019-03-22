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
 * Arduino blink on pin 13 sketch.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-03-20 - 2019-03-20)
 */

// Here we will hold the values coming from Python via Bridge
char D13value[2];

void setup() {
  // Zero out the memory we're using for the Bridge
  memset(D13value, 0, 2);

  // Initialize digital pins 12 and 13 as output
  pinMode(13, OUTPUT);

  // Start using the Bridge
  Bridge.begin();
}

void loop() {
  // Write current value of D13 to the pin (basically turning it on or off)
  Bridge.get("D13", D13value, 2);
  int D13int = atoi(D13value);
  digitalWrite(13, D13int);

  // An arbitrary amount of delay to make the whole thing more reliable
  delay(10);
}
