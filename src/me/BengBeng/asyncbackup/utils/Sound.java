package me.BengBeng.asyncbackup.utils;

import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import javazoom.jl.player.Player;

public class Sound {
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public void playMP3() {
		try {
			Player player = new Player(Sound.class.getResourceAsStream("/sounds/" + getName() + ".mp3"));
			new Thread(() -> {
				try {
					player.play();
					if(player.isComplete()) {
						// Thực thi sau khi hoàn thành bài nhạc...
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}).start();
			// Thực thi thêm điều kiện khác ở đây (nếu có)...
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void playWav() {
		new Thread(() -> {
			try {
				URL url = Sound.class.getResource("/sounds/" + name + ".wav");
				AudioInputStream ais = AudioSystem.getAudioInputStream(url);
				AudioFormat format = ais.getFormat();
				SourceDataLine line = AudioSystem.getSourceDataLine(format);
				line.open(format);
				line.start();
				
				byte[] buffer = new byte[4096];
				int i = -1;
				
				while((i = ais.read(buffer)) != -1) {
					line.write(buffer, 0, i);
				}
				
				line.drain();
				line.close();
				ais.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}).start();
	}
	
}
