package acmr.springframework.util.table;

public class CellItem {
	
	private String colCode;
	private String colName;
	private String cellValue;
	private Object cellObject;
	private int width;
	private String ifshow;
	private String ifsys;
	private String name;
	private String ifgroup;
	private String iferror;
	
	
	public String getIferror() {
		return iferror;
	}
	public void setIferror(String iferror) {
		this.iferror = iferror;
	}
	public String getIfgroup() {
		return ifgroup;
	}
	public void setIfgroup(String ifgroup) {
		this.ifgroup = ifgroup;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CellItem() {
		super();
	}
	public CellItem(String cellValue) {
		super();
		this.cellValue = cellValue;
	}
	public String getColCode() {
		return colCode;
	}
	public void setColCode(String colCode) {
		this.colCode = colCode;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getCellValue() {
		return cellValue;
	}
	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getIfshow() {
		return ifshow;
	}
	public void setIfshow(String ifshow) {
		this.ifshow = ifshow;
	}
	public String getIfsys() {
		return ifsys;
	}
	public void setIfsys(String ifsys) {
		this.ifsys = ifsys;
	}
	public Object getCellObject() {
		return cellObject;
	}
	public void setCellObject(Object cellObject) {
		this.cellObject = cellObject;
	}
	public CellItem(String colCode, String colName, String cellValue,
                    int width, String ifshow, String ifsys) {
		super();
		this.colCode = colCode;
		this.colName = colName;
		this.cellValue = cellValue;
		this.width = width;
		this.ifshow = ifshow;
		this.ifsys = ifsys;
	}
	
	
	

}
