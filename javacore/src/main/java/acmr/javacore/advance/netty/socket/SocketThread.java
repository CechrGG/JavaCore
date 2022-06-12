package acmr.javacore.advance.netty.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.Socket;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/12
 * @description
 */
@Slf4j
public class SocketThread implements Runnable{
    private Socket socket;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String client = "客户端[" + socket.getInetAddress() + ":" + socket.getPort() + "]";
        log.debug(client + "已连接");
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String msg = reader.readLine();
            log.info("接收到" + client + "发送的消息：" + msg);
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write("服务器已接收到" + client + "发送的消息。\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(client + "读取失败：" + e.getMessage());
        }
    }
}
