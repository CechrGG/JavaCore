package acmr.springframework.ioc;

import acmr.springframework.ioc.entity.auth.Cat;
import acmr.springframework.ioc.entity.common.CatColor;
import acmr.springframework.ioc.service.CatService;
import acmr.springframework.util.SpringUtil;
import acmr.springframework.util.StringUtil;

import java.text.ParseException;
import java.util.Date;

public class SpringApp {

    public static void main(String[] args) {
        SpringUtil.getContext();
        CatService catSrv = SpringUtil.getBean("catSrv", CatService.class);
        Cat cat = SpringUtil.getBean("cat");
        cat.setName("虎妞");
        cat.setAge(1);
        cat.setColor(CatColor.white.ordinal());
        try {
            cat.setBirthday(StringUtil.strToDate("2019-04-28"));
            cat.setDeathday(StringUtil.strToDate("2099-12-30"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cat.setGmt_create(new Date());
        cat.setGmt_update(new Date());
        cat.setSex('F');
        cat.setWeight(5.6f);
        cat.setLength(24.6f);
        cat.setMemo("备注");

        System.out.println(catSrv.selfIntroduce(catSrv.register(cat)));
    }
}
