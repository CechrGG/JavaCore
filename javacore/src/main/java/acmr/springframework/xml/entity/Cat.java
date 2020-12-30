package acmr.springframework.xml.entity;

import java.util.Date;

public class Cat {
    private long id;
    private String name;
    private long owner_id;
    private char sex;
    private int breed;
    private int color;
    private float weight;
    private float length;
    private Date gmt_birthday;
    private Date gmt_deathday;
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

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getBreed() {
        return breed;
    }

    public void setBreed(int breed) {
        this.breed = breed;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public Date getGmt_birthday() {
        return gmt_birthday;
    }

    public void setGmt_birthday(Date gmt_birthday) {
        this.gmt_birthday = gmt_birthday;
    }

    public Date getGmt_deathday() {
        return gmt_deathday;
    }

    public void setGmt_deathday(Date gmt_deathday) {
        this.gmt_deathday = gmt_deathday;
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
