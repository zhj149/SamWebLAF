package org.sam.weblaf.table;

import java.text.Format;

import javax.swing.table.TableColumn;

/**
 * 扩展的列属性对象
 * 
 * @author sam
 *
 */
public class JWTableColumn extends TableColumn {

	/**
	 * 原始数据字段
	 */
	public static final String COLUMN_ORIGINAL = "col_original";

	/**
	 * 
	 */
	private static final long serialVersionUID = 7093063365050161392L;

	/**
	 * 自动生成新行的时候的默认值
	 */
	private Object defaultValue;

	/**
	 * 在JSTableColumn对象上扩展一个属性canBeNull；
	 * 当一些特殊类型，用户设置为true时，比如掩码格式的Date类型的时候，用户清空输入，或者设置为0000-00-00
	 * 00:00:00这样的格式后，在后台转换为null； false的时候，提示用户数据的格式不正确，默认样式为单元格边框反红，不允许接受输入。
	 */
	private Boolean canBeNull = false;

	/**
	 * 显示的值表达式
	 */
	private Format formator;
	
	/**
	 * 当前列是否可编辑
	 */
	private boolean editable = true;
	
	/**
	 * 标题名称
	 */
	private String title = "";

	/**
	 * 显示的值表达式
	 * @return
	 */
	public Format getFormator() {
		return formator;
	}

	/**
	 * 显示的值表达式
	 * @param formator
	 */
	public void setFormator(Format formator) {
		this.formator = formator;
	}

	/**
	 * 自动生成新行的时候的默认值
	 * 
	 * @return
	 */
	public Object getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 自动生成新行的时候的默认值
	 * 
	 * @param defaultValue
	 */
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * 在JSTableColumn对象上扩展一个属性canBeNull；
	 * 当一些特殊类型，用户设置为true时，比如掩码格式的Date类型的时候，用户清空输入，或者设置为0000-00-00
	 * 00:00:00这样的格式后，在后台转换为null； false的时候，提示用户数据的格式不正确，默认样式为单元格边框反红，不允许接受输入。
	 */
	public Boolean getCanBeNull() {
		return canBeNull;
	}

	/**
	 * 在JSTableColumn对象上扩展一个属性canBeNull；
	 * 当一些特殊类型，用户设置为true时，比如掩码格式的Date类型的时候，用户清空输入，或者设置为0000-00-00
	 * 00:00:00这样的格式后，在后台转换为null； false的时候，提示用户数据的格式不正确，默认样式为单元格边框反红，不允许接受输入。
	 */
	public void setCanBeNull(Boolean canBeNull) {
		this.canBeNull = canBeNull;
	}
	
	/**
	 * 该列是否可编辑
	 * @return
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * 该列是否可编辑
	 * @param editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	/**
	 * 表头标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 表头标题
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.getIdentifier() == null ? super.toString() : this.getIdentifier().toString();
	}
}
