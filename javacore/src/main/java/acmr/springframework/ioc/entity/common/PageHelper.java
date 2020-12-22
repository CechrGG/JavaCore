package acmr.springframework.ioc.entity.common;

public class PageHelper {
    private int offset;
    private int limit;

    public PageHelper(){}

    public PageHelper(int pagenum, int pagesize) {
        this.offset = (pagenum - 1) * pagesize;
        this.limit = pagesize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
