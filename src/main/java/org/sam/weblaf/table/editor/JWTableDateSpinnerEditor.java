package org.sam.weblaf.table.editor;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

import org.sam.weblaf.swing.JWDateSpinner;

/**
 * 日期滚动框
 * 
 * @author sam
 *
 */
public class JWTableDateSpinnerEditor extends AbstractCellEditor implements TreeCellEditor, TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2254897074135560584L;

	/**
	 * 当前的掩码格式控件
	 */
	private JWDateSpinner editor;

	/**
	 * 当前的掩码格式控件
	 * 
	 * @return
	 */
	public JWDateSpinner getEditor() {
		return editor;
	}

	/**
	 * 当前的掩码格式控件
	 * 
	 * @param editor
	 */
	public void setEditor(JWDateSpinner editor) {
		this.editor = editor;
	}
	
	/**
	 * 几次鼠标操作开始编辑
	 */
	private int clickCountToStart = 2;
	
	/**
	 * 几次鼠标操作开始编辑
	 * @return
	 */
	public int getClickCountToStart() {
		return clickCountToStart;
	}

	/**
	 * 几次鼠标操作开始编辑
	 * @param clickCountToStart
	 */
	public void setClickCountToStart(int clickCountToStart) {
		this.clickCountToStart = clickCountToStart;
	}

	/**
	 * 不带掩码格式的构造函数
	 */
	public JWTableDateSpinnerEditor() {
		super();
		editor = new JWDateSpinner();
	}

	/**
	 * 带有掩码格式的构造函数
	 * 
	 * @param format
	 */
	public JWTableDateSpinnerEditor(String format) {
		super();
		editor = new JWDateSpinner(format);
	}

	/**
	 * 带有控件的构造函数
	 * 
	 * @param editor
	 */
	public JWTableDateSpinnerEditor(JWDateSpinner editor) {
		super();
		this.setEditor(editor);
	}

	@Override
	public Object getCellEditorValue() {
		try {

			if (JWDateSpinner.isNull((Date) editor.getValue()))
				return null;
			else
				return editor.getValue();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.editor.setValue(value);
		return this.editor;
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {

		this.editor.setValue(value);
		return this.editor;
	}

	/**
	 * 什么情况下开始允许编辑
	 */
	@Override
	public boolean isCellEditable(EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
            return ((MouseEvent)anEvent).getClickCount() >= clickCountToStart;
        }
        return true;
    }
}
