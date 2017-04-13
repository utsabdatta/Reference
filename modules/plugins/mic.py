#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sun Feb 26 20:34:46 2017

@author: root
"""

import subprocess

def Mic(speak):
    subprocess.call("espeak -s 150 '" + speak + "'", shell=True)
    return