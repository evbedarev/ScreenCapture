package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebServer extends Thread{
    @Override
    public void run() {
        try {
            startWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void startWeb() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/logs", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            byte[] array = Files.readAllBytes(Paths.get("C:\\TEMP\\ScreenCapture\\messages"));
            t.sendResponseHeaders(200,array.length);
            OutputStream os = t.getResponseBody();
            os.write(array);
            os.close();
        }
    }
}
