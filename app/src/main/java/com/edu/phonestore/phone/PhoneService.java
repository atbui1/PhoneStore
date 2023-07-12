package com.edu.phonestore.phone;

import com.edu.phonestore.api.ApiUlrConfig;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;

public interface PhoneService {
//    @GET(ApiUlrConfig.Ulr.ACCOUNT_LOGIN)
//    @Headers("Content-Type:application/json; charset=utf-8")
    @HTTP(method = "POST", path = ApiUlrConfig.Ulr.ACCOUNT_LOGIN, hasBody = true)
    Call<ResponseBody> loginApp(@Body RequestBody body);

    @HTTP(method = "GET", path = ApiUlrConfig.Ulr.PRODUCT)
    Call<ResponseBody> getProducts();

    @HTTP(method = "GET", path = ApiUlrConfig.Ulr.ACCOUNTS)
    Call<ResponseBody> getMessages();

}
