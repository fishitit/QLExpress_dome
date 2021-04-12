package com.ql.template;

import java.io.Serializable;

/**
 * 
 * @author wangmengguang
 *
 */
public class Parameter implements Serializable {
	private static final long serialVersionUID = 1L;

	private String type;
	private String name;
	private String range;
	/**
	 * 数据源条件 index是位置，sourceCode是资源对象的code
	 */
	private int index;
	/**
	 * sourceCode是资源对象的code
	 */
	private String sourceCode;
	/**
	 * ruleMapCode是替换条件参数
	 */
	private String ruleMapCode;
	/**
	 * 配置单个属性
	 */
	private String value;

	/**
	 * 配置列表某字段集合
	 */
	private String values;
	/**
	 * 数组分隔符
	 */
	private String grepCode;

	public Parameter() {
	};

	/**
	 * 单项构造方法
	 * 
	 * @param name
	 * @param type
	 * @param index
	 * @param sourceCode
	 * @param ruleMapCode
	 * @param value
	 * @param range
	 */
	public Parameter(String name, String type, int index, String sourceCode, String ruleMapCode, String value,
			String range) {
		super();
		this.type = type;
		this.name = name;
		this.range = range;
		this.index = index;
		this.sourceCode = sourceCode;
		this.ruleMapCode = ruleMapCode;
		this.value = value;
	}

	/**
	 * 多项构造方法
	 * 
	 * @param name
	 * @param type
	 * @param index
	 * @param sourceCode
	 * @param ruleMapCode
	 * @param values
	 * @param grepCode
	 * @param range
	 */
	public Parameter(String name, String type, int index, String sourceCode, String ruleMapCode, String values,
			String grepCode, String range) {
		super();
		this.type = type;
		this.name = name;
		this.range = range;
		this.index = index;
		this.sourceCode = sourceCode;
		this.ruleMapCode = ruleMapCode;
		this.values = values;
		this.grepCode = grepCode;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getGrepCode() {
		return grepCode;
	}

	public void setGrepCode(String grepCode) {
		this.grepCode = grepCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRuleMapCode() {
		return ruleMapCode;
	}

	public void setRuleMapCode(String ruleMapCode) {
		this.ruleMapCode = ruleMapCode;
	}

}
