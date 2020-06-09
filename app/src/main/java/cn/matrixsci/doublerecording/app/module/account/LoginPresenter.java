package cn.matrixsci.doublerecording.app.module.account;

public class LoginPresenter {
    public void login(String name, String pwd,LoginCallback loginCallback) {
        //在这里异步调用登录sdk或api
        //在收到的回调里调参数loginCallback的相应方法
        loginCallback.onSuccess("测试登录成功");
    }
}
