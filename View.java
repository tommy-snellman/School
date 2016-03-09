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
	private CurrencyListener currencyListener;
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
		this.setVisible(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{10, 251, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setForeground(Color.GRAY);
		searchPanel.setBorder(new LineBorder(Color.DARK_GRAY));
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchPanel.gridx = 0;
		gbc_searchPanel.gridy = 0;
		this.getContentPane().add(searchPanel, gbc_searchPanel);
		GridBagLayout gbl_searchPanel = new GridBagLayout();
		gbl_searchPanel.columnWidths = new int[] {80, 80, 100, 90, 100};
		gbl_searchPanel.rowHeights = new int[] {14, 14, 20, 30};
		gbl_searchPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_searchPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		searchPanel.setLayout(gbl_searchPanel);
		
		JLabel ticker1Label = new JLabel("Ticker 1:");
		GridBagConstraints gbc_ticker1Label = new GridBagConstraints();
		gbc_ticker1Label.anchor = GridBagConstraints.EAST;
		gbc_ticker1Label.insets = new Insets(0, 0, 5, 5);
		gbc_ticker1Label.gridx = 0;
		gbc_ticker1Label.gridy = 0;
		searchPanel.add(ticker1Label, gbc_ticker1Label);
		ticker1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		ticker1Label.setVerticalAlignment(SwingConstants.TOP);
		
		ticker1 = new JTextField();
		GridBagConstraints gbc_ticker1 = new GridBagConstraints();
		gbc_ticker1.fill = GridBagConstraints.HORIZONTAL;
		gbc_ticker1.insets = new Insets(0, 0, 5, 5);
		gbc_ticker1.gridx = 1;
		gbc_ticker1.gridy = 0;
		searchPanel.add(ticker1, gbc_ticker1);
		ticker1.setColumns(10);
		
		JLabel startDateLabel = new JLabel("Start Date:");
		GridBagConstraints gbc_startDateLabel = new GridBagConstraints();
		gbc_startDateLabel.anchor = GridBagConstraints.EAST;
		gbc_startDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_startDateLabel.gridx = 2;
		gbc_startDateLabel.gridy = 0;
		searchPanel.add(startDateLabel, gbc_startDateLabel);
		startDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		date1 = new JTextField();
		GridBagConstraints gbc_date1 = new GridBagConstraints();
		gbc_date1.fill = GridBagConstraints.HORIZONTAL;
		gbc_date1.insets = new Insets(0, 0, 5, 5);
		gbc_date1.gridx = 3;
		gbc_date1.gridy = 0;
		searchPanel.add(date1, gbc_date1);
		date1.setColumns(10);
		
		JRadioButton radioUSD = new JRadioButton("USD");
		GridBagConstraints gbc_radioUSD = new GridBagConstraints();
		gbc_radioUSD.anchor = GridBagConstraints.NORTH;
		gbc_radioUSD.fill = GridBagConstraints.HORIZONTAL;
		gbc_radioUSD.insets = new Insets(0, 0, 5, 0);
		gbc_radioUSD.gridx = 4;
		gbc_radioUSD.gridy = 0;
		searchPanel.add(radioUSD, gbc_radioUSD);
		radioUSD.addActionListener(this);
		buttonGroup.add(radioUSD);
		
		JLabel ticker2Label = new JLabel("Ticker 2:");
		GridBagConstraints gbc_ticker2Label = new GridBagConstraints();
		gbc_ticker2Label.anchor = GridBagConstraints.EAST;
		gbc_ticker2Label.insets = new Insets(0, 0, 5, 5);
		gbc_ticker2Label.gridx = 0;
		gbc_ticker2Label.gridy = 1;
		searchPanel.add(ticker2Label, gbc_ticker2Label);
		ticker2Label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		ticker2 = new JTextField();
		GridBagConstraints gbc_ticker2 = new GridBagConstraints();
		gbc_ticker2.fill = GridBagConstraints.HORIZONTAL;
		gbc_ticker2.anchor = GridBagConstraints.NORTH;
		gbc_ticker2.insets = new Insets(0, 0, 5, 5);
		gbc_ticker2.gridx = 1;
		gbc_ticker2.gridy = 1;
		searchPanel.add(ticker2, gbc_ticker2);
		ticker2.setColumns(10);
		
		JLabel endDateLabel = new JLabel("End Date:");
		GridBagConstraints gbc_endDateLabel = new GridBagConstraints();
		gbc_endDateLabel.anchor = GridBagConstraints.EAST;
		gbc_endDateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_endDateLabel.gridx = 2;
		gbc_endDateLabel.gridy = 1;
		searchPanel.add(endDateLabel, gbc_endDateLabel);
		endDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		date2 = new JTextField();
		GridBagConstraints gbc_date2 = new GridBagConstraints();
		gbc_date2.fill = GridBagConstraints.HORIZONTAL;
		gbc_date2.insets = new Insets(0, 0, 5, 5);
		gbc_date2.gridx = 3;
		gbc_date2.gridy = 1;
		searchPanel.add(date2, gbc_date2);
		date2.setColumns(10);
		
		JRadioButton radioSEK = new JRadioButton("SEK");
		GridBagConstraints gbc_radioSEK = new GridBagConstraints();
		gbc_radioSEK.anchor = GridBagConstraints.NORTH;
		gbc_radioSEK.fill = GridBagConstraints.HORIZONTAL;
		gbc_radioSEK.gridx = 4;
		gbc_radioSEK.gridy = 1;
		searchPanel.add(radioSEK, gbc_radioSEK);
		radioSEK.addActionListener(this);
		buttonGroup.add(radioSEK);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(this);
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchButton.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton.gridx = 1;
		gbc_searchButton.gridy = 2;
		searchPanel.add(searchButton, gbc_searchButton);
		
		JRadioButton radioEUR = new JRadioButton("EUR");
		GridBagConstraints gbc_radioEUR = new GridBagConstraints();
		gbc_radioEUR.anchor = GridBagConstraints.NORTH;
		gbc_radioEUR.fill = GridBagConstraints.HORIZONTAL;
		gbc_radioEUR.gridx = 4;
		gbc_radioEUR.gridy = 2;
		searchPanel.add(radioEUR, gbc_radioEUR);
		radioEUR.addActionListener(this);
		buttonGroup.add(radioEUR);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.anchor = GridBagConstraints.SOUTH;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		this.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
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
		textArea.setEditable(false);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			String t1 = ticker1.getText();
			String t2 = ticker2.getText();
			String dtFrom = date1.getText();
			String dtTo = date2.getText();
			SearchFormEvent ev = new SearchFormEvent(t1, t2, dtFrom, dtTo);
			fireSearchEvent(ev);
		} else if (e.getActionCommand().equals("USD") || e.getActionCommand().equals("SEK")
				|| e.getActionCommand().equals("EUR")) {
			String curr = e.getActionCommand();
			CurrencyChangeEvent ev = new CurrencyChangeEvent(curr);
			fireCurrencyChangeEvent(ev);
		}

	}
	
	private void fireSearchEvent(SearchFormEvent ev) {
		if (searchListener != null) {
			searchListener.searchPerformed(ev);
		}
	}

	private void fireCurrencyChangeEvent(CurrencyChangeEvent ev) {
		if (currencyListener != null) {
			currencyListener.currencySwitch(ev);
		}
	}

	public void setCurrencyListener(CurrencyListener listener) {
		this.currencyListener = listener;
	}

	public void setSearchListener(SearchListener listener) {
		this.searchListener = listener;
	}
	
	public void setText(String s) {
		textArea.setText(s);
	}
}
