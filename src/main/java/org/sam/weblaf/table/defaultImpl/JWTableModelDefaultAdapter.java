package org.sam.weblaf.table.defaultImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.sam.weblaf.table.JWTableColumn;
import org.sam.weblaf.table.JWTableModel;
import org.sam.weblaf.table.JWTableModelEvent;
import org.sam.weblaf.table.JWTableModelLinster;

/**
 * 默认的系统实现
 * @author sam
 *
 * @param <E>
 */
public class JWTableModelDefaultAdapter<E> implements JWTableModelLinster<List<E>> {
	
	/**
	 * 当前的tablemodel对象
	 */
	private JWTableModel<List<E>> tableModel;
	
	/**
	 * 当前的tablemodel
	 * @return
	 */
	public JWTableModel<List<E>> getTableModel() {
		return tableModel;
	}

	/**
	 * 当前的操作tabelmodel对象
	 * @param tableModel
	 */
	public void setTableModel(JWTableModel<List<E>> tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * 带有构造函数的tableModel
	 * @param tableModel
	 */
	public JWTableModelDefaultAdapter(JWTableModel<List<E>> tableModel)
	{
		super();
		this.setTableModel(tableModel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforRetrieve(JWTableModelEvent event) throws Exception {
		tableModel.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<E> onRetrieve() throws Exception {
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterRetrieve(JWTableModelEvent event) throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeUpdate(JWTableModelEvent event) throws Exception {
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(JWTableModelEvent event) throws Exception {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atfterUpdate(JWTableModelEvent event) throws Exception {
		tableModel.resetUpdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforDelete(JWTableModelEvent event) throws Exception {
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterDelete(JWTableModelEvent event) throws Exception {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeAppend(JWTableModelEvent event) throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void aftterAppend(JWTableModelEvent event) throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeInsert(JWTableModelEvent event) throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void aftterInsert(JWTableModelEvent event) throws Exception {
	}
	
	/**
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	public Object getDataTranstor(JWTableColumn col, Object value, Class<?> targetCls) throws ParseException {
		if (value == null)
			return null;

		if (targetCls == null)
			return value;

		if (targetCls.equals(Integer.class)) {
			if (value.toString().trim().length() <= 0) {
				return col.getCanBeNull() ? null : 0;
			} else if (value.equals("true") || value.equals("false")) {
				return value.equals("true") ? 1 : 0;
			} else if (value instanceof Boolean) {
				return value.equals(true) ? 1 : 0;
			} else {
				return Integer.parseInt(value.toString());
			}
		} else if (targetCls.equals(String.class)) {
			return value.toString();
		} else if (targetCls.equals(Double.class)) {
			if (value.toString().trim().length() <= 0) {
				return col.getCanBeNull() ? null : 0d;
			} else {
				return Double.parseDouble(value.toString());
			}
		} else if (targetCls.equals(Float.class)) {
			if (value.toString().trim().length() <= 0) {
				return col.getCanBeNull() ? null : 0f;
			} else {
				return Float.parseFloat(value.toString());
			}
		} else if (targetCls.equals(Long.class)) {
			if (value.toString().trim().length() <= 0) {
				return col.getCanBeNull() ? null : 0l;
			} else {
				if (value.toString().equals("true"))
					return 1L;
				else if (value.toString().equals("false"))
					return 0L;
				else 
					return Long.parseLong(value.toString());
			}
		} else if (targetCls.equals(Character.class)) {
			if (value.toString().trim().length() <= 0) {
				return col.getCanBeNull() ? null : ' ';
			} else {
				return value.toString().toCharArray()[0];
			}			
		} else if (targetCls.equals(Boolean.class)) {
			if (value.toString().trim().length() <= 0) {
				return false;
			} else {
				return Boolean.parseBoolean(value.toString());
			}	
		} else if (targetCls.equals(Short.class)) {
			if (value.toString().trim().length() <= 0) {
				return col.getCanBeNull() ? null : (short)0;
			} else {
				return Short.parseShort(value.toString());
			}	
		} else if (targetCls.equals(Date.class)) {
			if (value instanceof Date) {
				return value;
			} else if (value.toString().trim().equals("") || value.equals("0000-00-00") || value.equals("0000/00/00")
					|| value.equals("0000-00-00 00:00:00") || value.equals("0000-00-00-00-00-00")
					|| value.equals("0000/00/00 00:00:00")) {
				return null;
			} else {
				return col.getFormator().parseObject(value.toString());
			}
		} else if (targetCls.equals(BigDecimal.class)) {
			return new BigDecimal(value.toString());
		} else {
			return value;
		}
	}

}
