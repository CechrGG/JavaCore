package acmr.javacore.basic.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class FileChannelTest {

    public static void main(String[] args) {
        FileInputStream fis = null;
        FileChannel fisChannel = null;
        try {
            fis = new FileInputStream("F:/idea/JavaCore/javacore/mark/io.md");
            fisChannel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            fisChannel.read(buffer);
            byte[] array = buffer.array();
            System.out.println("第一次读\n " + new String(array));
            System.out.println("********************************");
            buffer.flip();  //写入buffer之后读取buffer之前调用，从头读到上次写入位置
            System.out.println(buffer.remaining());
//            Buffer position = buffer.position(1025);  //异常
//            Buffer limit = buffer.limit(1025);  //异常
//            fisChannel.read(buffer);
            array = buffer.array();
            System.out.println("flip\n " + new String(array));
            System.out.println("********************************");
            buffer.clear();  //写buffer之前调用，从头写到最大位置
            System.out.println(buffer.remaining());
            array = buffer.array();
            System.out.println("clear\n " + new String(array));
            System.out.println("********************************");
            CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            StringBuilder result = new StringBuilder();
            int count;
            while ((count = fisChannel.read(buffer)) > 0) {
                buffer.flip();  //写入buffer之后读取buffer之前调用，从头读到上次写入位置
                decoder.decode(buffer, charBuffer, false);
                buffer.clear();
                charBuffer.flip();
                result.append(charBuffer);
                charBuffer.clear();
//                array = buffer.array();
//                System.out.println("读" + buffer.position() + "字节\n " + new String(array, 0 , buffer.position()));
//                System.out.println("********************************");
//                buffer.clear();
            }
            buffer.flip();
            decoder.decode(buffer, charBuffer, true);
            charBuffer.flip();
            result.append(charBuffer);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fisChannel != null) {
                    fisChannel.close();
                }
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
