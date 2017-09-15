package org.sam.weblaf.swing;

import javax.swing.Action;
import javax.swing.Icon;

import com.alee.laf.radiobutton.WebRadioButton;

/**
 * 基于webradiobuton的控件
 * @author sam
 *
 */
public class JWRadioButton extends WebRadioButton {

	private static final long serialVersionUID = 4930332464719340160L;

	/**
	 * 绑定值
	 */
	private Object tag;

	/**
	 * 绑定值
	 * 
	 * @return
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * 绑定值
	 * 
	 * @param tag
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * Creates an initially unselected radio button with no set text.
	 */
	public JWRadioButton() {
		this(null, null, null, false);
	}
	
	/**
	 * 只有绑定值的控件，
	 * @param tag 绑定值
	 */
	public JWRadioButton(Object tag)
	{
		this(tag == null ? "" : tag.toString(), null, tag, false);
	}

	/**
	 * 带有图标，绑定值的构造函数
	 * @param icon 图标
	 * @param tag 绑定值
	 */
	public JWRadioButton(Icon icon, Object tag) {
		this(null, icon, tag, false);
	}

	/**
	 * Creates a radiobutton where properties are taken from the Action
	 * supplied.
	 *
	 * @since 1.3
	 */
	public JWRadioButton(Action a) {
		this();
		setAction(a);
	}

	/**
	 * 图标，绑定值，是否选中的构造函数
	 * 
	 * @param icon
	 *            图标
	 * @param tag
	 *            绑定值
	 * @param selected
	 *            是否选中
	 */
	public JWRadioButton(Icon icon, Object tag, boolean selected) {
		this(null, icon, tag, selected);
	}

	/**
	 * 带有显示值和绑定值的构造函数
	 * 
	 * @param text
	 *            显示值
	 * @param tag
	 *            绑定值
	 */
	public JWRadioButton(String text, Object tag) {
		this(text, null, tag, false);
	}

	/**
	 * 是否选中的构造函数
	 * 
	 * @param text
	 *            文本
	 * @param tag
	 *            绑定值
	 * @param selected
	 *            是否选中
	 */
	public JWRadioButton(String text, Object tag, boolean selected) {
		this(text, null, tag, selected);
	}

	/**
	 * 默认不选中的所有构造函数
	 * 
	 * @param text
	 *            文本
	 * @param icon
	 *            图标
	 * @param tag
	 *            绑定值
	 */
	public JWRadioButton(String text, Icon icon, Object tag) {
		this(text, icon, tag, false);
	}

	/**
	 * 带有全部参数的构造函数
	 * 
	 * @param text
	 *            显示文本
	 * @param icon
	 *            图标
	 * @param tag
	 *            绑定值
	 * @param selected
	 *            是否选中
	 */
	public JWRadioButton(String text, Icon icon, Object tag, boolean selected) {
		super(text, icon, selected);
		this.setTag(tag);
		setBorderPainted(false);
		setHorizontalAlignment(LEADING);
	}
}
