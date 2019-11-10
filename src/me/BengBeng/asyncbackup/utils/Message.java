package me.BengBeng.asyncbackup.utils;

import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Message {
	
	/*
	 * KHI THÀNH CÔNG:
	 */
	
	public static void success(String msg) {
		Utils.getSound("success").playWav();
		JOptionPane.showMessageDialog(null, msg, "THÀNH CÔNG!", JOptionPane.DEFAULT_OPTION, Utils.getIcon("success"));
	}
	
	public static void success(String msg, String title) {
		Utils.getSound("success").playWav();
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.DEFAULT_OPTION, Utils.getIcon("success"));
	}
	
	public static void success(String msg, Icon icon) {
		Utils.getSound("success").playWav();
		JOptionPane.showMessageDialog(null, msg, "THÀNH CÔNG!", JOptionPane.DEFAULT_OPTION, icon);
	}
	
	public static void success(String msg, String title, Icon icon) {
		Utils.getSound("success").playWav();
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.DEFAULT_OPTION, icon);
	}
	
	
	
	/*
	 * KHI THẤT BẠI:
	 */
	
	public static void error(String msg) {
		Utils.getSound("error").playWav();
		JOptionPane.showMessageDialog(null, msg, "LỖI!", JOptionPane.DEFAULT_OPTION, Utils.getIcon("error"));
	}
	
	public static void error(String msg, String title) {
		Utils.getSound("error").playWav();
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.DEFAULT_OPTION, Utils.getIcon("error"));
	}
	
	public static void error(String msg, Icon icon) {
		Utils.getSound("error").playWav();
		JOptionPane.showMessageDialog(null, msg, "LỖI!", JOptionPane.DEFAULT_OPTION, icon);
	}
	
	public static void error(String msg, String title, Icon icon) {
		Utils.getSound("error").playWav();
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.DEFAULT_OPTION, icon);
	}
	
	
	
	/*
	 * KHI YÊU CẦU XÁC NHẬN:
	 */
	
	public static int warning(String msg) {
		Utils.getSound("warning").playWav();
		return JOptionPane.showConfirmDialog(null, msg, "XÁC NHẬN!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, Utils.getIcon("warning"));
	}
	
	public static int warning(String msg, String title) {
		Utils.getSound("warning").playWav();
		return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, Utils.getIcon("warning"));
	}
	
	public static int warning(String msg, Icon icon) {
		Utils.getSound("warning").playWav();
		return JOptionPane.showConfirmDialog(null, msg, "XÁC NHẬN!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, icon);
	}
	
	public static int warning(String msg, String title, Icon icon) {
		Utils.getSound("warning").playWav();
		return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, icon);
	}
	
}
