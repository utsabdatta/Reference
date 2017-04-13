#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
@author: Sathish V
"""

dict = \
{
  "movement": [
    "come",
    "turn",
    "moving",
    "move",
    "go"
  ],
  "time": [
    "time",
    "date",
    "day"
  ],
  "news": [
    "news",
    "latest news",
    "around",
    "world",
    "happen"
  ],
  "speaker": [
    "speaker"
  ],
  "system": [
    "play",
    "open"
  ],
  "short-key": [
    "change (my name, my nickname)",
    "what (can u do, are you, up[just like wassup?] ) ",
    "say (something, abt  ur master, abt me)",
    "throw (some jokes at me, news)",
    "when (will u wake me up, ",
    "give ( tips [health, studies, others])",
    "who (are you, devel [developer, developed, etc], created u, is ur master, is ur father, mother, bosss ",
    "let (play something, talk, do something)",
    "tell (joke, something, news, abt me, abt ur master",
    "set (reminder, alarm, my name, speaker to pi/mobile, active/passive mode)"
  ]
}


def testing():
    for head in dict:
        val = 0
        print head
        for index,keyword in enumerate(dict[head]):
            print "    Values[ ", index, "] = ", keyword
            val += 1
     

#testing() 