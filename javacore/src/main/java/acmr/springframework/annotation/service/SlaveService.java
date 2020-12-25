package acmr.springframework.annotation.service;

import acmr.springframework.annotation.dao.SlaveDao;
import acmr.springframework.annotation.entity.Slave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("slaveSrv")
public class SlaveService implements ISlaveService {

    private final SlaveDao slaveDao;

    @Autowired
    public SlaveService(SlaveDao slaveDao) {
        this.slaveDao = slaveDao;
    }

    @Override
    public int registerSlave(Slave slave) {
        return slaveDao.registerSlave(slave);
    }

    @Override
    public Slave getSlaveById(long id) {
        return slaveDao.getSlaveById(id);
    }
}
