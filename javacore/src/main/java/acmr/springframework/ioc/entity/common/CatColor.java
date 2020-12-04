package acmr.springframework.ioc.entity.common;

public enum CatColor {
    white("白色"),
    orange("橘色"),
    black("黑色"),
    calico("三花"),
    dragonli("狸花");
    

    private final String color;
    CatColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }

    public String getColor(int i) {
        for(CatColor color : CatColor.values()) {
            if(color.ordinal() == i) {
                return color.getColor();
            }
        }
        return "";
    }
}
