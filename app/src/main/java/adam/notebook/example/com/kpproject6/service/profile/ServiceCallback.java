package adam.notebook.example.com.kpproject6.service.profile;

public interface ServiceCallback {
    void onSuccess();

    void onFailed(String message);

    void onAccountDeleted();

    void showAlamatUtama(String alamat);
}