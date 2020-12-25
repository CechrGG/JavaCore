package acmr.springframework.annotation.service;

import acmr.springframework.annotation.entity.Slave;

public interface ISlaveService {
    int registerSlave(Slave slave);

    Slave getSlaveById(long id);
}
