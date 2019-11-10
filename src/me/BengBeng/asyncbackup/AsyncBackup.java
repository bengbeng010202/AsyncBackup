package me.BengBeng.asyncbackup;

import javax.swing.UIManager;

import me.BengBeng.asyncbackup.utils.Utils;

public class AsyncBackup {
	
	public static void main(String[] args) {
		Utils.getFileAPI().loadConfig();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		Utils.open();
	}
	
}
