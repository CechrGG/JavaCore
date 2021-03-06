package acmr.springframework.annotation.dao;

import acmr.springframework.annotation.entity.Slave;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

@Repository("slaveDao")
public interface SlaveDao {
    @Insert("insert into auth_slave(name, sex, mobile, gmt_birthday, gmt_enslaved, memo, gmt_create, gmt_update) values (" +
            "#{name}, #{sex}, #{mobile}, #{gmt_birthday}, #{gmt_enslaved}, #{memo}, #{gmt_create}, #{gmt_update})")
    @SelectKey(keyColumn = "id", keyProperty = "id", statement = "select last_insert_id()", resultType = Integer.class, before = false)
    int registerSlave(Slave slave);

    @Select("select * from auth_slave where id=#{id}")
    Slave getSlaveById(long id);

}
