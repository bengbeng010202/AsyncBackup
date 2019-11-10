package me.BengBeng.asyncbackup.utils;

import java.awt.Color;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class OptionPane {
	
	public void openFileChooser(JFrame frame, String type) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("CHỌN THƯ MỤC HOẶC TẬP TIN");
		chooser.setFileSelectionMode(type.equals("INPUT") ? JFileChooser.FILES_AND_DIRECTORIES : JFileChooser.DIRECTORIES_ONLY);
		
		File file = (frame.equals(Utils.getGui()) ? (type.equals("INPUT") ? ((Utils.getGui().getInputPath().isEmpty()) ? FileSystemView.getFileSystemView().getHomeDirectory() : new File(Utils.getGui().getInputPath())) : (Utils.getGui().getOutputPath().isEmpty()) ? FileSystemView.getFileSystemView().getHomeDirectory() : new File(Utils.getGui().getOutputPath())) : 
			(type.equals("INPUT") ? ((Utils.getAutoGui().getInputPath().isEmpty()) ? FileSystemView.getFileSystemView().getHomeDirectory() : new File(Utils.getAutoGui().getInputPath())) : (Utils.getAutoGui().getOutputPath().isEmpty()) ? FileSystemView.getFileSystemView().getHomeDirectory() : new File(Utils.getAutoGui().getOutputPath())));
		chooser.setCurrentDirectory(file);
		
		chooser.setApproveButtonText("Chọn");
		chooser.setMultiSelectionEnabled(false);
		chooser.showOpenDialog(null);
		File selected = chooser.getSelectedFile();
		if((selected != null) && (selected.exists())) {
			String path = selected.getPath();
			if(type.equals("INPUT")) {
				if(frame.equals(Utils.getGui())) {
					Utils.getGui().setInputPath(path);
				} else if(frame.equals(Utils.getAutoGui())) {
					Utils.getAutoGui().setInputPath(path);
				}
			} else if(type.equals("OUTPUT")) {
				if(frame.equals(Utils.getGui())) {
					Utils.getGui().setOutputPath(path);
				} else if(frame.equals(Utils.getAutoGui())) {
					Utils.getAutoGui().setOutputPath(path);
				}
			}
		}
	}
	
	
	
	public void setToolTipText(JComponent comp, String... text) {
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		for(int x = 0; x < text.length; x++) {
			String str = text[x];
			builder.append(str + "<br>");
		}
		builder.append("</html>");
		comp.setToolTipText(builder.toString());
	}
	
	
	
	public String getText(JTextPane textPane) {
		return textPane.getText();
	}
	
	public void addText(JTextPane textPane, Color color, String text) {
		StyledDocument doc = textPane.getStyledDocument();
		Style style = textPane.addStyle("Style", null);
		StyleConstants.setForeground(style, color);
		try {
			doc.insertString(doc.getLength(), text + '\n', style);
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
		textPane.setDocument(doc);
		scroll(textPane);
	}
	
	public void setText(JTextPane textPane, String text) {
		textPane.setText(text);
	}
	
	public void scroll(JTextComponent textComp) {
		textComp.setCaretPosition(textComp.getDocument().getLength());
	}
	
}
