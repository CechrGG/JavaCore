package acmr.springframework.ioc.service;

import acmr.springframework.ioc.dao.CatDao;
import acmr.springframework.ioc.entity.auth.Cat;

public class CatService implements ICatService{

    private final CatDao catDao;

    public CatService(CatDao catDao) {
        this.catDao = catDao;
    }


    @Override
    public String selfIntroduce(long id) {
        return catDao.getCatById(id).getName();
    }

    @Override
    public int register(Cat cat) {
        return 0;
    }
}
