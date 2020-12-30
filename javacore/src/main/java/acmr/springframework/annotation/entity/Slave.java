package acmr.springframework.annotation.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("slave")
public class Slave {
    private long id;
    private String name;
    private char sex;
    private String mobile;
    private Date gmt_birthday;
    private Date gmt_enslaved;
    private String memo;
    private Date gmt_create;
    private Date gmt_update;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getGmt_birthday() {
        return gmt_birthday;
    }

    public void setGmt_birthday(Date gmt_birthday) {
        this.gmt_birthday = gmt_birthday;
    }

    public Date getGmt_enslaved() {
        return gmt_enslaved;
    }

    public void setGmt_enslaved(Date gmt_enslaved) {
        this.gmt_enslaved = gmt_enslaved;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_update() {
        return gmt_update;
    }

    public void setGmt_update(Date gmt_update) {
        this.gmt_update = gmt_update;
    }
}
