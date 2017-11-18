package com.cornez.todotodayii;

class ToDo_Item {

    //MEMBER ATTRIBUTES
    private int _id;
    private String description;
    private int is_done;

    public ToDo_Item() {
    }

    public ToDo_Item(String desc, int done) {
        description = desc;
        is_done = done;
    }

    public int getId() {
        return _id;
    }
    public void setId(int id) {
        _id = id;
    }

    public String getDescription () {
        return description;
    }
    public void setDescription (String desc) {
        description = desc;
    }

    public int getIs_done() {
        return is_done;
    }

    public void setIs_done(int done) {
        is_done = done;
    }


}
