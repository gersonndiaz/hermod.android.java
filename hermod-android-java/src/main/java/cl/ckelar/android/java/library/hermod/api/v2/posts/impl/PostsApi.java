package cl.ckelar.android.java.library.hermod.api.v2.posts.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cl.ckelar.android.java.library.hermod.api.v2.posts.IPostsApi;
import cl.ckelar.android.java.library.hermod.dto.v2.posts.Post;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class PostsApi {

    private static final String TAG = PostsApi.class.getName();

    /**
     * Obtiene los posts
     *
     * @param baseUrl Dirección URL del sitio web
     * @param page Nª de página actual
     * @param per_page cantidad de registros por página
     * @param orderby ordenar por
     * @param _embed
     *
     * @return Posts
     * **/
    public List<Post> getPosts(String baseUrl, int page, int per_page, String orderby, String _embed) {

        List<Post> posts = new ArrayList<>();

        try {

            Log.i(TAG, "--- METHOD: getPosts ---");

            Log.i(TAG, "URL API: " + baseUrl);     // Debug

            OkHttpClient.Builder b = new OkHttpClient.Builder();
            b.readTimeout(30, TimeUnit.SECONDS);
            b.writeTimeout(30, TimeUnit.SECONDS);
            b.connectTimeout(30, TimeUnit.SECONDS);
            OkHttpClient client = b.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .build();
            IPostsApi iPostsApi = retrofit.create(IPostsApi.class);

            Call<ResponseBody> call = iPostsApi.getPosts(page, per_page, orderby, _embed);
            retrofit2.Response<ResponseBody> responseBody = call.execute();

            Log.i(TAG, call.request().url().toString());

            if (responseBody.isSuccessful()) {

                Log.i(TAG, "--- Obtiene los posts ---");

                try {

                    String response = responseBody.body().string();

                    Log.d(TAG, response);   // Debug

                    Gson gson = new Gson();

                    Type collectionType = new TypeToken<List<Post>>(){}.getType();
                    posts= gson.fromJson(response, collectionType);

                    Log.i(TAG, "--- Decodifica el JSON Posts ---");

                }
                catch (Exception ex) {
                    Log.e(TAG, "--- ERROR al decodificar el JSON Posts ---");
                    Log.e(TAG, ex.toString());
                    posts = null;
                }

            } else {
                /*try {

                    String response = responseBody.errorBody().string();

                    Log.d(TAG, response);   // Debug

                    Gson gson = new Gson();

                    accountResponse = gson.fromJson(response, AccountResponse.class);

                }
                catch (Exception ex) {
                    Log.i(TAG, "--- ERROR al decodificar el JSON Posts ---");
                    posts = null;
                    Log.e(TAG, ex.toString());
                }*/

                Log.e(TAG, "--- ERROR al consultar el Servicio ---");
                posts = null;

            }

        } catch (Exception e) {

            Log.e(TAG, "--- ERROR al consultar Posts ---");
            Log.e(TAG, e.toString());
            posts = null;
        }

        return posts;

    }
}
