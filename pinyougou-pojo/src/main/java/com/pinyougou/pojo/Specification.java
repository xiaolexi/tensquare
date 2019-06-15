package com.pinyougou.pojo;

/**
 * Specification 实体类
 * @date 2019-04-28 17:06:03
 * @version 1.0
 */
public class Specification implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String specName;

	/** setter and getter method */
	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return this.id;
	}
	public void setSpecName(String specName){
		this.specName = specName;
	}
	public String getSpecName(){
		return this.specName;
	}

}