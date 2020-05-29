package cn.matrixsci.doublerecording.app.presenter;

public interface LoginCallback {
    void onSuccess(String data);

    void onFailure(String error);
}
