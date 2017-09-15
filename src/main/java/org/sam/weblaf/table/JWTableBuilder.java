package org.sam.weblaf.table;

/**
 * 表格生成器对象
 * @author sam
 *
 */
public interface JWTableBuilder<E extends Object> {
	
	/**
	 * 构建columnModel
	 * @return
	 * @throws Exception 抛出一切之异常
	 */
	public JWTableColumnModel buildTableColumnModel() throws Exception;
	
	/**
	 * 创建tablemodel的方法
	 * @return
	 * @throws Exception
	 */
	public JWTableModel<E> buildTableModel() throws Exception;

}
