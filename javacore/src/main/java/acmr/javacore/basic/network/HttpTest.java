package acmr.javacore.basic.network;

import org.apache.http.client.methods.HttpGet;

public class HttpTest {
    public static void main(String[] args) {
//        HttpClientUtil.get(new HttpGet("http://localhost:8080/javacore/cat"));
        HttpClientUtil.get(new HttpGet("http://localhost:8080/javacore/slave/main"));
        HttpClientUtil.get(new HttpGet("http://localhost:8080/javacore/httptest/test"));
    }
}
