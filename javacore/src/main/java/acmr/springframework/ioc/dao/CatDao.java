package acmr.springframework.ioc.dao;

import acmr.springframework.ioc.entity.auth.Cat;

import java.util.List;

public interface CatDao {
    int insertCat(Cat cat);
    Cat getCatById(long id);
    List<Cat> getCatByPage();
}
