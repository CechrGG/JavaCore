package acmr.springframework.xml;

import acmr.springframework.annotation.entity.Slave;
import acmr.springframework.annotation.service.SlaveService;
import acmr.springframework.util.SpringAtUtil;
import acmr.springframework.util.SpringXmlUtil;
import acmr.springframework.util.StringUtil;
import acmr.springframework.xml.entity.Cat;
import acmr.springframework.xml.entity.CatColor;
import acmr.springframework.xml.service.CatService;
import acmr.springframework.xml.service.EzCatService;

import java.text.ParseException;
import java.util.Date;

public class SpringApp {

    public static void main(String[] args) {
        CatService CatSrv = SpringXmlUtil.getBean("catSrv", CatService.class);
        Cat cat = SpringXmlUtil.getBean("cat");
        cat.setName("虎妞");
        cat.setOwner_id(1);
        cat.setColor(CatColor.dragonli.ordinal());
        try {
            cat.setGmt_birthday(StringUtil.strToDate("2019-04-28"));
            cat.setGmt_deathday(StringUtil.strToDate("2099-12-30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cat.setGmt_create(new Date());
        cat.setGmt_update(new Date());
        cat.setSex('M');
        cat.setWeight(5.6f);
        cat.setLength(24.6f);
        cat.setMemo("备注");

//        catSrv.register(cat);
//        System.out.println(catSrv.selfIntroduce(cat.getId()));
//        System.out.println(catSrv.getCatList(1,5));
        EzCatService ezCatSrv = SpringXmlUtil.getBean("ezCatSrv", EzCatService.class);
        ezCatSrv.register(cat);
        System.out.println(ezCatSrv.selfIntroduce(1));
        System.out.println(ezCatSrv.getCatList(2,5));
        Slave slave = new Slave();
        slave.setName("老陈");
        slave.setSex('F');
        slave.setMobile("1331133341");
        try {
            slave.setGmt_enslaved(StringUtil.strToDate("2019-04-28"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        slave.setMemo("铲屎官");
        slave.setGmt_create(new Date());
        slave.setGmt_update(new Date());
        SlaveService slaveSrv = SpringAtUtil.getBean("slaveSrv", SlaveService.class);
        slaveSrv.registerSlave(slave);
        System.out.println(slaveSrv.getSlaveById(slave.getId()).getName());
    }
}
