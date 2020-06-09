package cn.matrixsci.doublerecording.app.module.account;

public interface LoginCallback {
    void onSuccess(String data);

    void onFailure(String error);
}
