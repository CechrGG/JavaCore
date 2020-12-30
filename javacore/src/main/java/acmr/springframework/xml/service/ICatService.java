package acmr.springframework.xml.service;

import acmr.springframework.xml.entity.Cat;

import java.util.List;

public interface ICatService {
    String selfIntroduce(long id);

    int register(Cat cat);

    List<Cat> getCatList(int pagenum, int pagesize);
}
