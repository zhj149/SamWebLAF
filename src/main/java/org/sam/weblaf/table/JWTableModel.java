package org.sam.weblaf.table;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.table.DefaultTableModel;

/**
 * 扩展的TableModule对象 最终生成类的父类 采用模版模式
 * 
 * @author sam
 *
 */
public abstract class JWTableModel<T> extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5129544725258528221L;
	
	/**
	 * 无数据行的构造函数
	 */
	public JWTableModel()
	{
		super();
	}

	/**
	 * 带参数的构造函数
	 * 
	 * @param cols
	 *            当前的列集合
	 */
	public JWTableModel(JWTableColumn[] cols) {
		super(0,cols.length);
		this.setColumnIdentifiers(cols);
	}

	/**
	 * 原始的数据集合
	 * 
	 * @return
	 */
	public abstract T getOrginal();

	/**
	 * 原始的数据集合
	 * 
	 * @param orginal
	 */
	public abstract void setOrginal(T orginal);

	/**
	 * 已删除的数据集合
	 * 
	 * @return
	 */
	public abstract T getDeletes();

	/**
	 * 已删除的数据集合
	 * 
	 * @param deletes
	 */
	public abstract void setDeletes(T deletes);

	/**
	 * 新增数据的集合
	 * 
	 * @return
	 */
	public abstract T getCreates();

	/**
	 * 新增数据的集合
	 * 
	 * @param creates
	 */
	public abstract void setCreates(T creates);

	/**
	 * 更新的数据集合
	 * 
	 * @return
	 */
	public abstract T getModified();

	/**
	 * 更新的数据集合
	 * 
	 * @param modified
	 */
	public abstract void setModified(T modified);

	/**
	 * 清空所有缓冲区
	 */
	public abstract void resetUpdate();
	
	/**
	 * 初始化数据的操作
	 * @return 0无数据 -1出错 成功返回行数
	 * @exception Exception
	 */
	public abstract int onRetrieve() throws Exception;
	
	/**
	 * 删除时候执行的操作
	 * @return
	 * @exception Exception
	 */
	public abstract boolean onDelete(int moduleRow) throws Exception;
	
	/**
	 * 插入的时候执行的操作
	 * @param moduleRow
	 * @return
	 * @exception Exception
	 */
	public abstract boolean onInsert(int moduleRow) throws Exception;
	
	/**
	 * 当执行追加的时候执行的操作
	 * @return
	 * @exception Exception
	 */
	public abstract boolean onAppend() throws Exception;
	
	/**
	 * 获取列列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JWTableColumn[] getTableColumns()
	{
		return (JWTableColumn[])this.columnIdentifiers.toArray(new JWTableColumn[this.getColumnCount()]);
	}

	/**
	 * 快速检索模式，默认不开启 主要是retrieve的时候，不切换数据库
	 */
	private boolean quickRetrieveModel = false;

	/**
	 * 快速检索模式，默认不开启 主要是retrieve的时候，不切换数据库
	 * 
	 * @return
	 */
	public boolean isQuickRetrieveModel() {
		return quickRetrieveModel;
	}

	/**
	 * 快速检索模式，默认不开启 主要是retrieve的时候，不切换数据库
	 * 
	 * @param quickRetrieveModel
	 */
	public void setQuickRetrieveModel(boolean quickRetrieveModel) {
		this.quickRetrieveModel = quickRetrieveModel;
	}

	/**
	 * 是否编辑状态
	 */
	private Boolean editable = true;

	/**
	 * 是否编辑状态
	 * 
	 * @return
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * 是否编辑状态
	 * 
	 * @param editable
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	/**
	 * 是否在retrieve的时候加入空白行
	 */
	private boolean retrieveWithEmptyRow = false;

	/**
	 * 是否在retrieve的时候加入空白行
	 * 
	 * @return
	 */
	public boolean isRetrieveWithEmptyRow() {
		return retrieveWithEmptyRow;
	}

	/**
	 * 是否在retrieve的时候加入空白行
	 * 
	 * @param retrieveWithEmptyRow
	 */
	public void setRetrieveWithEmptyRow(boolean retrieveWithEmptyRow) {
		this.retrieveWithEmptyRow = retrieveWithEmptyRow;
	}

	/**
	 * 保护起来的单元格 存储格式为，行，列
	 */
	private Map<Integer, Set<Integer>> protectCell = new LinkedHashMap<Integer, Set<Integer>>();

	/**
	 * 保护起来的单元格
	 * 
	 * @return
	 */
	public Map<Integer, Set<Integer>> getProtectCell() {
		return protectCell;
	}

	/**
	 * 保护起来的单元格
	 * 
	 * @param protectCell
	 */
	public void setProtectCell(Map<Integer, Set<Integer>> protectCell) {
		this.protectCell = protectCell;
	}

	/**
	 * 注入的tablemodel对象
	 */
	private JWTableModelLinster<T> tableModelLinster;

	/**
	 * 注入的tablemodel对象
	 * 
	 * @return
	 */
	public JWTableModelLinster<T> getTableModelLinster() {
		return tableModelLinster;
	}

	/**
	 * 注入的tablemodel对象
	 * 
	 * @param tableModelLinster
	 */
	public void setTableModelLinster(JWTableModelLinster<T> tableModelLinster) {
		this.tableModelLinster = tableModelLinster;
	}

	/**
	 * 重写的返回字段是否可编辑的功能
	 */
	@Override
	public boolean isCellEditable(int row, int column) {

		if (!this.getEditable())
			return false;

		if (protectCell != null && protectCell.containsKey(row)) {
			if (protectCell.get(row).contains(column))
				return false;
		}

		if (this.columnIdentifiers != null && this.columnIdentifiers.size() > column) {
			if (this.columnIdentifiers.get(column) instanceof JWTableColumn)
				return ((JWTableColumn) this.columnIdentifiers.get(column)).isEditable();
		}

		return true;
	}

	/**
	 * 查询是否有需要更新的操作
	 * 
	 * @return
	 */
	public abstract boolean hasChange();

	/**
	 * 交换2行数据，无保存状态交互数据
	 * 
	 * @param srcRow
	 *            原始数据行
	 * @param tarRow
	 *            目标数据行
	 * @throws Exception
	 */
	public abstract void moveRow(int srcRow, int tarRow) throws Exception;

	/**
	 * 收集当前变更后的数据集合
	 * 
	 * @throws Exception
	 */
	public abstract T getDatas() throws Exception;

	/**
	 * 重建索引列
	 * 
	 * @param colIndex
	 *            列索引
	 * @param begin
	 *            开始值，比如1，0作为索引开始值
	 * @param seed
	 *            增速
	 * @throws Exception
	 */
	public abstract void reBuildIndex(int colIndex, int begin, int seed) throws Exception;

	/**
	 * 返回当前选中行的数据
	 * 
	 * @param modelRow
	 *            模式行
	 * @return 返回当前行的数据
	 * @throws Exception
	 */
	public abstract Object getData(int modelRow) throws Exception;

	/**
	 * 新生成一个数据,但不插入集合
	 * 
	 * @return 新生成一个数据集合
	 * @throws Exception
	 */
	public abstract Object[] createNew() throws Exception;

	/**
	 * 清空所有当前显示数据 并且清空所有缓冲区
	 * 
	 * @throws Exception
	 */
	public abstract void clear() throws Exception;

	/**
	 * 更新操作
	 * 
	 * @return 更新操作
	 * @throws Exception
	 *             出错抛出异常
	 */
	public boolean update() throws Exception {

		JWTableModelEvent event = new JWTableModelEvent(this);

		this.getTableModelLinster().beforeUpdate(event);
		if (event.isCancel() || !event.getResult())
			return false;

		if (!this.getTableModelLinster().update(event))
			return false;

		event.setCancel(false);
		event.setResult(true);
		this.getTableModelLinster().atfterUpdate(event);

		return true;
	}

	/**
	 * 加载数据的操作
	 * 
	 * @return 负数出错 其它为返回的加载数据行数
	 * @throws Exception
	 *             抛出一切之错误
	 */
	public int retrieve() throws Exception {

		JWTableModelEvent event = new JWTableModelEvent(this);

		this.getTableModelLinster().beforRetrieve(event);
		if (event.isCancel() || !event.getResult())
			return -1;

		int iResult = onRetrieve();

		event.setCancel(false);
		event.setRow(iResult);
		event.setResult(iResult >= 0);
		this.getTableModelLinster().afterRetrieve(event);

		return iResult;
	}

	/**
	 * 删除操作
	 * 
	 * @return true 成功 false失败
	 * @param modelRow
	 *            要删除的table model数据行
	 * @throws Exception
	 *             抛出异常
	 */
	public boolean delete(int modelRow) throws Exception {

		JWTableModelEvent event = new JWTableModelEvent(this);
		event.setRow(modelRow);

		this.getTableModelLinster().beforDelete(event);
		if (event.isCancel() || !event.getResult())
			return false;

		if (!this.onDelete(modelRow))
			return false;

		event.setCancel(false);
		event.setResult(true);
		this.getTableModelLinster().afterDelete(event);

		return true;
	}

	/**
	 * 插入一行数据的操作
	 * 
	 * @return true插入成功 false插入失败
	 * @throws Exception
	 *             抛出一切可以抛出之异常
	 */
	public boolean append() throws Exception {

		JWTableModelEvent event = new JWTableModelEvent(this);

		this.getTableModelLinster().beforeAppend(event);
		if (event.isCancel() || !event.getResult())
			return false;

		if (!this.onAppend())
			return false;

		event.setCancel(false);
		event.setResult(true);
		event.setRow(this.getRowCount() - 1);
		this.getTableModelLinster().aftterAppend(event);

		return true;
		
	}

	/**
	 * 插入一行数据
	 * 
	 * @param index插入行的索引位置
	 * @return
	 * @throws Exception
	 */
	public boolean insert(int modelRow) throws Exception {
		
		JWTableModelEvent event = new JWTableModelEvent(this);
		event.setRow(modelRow);

		this.getTableModelLinster().beforeInsert(event);
		if (event.isCancel() || !event.getResult())
			return false;

		if (!this.onInsert(modelRow))
			return false;

		event.setCancel(false);
		event.setResult(true);
		this.getTableModelLinster().aftterInsert(event);

		return true;
	}
}
