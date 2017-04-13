#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sat Feb 25 21:11:34 2017

@author: Sathish. V
"""
import feedparser
import json
from plugins import debug as d
print "Inside news"
                                            
dict_news = \
{
  "news": None,
  "latest news": None,
  "happen": {
    "around": None,
    "in":None
  }
}

news_text = "this is just news"


def check(key, value, text):
    if(text.find(key) >= 0 and value == None):
        return "found"
    elif (text.find(key) >= 0 and value != None):
        return "next"
    else:
        return None

        
def find(dictionary, text):
    found = 0
    for key, value in dictionary.items():
        result = check(key, value, text)
        if(result == "found"):
            found = 1
            return found
        elif(result == "next"):
            found = find(dictionary[key], text)
    return found
        
def execute(head, keyword, text):
    
    if(find(dict_news, text)):
        print news_text
    else:
        print ("moving to chat")
        import chat
        
    return news_text

class Article:

    def __init__(self, title, URL):
        self.title = title
        self.URL = URL

def getTopArticles(maxResults=None):
    d = feedparser.parse("http://news.google.com/?output=rss")

    count = 0
    articles = []
    for item in d['items']:
        articles.append(Article(item['title'], item['link'].split("&url=")[1]))
        count += 1
        if maxResults and count > maxResults:
            break

    return articles

def handle():
    articles = getTopArticles()
    #provides list of titles
    titles = [" ".join(x.title.split(" - ")[:-1]) for x in articles]
    #provides single string of all the titles
    all_titles = "... ".join(str(idx + 1) + ")" +
                             title for idx, title in enumerate(titles)) 
    #provides links for respective articles
    links = []
    for x in articles:
        links.append(str(x.URL))
    
    
    print titles
    print links
    
#handle()
#execute("a","b","what is happening in the world?")