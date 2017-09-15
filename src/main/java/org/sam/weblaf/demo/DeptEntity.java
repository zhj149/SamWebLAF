package org.sam.weblaf.demo;

/**
 * 部门实体
 * @author sam
 *
 */
public class DeptEntity {

	/**
	 * 主键0
	 */
	private Integer id;
	
	/**
	 * 代码
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 主键
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 代码
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 *  代码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
