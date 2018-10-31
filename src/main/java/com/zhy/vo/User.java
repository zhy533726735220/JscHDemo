package com.zhy.vo;

public class User implements IBean {
    private String cardId;
    private String username;
    private String mobile;
    private String img;
    private String field;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "User{" +
                "cardId='" + cardId + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", img='" + img + '\'' +
                ", field='" + field + '\'' +
                '}';
    }

    public String getKeyWord() {
        return cardId;
    }
}
