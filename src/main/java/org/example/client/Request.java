package org.example.client;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Request {


    public static String sendRequest(JSONObject jsonBody , String endpoint ) throws Exception {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.parse("application/json")
        );

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url( endpoint )
                .post(body)
                .build();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<String> result = executor.submit(() -> {
            try (okhttp3.Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response: " + response);
                }
                String responseBody = response.body().string();
                return responseBody.isEmpty() ? "⚠️ Empty body received!" : responseBody;
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        });



        executor.shutdown();

        return result.get();

    }

}
