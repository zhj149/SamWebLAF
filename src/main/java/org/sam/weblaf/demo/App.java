package org.sam.weblaf.demo;

import java.awt.Font;

import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;

/**
 * 演示代码
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			WebLookAndFeel.globalControlFont = new Font("宋体", Font.PLAIN, 12);
			WebLookAndFeel.globalAcceleratorFont = new Font("宋体", Font.PLAIN, 12);
			WebLookAndFeel.globalAlertFont = new Font("宋体", Font.PLAIN, 12);
			WebLookAndFeel.globalMenuFont = new Font("宋体", Font.PLAIN, 12);
			WebLookAndFeel.globalTextFont = new Font("宋体", Font.PLAIN, 12);
			WebLookAndFeel.globalTitleFont = new Font("宋体", Font.PLAIN, 12);
			WebLookAndFeel.globalTooltipFont = new Font("宋体", Font.PLAIN, 12);
			UIManager.setLookAndFeel(new WebLookAndFeel());

		} catch (Exception e) {
			e.printStackTrace();
		}

		JFrameDefaultTableDemo frm = new JFrameDefaultTableDemo();
		frm.setSize(1024, 768);
		frm.setLocationRelativeTo(null); // 在屏幕上居中
		frm.setVisible(true);

	}
}
