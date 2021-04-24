package acmr.javacore.basic.io.stream;

import java.io.*;

public class IOStreamTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File file = new File("F:\\idea\\JavaCore\\javacore\\mark\\io.md");
            if(!file.exists()) {
                file.createNewFile();
            }
            File bakFile = new File("F:\\idea\\JavaCore\\javacore\\mark\\io_bak.md");
            if(!bakFile.exists()) {
                bakFile.createNewFile();
            }
            fis = new FileInputStream(file);
            fos = new FileOutputStream(bakFile);
            byte[] buffer = new byte[1024];
            int count;
            int total = 0;
            while ((count = fis.read(buffer)) != -1) { //一次读取buffer.length字节
                total += count;
                System.out.println("已读取" + total + "个字节");
                fos.write(buffer, 0, count);    //如果直接write(buffer),最后一次默认刷buffer.length
            }
//            fos.flush();  //FileOutputStream中并没有重写flush, 而OutputStream中的flush也啥都没做
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {   //关闭流还是很重要的，否则可能会产生问题
                if (fos != null) {
                    fos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
