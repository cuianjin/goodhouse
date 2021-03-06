package com.web.webstart.base.excelUtil;

import java.util.List;
import java.util.Map;

/**
 * excel头信息
 * @createTime: 2014-8-19 下午12:03:49
 * @author: zhanglin
 * @version: 0.1
 */
public class ExcelHead {
	
	/**
     * 列信息
     */
    private List<ExcelColumn> columns;
     
    /**
     * 需要转换的列
     */
    @SuppressWarnings("rawtypes")
	private Map<String, Map> columnsConvertMap;
     
    /**
     * 头部所占用的行数
     */
    private int rowCount;
     
    /**
     * 头部所占用的列数
     */
    private int columnCount;
 
    public List<ExcelColumn> getColumns() {
        return columns;
    }
 
    public int getRowCount() {
        return rowCount;
    }
 
    public int getColumnCount() {
        return columnCount;
    }
 
    public void setColumns(List<ExcelColumn> columns) {
        this.columns = columns;
    }
    
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
 
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
 
    @SuppressWarnings("rawtypes")
	public Map<String, Map> getColumnsConvertMap() {
        return columnsConvertMap;
    }
 
    @SuppressWarnings("rawtypes")
	public void setColumnsConvertMap(Map<String, Map> columnsConvertMap) {
        this.columnsConvertMap = columnsConvertMap;
    }
 
    @Override
    public String toString() {
        return "ExcelHead [columnCount=" + columnCount + ", columns=" + columns
                + ", columnsConvertMap=" + columnsConvertMap + ", rowCount="
                + rowCount + "]";
    }

}
