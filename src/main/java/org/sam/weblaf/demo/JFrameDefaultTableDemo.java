package org.sam.weblaf.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;

import org.sam.weblaf.resources.ResourceLoader;
import org.sam.weblaf.table.JWTable;
import org.sam.weblaf.table.JWTableBuilder;
import org.sam.weblaf.table.JWTableColumn;
import org.sam.weblaf.table.JWTableColumnModel;
import org.sam.weblaf.table.JWTableModel;
import org.sam.weblaf.table.defaultImpl.JWTableDefaultBuilderImpl;
import org.sam.weblaf.table.defaultImpl.JWTableModelDefaultAdapter;
import org.sam.weblaf.table.editor.JWTableCheckboxEditor;
import org.sam.weblaf.table.editor.JWTableColorEditor;
import org.sam.weblaf.table.editor.JWTableDateSpinnerEditor;
import org.sam.weblaf.table.editor.JWTableFontEditor;
import org.sam.weblaf.table.editor.JWTableRadioButtonGroupEditor;
import org.sam.weblaf.table.editor.JWTableSpinnerEditor;
import org.sam.weblaf.table.renderer.JWTableCheckboxRenderer;
import org.sam.weblaf.table.renderer.JWTableColorRenderer;
import org.sam.weblaf.table.renderer.JWTableFormatRenderer;
import org.sam.weblaf.table.renderer.JWTableRadioButtonGroupRenderer;
import org.sam.weblaf.table.renderer.JWTableRowNumberRenderer;

import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.toolbar.WebToolBar;

/**
 * table表格方法
 * 
 * @author sam
 *
 */
public class JFrameDefaultTableDemo extends JFrame {

	private static final long serialVersionUID = 8818584079585682536L;

	/**
	 *
	 */
	public JFrameDefaultTableDemo() {
		super();
		initCompents();
	}

	/**
	 * 当前的table
	 */
	private JWTable table;

	/**
	 * tablemodel
	 */
	private JWTableModel<List<TestEntity>> tableModel;

	/**
	 * colmodel
	 */
	private JWTableColumnModel colModel;

