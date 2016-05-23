package com.benutzer.washbayin.utilities;

/**
 * Created by amitesh on 19/2/16.
 */
public class UserModel {
    private String _email;
    private String _name;
    private String _password;
    private String _phone;
    private int _utype;

    public UserModel(){
        _email = "";
        _name = "";
        _password = "";
        _phone = "";
        _utype = 0;
    }

    public UserModel(String email, String name, String psswd, String phone, int utype){
        set_email(email);
        set_name(name);
        set_password(psswd);
        set_phone(phone);
        set_utype(utype);
    }

    public int get_utype() {
        return _utype;
    }

    public void set_utype(int _utype) {
        this._utype = _utype;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_email() {
        return _email;
    }

    public String get_name() {
        return _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
