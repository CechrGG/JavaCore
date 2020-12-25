package acmr.springframework.xml.dao;

import acmr.springframework.xml.entity.Cat;
import acmr.springframework.util.PageHelper;

import java.util.List;

public interface CatDao {
    /**
     * 喵喵注册
     * @param cat
     * @return
     */
    int insertCat(Cat cat);

    /**
     * 通过id查询主子
     * @param id
     * @return
     */
    Cat getCatById(long id);

    /**
     * 分页查询主子
     * @param pageHelper
     * @return
     */
    List<Cat> getCatByPage(PageHelper pageHelper);

    /**
     * 通过奴才id查询
     * @param slaveId
     * @return
     */
    List<Cat> getCatBySlaveId(long slaveId);
}
