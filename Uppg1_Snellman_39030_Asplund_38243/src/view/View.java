package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.Model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JTextArea;


public class View extends JFrame implements ActionListener {
	
	private Model model;
	private SearchListener searchListener;
	private JTextField ticker1;
	private JTextField ticker2;
	private JTextField date1;
	private JTextField date2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea textArea;


	/**
	 * Create the application.
	 * @param model 
	 */
	public View(Model model) {
		this.model = model;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("57ock 15l4nd");
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 560);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel SearchPanel = new JPanel();
		SearchPanel.setBounds(0, 0, 784, 100);
		panel.add(SearchPanel);
		SearchPanel.setForeground(Color.GRAY);
		SearchPanel.setBorder(new LineBorder(Color.DARK_GRAY));
		SearchPanel.setLayout(null);
		
		JLabel ticker1Label = new JLabel("Ticker 1:");
		ticker1Label.setBounds(10, 8, 54, 14);
		SearchPanel.add(ticker1Label);
		ticker1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		ticker1Label.setVerticalAlignment(SwingConstants.TOP);
		
		ticker1 = new JTextField();
		ticker1.setBounds(74, 5, 86, 20);
		SearchPanel.add(ticker1);
		ticker1.setColumns(10);
		
		JLabel startDateLabel = new JLabel("Start Date:");
		startDateLabel.setBounds(174, 8, 54, 14);
		SearchPanel.add(startDateLabel);
		startDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		date1 = new JTextField();
		date1.setBounds(231, 5, 86, 20);
		SearchPanel.add(date1);
		date1.setColumns(10);
		
		JLabel ticker2Label = new JLabel("Ticker 2:");
		ticker2Label.setBounds(10, 33, 54, 14);
		SearchPanel.add(ticker2Label);
		ticker2Label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		ticker2 = new JTextField();
		ticker2.setBounds(74, 30, 86, 20);
		SearchPanel.add(ticker2);
		ticker2.setColumns(10);
		
		JLabel endDateLabel = new JLabel("End Date:");
		endDateLabel.setBounds(180, 33, 48, 14);
		SearchPanel.add(endDateLabel);
		endDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		date2 = new JTextField();
		date2.setBounds(231, 30, 86, 20);
		SearchPanel.add(date2);
		date2.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		searchButton.setBounds(74, 57, 86, 23);
		SearchPanel.add(searchButton);
		
		JRadioButton radioSEK = new JRadioButton("SEK");
		radioSEK.setBounds(360, 25, 109, 23);
		SearchPanel.add(radioSEK);
		radioSEK.addActionListener(this);
		buttonGroup.add(radioSEK);
		
		JRadioButton radioEUR = new JRadioButton("EUR");
		radioEUR.setBounds(360, 45, 109, 23);
		SearchPanel.add(radioEUR);
		radioEUR.addActionListener(this);
		buttonGroup.add(radioEUR);
		
		JRadioButton radioUSD = new JRadioButton("USD");
		radioUSD.setBounds(360, 4, 109, 23);
		SearchPanel.add(radioUSD);
		radioUSD.addActionListener(this);
		buttonGroup.add(radioUSD);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 99, 784, 461);
		panel.add(tabbedPane);
		
		JPanel graphPanel = new JPanel();
		graphPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Graph", null, graphPanel, null);
		GridBagLayout gbl_graphPanel = new GridBagLayout();
		gbl_graphPanel.columnWidths = new int[]{779, 0};
		gbl_graphPanel.rowHeights = new int[]{433, 0};
		gbl_graphPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_graphPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		graphPanel.setLayout(gbl_graphPanel);
		
		JPanel tablePanel = new JPanel();
		tabbedPane.addTab("Table", null, tablePanel, null);
		GridBagLayout gbl_tablePanel = new GridBagLayout();
		gbl_tablePanel.columnWidths = new int[]{390, 0};
		gbl_tablePanel.rowHeights = new int[]{398, 0};
		gbl_tablePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_tablePanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		tablePanel.setLayout(gbl_tablePanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		tablePanel.add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		textArea.setEditable(false);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.getActionCommand());
		String t1 = ticker1.getText();
		String t2 = ticker2.getText();
		String dtFrom = date1.getText();
		String dtTo = date2.getText();
		
		SearchFormEvent ev = new SearchFormEvent(t1, t2, dtFrom, dtTo);
		fireSearchEvent(ev);
		
	}
	
	private void fireSearchEvent(SearchFormEvent ev) {
		if (searchListener != null) {
			searchListener.searchPerformed(ev);
		}
	}

	public void setSearchListener(SearchListener listener) {
		this.searchListener = listener;
	}
	
	public void setText(String s) {
		textArea.setText(s);
	}
}
