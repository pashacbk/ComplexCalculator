package com.interntask.calculator;

import com.orm.SugarRecord;

/**
 * Created by manga on 03.12.2017.
 */

public class History extends SugarRecord {

    String userName;
    String  operator1;
    String operator2;
    String operation;
    String result;

    public History() {

    }

    public History(String userName, String operator1,
                   String operator2, String operation, String result) {
        this.userName = userName;
        this.operator1 = operator1;
        this.operator2 = operator2;
        this.operation = operation;
        this.result = result;
    }

}
