package acmr.javacore.basic.io.bio;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ClientTest implements Runnable{
    private final Logger logger = LogManager.getLogger(ClientTest.class);
    private final String ip;
    private final int port;
    private final int number;

    public ClientTest(String ip, int port, int number) {
        this.ip = ip;
        this.port = port;
        this.number = number;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("我是客户端" + number);
            writer.println("你是服务器端口" + port + "吧？");
            writer.println("end");
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
                if(line.equals("接收完毕"))
                    break;
            }
            logger.info("没错儿，对上了");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
                if(writer != null) {
                    writer.close();
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
