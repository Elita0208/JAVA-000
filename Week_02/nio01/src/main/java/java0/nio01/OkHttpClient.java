package java0.nio01;

import java.io.IOException;
import okhttp3.*;

class OKHttpClient {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request build = new Request.Builder()
                .get()
                .url("http://localhost:8801")
                .build();

        Response response = okHttpClient.newCall(build).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        System.out.println(response.body());
    }

}
