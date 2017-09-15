package org.sam.weblaf.table.defaultImpl;

import java.util.List;

import org.sam.weblaf.table.JWTableBuilder;
import org.sam.weblaf.table.JWTableColumn;
import org.sam.weblaf.table.JWTableColumnModel;
import org.sam.weblaf.table.JWTableModel;

/**
 * 默认的tablemodel和columnmodel实现
 * @author sam
 *
 * @param <E>
 */
public class JWTableDefaultBuilderImpl<E> implements JWTableBuilder<List<E>> {

	/**
	 * 当前的column列表
	 */
	private JWTableColumn[] columns;
	
	private Class<E> cls;
	
	/**
	 * 当前操作的column列表
	 * @return
	 */
	public JWTableColumn[] getColumns() {
		return columns;
	}

	/**
	 * 当前操作的column列表
	 * @param columns
	 */
	public void setColumns(JWTableColumn[] columns) {
		this.columns = columns;
		
	}

	/**
	 * 带有列信息的构造函数
	 * @param cols
	 */
	public JWTableDefaultBuilderImpl(Class<E> cls,JWTableColumn... cols)
	{
		this.columns = cols;
		this.cls = cls;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JWTableColumnModel buildTableColumnModel() throws Exception {
		JWTableColumnModel colModel = new JWTableColumnModel();

		if (this.columns == null || this.columns.length <= 0)
			throw new Exception("no columns");

		int i = 0;
		for (JWTableColumn col : this.columns) {
			if (col.getModelIndex() < 0)
			{
				col.setModelIndex(i);
			}
			colModel.addColumn(col);
			i++;
		}

		return colModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JWTableModel<List<E>> buildTableModel() throws Exception {
		JWTableDefaultModel<E> tabModel = new JWTableDefaultModel<>(cls);

		if (this.columns == null || this.columns.length <= 0)
			throw new Exception("no columns");

		for (JWTableColumn col : this.columns) {
			tabModel.addColumn(col);
		}

		// 生成原始数据的列
		if (tabModel.findColumn(JWTableColumn.COLUMN_ORIGINAL) < 0) {
			JWTableColumn column = new JWTableColumn();
			column.setTitle(JWTableColumn.COLUMN_ORIGINAL);
			column.setHeaderValue(JWTableColumn.COLUMN_ORIGINAL);
			tabModel.addColumn(column);
		}

		return tabModel;
	}

}
