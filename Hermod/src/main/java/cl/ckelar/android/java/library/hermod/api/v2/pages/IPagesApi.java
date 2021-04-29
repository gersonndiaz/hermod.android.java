package cl.ckelar.android.java.library.hermod.api.v2.pages;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPagesApi {

    @GET("wp-json/wp/v2/pages")
    Call<ResponseBody> getPageBySlug(@Query("slug") String slug,
                                     @Query("_embed") String _embed);

}
