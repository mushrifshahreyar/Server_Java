<h3 align=center>Server implementation using Java</h3>

How to run in linux:

    1. Open terminal

    2. Type the command:
        make
    
    3. If the above command doesnt work then:

        a. Compiling the java code
            javac server.java
        b. Running the java code
            java server


Functions included:

    404: Page not found, Server doesnt find the file.

    200: OK, if server finds the file it responds with the content passed in the request.

    301: Permanently Moved, if server detects the file name passed is "getmoved.html" it redirects the page to "https://google.com/"

Testing:

    Python script for testing is also included. Scripts sends multiple client requests and checks whether the server is capable of handling it.

    How to run:

        1. Open Terminal 

        2. python3 client_request.py

    urls and expected output:

        http://localhost/               -   returns index.html page

        http://localhost/page1.html     -   returns page1.html page

        http://localhost/page2.html     -   returns page2.html page

        http://localhost/getmoved.html  -   Redirects to https://google.com/

        http://localhost/page3.html     -   Page is not found hence returns 404 error

Thank you