Server implementation using Java

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


Thank you