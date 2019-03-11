package com.cc.zuimei.info;

/**
 * 最新发布图片的info
 */
public class ImageNewInfo {

    private String img_no;

    private int img_url;

    @Override
    public String toString() {
        return "ImageNewInfo{" +
                "img_no='" + img_no + '\'' +
                ", img_url=" + img_url +
                '}';
    }

    public String getImg_no() {
        return img_no;
    }

    public void setImg_no(String img_no) {
        this.img_no = img_no;
    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    public ImageNewInfo(String img_no, int img_url) {
        this.img_no = img_no;
        this.img_url = img_url;
    }
}
