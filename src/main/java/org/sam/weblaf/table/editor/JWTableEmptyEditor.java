package org.sam.weblaf.table.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

/**
 * 空编辑模式的editor对象
 * @author sam
 *
 */
public class JWTableEmptyEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4070221556048242744L;
	
	/**
	 * 当前的控件
	 */
	private JLabel label;
	
	/**
	 * 操作代理对象
	 */
	private EditorDelegate delegate;

	/**
	 * 无参数构造函数
	 */
	public JWTableEmptyEditor()
	{
		this("");
	}
	
	/**
	 * 有参数构造函数
	 * @param fieldName 字段名称
	 */
	public JWTableEmptyEditor(String fieldName)
	{
		super();
		label = new JLabel("");
		delegate = new EditorDelegate();
	}
	
	/**
	 * 设置值
	 * @param value
	 */
	public void setValue(Object value)
	{
		this.delegate.setValue(value);
		this.label.setText(value == null ? "" : value.toString());
	}

	@Override
	public Object getCellEditorValue() {
		return delegate.getCellEditorValue();
	}

	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {
		
		delegate.setValue(value);
		
		String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, false);
		this.label.setText(stringValue);
		
		return this.label;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		delegate.setValue(value);
		
		this.label.setText(value == null ? "" : value.toString());
		return this.label;
	}
	
	/**
	 * 
	 * @author sam
	 *
	 */
	protected class EditorDelegate extends JWEditorDelegateAdapter
	{
		private static final long serialVersionUID = 4366510118323198573L;
		
		public EditorDelegate()
		{
			super();
		}

		/**
		 * Stops editing and returns true to indicate that editing has stopped.
		 * This method calls <code>fireEditingStopped</code>.
		 *
		 * @return true
		 */
		public boolean stopCellEditing() {
			fireEditingStopped();
			return true;
		}

		/**
		 * Cancels editing. This method calls <code>fireEditingCanceled</code>.
		 */
		public void cancelCellEditing() {
			fireEditingCanceled();
		}

		/**
		 * When an action is performed, editing is ended.
		 * 
		 * @param e
		 *            the action event
		 * @see #stopCellEditing
		 */
		public void actionPerformed(ActionEvent e) {
			JWTableEmptyEditor.this.stopCellEditing();
		}

		/**
		 * When an item's state changes, editing is ended.
		 * 
		 * @param e
		 *            the action event
		 * @see #stopCellEditing
		 */
		public void itemStateChanged(ItemEvent e) {
			JWTableEmptyEditor.this.stopCellEditing();
		}
	}

}
