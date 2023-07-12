package com.edu.phonestore.api;

import com.edu.phonestore.phone.PhoneService;

public class ApiClient extends ApiBase {
    public PhoneService phoneService() {
        return this.getService(PhoneService.class, ApiUlrConfig.BASE_URL);
    }
}
