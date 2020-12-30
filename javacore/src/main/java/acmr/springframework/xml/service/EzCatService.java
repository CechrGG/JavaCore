package acmr.springframework.xml.service;

import acmr.springframework.util.StringUtil;
import acmr.springframework.xml.dao.CatDao;
import acmr.springframework.xml.entity.Cat;
import acmr.springframework.util.PageHelper;

import java.util.List;

public class EzCatService implements ICatService{

    private CatDao catDao;

//    public EzCatService(CatDao catDao) {
//        this.catDao = catDao;
//    }

    public CatDao getCatDao() {
        return catDao;
    }

    public void setCatDao(CatDao catDao) {
        this.catDao = catDao;
    }

    @Override
    public String selfIntroduce(long id) {
        Cat cat = catDao.getCatById(id);
        return "Hello, 大家好！我是" + cat.getName() + "，我今年" + StringUtil.getAge(cat.getGmt_birthday()) + "岁了！";
    }

    @Override
    public int register(Cat cat) {
        return catDao.insertCat(cat);
    }

    @Override
    public List<Cat> getCatList(int pagenum, int pagesize) {
        List<Cat> cats = catDao.getCatByPage(new PageHelper(pagenum, pagesize));
//        StringBuilder catList = new StringBuilder("id, name\n");
//        for(Cat cat : cats) {
//            catList.append(cat.getId() + ", " + cat.getName() + "\n");
//        }
        return cats;
    }
}
