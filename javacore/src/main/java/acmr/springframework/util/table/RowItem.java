package acmr.springframework.util.table;

import java.util.ArrayList;
import java.util.List;

public class RowItem {
	private String keycode;
	private String group;
	private String groupname;
	private String rowtype;//行数据类型
	private List<CellItem> col=new ArrayList<CellItem>();
	
	
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	

	public List<CellItem> getCol() {
		return col;
	}

	public void setCol(List<CellItem> col) {
		this.col = col;
	}

	public String getKeycode() {
		return keycode;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	public String getRowtype() {
		return rowtype;
	}

	public void setRowtype(String rowtype) {
		this.rowtype = rowtype;
	}
	
	

}
