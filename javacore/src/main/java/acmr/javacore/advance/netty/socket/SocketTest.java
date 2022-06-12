package acmr.javacore.advance.netty.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.stream.IntStream;

/**
 * @author guoguo
 * @version 1.0.0
 * @date 2022/6/12
 * @description
 */
@Slf4j
public class SocketTest {
    public static void main(String[] args) {
//        log.trace("trace");
//        log.debug("debug");
//        log.info("info");
//        log.warn("warn");
//        log.error("error");
        MyServerSocket myServerSocket = new MyServerSocket();
        myServerSocket.start();
        IntStream.rangeClosed(1, 50).forEach(i -> {
            new Thread(() -> {
                try {
                    Socket socket = new Socket("localhost", 8848);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write("你好，服务器，我是客户端[" + i + "]\n");
                    writer.flush();
                    myServerSocket.printPoolInfo();
                    while (true) {
                        InputStream inputStream = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        String msg = reader.readLine();
                        if(StringUtils.hasText(msg)) {
                            log.debug("接收到服务器的回复：" + msg);
                            writer.close();
                            socket.close();
                            break;
                        }
                    }
                    myServerSocket.printPoolInfo();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.warn("客户端[" + i + "]连接服务器失败");
                }
            }).start();
        });
    }
}
