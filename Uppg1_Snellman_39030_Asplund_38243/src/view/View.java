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
import java.awt.Insets;


public class View extends JFrame implements ActionListener {
	
	private Model model;
	private SearchListener searchListener;
	private CurrencyListener currencyListener;
	private JTextField ticker1;
	private JTextField ticker2;
	private JTextField date1;
	private JTextField date2;
	private final ButtonGroup buttonGroup = new ButtonGroup();


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
		
		JPanel tablePAnel = new JPanel();
		tabbedPane.addTab("Table", null, tablePAnel, null);
		GridBagLayout gbl_tablePAnel = new GridBagLayout();
		gbl_tablePAnel.columnWidths = new int[]{0, 0, 0};
		gbl_tablePAnel.rowHeights = new int[]{0, 0, 0};
		gbl_tablePAnel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_tablePAnel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		tablePAnel.setLayout(gbl_tablePAnel);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		tablePAnel.add(list, gbc_list);
		
		JScrollBar scrollBar = new JScrollBar();
		GridBagConstraints gbc_scrollBar = new GridBagConstraints();
		gbc_scrollBar.fill = GridBagConstraints.VERTICAL;
		gbc_scrollBar.gridx = 1;
		gbc_scrollBar.gridy = 0;
		tablePAnel.add(scrollBar, gbc_scrollBar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Search")) {
			String t1 = ticker1.getText();
			String t2 = ticker2.getText();
			String dtFrom = date1.getText();
			String dtTo = date2.getText();
			
			SearchFormEvent ev = new SearchFormEvent(t1, t2, dtFrom, dtTo);
			fireSearchEvent(ev);
		}else if (e.getActionCommand().equals("EUR") || 
					e.getActionCommand().equals("USD") ||
					e.getActionCommand().equals("SEK")) {
			String currency = e.getActionCommand();
			String dF = date1.getText();
			String dT = date2.getText();
			
			CurrencyChangeEvent ev = new CurrencyChangeEvent(currency, dF, dT);
			fireCurrencyChangeEvent(ev);
		}
		
	}
	
	private void fireCurrencyChangeEvent(CurrencyChangeEvent ev) {
		if (currencyListener != null) {
			currencyListener.currencySwitch(ev);
		}
	}

	private void fireSearchEvent(SearchFormEvent ev) {
		if (searchListener != null) {
			searchListener.searchPerformed(ev);
		}
	}

	public void setCurrencyListener(CurrencyListener listener) {
		this.currencyListener = listener;
	}
	
	public void setSearchListener(SearchListener listener) {
		this.searchListener = listener;
	}
}
