#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sun Feb 26 21:14:02 2017

@author: root
"""

import subprocess

dict  = \
[
 "video",
 "audio",
 "image",
 "music",
 "song",
 "songs"
]

def execute(head, key, text):
    found = 0
    for words in dict:
        if(text.find(words) >= 0):
            found = 1
            if(key.find("play") >= 0):
                if(words == "video"):
                    subprocess.call("totem ~/Desktop/Rockstar.mkv &", shell=True)
                    return "playing video"
                elif(words == "audio" or words == "music" or words == "song"  or words == "songs"):
                    subprocess.call("totem ~/Desktop/music.mp3 &", shell=True)
                    return "playing audio"
                else:
                    found = 0
                    break;
            elif(key.find("open") >= 0):
                if (words == "image"):
                    subprocess.call("eog ~/Desktop/licence.jpeg &", shell=True)
                    return "opening image"
                else:
                    found = 0
                    break;
            else:
                found = 0
                break;
    if (found == 0):
        return "what i have to play?"