package org.sam.weblaf.table.renderer;

import com.alee.laf.table.renderers.WebTableCellRenderer;

/**
 * 表达式渲染器
 * 
 * @author sam
 *
 */
public class JWTableExpressionRenderer extends WebTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8993416282846463368L;

	/**
	 * 当前的el的表达式
	 */
	private Expression el;

	/**
	 * 当前的el表达式
	 * 
	 * @return
	 */
	public Expression getEl() {
		return el;
	}

	/**
	 * 当前的el表达式
	 * 
	 * @param el
	 */
	public void setEl(Expression el) {
		this.el = el;
	}

	/**
	 * 使用el表达式的renderer
	 * 
	 * @param el
	 */
	public JWTableExpressionRenderer(Expression el) {
		super();
		this.el = el;
	}

	/**
	 * 设置显示值
	 */
	@Override
	protected void setValue(Object value) {
		if (this.el != null && value != null) {
			setText(el.getText(value));
		} else {
			setText((value == null) ? "" : value.toString());
		}
	}

	/**
	 * 获取值
	 * 
	 * @author sam
	 *
	 */
	public interface Expression {
		public String getText(Object value);
	}
}
