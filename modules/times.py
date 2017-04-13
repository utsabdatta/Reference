#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sun Feb 26 10:38:54 2017

@author: Sathish. V
"""
from datetime import datetime as t
from plugins.mic import Mic
from time import strftime
from plugins import debug as d

d.out("TIME", "Inside time Module")


    
#gives out 4 strings of hour, minute, second, am/pm
def convertTime(now):
    am_pm = "am"
    hh = now.hour
    if(hh > 12):
        am_pm = "pm"
        hh = hh - 12
    mm = now.minute
    ss = now.second
    return str(hh),str(mm),str(ss),am_pm
    
def getTimeToView():
    hh,mm,ss,am_pm = strftime("%I"),strftime("%M"),strftime("%S"),strftime("%p")
    current_time = hh + ":" + mm + " " + am_pm
    return current_time

def getTimeToMic():
    hh,mm,ss,am_pm = convertTime(t.now())
    if(am_pm == "am"):
        am_pm = "a m"
    speech_time = "Time now is " + hh + " " + mm + ", " + am_pm
    return speech_time
    
    
def getDateToView():
    day, dd, mm, yy = strftime("%a"),strftime("%d"),strftime("%b"),strftime("%Y")
    current_date = day + " " + dd + ", " + mm + " " + yy
    return current_date

def getDateToMic():
    day, dd, mm, yy = strftime("%A"),strftime("%d"),strftime("%B"),strftime("%Y")
    if(dd == "01"):
        dd = dd + "st"
    elif(dd == "02"):
        dd = dd + "nd"
    elif(dd == "03"):
        dd = dd + "rd"
    else:
        dd = dd + "th"
    
    if(dd[0] == "0"):
        dd = dd.replace("0","",1)
        
    speech_date = "Date is " + dd + " of " + mm + ", " + yy + " and today is " + day
    return speech_date
    #"Date is 17th of March, 2016 and today is Saturday"
    
def execute(head, keyword, text):

    if(keyword == "time"):
        print getTimeToView()
        return getTimeToMic()
    elif(keyword == "date"):
        print getDateToView()
        return getDateToMic()
    elif(keyword == "day"):
        temp = strftime("%A")
        print temp
        return "It is " + temp
