package org.sam.weblaf.table.renderer;

import java.lang.reflect.Field;

import com.alee.laf.table.renderers.WebTableCellRenderer;

/**
 * 使用实体字段方式显示的渲染器
 * 
 * @author sam
 *
 */
public class JWTableEntityFieldRenderer extends WebTableCellRenderer {

	private static final long serialVersionUID = -959451058213640129L;

	/**
	 * 实体对象名称
	 */
	private String fieldName = "";

	/**
	 * 实体对象名称
	 * 
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * 实体对象名称
	 * 
	 * @param fieldName
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * 使用实体字段方式显示的渲染器
	 * 
	 * @param fieldName
	 */
	public JWTableEntityFieldRenderer(String fieldName) {
		super();
		this.setFieldName(fieldName);
	}

	/**
	 * 重写的赋值部分
	 */
	@Override
	protected void setValue(Object value) {
		if (value != null && this.fieldName != null && this.fieldName.length() > 0) {
			try {
				//带有嵌套效果的对象数据
				if (fieldName.indexOf('.') >= 0) {
					
					String[] objs = fieldName.split("\\.");
					Object object = value;
					
					for(int i = 0 ; i < objs.length ; i++)
					{
						Field field = object.getClass().getDeclaredField(objs[i]);
						field.setAccessible(true);
						object = field.get(object);
					}
					
					setText((object == null) ? "" : object.toString());
					
				} else {
					Field field = value.getClass().getDeclaredField(this.fieldName);
					field.setAccessible(true);
					Object object = field.get(value);
					setText((object == null) ? "" : object.toString());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				setText("");
			}

		} else {
			setText("");
		}
	}
}
