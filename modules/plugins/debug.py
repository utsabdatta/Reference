#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sun Feb 26 12:42:48 2017

@author: root
"""


import variables as v

def out(topic, msg):
    if(v.DEBUG == 1):
        print "[DEBUG] ", topic, ": ", msg 