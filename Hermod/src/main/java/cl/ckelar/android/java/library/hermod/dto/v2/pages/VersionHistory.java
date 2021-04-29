
package cl.ckelar.android.java.library.hermod.dto.v2.pages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionHistory {

    @SerializedName("href")
    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
