package com.edu.phonestore.room.management;

import android.content.Context;
import android.os.AsyncTask;

import com.edu.phonestore.room.dao.UserDao;
import com.edu.phonestore.room.database.PhoneDatabase;
import com.edu.phonestore.room.entity.User;

public class UserManagement {
    private UserDao userDao;
    private Context context;

    public UserManagement(Context context) {
        this.context = context;
        PhoneDatabase phoneDatabase = PhoneDatabase.getDatabase(context);
        userDao = phoneDatabase.userDao();
    }

    public interface OnDataCallBackUser{
        void onDataSuccess(User user);
        void onDataFail();
    }

    /**
     * add user
     */
    public void addUserToDB(User user, OnDataCallBackUser listener) {
        AddUserAsync addUserAsync = new AddUserAsync(userDao, listener);
        addUserAsync.execute(user);
    }
    private class AddUserAsync extends AsyncTask<User, Void, Void>{
        private UserDao userDao;
        private OnDataCallBackUser listener;

        public AddUserAsync(UserDao userDao, OnDataCallBackUser listener) {
            this.userDao = userDao;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.addUser(users);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.onDataSuccess(null);
        }
    }

    /**
     * checkExist user
     */
    public void checkExistUserToDB(OnDataCallBackUser listener) {
        CheckExistUserAsync checkExistUserAsync = new CheckExistUserAsync(userDao, listener);
        checkExistUserAsync.execute();
    }
    private class CheckExistUserAsync extends AsyncTask<User, Void, Void>{
        private UserDao userDao;
        private OnDataCallBackUser listener;
        private User user;

        public CheckExistUserAsync(UserDao userDao, OnDataCallBackUser listener) {
            this.userDao = userDao;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            user = userDao.getUserInfo();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (user != null) {
                listener.onDataSuccess(user);
            } else {
                listener.onDataFail();
            }
        }
    }

    /**
     * get info user
     */
    public void userInfoFromDB(OnDataCallBackUser listener) {
        UserInfoAsync userInfoAsync = new UserInfoAsync(userDao, listener);
        userInfoAsync.execute();
    }
    private class UserInfoAsync extends AsyncTask<User, Void, Void>{
        private UserDao userDao;
        private OnDataCallBackUser listener;
        private User user;

        public UserInfoAsync(UserDao userDao, OnDataCallBackUser listener) {
            this.userDao = userDao;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            user = userDao.getUserInfo();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (user != null) {
                listener.onDataSuccess(user);
            } else {
                listener.onDataFail();
            }
        }
    }

    /**
     * delete user
     */
    public void deleteUserToDB(OnDataCallBackUser listener) {
        DeleteUserAsync deleteUserAsync = new DeleteUserAsync(userDao, listener);
        deleteUserAsync.execute();
    }
    private class DeleteUserAsync extends AsyncTask<User, Void, Void>{
        private UserDao userDao;
        private OnDataCallBackUser listener;

        public DeleteUserAsync(UserDao userDao, OnDataCallBackUser listener) {
            this.userDao = userDao;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listener.onDataSuccess(null);
        }
    }
}
