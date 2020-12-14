package acmr.springframework.ioc.dao;

import acmr.springframework.ioc.entity.auth.Cat;

public interface CatDao {
    int insertCat(Cat cat);
    Cat getCatById(long id);
}
