
package cl.ckelar.android.java.library.hermod.dto.v2.error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("params")
    @Expose
    private Params params;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

}
