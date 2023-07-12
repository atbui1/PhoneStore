package com.edu.phonestore.phone;

import android.content.Context;

import com.edu.phonestore.api.ApiClient;
import com.edu.phonestore.api.CallBackData;
import com.edu.phonestore.model.Product;
import com.edu.phonestore.payload.response.LoginResponse;
import com.edu.phonestore.room.entity.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneRepositoryImp implements PhoneRepository{

    private static List<User> users;
    private static List<Product> products;

    @Override
    public void loginApp(Context context, String username, String password, final CallBackData<User> callBackData) {
        ApiClient apiClient = new ApiClient();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            System.out.println("11111111111111 *************************************");
            System.out.println("username1: " + username);
            System.out.println("password1: " + password);
            System.out.println("222222222222222 *************************************");
        } catch (Exception e) {
            System.out.println("ERR: 11111111111111111111111111111111111111111111111111111111");
            System.out.println("ERR: 222222222222222222222222222222222222222222222222222222222");
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> serviceCall = apiClient.phoneService().loginApp(body);
        System.out.println("37 iml **********************************************************************************************************************");
        System.out.println("bodyJson: " + body);
        System.out.println("serviceCall: " + serviceCall);
        System.out.println("40 iml **********************************************************************************************************************");
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println("45 --------------- iml");
                    if (response.code() == 200 && response.body() != null) {
                        System.out.println("47 --------------- iml");
                        try {
                            System.out.println("49 --------------- iml");
                            String result = response.body().string();
                            JSONObject jsonObject = new JSONObject(result);//.getJSONObject("data");
                            User userResponse = new Gson().fromJson(jsonObject.getString("data").toString(), User.class);
                            if (userResponse == null) {
                                System.out.println("54 --------------- iml");
                                callBackData.onFail("login response null");
                            } else{
                                System.out.println("57 --------------- iml");
                                callBackData.onSuccess(userResponse);
                            }
                            System.out.println("59 --------------- iml");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        System.out.println("64 --------------- iml");
                    } else {
                        System.out.println("66 --------------- iml");
                        callBackData.onFail("");
                    }
                    System.out.println("69 --------------- iml");
                } catch (Exception ex) {
                    System.out.println("71 --------------- iml");
                    ex.printStackTrace();
                }
                System.out.println("74 --------------- iml");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("");
            }
        });
    }

    @Override
    public void getMessages(Context context, CallBackData<List<User>> callBackData) {
        users = new ArrayList<>();
        try {
            ApiClient apiClient = new ApiClient();
            Call<ResponseBody> serviceCall = apiClient.phoneService().getMessages();
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200 && response.body() != null) {
                        try {
                            String result = response.body().string();
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                User user = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), User.class);
                                users.add(user);
                            }
                            if (users != null) {
                                System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS size: " + users.size());
                                callBackData.onSuccess(users);
                            } else {
                                callBackData.onFail("not found users: " + response.message());
                            }
                        } catch (Exception ex) {
                            System.out.println("parse json failed -- Exception message: " + ex);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callBackData.onFail("connect server failed...!!! ");

                }
            });
        } catch (Exception ex) {
            System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFF Exception users: " + ex);
        }
    }

    /** product */
    @Override
    public void getProducts(Context context, CallBackData<List<Product>> callBackData) {
        products = new ArrayList<>();
        try {
            ApiClient apiClient = new ApiClient();
            Call<ResponseBody> serviceCall = apiClient.phoneService().getProducts();
            serviceCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200 && response.body() != null) {
                        try {
                            String result = response.body().string();
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Product product = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), Product.class);
                                products.add(product);
                            }
                            if (products != null) {
                                callBackData.onSuccess(products);
                            } else {
                                callBackData.onFail("failed get products: " + response.message());
                            }
                        } catch (Exception ex) {
                            System.out.println("parse json failed -- Exception products: " + ex);
                            ex.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callBackData.onFail("khong the ket noi voi server....!!!");
                }
            });
        } catch (Exception ex) {
            System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFF Exception products: " + ex);
            ex.printStackTrace();
        }
        }
}
