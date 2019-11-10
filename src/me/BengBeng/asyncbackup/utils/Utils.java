package me.BengBeng.asyncbackup.utils;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import me.BengBeng.asyncbackup.file.FileAPI;
import me.BengBeng.asyncbackup.form.AutoGUI;
import me.BengBeng.asyncbackup.form.GUI;

public class Utils {
	
	/*
	 * KHAI BÁO CLASS:
	 */
	
	private static ZipFile zipFile = new ZipFile();
	
	public static ZipFile getZipFile() {
		return zipFile;
	}
	
	private static FileAPI fileApi = new FileAPI();
	
	public static FileAPI getFileAPI() {
		return fileApi;
	}
	
	private static OptionPane optionPane = new OptionPane();
	
	public static OptionPane getOptionPane() {
		return optionPane;
	}
	
	private static Sound sound = new Sound();
	
	public static Sound getSound(String name) {
		sound.setName(name);
		return sound;
	}
	
	
	
	/*
	 * MỞ GUI:
	 */
	
	private static GUI gui;
	
	public static GUI getGui() {
		return gui;
	}
	
	public static void setGui(GUI gui) {
		Utils.gui = gui;
	}
	
	public static void openGui() {
		setGui(new GUI());
		getGui().setVisible(true);
	}
	
	public static void closeGui() {
		getGui().dispose();
	}
	
	private static AutoGUI autoGui;
	
	public static AutoGUI getAutoGui() {
		return autoGui;
	}
	
	public static void setAutoGui(AutoGUI autoGui) {
		Utils.autoGui = autoGui;
	}
	
	public static void openAutoGui() {
		setAutoGui(new AutoGUI());
		getAutoGui().setVisible(true);
	}
	
	public static void closeAutoGui() {
		getAutoGui().dispose();
	}
	
	
	public static void open() {
		boolean auto = Utils.getFileAPI().getBoolean("auto-backup");
		if(auto == true) {
			openAutoGui();
		} else {
			openGui();
		}
	}
	
	
	
	/*
	 * CÁC CÀI ĐẶT LẤY TỪ CONFIG.PROPERTIES:
	 */
	
	public static boolean isAutoBackup() {
		return getFileAPI().getBoolean("auto-backup");
	}
	
	public static boolean isOverrideFile() {
		return getFileAPI().getBoolean("override-file");
	}
	
	
	
	/*
	 * CÁC PHẦN KHÁC:
	 */
	
	public static Icon getIcon(String name) {
		Icon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(Utils.class.getResourceAsStream("/images/" + name + ".png")));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return icon;
	}
	
	public static String getDate() {
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
		Calendar cal = Calendar.getInstance(timeZone);
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.getFileAPI().getString("date-format"));
		sdf.setTimeZone(timeZone);
		return sdf.format(cal.getTime());
	}
	
	public static String getTime() {
		TimeZone timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
		Calendar cal = Calendar.getInstance(timeZone);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(timeZone);
		return sdf.format(cal.getTime());
	}
	
	public static String removeInvalid(String name, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		
		String str = name;
		int length = str.length();
		for(int x = 0; x < length; x++) {
			String word = String.valueOf(str.charAt(x));
			Matcher matcher = pattern.matcher(word);
			if(!matcher.matches()) {
				name = name.replace(word, "");
			}
		}
		return name;
	}
	
	public static boolean isValidName(String name) {
		String regex = "[\\p{L}\\d\\_\\-\\.\\{\\}\\%\\s]*";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	public static String getFormatTime(long time) {
		YearMonth year = YearMonth.now();
		int dayInMonth = year.lengthOfMonth();
		long seconds = time % 60;
		long minutes = time / 60 % 60;
		long hours = time / (60 * 60) % 24;
		long days = time / (60 * 60 * 24) % dayInMonth;
		long months = time / (60 * 60 * 24 * dayInMonth) % 12;
		long years = time / (60 * 60 * 24 * dayInMonth * 12);
		String result = "";
		if(years != 0) {
			result = years + " năm " + months + " tháng " + days + " ngày " + hours + " giờ " + minutes + " phút " + seconds + " giây";
		} else {
			if(months != 0) {
				result = months + " tháng " + days + " ngày " + hours + " giờ " + minutes + " phút " + seconds + " giây";
			} else {
				if(days != 0) {
					result = days + " ngày " + hours + " giờ " + minutes + " phút " + seconds + " giây";
				} else {
					if(hours != 0) {
						result = hours + " giờ " + minutes + " phút " + seconds + " giây";
					} else {
						if(minutes != 0) {
							result = minutes + " phút " + seconds + " giây";
						} else {
							result = seconds + " giây";
						}
					}
				}
			}
		}
		return result;
	}
	
	public static int getTimeByCorrect(int days, int hours, int min, int sec) {
		int result = (days * 86400) + (hours * 3600) + (min * 60) + sec;
		return result;
	}
	
}
