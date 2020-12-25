package acmr.springframework.xml.service;

import acmr.springframework.xml.entity.Cat;

public interface ICatService {
    String selfIntroduce(long id);

    int register(Cat cat);

    String getCatList(int pagenum, int pagesize);
}
