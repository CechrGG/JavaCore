package acmr.springframework.ioc.service;

import acmr.springframework.ioc.dao.CatDao;
import acmr.springframework.ioc.entity.auth.Cat;
import acmr.springframework.ioc.entity.common.PageHelper;

import java.util.List;

public class EzCatService implements ICatService{

    private CatDao catDao;

    public EzCatService(CatDao catDao) {
        this.catDao = catDao;
    }

    @Override
    public String selfIntroduce(long id) {
        Cat cat = catDao.getCatById(id);
        return "Hello, 大家好！我是" + cat.getName() + "，我今年" + cat.getAge() + "岁了！";
    }

    @Override
    public int register(Cat cat) {
        return catDao.insertCat(cat);
    }

    @Override
    public String getCatList(int pagenum, int pagesize) {
        List<Cat> cats = catDao.getCatByPage(new PageHelper(pagenum, pagesize));
        StringBuilder catList = new StringBuilder("id, name\n");
        for(Cat cat : cats) {
            catList.append(cat.getId() + ", " + cat.getName() + "\n");
        }
        return catList.toString();
    }
}
