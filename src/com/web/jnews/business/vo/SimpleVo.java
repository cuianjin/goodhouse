package com.web.jnews.business.vo;

import com.web.webstart.base.util.NumberUtils;

/**
 * 用于前端展示数据，如下拉框
 * @author Zzc
 *
 */
public class SimpleVo {

	//数据的顺序  index
	private Long id;//0
	private String val;//1
	private String name;//2
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public SimpleVo(String name, String val) {
		super();
		this.name = name;
		this.val = val;
	}
	
	public SimpleVo() {
		super();
	}
	
	public static SimpleVo Object2Simple(Object obj){
		SimpleVo vo = new SimpleVo(); 
		try{
			if(obj instanceof Object[]){
				Object[] obj2 = (Object[])obj;
				vo.setId(NumberUtils.parseBigInteger2Long(obj2[0]));
				vo.setVal((String) obj2[1]);
				vo.setName((String) obj2[2]);
			}
		}catch(Exception e ){
			System.out.println(e.getMessage());
		}
		return vo;
	}
}
