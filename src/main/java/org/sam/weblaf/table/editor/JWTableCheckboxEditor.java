package org.sam.weblaf.table.editor;

import javax.swing.DefaultCellEditor;

import com.alee.laf.checkbox.WebCheckBox;

/**
 * 带有多种值类型的checkbox工具
 * 
 * @author sam
 *
 */
public class JWTableCheckboxEditor extends DefaultCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 545401568684102849L;

	/**
	 * 不带参数的构造函数
	 */
	public JWTableCheckboxEditor() {
		this(new WebCheckBox());
	}

	/**
	 * 带参数的构造函数
	 * 
	 * @param checkBox
	 */
	public JWTableCheckboxEditor(WebCheckBox checkBox) {
		super(checkBox);
		checkBox.removeActionListener(this.delegate);
		delegate = new EditorDelegate() {
			private static final long serialVersionUID = 1L;

			@Override
			public void setValue(Object value) {
				
				super.setValue(value);
				boolean isSelected = false;

				if (value instanceof Integer) {
					isSelected = Integer.valueOf(1).equals(value);
				} else if (value instanceof Long) {
					isSelected = Long.valueOf(1L).equals(value);
				} else if (value instanceof Boolean) {
					isSelected = Boolean.TRUE.equals(value);
				} else if (value instanceof String) {
					isSelected = "true".equals(value);
				} else if (value instanceof Character) {
					isSelected = Character.valueOf('t').equals(value);
				}
				
				checkBox.setSelected(isSelected);
			}

			@Override
			public Object getCellEditorValue() {
				return Boolean.valueOf(checkBox.isSelected());
			}
		};

		checkBox.addActionListener(delegate);
		checkBox.setRequestFocusEnabled(false);
	}

}
