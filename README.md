# Chaitanya
FILE Process
============

Sample Request:

Get all detail about files in a directory
-----------------------------------------
URL : http://localhost:6005/FileProcess/rest/getFileDetail
Headers:
Content-Type: application/json

Body:
{"path":"c:\test"}

Get the detail about files in a directory with limitted file extensions
-----------------------------------------------------------------------
URL : http://localhost:6005/FileProcess/rest/getFileDetailForSpecifiedMimeType
Headers:
Content-Type: application/json

Body:
{"path":"c:\test","acceptedMimeType":"json,excel,csv"}
