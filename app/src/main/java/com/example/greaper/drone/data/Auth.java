package com.example.greaper.drone.data;

import com.example.greaper.drone.utils.AppUtils;
import com.example.greaper.drone.utils.Const;

public class Auth {

    public interface Callback {
        void onFinish(Task<User> task);
    }

    public static class Task<T> {
        boolean isSuccessful = false;
        T response;

        public Task(boolean isSuccessful, T response) {
            this.isSuccessful = isSuccessful;
            this.response = response;
        }

        public boolean isSuccessful() {
            return isSuccessful;
        }

        public T getResponse() {
            return response;
        }
    }

    private static Auth instance;

    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    private User currentUser = null;

    private Auth() {

    }

    public void login(String username, String password, Callback callback) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            callback.onFinish(new Task<>(false, null));
            return;
        }
        if (username.equals("admin") && password.equals("admin1234")) {
            currentUser = new User();
            currentUser.setName("Quản trị viên");
            currentUser.setRole(Const.ADMIN);
            AppUtils.setCurrentRole(Const.ADMIN);
            callback.onFinish(new Task<>(true, currentUser));
            return;
        }
        if (username.equals("kiemlam") && password.equals("kiemlam")) {
            currentUser = new User();
            currentUser.setName("Kiểm lâm");
            currentUser.setRole(Const.RANGER);
            AppUtils.setCurrentRole(Const.RANGER);
            callback.onFinish(new Task<>(true, currentUser));
            return;
        }
        callback.onFinish(new Task<>(false, null));
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
