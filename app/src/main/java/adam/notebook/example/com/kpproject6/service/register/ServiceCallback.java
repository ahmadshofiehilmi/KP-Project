package adam.notebook.example.com.kpproject6.service.register;

public interface ServiceCallback {

    void onSuccess();
    void onFailed(String message);
    void showProgress(boolean show);
}