package teach;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class MytableModel1 extends DefaultTableModel{
	private ArrayList <Integer> editedIntex =new ArrayList<Integer>();
	public MytableModel1() {
		super();
	}
	public boolean isCellEditable(int row,int column) {
			return false;
	}//设置表格模型第一列不能修改
	public void setValueAt(Object aValue,int row,int column) {
		super.setValueAt(aValue, row, column);
		int i,count=editedIntex.size();
		if(count==0)
			editedIntex.add(row);
		else {
			for(i=0;i<count;i++) {
				if(editedIntex.get(i).intValue()>row) {
					editedIntex.add(i+1,row);
					break;
				}
			}
			if(i>=count)
				editedIntex.add(row);
		}
	}//表格模型数据修改后，记录其行号
	public ArrayList<Integer>getEditedIndex(){
		return editedIntex;
	}
	public void clearEditedIndex() {
		editedIntex.clear();
	}
}
