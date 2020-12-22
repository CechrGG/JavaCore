package acmr.springframework.ioc;

import acmr.springframework.ioc.entity.auth.Cat;
import acmr.springframework.ioc.entity.common.CatColor;
import acmr.springframework.ioc.service.CatService;
import acmr.springframework.ioc.service.EzCatService;
import acmr.springframework.util.SpringUtil;
import acmr.springframework.util.StringUtil;

import java.text.ParseException;
import java.util.Date;

public class SpringApp {

    public static void main(String[] args) {
        SpringUtil.getContext();
        CatService CatSrv = SpringUtil.getBean("catSrv", CatService.class);
        Cat cat = SpringUtil.getBean("cat");
        cat.setName("端午");
        cat.setOwner_id(1);
        cat.setAge(1);
        cat.setColor(CatColor.dragonli.ordinal());
        try {
            cat.setBirthday(StringUtil.strToDate("2019-04-28"));
            cat.setDeathday(StringUtil.strToDate("2099-12-30"));
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
        EzCatService ezCatSrv = SpringUtil.getBean("ezCatSrv", EzCatService.class);

        System.out.println(ezCatSrv.selfIntroduce(1));
        System.out.println(ezCatSrv.getCatList(2,5));
    }
}
