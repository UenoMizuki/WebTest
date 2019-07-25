package com.example.demo;

import javax.validation.constraints.Size;

public class MessageForm {

    @Size(max=80)
    private String name,name2;

    @Size(min=1, max=140)
    private String text;

    public String getName() {
  
        return name;
    }
    public String getName2() {
    	  
        return name2;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setName2(String name) {
        this.name2 = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}