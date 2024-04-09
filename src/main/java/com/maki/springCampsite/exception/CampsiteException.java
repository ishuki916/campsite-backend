package com.maki.springCampsite.exception;

public class CampsiteException extends RuntimeException {
    public static final String ERROR_MSG = "錯誤";

    public static final String NAME_ERROR = "名稱必填";

    public static final String PRICE_ERROR = "價格必填且不可為負數";

    public static final String DISTRICT_ERROR = "區域錯誤";

    public static final String FACILITY_ERROR = "設施類別錯誤";

    public static final String ID_ERROR = "Id 錯誤";


    public CampsiteException() {
        super(ERROR_MSG);
    }

    public CampsiteException(String message) {
        super(message);
    }
}