	/**
	 * 初始化控件的操作
	 */
	/**
	 * 
	 */
	protected void initCompents() {
		JPanel panel = new JPanel(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultTableCellRenderer renderC = new DefaultTableCellRenderer();
		renderC.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

		DefaultTableCellRenderer renderR = new DefaultTableCellRenderer();
		renderR.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

		JWTableColumn col0 = new JWTableColumn();
		col0.setIdentifier("");
		col0.setTitle("#");
		col0.setHeaderValue("#");
		col0.setModelIndex(0);
		col0.setEditable(false);
		col0.setMaxWidth(30);
		JWTableRowNumberRenderer rownNumRender = new JWTableRowNumberRenderer();
		rownNumRender.setHorizontalAlignment(JWTableRowNumberRenderer.CENTER);
		col0.setCellRenderer(rownNumRender); // 行号渲染器

		// 系统默认的渲染器和编辑器
		JWTableColumn col1 = new JWTableColumn();
		col1.setIdentifier("code"); // 对应的实体字段
		col1.setTitle("代码");
		col1.setHeaderValue("代码");
		col1.setModelIndex(1); // 这个必须逐次增加
		col1.setWidth(75);
		col1.setMinWidth(25);
		col1.setDefaultValue("");

		// 系统默认的渲染器和编辑器
		JWTableColumn col2 = new JWTableColumn();
		col2.setIdentifier("name");
		col2.setTitle("姓名");
		col2.setHeaderValue("姓名");
		col2.setModelIndex(2);
		col2.setWidth(75);
		col2.setMinWidth(25);
		col2.setDefaultValue("");

		// 因为这个字段是存的字符串，演示一下原生的控件的效果
		JWTableColumn col3 = new JWTableColumn();
		col3.setIdentifier("gender");
		col3.setTitle("性别");
		col3.setHeaderValue("性别");
		col3.setModelIndex(3);
		col3.setWidth(75);
		col3.setMinWidth(25);
		col3.setDefaultValue("男");
		JComboBox<String> cbGender = new JComboBox<>(new String[] { "男", "女" });
		DefaultCellEditor editorGender = new DefaultCellEditor(cbGender);
		col3.setCellRenderer(renderC); // 居中显示
		col3.setCellEditor(editorGender); // 普通的下拉列表框

		// 以下是数字微调控件的示例
		JWTableColumn col4 = new JWTableColumn();
		col4.setIdentifier("age");
		col4.setTitle("年龄");
		col4.setHeaderValue("年龄");
		col4.setModelIndex(4);
		col4.setWidth(25);
		col4.setMinWidth(25);
		col4.setDefaultValue(1);
		col4.setCellRenderer(renderR); // 右侧显示
		// 带有范围限定的
		col4.setCellEditor(new JWTableSpinnerEditor(new JSpinner(new SpinnerNumberModel(1, 1, 255, 1))));

		// 日期下拉控件
		JWTableColumn col5 = new JWTableColumn();
		col5.setIdentifier("birthday");
		col5.setTitle("生日");
		col5.setHeaderValue("生日");
		col5.setModelIndex(5);
		col5.setWidth(85);
		col5.setMinWidth(85);
		col5.setDefaultValue(null);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		col5.setCellRenderer(new JWTableFormatRenderer(dateFormat, "0000-00-00"));
		col5.setCellEditor(new JWTableDateSpinnerEditor());

		// 单选按钮
		JWTableColumn col6 = new JWTableColumn();
		col6.setIdentifier("role");
		col6.setTitle("角色");
		col6.setHeaderValue("角色");
		col6.setModelIndex(6);
		col6.setWidth(175);
		col6.setMinWidth(175);
		col6.setDefaultValue(1);

		Map<Integer, String> roles = new LinkedHashMap<>();
		roles.put(0, "管理员");
		roles.put(1, "经理");
		roles.put(2, "员工");

		col6.setCellRenderer(new JWTableRadioButtonGroupRenderer<Integer, String>(roles));
		col6.setCellEditor(new JWTableRadioButtonGroupEditor<Integer, String>(roles));

		JWTableColumn col7 = new JWTableColumn();
		col7.setIdentifier("onDuty");
		col7.setTitle("在职状态");
		col7.setHeaderValue("在职状态");
		col7.setModelIndex(7);
		col7.setWidth(15);
		col7.setMinWidth(15);
		col7.setDefaultValue(false);
		JWTableCheckboxRenderer jsTableCheckboxRenderer = new JWTableCheckboxRenderer();
		jsTableCheckboxRenderer.setHorizontalAlignment(JWTableCheckboxRenderer.CENTER);
		col7.setCellRenderer(jsTableCheckboxRenderer);
		WebCheckBox jCheckBox = new WebCheckBox();
		jCheckBox.setHorizontalAlignment(WebCheckBox.CENTER);
		col7.setCellEditor(new JWTableCheckboxEditor(jCheckBox));

		// 颜色选择控件
		JWTableColumn col8 = new JWTableColumn();
		col8.setIdentifier("color");
		col8.setTitle("颜色");
		col8.setHeaderValue("颜色");
		col8.setModelIndex(8);
		col8.setWidth(15);
		col8.setMaxWidth(50);
		col8.setDefaultValue(Color.BLACK.getRGB());
		col8.setCellRenderer(new JWTableColorRenderer());
		col8.setCellEditor(new JWTableColorEditor());

		JWTableColumn col9 = new JWTableColumn();
		col9.setIdentifier("remark");
		col9.setTitle("字体");
		col9.setHeaderValue("字体");
		col9.setModelIndex(9);
		col9.setWidth(15);
		col9.setMaxWidth(125);
		col9.setDefaultValue("");
		col9.setCellEditor(new JWTableFontEditor());

		try {
			JWTableBuilder<List<TestEntity>> builder = new JWTableDefaultBuilderImpl<>(TestEntity.class, col0, col1,
					col2, col3, col4, col5, col6, col7, col8,col9);
			colModel = builder.buildTableColumnModel();
			tableModel = builder.buildTableModel();
			table = new JWTable(tableModel, colModel);

			tableModel.setTableModelLinster(new JWTableModelDefaultAdapter<TestEntity>(tableModel) {
				/**
				 * 加载数据的方法
				 */
				public List<TestEntity> onRetrieve() throws Exception {

					List<TestEntity> result = new LinkedList<>();
					for (int i = 0; i < 1000; i++) {
						TestEntity entity = new TestEntity();
						entity.setId(i);
						entity.setCode("" + i);
						entity.setName("name:" + i);
						entity.setGender(i % 3 == 0 ? "男" : (i % 2 == 1 ? "女" : null));
						entity.setAge((i + 1) % 255);
						entity.setRole(i % 3 == 0 ? 0 : (i % 2 == 1 ? 1 : 2));
						entity.setOnDuty(i % 2 == 0);
						entity.setColor(i % 2 == 0 ? Color.CYAN.getRGB() : Color.BLACK.getRGB());
						entity.setRemark(i % 2 == 0 ? null : "");
						result.add(entity);
					}
					return result;
				}
			});

			panel.add(new JScrollPane(table), BorderLayout.CENTER);
			this.add(panel, BorderLayout.CENTER);

			// 加载数据
			tableModel.retrieve();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 加个工具条
		// 以下是测试表格显示风格的操作
		WebToolBar toolBar = new WebToolBar();
		add(toolBar, BorderLayout.NORTH);

		WebButton btnRefresh = new WebButton("刷新",
				new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_RETRIEVE)));
		btnRefresh.setBorderPainted(false);
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableModel.retrieve();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		toolBar.add(btnRefresh);

		WebButton btnEditor = new WebButton("编辑", new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_MODIFY)));
		btnEditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.readOnly();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		toolBar.add(btnEditor);
		
		WebButton btnAdd = new WebButton("新增", new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_NEW)));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.append();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		toolBar.add(btnAdd);

		WebButton btnDel = new WebButton("删除", new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_DELETE)));
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.delete();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		toolBar.add(btnDel);

		WebButton btnSave = new WebButton("保存", new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_SAVE)));
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table.update();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "保存失败\r\n出错信息为：\r\n" + ex.getMessage());
				}
			}
		});
		toolBar.add(btnSave);

	}

}
