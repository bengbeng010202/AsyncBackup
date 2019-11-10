package me.BengBeng.asyncbackup.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.BengBeng.asyncbackup.utils.Message;
import me.BengBeng.asyncbackup.utils.Utils;

public class AutoGUI
	extends JFrame {
	
	private boolean opened = false;
	private int mouseX = 0;
	private int mouseY = 0;
	private boolean starting = false;
	private boolean backuping = false;
	private boolean correctTime = true;
	
	private Timer timer;
	
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
	private JCheckBox OVERRIDE_checkBox;
	
	private JPanel TIME_panel;
	
	private JLabel START_STOP_button;
	private JLabel GOTO_MANUALLY_button;
	
	private JScrollPane CONSOLE_scrollPane;
	public JTextPane CONSOLE_textPane;
	
	private JLabel SWITCH_button;
	private JSeparator separator;
	
	private JSpinner DAYS_spinner;
	private JLabel DAYS_label;
	private JSpinner HOURS_spinner;
	private JLabel HOURS_label;
	private JSpinner MINUTES_spinner;
	private JLabel MINUTES_label;
	private JSpinner SECONDS_spinner;
	private JLabel SECONDS_label;
	
	private JSpinner ONLY_SECONDS_spinner;
	private JLabel ONLY_SECONDS_label;
	private JSeparator ONLY_SECONDS_separator;
	private JLabel ONLY_SECONDS_time;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoGUI frame = new AutoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AutoGUI() {
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
		setSize(550, 460);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BACKGROUND = new JPanel();
		BACKGROUND.setOpaque(true);
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
				if(starting == true) {
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
				if(starting == true) {
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
				if(starting == true) {
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
				if(starting == true) {
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
				if(starting == true) {
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
				if(starting == true) {
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
		main_panel.setBounds(5, 25, 540, 430);
		main_panel.setBackground(new Color(50, 50, 50));
		main_panel.setLayout(null);
		BACKGROUND.add(main_panel);
		
		
		
		FIRST_label = new JLabel("~ SAO LƯU TỰ ĐỘNG ~");
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
				if(starting == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					Utils.getSound("click").playWav();
					Utils.getOptionPane().openFileChooser(Utils.getAutoGui(), "INPUT");
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(starting == true) {
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
				if(starting == true) {
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
				if(starting == true) {
					return;
				}
				if(!OUTPUT_button.isEnabled()) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					Utils.getSound("click").playWav();
					Utils.getOptionPane().openFileChooser(Utils.getAutoGui(), "OUTPUT");
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(starting == true) {
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
				if(starting == true) {
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
		
		OVERRIDE_checkBox = new JCheckBox("GHI ĐÈ");
		OVERRIDE_checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boolean override = OVERRIDE_checkBox.isSelected();
				Utils.getFileAPI().set("override-file", override);
			}
		});
		OVERRIDE_checkBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		OVERRIDE_checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		OVERRIDE_checkBox.setForeground(Color.ORANGE);
		OVERRIDE_checkBox.setBackground(new Color(50, 50, 50));
		OVERRIDE_checkBox.setBounds(30, 210, 95, 20);
		main_panel.add(OVERRIDE_checkBox);
		
		
		
		TIME_panel = new JPanel();
		TIME_panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 255), 2, true), "CH\u1EC8NH TH\u1EDCI GIAN", TitledBorder.LEADING, TitledBorder.TOP, null, Color.CYAN));
		TIME_panel.setBackground(new Color(50, 50, 50));
		TIME_panel.setBounds(10, 135, 520, 55);
		TIME_panel.setLayout(null);
		main_panel.add(TIME_panel);
		
		/*
		 * THỜI GIAN CHUẨN:
		 */
		
		SWITCH_button = new JLabel((correctTime == true) ? "BẰNG GIÂY" : "CHUẨN");
		SWITCH_button.setOpaque(true);
		SWITCH_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(starting == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					Utils.getSound("click").playWav();
					correctTime = !correctTime;
					switchToOther();
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(starting == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					SWITCH_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(50, 50, 50), null, null));
					SWITCH_button.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				if(starting == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					SWITCH_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					SWITCH_button.setFont(new Font("Tahoma", Font.BOLD, 13));
				}
				return;
			}
		});
		SWITCH_button.setHorizontalAlignment(SwingConstants.CENTER);
		SWITCH_button.setForeground(new Color(30, 30, 30));
		SWITCH_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		SWITCH_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		SWITCH_button.setBackground(new Color(200, 200, 200));
		SWITCH_button.setBounds(415, 20, 90, 20);
		Utils.getOptionPane().setToolTipText(SWITCH_button, "* Chuyển đổi dạng thời gian.",
				"Nhấp để chuyển sang nhập thời gian <b>" + ((correctTime == true) ? "bằng giây" : "chuẩn") + "</b>.");
		TIME_panel.add(SWITCH_button);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(400, 15, 5, 30);
		TIME_panel.add(separator);
		
		
		
		DAYS_spinner = new JSpinner();
		DAYS_spinner.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		DAYS_spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		DAYS_spinner.setBounds(35, 20, 40, 20);
		TIME_panel.add(DAYS_spinner);
		
		DAYS_label = new JLabel("NGÀY");
		DAYS_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		DAYS_label.setForeground(Color.WHITE);
		DAYS_label.setHorizontalAlignment(SwingConstants.CENTER);
		DAYS_label.setBounds(80, 20, 35, 20);
		TIME_panel.add(DAYS_label);
		
		HOURS_spinner = new JSpinner();
		HOURS_spinner.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		HOURS_spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		HOURS_spinner.setBounds(125, 20, 40, 20);
		TIME_panel.add(HOURS_spinner);
		
		HOURS_label = new JLabel("GIỜ");
		HOURS_label.setHorizontalAlignment(SwingConstants.CENTER);
		HOURS_label.setForeground(Color.WHITE);
		HOURS_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		HOURS_label.setBounds(170, 20, 30, 20);
		TIME_panel.add(HOURS_label);
		
		MINUTES_spinner = new JSpinner();
		MINUTES_spinner.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		MINUTES_spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MINUTES_spinner.setBounds(210, 20, 40, 20);
		TIME_panel.add(MINUTES_spinner);
		
		MINUTES_label = new JLabel("PHÚT");
		MINUTES_label.setHorizontalAlignment(SwingConstants.CENTER);
		MINUTES_label.setForeground(Color.WHITE);
		MINUTES_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		MINUTES_label.setBounds(255, 20, 35, 20);
		TIME_panel.add(MINUTES_label);
		
		SECONDS_spinner = new JSpinner();
		SECONDS_spinner.setModel(new SpinnerNumberModel(5, 5, 999, 1));
		SECONDS_spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SECONDS_spinner.setBounds(300, 20, 40, 20);
		TIME_panel.add(SECONDS_spinner);
		
		SECONDS_label = new JLabel("GIÂY");
		SECONDS_label.setHorizontalAlignment(SwingConstants.CENTER);
		SECONDS_label.setForeground(Color.WHITE);
		SECONDS_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		SECONDS_label.setBounds(345, 20, 35, 20);
		TIME_panel.add(SECONDS_label);
		
		
		ONLY_SECONDS_spinner = new JSpinner();
		ONLY_SECONDS_spinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent event) {
				new Thread(() -> {
					int time = Integer.parseInt(ONLY_SECONDS_spinner.getValue().toString());
					String formatTime = Utils.getFormatTime(time);
					setTimePreview(formatTime);
				}).start();
			}
			@Override
			public void focusLost(FocusEvent event) {
				new Thread(() -> {
					int time = Integer.parseInt(ONLY_SECONDS_spinner.getValue().toString());
					String formatTime = Utils.getFormatTime(time);
					setTimePreview(formatTime);
				}).start();
			}
		});
		ONLY_SECONDS_spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				new Thread(() -> {
					int time = Integer.parseInt(ONLY_SECONDS_spinner.getValue().toString());
					String formatTime = Utils.getFormatTime(time);
					setTimePreview(formatTime);
				}).start();
			}
		});
		ONLY_SECONDS_spinner.setVisible(false);
		ONLY_SECONDS_spinner.setModel(new SpinnerNumberModel(5, 5, 2147483647, 1));
		ONLY_SECONDS_spinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ONLY_SECONDS_spinner.setBounds(20, 20, 90, 20);
		TIME_panel.add(ONLY_SECONDS_spinner);
		
		ONLY_SECONDS_label = new JLabel("GIÂY");
		ONLY_SECONDS_label.setVisible(false);
		ONLY_SECONDS_label.setHorizontalAlignment(SwingConstants.CENTER);
		ONLY_SECONDS_label.setForeground(Color.WHITE);
		ONLY_SECONDS_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		ONLY_SECONDS_label.setBounds(115, 20, 35, 20);
		TIME_panel.add(ONLY_SECONDS_label);
		
		ONLY_SECONDS_separator = new JSeparator();
		ONLY_SECONDS_separator.setVisible(false);
		ONLY_SECONDS_separator.setOrientation(SwingConstants.VERTICAL);
		ONLY_SECONDS_separator.setBounds(155, 15, 5, 30);
		TIME_panel.add(ONLY_SECONDS_separator);
		
		ONLY_SECONDS_time = new JLabel("Xem trước thời gian...");
		ONLY_SECONDS_time.setVisible(false);
		ONLY_SECONDS_time.setForeground(Color.WHITE);
		ONLY_SECONDS_time.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ONLY_SECONDS_time.setBounds(165, 20, 225, 20);
		TIME_panel.add(ONLY_SECONDS_time);
		
		
		
		START_STOP_button = new JLabel("BẮT ĐẦU");
		START_STOP_button.setOpaque(true);
		START_STOP_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int id = event.getModifiers();
				if(id == 16) {
					Utils.getSound("click").playWav();
					setStatusAll(starting);
					switchButton(START_STOP_button, starting);
					if(starting == true) {
						Utils.getOptionPane().addText(CONSOLE_textPane, new Color(255, 50, 50), "[" + Utils.getTime() + "] Thời gian tự động sao lưu đã dừng lại.");
						stop();
						starting = false;
					} else {
						if(correctTime == true) {
							int days = Integer.parseInt(DAYS_spinner.getValue().toString());
							int hours = Integer.parseInt(HOURS_spinner.getValue().toString());
							int minutes = Integer.parseInt(MINUTES_spinner.getValue().toString());
							int seconds = Integer.parseInt(SECONDS_spinner.getValue().toString());
							
							int time = Utils.getTimeByCorrect(days, hours, minutes, seconds);
							Utils.getFileAPI().set("backup-time", time);
							Utils.getOptionPane().addText(CONSOLE_textPane, Color.YELLOW, "[" + Utils.getTime() + "] Đã bắt đầu tự động sao lưu mỗi: " + Utils.getFormatTime(time) + ".");
							start(time);
						} else {
							int time = Integer.parseInt(ONLY_SECONDS_spinner.getValue().toString());
							Utils.getFileAPI().set("backup-time", time);
							Utils.getOptionPane().addText(CONSOLE_textPane, Color.YELLOW, "[" + Utils.getTime() + "] Đã bắt đầu tự động sao lưu mỗi: " + Utils.getFormatTime(time) + ".");
							start(time);
						}
						starting = true;
					}
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				int id = event.getModifiers();
				if(id == 16) {
					START_STOP_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(50, 50, 50), null, null));
					START_STOP_button.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				int id = event.getModifiers();
				if(id == 16) {
					START_STOP_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					START_STOP_button.setFont(new Font("Tahoma", Font.BOLD, 13));
				}
				return;
			}
		});
		START_STOP_button.setHorizontalAlignment(SwingConstants.CENTER);
		START_STOP_button.setForeground(new Color(30, 30, 30));
		START_STOP_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		START_STOP_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		START_STOP_button.setBackground(new Color(120, 255, 120));
		START_STOP_button.setBounds(170, 210, 90, 20);
		main_panel.add(START_STOP_button);
		
		GOTO_MANUALLY_button = new JLabel("THỦ CÔNG");
		GOTO_MANUALLY_button.setOpaque(true);
		GOTO_MANUALLY_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if(starting == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					Utils.getSound("switch").playWav();
					Utils.getFileAPI().set("auto-backup", false);
					Utils.closeAutoGui();
					Utils.openGui();
				}
				return;
			}
			@Override
			public void mousePressed(MouseEvent event) {
				if(starting == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					GOTO_MANUALLY_button.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(100, 100, 100), new Color(50, 50, 50), null, null));
					GOTO_MANUALLY_button.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
				return;
			}
			@Override
			public void mouseReleased(MouseEvent event) {
				if(starting == true) {
					return;
				}
				int id = event.getModifiers();
				if(id == 16) {
					GOTO_MANUALLY_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
					GOTO_MANUALLY_button.setFont(new Font("Tahoma", Font.BOLD, 13));
				}
				return;
			}
		});
		GOTO_MANUALLY_button.setHorizontalAlignment(SwingConstants.CENTER);
		GOTO_MANUALLY_button.setForeground(new Color(30, 30, 30));
		GOTO_MANUALLY_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		GOTO_MANUALLY_button.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GOTO_MANUALLY_button.setBackground(new Color(200, 200, 200));
		GOTO_MANUALLY_button.setBounds(280, 210, 90, 20);
		main_panel.add(GOTO_MANUALLY_button);
		
		
		
		JLabel COPY_RIGHT_label = new JLabel("© 2019 Copyright by [ßeñg ßeñg]");
		COPY_RIGHT_label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		COPY_RIGHT_label.setForeground(new Color(0, 255, 0, 50));
		COPY_RIGHT_label.setHorizontalAlignment(SwingConstants.CENTER);
		COPY_RIGHT_label.setBounds(20, 240, 500, 20);
		main_panel.add(COPY_RIGHT_label);
		
		
		
		JPanel CONSOLE_panel = new JPanel();
		CONSOLE_panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 255), 2, true), "CONSOLE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 255)));
		CONSOLE_panel.setBounds(5, 270, 530, 155);
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
	
	
	
	public void switchToOther() {
		SWITCH_button.setText((correctTime == true) ? "BẰNG GIÂY" : "CHUẨN");
		Utils.getOptionPane().setToolTipText(SWITCH_button, "* Chuyển đổi dạng thời gian.",
				"Nhấp để chuyển sang nhập thời gian <b>" + ((correctTime == true) ? "bằng giây" : "chuẩn") + "</b>.");
		if(correctTime == true) {
			DAYS_spinner.setVisible(true);
			DAYS_label.setVisible(true);
			HOURS_spinner.setVisible(true);
			HOURS_label.setVisible(true);
			MINUTES_spinner.setVisible(true);
			MINUTES_label.setVisible(true);
			SECONDS_spinner.setVisible(true);
			SECONDS_label.setVisible(true);
			
			ONLY_SECONDS_spinner.setVisible(false);
			ONLY_SECONDS_label.setVisible(false);
			ONLY_SECONDS_separator.setVisible(false);
			ONLY_SECONDS_time.setVisible(false);
		} else {
			DAYS_spinner.setVisible(false);
			DAYS_label.setVisible(false);
			HOURS_spinner.setVisible(false);
			HOURS_label.setVisible(false);
			MINUTES_spinner.setVisible(false);
			MINUTES_label.setVisible(false);
			SECONDS_spinner.setVisible(false);
			SECONDS_label.setVisible(false);
			
			ONLY_SECONDS_spinner.setVisible(true);
			ONLY_SECONDS_label.setVisible(true);
			ONLY_SECONDS_separator.setVisible(true);
			ONLY_SECONDS_time.setVisible(true);
		}
	}
	
	
	
	public void setStatusAll(boolean stats) {
		INPUT_button.setEnabled(stats);
		OUTPUT_button.setEnabled(stats);
		
		typeFileNameText.setEditable(stats);
		typeFileNameText.setEnabled(stats);
		
		extension_comboBox.setEnabled(stats);
		
		OVERRIDE_checkBox.setEnabled(stats);
		
		if(correctTime == true) {
			DAYS_spinner.setEnabled(stats);
			HOURS_spinner.setEnabled(stats);
			MINUTES_spinner.setEnabled(stats);
			SECONDS_spinner.setEnabled(stats);
		} else {
			ONLY_SECONDS_spinner.setEnabled(stats);
		}
		SWITCH_button.setEnabled(stats);
		
		GOTO_MANUALLY_button.setEnabled(stats);
	}
	
	public void switchButton(JLabel button, boolean stats) {
		if(stats == true) {
			START_STOP_button.setText("BẮT ĐẦU");
			START_STOP_button.setBackground(new Color(120, 255, 120));
		} else {
			START_STOP_button.setText("DỪNG LẠI");
			START_STOP_button.setBackground(new Color(255, 80, 80));
		}
	}
	
	
	
	public void start(int time) {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				backup();
			}
		}, 1000 * time, 1000 * time);
	}
	
	public void stop() {
		timer.cancel();
	}
	
	
	
	public synchronized void backup() {
		String sep = File.separator;
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
		File[] files = new File(outputPath).listFiles();
		File output = new File(outputPath + sep + (outputName
				.replaceAll("(?ium)(\\{name}|\\%name%)", input.getName())
				.replaceAll("(?ium)(\\{date}|\\%date%|\\{time}|\\%time%)", Utils.getDate())
				.replaceAll("(?ium)(\\{number}|\\%number%)", String.valueOf(files.length))) + extension_comboBox.getSelectedItem().toString());
		Path outPath = output.toPath();
		
		if(!Utils.getFileAPI().getBoolean("override-file")) {
			for(int x = 0; x < files.length; x++) {
				String name = files[x].getName();
				if(name.equals(output.getName())) {
					stop();
					int key = Message.warning("Tên file này đã tồn tại! Bạn có muốn thay thế ?");
					if(key != JOptionPane.YES_OPTION) {
						Utils.getSound("error").playWav();
						Utils.getOptionPane().addText(CONSOLE_textPane, new Color(255, 50, 50), "[" + Utils.getTime() + "] Sao lưu thất bại vì bị trùng tên file!");
						setStatusAll(starting);
						switchButton(START_STOP_button, starting);
						starting = false;
						return;
					}
					break;
				}
			}
		}
		
		Utils.getOptionPane().addText(CONSOLE_textPane, Color.ORANGE, "[" + Utils.getTime() + "] Đang tiến hành sao lưu dữ liệu...");
		
		new Thread(() -> {
			if(backuping == false) {
				backuping = true;
				Utils.getZipFile().convertToZip(inPath, outPath);
			}
			
			if(backuping == true) {
				Utils.getSound("success").playWav();
				Utils.getOptionPane().addText(CONSOLE_textPane, new Color(0, 255, 0), "[" + Utils.getTime() + "] Sao lưu dữ liệu thành công!");
				backuping = false;
			}
			
			if(!Utils.getFileAPI().getBoolean("override-file")) {
				int time = Utils.getFileAPI().getInt("backup-time");
				start(time);
			}
		}).start();
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
	
	
	
	public void setTimePreview(String time) {
		ONLY_SECONDS_time.setText(time);
	}
	
	
	
	private void updateField() {
		String input = Utils.getFileAPI().getString("input-path");
		String output = Utils.getFileAPI().getString("output-path");
		String fileName = Utils.getFileAPI().getString("file-name");
		boolean override = Utils.getFileAPI().getBoolean("override-file");
		
		INPUT_textField.setText(input);
		OUTPUT_textField.setText(output);
		typeFileNameText.setText(fileName);
		OVERRIDE_checkBox.setSelected(override);
	}
	
	
	
	public void doRainbow(JLabel label) {
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
	
	public int getRandomInt(int min, int max) {
		return (new Random().nextInt((max - min) + 1) + min);
	}
}
