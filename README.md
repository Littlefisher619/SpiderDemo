# SpiderDemo
A java based spider demo project. Designed for xbiquge and bilibili. And code for homework.

------

## Table of contents
- [Dependencies](#dependencies)
- [Features](#features)
- [Environment Setup](#environment)
- [Cmd-line Arguments](#cmd-line-arguments)
- [FAQ](#faq)

## Dependencies
| item | version |
|:-:|:-:|
|gson| 2.8.5|
|selenium-java |3.141.59|
|jsoup|1.11.3|
|log4j-core|2.11.1|
|mysql-connector-java|8.0.14|

## Features
* MySQL supported
* Multithreaded
* Highly customized
* Resume jobs from the last interruption
* Detailed logs

## Environment
* Java Runtime Environment 
* MySQL: 
    1. Create a database named `spider` assigned to user `spider` with password `spider`. 
    2. Import .sql file in this project. 
    3. Program will access MYSQL through `localhost:3306`.
* [Selenium Standalone Server](https://www.seleniumhq.org/download/) & [Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/downloads): 
    1. Download and then put them into a folder.
    2. Run Selenium hub and node **BEFORE** you launch spider. [How to start-up hub and node?](#faq)

## Cmd-line Arguments
```
java spider.jar help
```
* For bilibili (No required arguments):

| argument | optional | default |description |
|:-:|:-:|:-:|:-:|
|--th|√|4| thread
|--divide|√|5000| Jobs want to divided into
|--to|√|1000000| the max number of avid to enum
|--conntimeout|√|15000| Connection Timeout `(ms)`
|--readtimeout|√|60000|Timeout for reading response `(ms)`


* For xbiquge:

| argument | optional | default |description |
|:-:|:-:|:-:|:-:|
|args\[1\]|x|-|Path to Chrome Driver|
|args\[2\]|x|5000| Selenium's Remote Driver URL
|--th|√|1000000| the max number of avid to enum
|--sftimeout|√|3600|  Summary Fetch Job Timeout In total `(s)`
|--cdtimeout|√|3600| Chapter Dump Job Timeout In total `(s)`
|--pltimeout|√|15| Selenium Page Load Timeout `(s)`
|--skipsf|√|-|Skip Summary Fetch Jobs
|--override|√|-| Override existing chapters
|--ignoreupdate|√|-| Skip novels update check


* Usage example:
```
java -jar spider.jar xbiquge D:\selenium\chromedriver.exe http://localhost:4444/wd/hub
java -jar spider.jar xbiquge D:\selenium\chromedriver.exe http://localhost:4444/wd/hub --skipsf --th16
java -jar spider.jar bilibili
java -jar spider.jar bilibili --to500000 --divide1000 --th16
```
## FAQ

* How to start-up Selenium-hub and nodes?

There is a example on windows:
```
java -jar selenium-server-standalone-3.141.59.jar -role hub -maxSession 40 -port 4444
java -Dwebdriver.ie.driver=chromedriver.exe -jar selenium-server-standalone-3.141.59.jar -role node -hub http://127.0.0.1:4444/grid/register -maxSession 20 -browser "browserName=chrome,maxInstances=20" -port 5555
```
