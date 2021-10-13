import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try{
            socket = new Socket("localhost",1234);
            isr = new InputStreamReader(socket.getInputStream());
            osw = new OutputStreamWriter(socket.getOutputStream());
            reader = new BufferedReader(isr);
            writer = new BufferedWriter(osw);

            Scanner scan = new Scanner(System.in);
            while(true)
            {
                String msgToSend = scan.nextLine();
                writer.write(msgToSend);
                writer.newLine();
                writer.flush();

                System.out.println("SERVER: "+reader.readLine());

                if(msgToSend.equalsIgnoreCase("Bye"))
                    break;
            }

        }
        catch(Exception e)
        {
        }
        finally {
            try {
                if (socket != null)
                    socket.close();
                if (isr != null)
                    isr.close();
                if (osw != null)
                    osw.close();
                if (reader != null)
                    reader.close();
                if (writer != null)
                    writer.close();
            } catch (IOException i) {

            }
        }
    }
}
