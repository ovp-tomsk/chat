package com.cb.web.mod.service;


public class TemplParamDbCheck
{
        private String  textH1 = "";
        private String  styleH1 = "";
        private boolean tblsExists = false;

    public TemplParamDbCheck (String textH1, String styleH1, boolean tblsExists)
    {
        this.textH1 = textH1;
        this.styleH1 = styleH1;
        this.tblsExists = tblsExists;
    }
    //********************************************************************************
    public String getTextH1() {
        return textH1;
    }
    public void setTextH1(String textH1) {
        this.textH1 = textH1;
    }
    public String getStyleH1() {
        return styleH1;
    }
    public void setStyleH1(String styleH1) {
        this.styleH1 = styleH1;
    }
    public boolean isTblsExists() {
        return tblsExists;
    }
    public void setTblsExists(boolean tblsExists) {
        this.tblsExists = tblsExists;
    }
}
