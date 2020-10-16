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
    404: Page not found, Server doesnt find the file. \n
    200: OK, if server finds the file it responds with the content passed in the request. \n
    301: Permanently Moved, if it detects the file name passed is "getmoved.html" it redirects the page to "https://google.com/ \n

Thank you