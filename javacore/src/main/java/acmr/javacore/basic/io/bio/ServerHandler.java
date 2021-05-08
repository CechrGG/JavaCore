package acmr.javacore.basic.io.bio;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ServerHandler implements Runnable{
    private final Logger logger = LogManager.getLogger(ServerHandler.class);
    private final Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            String line;
            while ((line = reader.readLine()) != null) {
                writer.println("收到请求消息:" + line);
                if(line.equalsIgnoreCase("end")) {
                    break;
                }
            }
            writer.println("接收完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close();
                }
                if(reader != null) {
                    reader.close();
                }
                if(socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
