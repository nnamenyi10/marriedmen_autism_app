package com.marriedmen.autismapp;

public class profileObj {

    //MEMBER ATTRIBUTES
    private int _id;
    private String name;
    private String information;


    public profileObj() {
    }

    public profileObj(String _name, String _information) {
        name = _name;
        information = _information;
    }

    public int getId() {
        return _id;
    }
    public void setId(int id) {
        _id = id;
    }

    public String getName () {
        return name;
    }
    public void setName (String _name) {
        name = _name;
    }

    public String getInfo () {
        return information;
    }
    public void setInfo (String _information) {
        information = _information;
    }

}
