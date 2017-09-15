package org.sam.weblaf.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.alee.laf.colorchooser.WebColorChooserDialog;

/**
 * 颜色选择对话框
 * @author sam
 *
 */
public class JWColorChooserDialog extends WebColorChooserDialog {

	private static final long serialVersionUID = -4073734321825991766L;
		
	/**
	 * 确定按钮
	 */
	private JButton btnOk;
	
	/**
	 * 取消按钮
	 */
	private JButton btnCancle;
	
	/**
	 * 回调事件
	 */
	private ColorChooserLisenter chooseListener;
	
	/**
	 * 设置颜色选择事件
	 * @param l
	 */
	public void addColorChooserLisenter(ColorChooserLisenter l)
	{
		chooseListener = l;
	}
	
	/**
	 * 移除listener
	 */
	public void removeColorChooserLisenter()
	{
		chooseListener = null;
	}

	/**
	 * 颜色选择对话框
	 */
	public JWColorChooserDialog()
	{
		super();
		setFont(new Font("宋体", Font.PLAIN, 12));
		initCompents();
	}
	
	/**
	 * 带有颜色参数的构造函数
	 * @param color
	 */
	public JWColorChooserDialog(Color color)
	{
		this();
		this.setColor(color);
	}
	
	/**
	 * 初始化空间的操作
	 */
	protected void initCompents()
	{
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("颜色选择器");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnOk = new JButton("确定");
		panel.add(btnOk);
		
		btnCancle = new JButton("取消");
		panel.add(btnCancle);
		
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setSize(580, 400);
		this.setResizable(false);
		
		btnCancle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chooseListener != null){
					chooseListener.afterCancle();
				}
				setVisible(false);
			}
		});
		
		/**
		 * 执行回调
		 */
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chooseListener != null){
					chooseListener.afterChoose(getColor());
				}
				setVisible(false);
			}
		});
	}
	
	/**
	 * 设置颜色的操作
	 * @author sam
	 *
	 */
	public interface ColorChooserLisenter extends EventListener
	{
		/**
		 * 点击确定的按钮执行的操作
		 * @param color
		 */
		public void afterChoose(Color color);
		
		/**
		 * 取消的操作
		 */
		public default void afterCancle() {};
	}

}
