package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            byte[] array = Files.readAllBytes(Paths.get("C:\\TEMP\\ScreenCapture\\messages"));
            byte[] lastTwenty = getLastTwentyRows(array);
            t.sendResponseHeaders(200,lastTwenty.length);
            OutputStream os = t.getResponseBody();
            os.write(lastTwenty);
            os.close();
        }
    }

    private byte[] getLastTwentyRows(byte[] arr) {
        int countSymbol= 0;
        byte[] newArray = new byte[arr.length];
        List<Integer> rows = new ArrayList<>();
        for (byte b : arr) {
            if ((char) b == '\n') {
                rows.add(countSymbol);
            }
            countSymbol++;
        }
        if (rows.size() > 20) {
            int j = 0;
            for (int i = rows.get(rows.size() - 20) ; i < arr.length; i++) {
                newArray[j++] = arr[i];
            }
            byte[] finalArr = new byte[j];
            System.arraycopy(newArray, 0, finalArr, 0, j);
            return finalArr;
        } else {
            newArray = arr;
        }
        return newArray;
    }
}
