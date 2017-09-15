package org.sam.weblaf.table.renderer;

import java.awt.Component;

import javax.swing.JTable;

import com.alee.laf.table.renderers.WebTableCellRenderer;

/**
 * 行号渲染器
 * @author sam
 *
 */
public class JWTableRowNumberRenderer extends WebTableCellRenderer {
	
	/**
	 * 行号渲染器
	 * 默认居中
	 */
	public JWTableRowNumberRenderer(){
		super();
		this.setHorizontalAlignment(JWTableRowNumberRenderer.CENTER);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7120562179856374728L;
	
	/**
	 * 直接返回行号渲染
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		this.setText(String.valueOf(row + 1));
		return this;
	}

}
