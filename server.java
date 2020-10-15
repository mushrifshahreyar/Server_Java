import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;


public class server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Listening to port 8080");

            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println("Connection created");

                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                
                Thread t = new HandleClient(connection,inputStreamReader);
                // BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                // String filename = readrequest(bufferedReader);

                // String Nresponse;
                // if(filename.equals("getmoved.html")) {
                //     System.out.println("reached here===========");
                //     Nresponse = "HTTP/1.1 302 Moved Permanently \r\n Location: http://127.0.0.1/Q1.html \r\n\r\n";    
                // }
                // else {
                //     String response = createresponce(filename);
                    
                //     if(response == null) {
                //         Nresponse = "HTTP/1.1 404 NOT FOUND \r\n\r\n " + "Page not found";    
                //     }
                //     else {
                //         Nresponse = "HTTP/1.1 200 OK \r\n\r\n " + response;
                //     }
                // }
                // connection.getOutputStream().write(Nresponse.getBytes("UTF-8"));
                
                // connection.close();

                t.start();
                
            }
        } catch (SocketException s) {
            s.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String createresponce(String filename) {
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
            e.printStackTrace();
        }
        if(response.isEmpty()) {
            System.out.println("Empty");
            response = null;
        }
        return response;
    }

    public static String readrequest(BufferedReader bufferedReader) {
        String filename = "";
        try {
            String text = bufferedReader.readLine();
            while(text != null && !text.isEmpty()) {
                if(text.contains("GET")) {
                    int start = text.indexOf("/");
                    int i = start;
                    for(;i < text.length() ; ++i) {
                        char temp = text.charAt(i);
                        if(temp == ' ') {
                            break;
                        }
                    }
                    if(start+1 == i) {
                        filename = "index.html";
                    }
                    else {
                        filename = text.substring(start+1, i);
                        System.err.println("Getmoved");
                    }
                }
                text = bufferedReader.readLine();
            }

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return filename;
    }
}

class HandleClient extends Thread {
    final InputStreamReader inputStreamReader;
    
    final Socket socket;

    public HandleClient(Socket socket, InputStreamReader inputStreamReader) {
        this.socket = socket;
        this.inputStreamReader = inputStreamReader;
    }

    @Override
    public void run() {
        String fileName;

        while(true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                fileName = readrequest(bufferedReader);
                System.out.println(fileName);
                String filename = readrequest(bufferedReader);

                String Nresponse;
                if(filename.equals("getmoved.html")) {
                    Nresponse = "HTTP/1.1 302 Moved Permanently \r\n Location: http://127.0.0.1/Q1.html \r\n\r\n";    
                }
                else {
                    String response = createresponce(filename);
                    
                    if(response == null) {
                        Nresponse = "HTTP/1.1 404 NOT FOUND \r\n\r\n " + "Page not found";    
                    }
                    else {
                        Nresponse = "HTTP/1.1 200 OK \r\n\r\n " + response;
                    }
                }
                socket.getOutputStream().write(Nresponse.getBytes("UTF-8"));
                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String readrequest(BufferedReader bufferedReader) {
        
        String filename = "";
        try {
            String text = bufferedReader.readLine();
            while(text != null && !text.isEmpty()) {
                if(text.contains("GET")) {
                    int start = text.indexOf("/");
                    int i = start;
                    for(;i < text.length() ; ++i) {
                        char temp = text.charAt(i);
                        if(temp == ' ') {
                            break;
                        }
                    }
                    if(start+1 == i) {
                        filename = "index.html";
                    }
                    else {
                        filename = text.substring(start+1, i);
                        System.err.println("Getmoved");
                    }
                }
                text = bufferedReader.readLine();
            }

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return filename;
    }

    private static String createresponce(String filename) {
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
            e.printStackTrace();
        }
        if(response.isEmpty()) {
            System.out.println("Empty");
            response = null;
        }
        return response;
    }
}