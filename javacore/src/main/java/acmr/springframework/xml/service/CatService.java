package acmr.springframework.xml.service;

import acmr.springframework.xml.entity.Cat;
import acmr.springframework.util.PageHelper;
import acmr.springframework.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CatService implements ICatService{

    private final SqlSessionFactory ssf = MybatisUtil.getSession();

    @Override
    public String selfIntroduce(long id) {
        SqlSession sqlSession = ssf.openSession();
        Cat cat = sqlSession.selectOne("getCatById", id);
        sqlSession.commit();
        sqlSession.close();
        return "Hello, 大家好！我是" + cat.getName() + "，我今年" + cat.getAge() + "岁了！";
    }

    @Override
    public int register(Cat cat) {
        SqlSession sqlSession = ssf.openSession();
        int id = sqlSession.insert("insertCat", cat);
        sqlSession.commit();
        sqlSession.close();
        return id;
    }

    @Override
    public String getCatList(int pagenum, int pagesize) {
        SqlSession sqlSession = ssf.openSession();
//        Map<String, Integer> params = new HashMap<>();
//        params.put("pagenum", pagenum);
//        params.put("pagesize", pagesize);
//        RowBounds bounds = new RowBounds(pagenum, pagesize);
        List<Cat> cats = sqlSession.selectList("getCatByPage", new PageHelper(pagenum, pagesize));
        sqlSession.commit();
        sqlSession.close();
        StringBuilder catList = new StringBuilder("id, name\n");
        for(Cat cat : cats) {
            catList.append(cat.getId() + ", " + cat.getName() + "\n");
        }
        return catList.toString();

    }
}
