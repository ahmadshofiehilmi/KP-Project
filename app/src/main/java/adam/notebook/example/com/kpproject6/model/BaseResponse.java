package adam.notebook.example.com.kpproject6.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("code")
    private String code;
    @SerializedName("status")
    private Boolean status;
    @SerializedName("message")
    private String message;
//    @SerializedName("new_token")
//    private String newToken;
    @SerializedName("user")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String getNewToken() {
//        return newToken;
//    }
//
//    public void setNewToken(String newToken) {
//        this.newToken = newToken;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}