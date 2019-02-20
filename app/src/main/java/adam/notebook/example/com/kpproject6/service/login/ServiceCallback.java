package adam.notebook.example.com.kpproject6.service.login;

import adam.notebook.example.com.kpproject6.model.User;

public interface ServiceCallback{

    void onSuccess(User data);
    void onFailed(String message);
    void showProgress(boolean show);
}
