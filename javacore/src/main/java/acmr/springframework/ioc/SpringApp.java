package acmr.springframework.ioc;

import acmr.springframework.ioc.entity.common.CatColor;

public class SpringApp {

    public static void main(String[] args) {
        for(CatColor color : CatColor.values()) {
            System.out.println(color.ordinal());
            System.out.println(color.getColor());
        }
    }
}
