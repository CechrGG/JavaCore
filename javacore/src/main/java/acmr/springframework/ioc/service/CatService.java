package acmr.springframework.ioc.service;

import acmr.springframework.ioc.entity.auth.Cat;
import acmr.springframework.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class CatService implements ICatService{

    private final SqlSessionFactory ssf = MybatisUtil.getSession();

    @Override
    public String selfIntroduce(long id) {
        SqlSession sqlSession = ssf.openSession();
        Cat cat = sqlSession.selectOne("getCatById", id);
        sqlSession.commit();
        sqlSession.close();
        return cat.getName();
    }

    @Override
    public int register(Cat cat) {
        SqlSession sqlSession = ssf.openSession();
        int id = sqlSession.insert("insertCat", cat);
        sqlSession.commit();
        sqlSession.close();
        return id;
    }
}
