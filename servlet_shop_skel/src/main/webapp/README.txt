*** Servlet shop ***
(also see comments in code)

This is the "request based" approach. The shop model
is "webbified" using Servlets and Java Server pages.
Application interacting directly (and solely) with low level HTTP 
(request, response objects)

Design
-------
This uses the "FrontControlller" pattern i.e. any request
is handled by a frontcontroller Servlet. Servlet performs 
(or delegates) updates of model and decides outcome view
(possible dependen of update result).

Front controller uses parameters "action" and "view"
to decide what to do and where to navigate

All pages are composed of "partials" and "content". 
Content is dynamically inserted into a common template.
Will give consistent look for all pages.

All css, img, etc. in folder resources

A reference to the shop is stored in the ApplicationScope at
application startup (using a weblistener).

JSP Style
---------
We only use document style (XML). No Java code in
JSP pages (aka scriptlets)

GUI Style
---------
This uses bootstrap for style (and a few own rules in default.css) 

Auth-entication and -orization
-----------------------------
No present right now. Possible to implement application
handled auth. using a Webfilter.

Execution
---------
This is run on Tomcat using "deploy on save" to speed
up development cycle.

