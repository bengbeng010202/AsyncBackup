package me.BengBeng.asyncbackup.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import me.BengBeng.asyncbackup.utils.Message;
import me.BengBeng.asyncbackup.utils.Utils;

public class GUI
	extends JFrame {
	
	private boolean opened = false;
	private int mouseX = 0;
	private int mouseY = 0;
	private boolean backuping = false;
	
	private JPanel BACKGROUND;
	private JLabel TITLE_label;
	
	private JPanel main_panel;
	private JLabel FIRST_label;
	private JLabel INPUT_label;
	private JTextField INPUT_textField;
	private JLabel INPUT_button;
	private JLabel OUTPUT_label;
	private JTextField OUTPUT_textField;
	private JLabel OUTPUT_button;
	private JLabel FILE_NAME_label;
	private JTextField typeFileNameText;
	private JComboBox<String> extension_comboBox;
	private JLabel BACKUP_button;
	private JLabel GOTO_AUTO_button;
	
	private JScrollPane CONSOLE_scrollPane;
	public JTextPane CONSOLE_textPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent event) {
				if(opened == false) {
					opened = true;
					doRainbow(TITLE_label);
				}
				updateField();
			}
			@Override
			public void windowClosed(WindowEvent event) {
				if(opened == true) {
					opened = false;
				}
			}
			@Override
			public void windowClosing(WindowEvent event) {
				if(opened == true) {
					opened = false;
				}
			}
		});
		setSize(550, 390);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BACKGROUND = new JPanel();
		BACKGROUND.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				int id = event.getModifiers();
				if(id == 16) {
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
					mouseX = event.getX();
					mouseY = event.getY();
				}
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				int id = event.getModifiers();
				if(id == 16) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					repaint();
				}
			}
		});
		BACKGROUND.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent event) {
				int id = event.getModifiers();
				if(id == 16) {
					int x = event.getXOnScreen();
					int y = event.getYOnScreen();
					setLocation(x - mouseX, y - mouseY);
				}
			}
		});
		BACKGROUND.setOpaque(true);
		BACKGROUND.setBackground(new Color(10, 10, 10, 120));
		BACKGROUND.setLayout(null);
		setContentPane(BACKGROUND);
		
		
		TITLE_label = new JLabel("SAO LƯU DỮ LIỆU CÁ NHÂN");
		TITLE_label.setForeground(Color.WHITE);
		TITLE_label.setHorizontalAlignment(SwingConstants.CENTER);
		TITLE_label.setFont(new Font("Tahoma", Font.BOLD, 12));
		TITLE_label.setBounds(5, 0, 540, 25);
		BACKGROUND.add(TITLE_label);
		
		JLabel MINIMIZE_button = new JLabel("-");
		MINIMIZE_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					setState(JFrame.ICONIFIED);
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					MINIMIZE_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(80, 80, 80), new Color(80, 80, 80)));
					MINIMIZE_button.setFont(new Font("Tahoma", Font.BOLD, 20));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					MINIMIZE_button.setBorder(null);
					MINIMIZE_button.setFont(new Font("Tahoma", Font.BOLD, 25));
					repaint();
				}
				return;
			}
		});
		MINIMIZE_button.setBorder(null);
		MINIMIZE_button.setFont(new Font("Tahoma", Font.BOLD, 25));
		MINIMIZE_button.setForeground(new Color(240, 240, 240));
		MINIMIZE_button.setHorizontalAlignment(SwingConstants.CENTER);
		MINIMIZE_button.setBounds(505, 0, 20, 25);
		BACKGROUND.add(MINIMIZE_button);
		
		JLabel EXIT_button = new JLabel("X");
		EXIT_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					System.exit(0);
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					EXIT_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(160, 160, 160), new Color(160, 160, 160), new Color(80, 80, 80), new Color(80, 80, 80)));
					EXIT_button.setFont(new Font("Tahoma", Font.BOLD, 13));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					EXIT_button.setBorder(null);
					EXIT_button.setFont(new Font("Tahoma", Font.BOLD, 15));
					repaint();
				}
				return;
			}
		});
		EXIT_button.setBorder(null);
		EXIT_button.setFont(new Font("Tahoma", Font.BOLD, 15));
		EXIT_button.setForeground(new Color(255, 65, 65));
		EXIT_button.setHorizontalAlignment(SwingConstants.CENTER);
		EXIT_button.setBounds(525, 0, 20, 25);
		BACKGROUND.add(EXIT_button);
		
		
		
		main_panel = new JPanel();
		main_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		main_panel.setBounds(5, 25, 540, 360);
		main_panel.setBackground(new Color(50, 50, 50));
		main_panel.setLayout(null);
		BACKGROUND.add(main_panel);
		
		
		
		FIRST_label = new JLabel("~ SAO LƯU THỦ CÔNG ~");
		FIRST_label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		FIRST_label.setForeground(Color.CYAN);
		FIRST_label.setHorizontalAlignment(SwingConstants.CENTER);
		FIRST_label.setBounds(0, 5, 540, 20);
		main_panel.add(FIRST_label);
		
		INPUT_label = new JLabel("ĐẦU VÀO:");
		INPUT_label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		INPUT_label.setForeground(Color.WHITE);
		INPUT_label.setHorizontalAlignment(SwingConstants.TRAILING);
		INPUT_label.setBounds(10, 35, 100, 20);
		main_panel.add(INPUT_label);
		
		INPUT_textField = new JTextField();
		INPUT_textField.setEditable(false);
		INPUT_textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		INPUT_textField.setForeground(Color.BLACK);
		INPUT_textField.setBackground(new Color(180, 180, 180));
		INPUT_textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		INPUT_textField.setBounds(120, 35, 300, 20);
		Utils.getOptionPane().setToolTipText(INPUT_textField, "Đường dẫn đến vị trí của file sẽ được sao lưu.");
		main_panel.add(INPUT_textField);
		
		INPUT_button = new JLabel("CHỌN");
		INPUT_button.setOpaque(true);
		INPUT_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					Utils.getSound("click").playWav();
					Utils.getOptionPane().openFileChooser(Utils.getGui(), "INPUT");
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					INPUT_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(50, 50, 50), null, null));
					INPUT_button.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					INPUT_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					INPUT_button.setFont(new Font("Tahoma", Font.BOLD, 13));
				}
				return;
			}
		});
		INPUT_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		INPUT_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		INPUT_button.setForeground(new Color(65, 255, 65));
		INPUT_button.setBackground(new Color(110, 110, 110));
		INPUT_button.setHorizontalAlignment(SwingConstants.CENTER);
		INPUT_button.setBounds(430, 35, 60, 20);
		main_panel.add(INPUT_button);
		
		OUTPUT_label = new JLabel("ĐẦU RA:");
		OUTPUT_label.setHorizontalAlignment(SwingConstants.TRAILING);
		OUTPUT_label.setForeground(Color.WHITE);
		OUTPUT_label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		OUTPUT_label.setBounds(10, 70, 100, 20);
		main_panel.add(OUTPUT_label);
		
		OUTPUT_textField = new JTextField();
		OUTPUT_textField.setEditable(false);
		OUTPUT_textField.setForeground(Color.BLACK);
		OUTPUT_textField.setBackground(new Color(180, 180, 180));
		OUTPUT_textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		OUTPUT_textField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		OUTPUT_textField.setBounds(120, 70, 300, 20);
		Utils.getOptionPane().setToolTipText(OUTPUT_textField, "Đường dẫn đến vị trí của file đã được sao lưu.");
		main_panel.add(OUTPUT_textField);
		
		OUTPUT_button = new JLabel("CHỌN");
		OUTPUT_button.setOpaque(true);
		OUTPUT_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				if(!OUTPUT_button.isEnabled()) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					Utils.getSound("click").playWav();
					Utils.getOptionPane().openFileChooser(Utils.getGui(), "OUTPUT");
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					OUTPUT_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(50, 50, 50), null, null));
					OUTPUT_button.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					OUTPUT_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					OUTPUT_button.setFont(new Font("Tahoma", Font.BOLD, 13));
				}
				return;
			}
		});
		OUTPUT_button.setHorizontalAlignment(SwingConstants.CENTER);
		OUTPUT_button.setForeground(new Color(65, 255, 65));
		OUTPUT_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		OUTPUT_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		OUTPUT_button.setBackground(new Color(110, 110, 110));
		OUTPUT_button.setBounds(430, 70, 60, 20);
		main_panel.add(OUTPUT_button);
		
		FILE_NAME_label = new JLabel("Tên file lưu trữ:");
		FILE_NAME_label.setHorizontalAlignment(SwingConstants.TRAILING);
		FILE_NAME_label.setForeground(Color.WHITE);
		FILE_NAME_label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		FILE_NAME_label.setBounds(10, 105, 120, 20);
		main_panel.add(FILE_NAME_label);
		
		typeFileNameText = new JTextField();
		typeFileNameText.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent event) {
				String name = typeFileNameText.getText();
				if(!Utils.isValidName(name)) {
					new Thread(() -> {
						String text = Utils.removeInvalid(name, "[\\p{L}\\_\\-\\.\\{\\}\\%\\s]*");
						typeFileNameText.setText(text);
						typeFileNameText.setCaretPosition(event.getDot());
						Message.error("Tên file không thể chứa ký tự đặc biệt!");
					}).start();
				} else {
					Utils.getFileAPI().set("file-name", name);
				}
			}
		});
		typeFileNameText.setForeground(Color.BLACK);
		typeFileNameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typeFileNameText.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		typeFileNameText.setBackground(Color.WHITE);
		typeFileNameText.setBounds(140, 105, 190, 20);
		Utils.getOptionPane().setToolTipText(typeFileNameText, "Nhập tên file mà bạn muốn lưu lại.",
				"Không cần thêm định dạng cho file.", "",
				"* Một số các kí hiệu:", " {time} : Chỉ thời gian hiện tại.", " {name} : Lấy tên file ban đầu.",
				" {number} : Tổng số file có trong nơi sao lưu +1.");
		main_panel.add(typeFileNameText);
		
		extension_comboBox = new JComboBox<String>();
		String[] types = { ".zip", ".rar", ".7z" };
		for(int x = 0; x < types.length; x++) {
			String type = types[x];
			extension_comboBox.addItem(type);
		}
		String extension = Utils.getFileAPI().getString("file-extension");
		extension_comboBox.setSelectedItem(extension);
		extension_comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED) {
					Utils.getFileAPI().set("file-extension", extension_comboBox.getSelectedItem().toString());
				}
			}
		});
		extension_comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		extension_comboBox.setForeground(Color.BLACK);
		extension_comboBox.setBackground(new Color(150, 150, 150));
		extension_comboBox.setBounds(340, 105, 65, 20);
		Utils.getOptionPane().setToolTipText(extension_comboBox, "Chọn định dạng cho file mà bạn muốn lưu lại.");
		main_panel.add(extension_comboBox);
		
		
		
		BACKUP_button = new JLabel("SAO LƯU");
		BACKUP_button.setOpaque(true);
		BACKUP_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				String sep = File.separator;
				int id = event.getModifiers();
				if(id == 16) {
					String inputPath = INPUT_textField.getText();
					if((inputPath.isEmpty()) || (inputPath.length() <= 0)) {
						Utils.getSound("error").playWav();
						Utils.getOptionPane().addText(CONSOLE_textPane, new Color(255, 50, 50), "[" + Utils.getTime() + "] Vui lòng chọn đường dẫn đến nơi sẽ được sao lưu!");
						return;
					}
					File input = new File(inputPath);
					Path inPath = input.toPath();
					
					String outputPath = OUTPUT_textField.getText();
					if((outputPath.isEmpty()) || (outputPath.length() <= 0)) {
						Utils.getSound("error").playWav();
						Utils.getOptionPane().addText(CONSOLE_textPane, new Color(255, 50, 50), "[" + Utils.getTime() + "] Vui lòng chọn đường dẫn đến nơi mà bạn muốn sao lưu!");
						return;
					}
					String outputName = typeFileNameText.getText();
					if((outputName.isEmpty()) || (outputName.length() <= 0)) {
						Utils.getSound("error").playWav();
						Utils.getOptionPane().addText(CONSOLE_textPane, new Color(255, 50, 50), "[" + Utils.getTime() + "] Vui lòng nhập tên file mà bạn muốn sao lưu!");
						return;
					}
					File output = new File(outputPath + sep + (outputName
							.replaceAll("(?ium)(\\{name}|\\%name%)", input.getName())
							.replaceAll("(?ium)(\\{date}|\\%date%|\\{time}|\\%time%)", Utils.getDate())
							.replaceAll("(?ium)(\\{number}|\\%number%)", String.valueOf(new File(outputPath).listFiles().length))) + extension_comboBox.getSelectedItem().toString());
					Path outPath = output.toPath();
					
					Utils.getSound("click").playWav();
					Utils.getOptionPane().addText(CONSOLE_textPane, Color.ORANGE, "[" + Utils.getTime() + "] Đang tiến hành sao lưu dữ liệu...");
					
					typeFileNameText.setEnabled(backuping);
					extension_comboBox.setEnabled(backuping);
					
					new Thread(() -> {
						if(backuping == false) {
							backuping = true;
							Utils.getZipFile().convertToZip(inPath, outPath);
						}
						
						if(backuping == true) {
							Utils.getSound("success").playWav();
							typeFileNameText.setEnabled(backuping);
							extension_comboBox.setEnabled(backuping);
							Utils.getOptionPane().addText(CONSOLE_textPane, new Color(0, 255, 0), "[" + Utils.getTime() + "] Sao lưu dữ liệu thành công!");
							backuping = false;
						}
					}).start();
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					BACKUP_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(50, 50, 50), null, null));
					BACKUP_button.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					BACKUP_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					BACKUP_button.setFont(new Font("Tahoma", Font.BOLD, 13));
				}
				return;
			}
		});
		BACKUP_button.setHorizontalAlignment(SwingConstants.CENTER);
		BACKUP_button.setForeground(new Color(30, 30, 30));
		BACKUP_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		BACKUP_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BACKUP_button.setBackground(new Color(120, 255, 120));
		BACKUP_button.setBounds(170, 145, 90, 20);
		main_panel.add(BACKUP_button);
		
		GOTO_AUTO_button = new JLabel("TỰ ĐỘNG");
		GOTO_AUTO_button.setOpaque(true);
		GOTO_AUTO_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					Utils.getSound("click").playWav();
					Utils.getFileAPI().set("auto-backup", true);
					Utils.closeGui();
					Utils.openAutoGui();
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					GOTO_AUTO_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(50, 50, 50), null, null));
					GOTO_AUTO_button.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				if(backuping == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					GOTO_AUTO_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					GOTO_AUTO_button.setFont(new Font("Tahoma", Font.BOLD, 13));
				}
				return;
			}
		});
		GOTO_AUTO_button.setHorizontalAlignment(SwingConstants.CENTER);
		GOTO_AUTO_button.setForeground(new Color(30, 30, 30));
		GOTO_AUTO_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		GOTO_AUTO_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GOTO_AUTO_button.setBackground(new Color(200, 200, 200));
		GOTO_AUTO_button.setBounds(280, 145, 90, 20);
		main_panel.add(GOTO_AUTO_button);
		
		
		
		JLabel COPY_RIGHT_label = new JLabel("© 2019 Copyright by [ßeñg ßeñg]");
		COPY_RIGHT_label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		COPY_RIGHT_label.setForeground(new Color(0, 255, 0, 50));
		COPY_RIGHT_label.setHorizontalAlignment(SwingConstants.CENTER);
		COPY_RIGHT_label.setBounds(20, 175, 500, 20);
		main_panel.add(COPY_RIGHT_label);
		
		
		
		JPanel CONSOLE_panel = new JPanel();
		CONSOLE_panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 255), 2, true), "CONSOLE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 255)));
		CONSOLE_panel.setBounds(5, 200, 530, 155);
		CONSOLE_panel.setBackground(new Color(50, 50, 50));
		CONSOLE_panel.setLayout(null);
		main_panel.add(CONSOLE_panel);
		
		CONSOLE_scrollPane = new JScrollPane();
		CONSOLE_scrollPane.setBorder(null);
		CONSOLE_scrollPane.setBounds(5, 15, 520, 135);
		CONSOLE_panel.add(CONSOLE_scrollPane);
		
		CONSOLE_textPane = new JTextPane();
		CONSOLE_textPane.setEditable(false);
		CONSOLE_textPane.setFont(new Font("Verdana", Font.PLAIN, 11));
		CONSOLE_textPane.setContentType("text/html");
		CONSOLE_textPane.setBorder(null);
		CONSOLE_textPane.setBackground(new Color(50, 50, 50));
		CONSOLE_scrollPane.setViewportView(CONSOLE_textPane);
	}
	
	
	
	public String getInputPath() {
		return INPUT_textField.getText();
	}
	
	public void setInputPath(String path) {
		INPUT_textField.setText(path);
		Utils.getFileAPI().set("input-path", path);
	}
	
	public String getOutputPath() {
		return OUTPUT_textField.getText();
	}
	
	public void setOutputPath(String path) {
		OUTPUT_textField.setText(path);
		Utils.getFileAPI().set("output-path", path);
	}
	
	
	
	private void updateField() {
		String input = Utils.getFileAPI().getString("input-path");
		String output = Utils.getFileAPI().getString("output-path");
		String fileName = Utils.getFileAPI().getString("file-name");
		
		INPUT_textField.setText(input);
		OUTPUT_textField.setText(output);
		typeFileNameText.setText(fileName);
	}
	
	
	
	private void doRainbow(JLabel label) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(opened == true) {
					int red = getRandomInt(60, 200);
					int green = getRandomInt(60, 200);
					int blue = getRandomInt(60, 200);
					label.setForeground(new Color(red, green, blue));
					repaint();
					try {
						Thread.sleep(300);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
	
	private int getRandomInt(int min, int max) {
		return (new Random().nextInt((max - min) + 1) + min);
	}
	
}
