package me.BengBeng.asyncbackup.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

public class FileAPI {
	
	private File config;
	
	public File getConfig() {
		return config;
	}
	
	public void setConfig(File config) {
		this.config = config;
	}
	
	
	
	public boolean hasConfig() {
		return ((config != null) && (config.exists()));
	}
	
	
	
	public void loadConfig() {
		config = new File("config.properties");
		if(!config.exists()) {
			try {
				config.createNewFile();
				setConfig(config);
				
				String home = FileSystemView.getFileSystemView().getHomeDirectory().getPath();
				set("date-format", "dd-MM-yyyy HH-mm-ss");
				set("input-path", home);
				set("output-path", home + File.separator + "BACKUP");
				set("file-name", "");
				set("file-extension", ".zip");
				
				set("auto-backup", false);
				set("backup-time", 0);
				set("override-file", false);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	
	public List<String> getAllKeys() {
		List<String> list = new ArrayList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			fis = new FileInputStream(getConfig());
			isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			reader = new BufferedReader(isr);
			String data = null;
			while((data = reader.readLine()) != null) {
				list.add(data);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
				isr.close();
				fis.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
					
		}
		return list;
	}
	
	
	
	public void set(String key, Object value) {
		List<String> allKeys = getAllKeys();
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter writer = null;
		try {
			fos = new FileOutputStream(getConfig());
			osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			writer = new BufferedWriter(osw);
			
			if((allKeys.isEmpty()) || (allKeys.size() <= 0)) {
				writer.write(key + "=" + String.valueOf(value) + '\n');
			} else {
				int count = 0;
				for(int x = 0; x < allKeys.size(); x++) {
					String keys = allKeys.get(x);
					String first = keys.split("=")[0];
					if(!first.equals(key)) {
						count += 1;
					} else {
						allKeys.set(x, key + "=" + String.valueOf(value));
						break;
					}
				}
				if(count == allKeys.size()) {
					allKeys.add(key + "=" + String.valueOf(value));
				}
				for(String keys : allKeys) {
					writer.write(keys + '\n');
				}
			}
			
			writer.flush();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
				osw.close();
				fos.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void addComment(String cmt) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter writer = null;
		try {
			fos = new FileOutputStream(getConfig());
			osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			writer = new BufferedWriter(osw);
			writer.write("#" + cmt);
			writer.flush();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
				osw.close();
				fos.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	
	public Object getObject(String key) {
		Object obj = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			fis = new FileInputStream(getConfig());
			isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			reader = new BufferedReader(isr);
			String data = null;
			while((data = reader.readLine()) != null) {
				String[] split = data.split("=");
				String first = split[0];
				if(key.equals(first)) {
					obj = ((split.length > 1) ? split[1] : "");
					break;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				reader.close();
				isr.close();
				fis.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
					
		}
		return obj;
	}
	
	public String getString(String key) {
		return String.valueOf(getObject(key));
	}
	
	public short getShort(String key) {
		return Short.parseShort(String.valueOf(getObject(key)));
	}
	
	public byte getByte(String key) {
		return Byte.parseByte(String.valueOf(getObject(key)));
	}
	
	public float getFloat(String key) {
		return Float.parseFloat(String.valueOf(getObject(key)));
	}
	
	public double getDouble(String key) {
		return Double.parseDouble(String.valueOf(getObject(key)));
	}
	
	public int getInt(String key) {
		return Integer.parseInt(String.valueOf(getObject(key)));
	}
	
	public long getLong(String key) {
		return Long.parseLong(String.valueOf(getObject(key)));
	}
	
	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(String.valueOf(getObject(key)));
	}
	
}
