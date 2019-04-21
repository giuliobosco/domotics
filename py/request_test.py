import urllib2
content = urllib2.urlopen("http://localhost:8080/alive").read()
print content
