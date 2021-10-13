import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;

        serverSocket = new ServerSocket(1234);

        try{
            while(true) {
                socket = serverSocket.accept();
                isr = new InputStreamReader(socket.getInputStream());
                osw = new OutputStreamWriter(socket.getOutputStream());
                reader = new BufferedReader(isr);
                writer = new BufferedWriter(osw);

                while(true){
                    String msgReceived = reader.readLine();
                    System.out.println("CLIENT: " + msgReceived);
                    writer.write("A Message Received!");
                    writer.newLine();
                    writer.flush();

                    if(msgReceived.equalsIgnoreCase("Bye"))
                        break;

                }
                socket.close();
                isr.close();
                osw.close();
                reader.close();
                writer.close();
            }
        }
        catch(IOException i)
        {

        }
    }
}
