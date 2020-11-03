package java0.nio01;

import java.io.*;
import java.nio.CharBuffer;

import okhttp3.*;

class MyOkHttpClient {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request build = new Request.Builder()
                .get()
                .url("http://localhost:8888")
                .addHeader("Connection", "close")
                .build();

        Response response = okHttpClient.newCall(build).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        Reader reader = response.body().charStream();
        CharBuffer buf = CharBuffer.allocate(100);
        reader.read(buf);

        buf.flip();
        char[] dst = new char[buf.limit()];
        buf.get(dst, 0 , buf.limit());
        System.out.println(dst);

    }

}

