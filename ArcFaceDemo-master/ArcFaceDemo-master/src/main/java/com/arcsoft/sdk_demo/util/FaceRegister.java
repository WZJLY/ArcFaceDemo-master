package com.arcsoft.sdk_demo.util;

public class FaceRegister {

    private String userName;
    private  int  boxId;



    public FaceRegister(String strUserName,int iBoxId){

        this.boxId = iBoxId;
        this.userName = strUserName;


    }

    public String getUserName() {
        return this.userName;
    }

    public int getBoxId() {
        return this.boxId;
    }
}
