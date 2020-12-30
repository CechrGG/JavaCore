package acmr.springframework.util.table;

import java.util.ArrayList;
import java.util.List;

public class QueryResult {
	
	
	private List<RowItem> data=new ArrayList<RowItem>(); //查询数据
	private int zongcount; //总条数
	private int thiscount; //当前条数
    private String ifglobal; //是否全局搜索 0:否 1:是
    private String ifsearch; //query（点击是操作） or find（简单查询） 0：query 1：find
    private RowItem tbheader; //查询结果表头对象
    
    
	public int getThiscount() {
		return thiscount;
	}
	public void setThiscount(int thiscount) {
		this.thiscount = thiscount;
	}
	public List<RowItem> getData() {
		return data;
	}
	public void setData(List<RowItem> data) {
		this.data = data;
	}
	public int getZongcount() {
		return zongcount;
	}
	public void setZongcount(int zongcount) {
		this.zongcount = zongcount;
	}
	public String getIfglobal() {
		return ifglobal;
	}
	public void setIfglobal(String ifglobal) {
		this.ifglobal = ifglobal;
	}
	public String getIfsearch() {
		return ifsearch;
	}
	public void setIfsearch(String ifsearch) {
		this.ifsearch = ifsearch;
	}
	public RowItem getTbheader() {
		return tbheader;
	}
	public void setTbheader(RowItem tbheader) {
		this.tbheader = tbheader;
	}
	
    
    
}
