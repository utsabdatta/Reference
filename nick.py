#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sat Feb 25 20:05:59 2017
@author: Sathish. V
"""

#system scripts
import socket  
import sys
#sathish scripts below
from modules.plugins import debug as d
from modules.plugins import variables as v
from modules.plugins.keywords import dict
from modules.plugins.mic import Mic

#Debugger Starter
if(len(sys.argv) == 2):
    if(sys.argv[1] == "debug"):
        v.DEBUG = 1
d.out("Init", "Started")        


#Socket Creation
d.out("CONNECTION", "Waiting for request")
soc = socket.socket()         # Create a socket object
host = "192.168.1.107"          # Get local machine name
port = 2005                # Reserve a port for your service.
soc.bind((host, port))       # Bind to the port
soc.listen(5) 


#matched module routing
def routeto(head_name, key_word, text_full):
    text = ""
    if(head_name == "time"):
        d.out("KEYWORD","Time module selected")
        from modules import times
        text = times.execute(head_name, key_word, text_full)
    elif(head_name == "news"):
        d.out("KEYWORD","News module selected")
        from modules import news
        text = news.execute(head_name, key_word, text_full)
    elif(head_name == "system"):
        from modules import syst
        text = syst.execute(head_name, key_word, text_full)
    elif(head_name == "movement"):
        from modules import movement
        text = movement.execute(head_name, key_word, text_full)
    elif(head_name == "speaker"):
        from modules import speaker
        text = speaker.execute(head_name, key_word, text_full)
    elif(head_name == "short-key"):
        from modules import shortkey
        text = shortkey.execute(head_name, key_word, text_full)
    '''
    elif(head_name == ""):
        from modules import
        text = .execute(head_name, key_word, text_full)
    '''
    return text


    
while 1:
    try:
        #text = raw_input("Enter Something: ")
        found = 0
        response = "Try Again!"
        print ("\n\n========================================================\n\n")
        
        #Waiting for mobile request
        conn, addr = soc.accept()
        d.out("CONNECTiON", "Got connection" )
        text = conn.recv(1024)
        d.out("CONNECTION", "Recieved txt is: " + text)
        
        for head in dict:
            if found == 1:
                break
            for index,keyword in enumerate(dict[head]):
                if found == 1:
                    break
                if (text.find(keyword) >= 0):
                    d.out("MATCHED", "'" + keyword + "' is found")
                    found = 1
                    response = routeto(head, keyword, text)
                    d.out("CONNECTION", "Sending txt: " + response)
                    conn.send( response + "\n")
                    Mic(response)
                    
        if (found == 0):
            d.out("KEYWORD","Chat module selected")
            from modules import chat
            d.out("CONNECTION", "Sending txt: " + response)
            conn.send( response + "\n")
            Mic(response)
   
    except KeyboardInterrupt:
        print 
        d.out("Init","Stopped")
        break     

    