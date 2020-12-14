package acmr.springframework.ioc.service;

import acmr.springframework.ioc.entity.auth.Cat;

public interface ICatService {
    String selfIntroduce(long id);

    int register(Cat cat);
}
