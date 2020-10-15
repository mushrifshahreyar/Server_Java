import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;


public class server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            System.out.println("\n\nServer Started...\n");
            
            //Creating server object and starts listening to port mentioned
            serverSocket = new ServerSocket(8080);

            System.out.println("Listening to port : 8080\n");

            while (true) {
                //Socket is created
                Socket connection = serverSocket.accept();
                System.out.println("\nConnection created\n");

                //Thread for handling multiple client requests and its started which will eventually run "RUN" program in the class
                HandleClient handleClient_Thread = new HandleClient(connection);
                handleClient_Thread.start();
                
            }

        } catch (SocketException s) {
            s.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class HandleClient extends Thread {
    
    final Socket socket;

    public HandleClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String fileName = null;
        
        while(true) {
            try {

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                
                //Readrequest is a function for reading the incoming request and will return the file name mentioned in the request
                fileName = readrequest(bufferedReader);
                System.out.println("\nFile name requesting is : " + fileName);
                
                String Nresponse;

                if(fileName.equals("getmoved.html")) {
                    System.out.println("\nRedirecting to Google\n");
                    Nresponse = "HTTP/1.1 301 Moved Permanently\r\nLocation: https://google.com/\r\n\r\n";    
                }
                else {
                    String response = createresponce(fileName);
                    
                    if(response == null) {
                        Nresponse = "HTTP/1.1 404 NOT FOUND \r\n\r\n " + "Page not found";    
                    }
                    else {
                        Nresponse = "HTTP/1.1 200 OK \r\n\r\n " + response;
                    }
                }

                socket.getOutputStream().write(Nresponse.getBytes("UTF-8"));
                
                socket.close();

                break;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String readrequest(BufferedReader bufferedReader) {
        
        System.out.println("Reading Request...\n");
        String filename = "";
        String line;
        try {
            line = bufferedReader.readLine();
            while(line != null && !line.isEmpty()) {
                if(line.contains("GET")) {
                    int start = line.indexOf("/");
                    int i = start;
                    for(;i < line.length() ; ++i) {
                        char temp = line.charAt(i);
                        if(temp == ' ') {
                            break;
                        }
                    }
                    if(start+1 == i) {
                        filename = "index.html";
                    }
                    else {
                        filename = line.substring(start+1, i);
                    }
                }
                
                
                System.out.println(line);
                
                line = bufferedReader.readLine();
            }
            
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
            return null;
        }
        return filename;        
    }

    private static String createresponce(String filename) {
        if(filename == null) {
            return null;
        }
        
        String response = "";
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                response += scanner.nextLine();
            }
            scanner.close();
        }
        catch(Exception e) {
            System.out.println("\n****File not found***\n");
            
            e.printStackTrace();
            
            return null;
        }
        if(response.isEmpty()) {
            return null;
        }
        return response;
    }
}