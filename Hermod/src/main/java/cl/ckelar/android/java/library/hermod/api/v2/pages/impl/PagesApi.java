package cl.ckelar.android.java.library.hermod.api.v2.pages.impl;

import android.util.Log;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import cl.ckelar.android.java.library.hermod.api.v2.pages.IPagesApi;
import cl.ckelar.android.java.library.hermod.dto.v2.pages.Page;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class PagesApi {

    private static final String TAG = PagesApi.class.getName();


    /**
     * Obtiene una page por su slug
     *
     * @param baseUrl Dirección URL del sitio web
     * @param slug
     * @param _embed
     *
     * @return Posts
     * **/
    public Page getPageBySlug(String baseUrl, String slug, String _embed) {

        Page page;

        try {

            Log.i(TAG, "--- METHOD: getPageBySlug ---");

            Log.d(TAG, "URL API: " + baseUrl);     // Debug

            OkHttpClient.Builder b = new OkHttpClient.Builder();
            b.readTimeout(30, TimeUnit.SECONDS);
            b.writeTimeout(30, TimeUnit.SECONDS);
            b.connectTimeout(30, TimeUnit.SECONDS);
            OkHttpClient client = b.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .build();
            IPagesApi iPagesApi = retrofit.create(IPagesApi.class);

            Call<ResponseBody> call = iPagesApi.getPageBySlug(slug, _embed);
            retrofit2.Response<ResponseBody> responseBody = call.execute();

            if (responseBody.isSuccessful()) {

                Log.i(TAG, "--- Obtiene la página ---");

                try {

                    String response = responseBody.body().string();
                    response = response.substring(1, response.length()-1);

                    Log.d(TAG, response);   // Debug

                    Gson gson = new Gson();

                    page = gson.fromJson(response, Page.class);

                    Log.i(TAG, "--- Decodifica el JSON Page ---");

                }
                catch (Exception ex) {
                    Log.e(TAG, "--- ERROR al decodificar el JSON Page ---");
                    Log.e(TAG, ex.toString());
                    page = null;
                }

            } else {

                Log.e(TAG, "--- ERROR al consultar el Servicio ---");
                page = null;

            }

        } catch (Exception e) {

            Log.e(TAG, "--- ERROR al consultar la página ---");
            Log.e(TAG, e.toString());
            page = null;
        }

        return page;

    }
}
