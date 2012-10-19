/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.ui;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SimpleSearch extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu FileMenu = null;

	private JMenu Exit = null;

	private JMenu Exit0 = null;

	private JScrollPane ResultPane = null;

	private JScrollPane NodeInfo = null;

	public JTextField Url = null;

	private JButton executeUrl = null;

	public JTextField searchProcess = null;

	private JButton searchButton = null;

	private JTextArea nodeInfoTextArea = null;

	private JTextArea resultTextArea = null;

	private JScrollPane pagerankPane = null;

	private JTextArea pagerankTextArea = null;

	private Action act = null;

	public SimpleSearch() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(1024, 700);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Simple Search");

		act = new Action(this);
	}

	public static void main(String args[]) {
		SimpleSearch ss = new SimpleSearch();
		ss.setVisible(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getResultPane(), null);
			jContentPane.add(getNodeInfo(), null);
			jContentPane.add(getUrl(), null);
			jContentPane.add(getExecuteUrl(), null);
			jContentPane.add(getSearchProcess(), null);
			jContentPane.add(getSearchButton(), null);
			jContentPane.add(getPagerankPane(), null);
		}
		return jContentPane;
	}

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getExit());
		}
		return jJMenuBar;
	}

	private JMenu getFileMenu() {
		if (FileMenu == null) {
			FileMenu = new JMenu();
			FileMenu.setText("File");
		}
		return FileMenu;
	}

	private JMenu getExit() {
		if (Exit == null) {
			Exit = new JMenu();
			Exit.setText("Exit");
			Exit.add(getExit0());
		}
		return Exit;
	}

	private JMenu getExit0() {
		if (Exit0 == null) {
			Exit0 = new JMenu();
			Exit0.setText("Exit");
		}
		return Exit0;
	}

	private JScrollPane getResultPane() {
		if (ResultPane == null) {
			ResultPane = new JScrollPane();
			ResultPane.setBounds(new Rectangle(3, 345, 1009, 280));
			ResultPane.setViewportView(getResultTextArea());
		}
		return ResultPane;
	}

	private JScrollPane getNodeInfo() {
		if (NodeInfo == null) {
			NodeInfo = new JScrollPane();
			NodeInfo.setBounds(new Rectangle(485, 14, 525, 324));
			NodeInfo.setViewportView(getNodeInfoTextArea());
		}
		return NodeInfo;
	}

	private JTextField getUrl() {
		if (Url == null) {
			Url = new JTextField();
			Url.setBounds(new Rectangle(10, 14, 383, 28));
			Url.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					//
				}
			});
			Url
					.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
						public void propertyChange(
								java.beans.PropertyChangeEvent e) {
							if ((e.getPropertyName().equals("enabled"))) {
								// 
							}
						}
					});
		}
		return Url;
	}

	private JButton getExecuteUrl() {
		if (executeUrl == null) {
			executeUrl = new JButton();
			executeUrl.setBounds(new Rectangle(396, 14, 87, 28));
			executeUrl.setText("process");
			executeUrl.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						act.process();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return executeUrl;
	}

	private JTextField getSearchProcess() {
		if (searchProcess == null) {
			searchProcess = new JTextField();
			searchProcess.setBounds(new Rectangle(10, 56, 378, 28));
			searchProcess.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					//
				}
			});
		}
		return searchProcess;
	}

	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setBounds(new Rectangle(397, 56, 84, 26));
			searchButton.setText("Search");
			searchButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					act.search();
				}
			});
		}
		return searchButton;
	}

	private JTextArea getNodeInfoTextArea() {
		if (nodeInfoTextArea == null) {
			nodeInfoTextArea = new JTextArea();
			nodeInfoTextArea.setEnabled(false);
			nodeInfoTextArea.setLineWrap(true);
			nodeInfoTextArea.setWrapStyleWord(true);
		}
		return nodeInfoTextArea;
	}

	public void print1(String text) {
		nodeInfoTextArea.append(text + "\n");
		nodeInfoTextArea.setCaretPosition(nodeInfoTextArea.getText().length());
	}

	public void print2(String text) {
		resultTextArea.append(text + "\n");
		resultTextArea.setCaretPosition(resultTextArea.getText().length());
	}

	public void print3(String text) {
		pagerankTextArea.append(text + "\n");
		pagerankTextArea.setCaretPosition(pagerankTextArea.getText().length());
	}

	private JTextArea getResultTextArea() {
		if (resultTextArea == null) {
			resultTextArea = new JTextArea();
			resultTextArea.setEnabled(false);
			resultTextArea.setLineWrap(true);
			resultTextArea.setWrapStyleWord(true);
		}
		return resultTextArea;
	}

	private JScrollPane getPagerankPane() {
		if (pagerankPane == null) {
			pagerankPane = new JScrollPane();
			pagerankPane.setBounds(new Rectangle(3, 91, 477, 246));
			pagerankPane.setViewportView(getPagerankTextArea());
		}
		return pagerankPane;
	}

	private JTextArea getPagerankTextArea() {
		if (pagerankTextArea == null) {
			pagerankTextArea = new JTextArea();
			pagerankTextArea.setEnabled(false);
			pagerankTextArea.setLineWrap(true);
			pagerankTextArea.setWrapStyleWord(true);
		}
		return pagerankTextArea;
	}

} 
