#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Fri Mar  3 09:13:14 2017

@author: root
"""
# left, right, front, back, backward, frontward, leftward, rightward
# keep, a bit, 10cm/ 10 feet
# and, then (for multiple movement)

import time

DIVIDER_WORDS = ("and then", "and", "then")
commands = []

dict_direction = \
{
    "left" : 101,
    "right": 102,
    "front": 103,
    "straight": 103,
    "back" : 104,
    
    "forward"  : 103,
    
    "stop" : 105
}

#priority is important here
dict_extras = \
{
     "bit" : 112,
     "centimetre"  : 113,
     "feet": 114,
     "keep": 111
}


def divide_text(commands):
    temp = []
    for cmd in commands:
        if(cmd.find(DIVIDER_WORDS[1]) >= 0):
            temp.extend(cmd.split(DIVIDER_WORDS[1]))
        elif(cmd.find(DIVIDER_WORDS[2]) >= 0):
            temp.extend(cmd.split(DIVIDER_WORDS[2]))
        else:
            temp.extend(cmd.split(DIVIDER_WORDS[0]))
        
    commands = temp
    for cmd in commands:
        for test in DIVIDER_WORDS:
            if(cmd.find(test) >= 0):
                commands = divide_text(commands)
                break
    return commands
    
def tempo(text):
    while 1:
        print text
        time.sleep(1)
        break
    print "\n"
    
def bot_movement(direction, distance, cmd):
    if(direction == 101):
        tempo("turning left")
    elif(direction == 102):
        tempo("turning right")
    elif(direction == 103):
        tempo("moving straight")
    elif(direction == 104):
        tempo("moving backwards")
    else:
        tempo("stopping")
    
def execute(head, keyword, text):
    #spliting text for multiple commands at a time
    commands = text.split(DIVIDER_WORDS[0])
    commands = divide_text(commands)  
    print commands
    for cmd in commands:
        for key1, value1 in dict_direction.items():
            for key2, value2 in dict_extras.items():
                if(cmd.find(key1) >= 0):
                    if(cmd.find(key2) >= 0):
                        bot_movement(value1, value2, cmd)
                    else:
                        bot_movement(value1, 0, cmd)
                    break
                    
                    
    
    return "Moving"
    
#execute("a","b", "a and then b and c then d and then e and f and g then h")
#execute("a","b", "move straight a bit and then go back a bit then stop and then again go front 10 feet more and stop finally")





