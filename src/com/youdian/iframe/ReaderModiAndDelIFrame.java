package com.youdian.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.youdian.dao.Dao;
import com.youdian.model.Reader;
import com.youdian.util.CreatecdIcon;
import com.youdian.util.MyDocument;

public class ReaderModiAndDelIFrame extends JInternalFrame {


	private JTextField keepmoney;
	private ButtonGroup buttonGroup = new ButtonGroup();//�Ա�ѡ��ť
	private JTable table;
	private JTextField readerCode;//���߱��
	private JTextField job;//ְҵ
	private JTextField tel;//�绰
	private JTextField vipDate;//��Ա֤��Ч����
	private JTextField maxborrow;//��������
	private JTextField bztime;//��֤����
	private JTextField identityCard;//֤������
	private JComboBox comboBox;
	private JTextField age;
	private JTextField readername;//��������
	private JRadioButton JRadioButton1;
	private JRadioButton JRadioButton2;
	private String[] columnNames={ "��������", "�����Ա�", "��������", "֤������", "��Ա֤��Ч����",
			"��������", "�绰","Ѻ��","֤��","ְҵ","���߱��","���߰�֤ʱ��" };
	private String[] array=new String[]{"���֤","����֤","ѧ��֤","����֤"};
	String id;
	

	/**
	 * Create the frame
	 */
	private Object[][] getFileStates(List list){
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			Reader reader=(Reader)list.get(i);
			//results[i][0]=reader.getId();
			results[i][0]=reader.getReadername();
			String gender;
			if(reader.getGender().equals("1")){
				gender="��";
			}else{
				gender="Ů";
			}
			results[i][1]=gender;
			
			results[i][2]=new Integer(reader.getAge());
			results[i][3]=reader.getIdentityCard();
			results[i][4]=reader.getVipDate();
			results[i][5]=new Integer(reader.getMaxborrow());
			results[i][6]=reader.getTel();
			results[i][7]=reader.getKeepMoney();
			//results[i][8]=array[reader.getReaderCode()];
			results[i][8]=reader.getReadercode();
			results[i][9]=reader.getJob();
			results[i][10]=reader.getReadercode();
			results[i][11]=reader.getBztime();
		}
		return results;
	         		
	}
	public ReaderModiAndDelIFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("������Ϣ�޸���ɾ��");
		setBounds(100, 100, 600, 420);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 80));
		getContentPane().add(panel, BorderLayout.NORTH);

		final JLabel logoLabel = new JLabel();
		ImageIcon readerModiAndDelIcon=CreatecdIcon.add("readerModiAndDel.jpg");
		logoLabel.setIcon(readerModiAndDelIcon);
		logoLabel.setBackground(Color.CYAN);
		logoLabel.setOpaque(true);
		logoLabel.setPreferredSize(new Dimension(400, 80));
		panel.add(logoLabel);
		logoLabel.setText("������Ϣ�޸�logo��400*80��");

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		getContentPane().add(panel_1);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0, 100));
		panel_1.add(scrollPane, BorderLayout.NORTH);

		
		final DefaultTableModel model=new DefaultTableModel();
		Object[][] results=getFileStates(Dao.selectReader());
		model.setDataVector(results,columnNames);
		
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableListener());

		final JPanel panel_2 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(9);
		panel_2.setLayout(gridLayout);
		panel_2.setPreferredSize(new Dimension(0, 200));
		panel_1.add(panel_2, BorderLayout.SOUTH);

		final JLabel label_1 = new JLabel();
		label_1.setText("  ��    ����");
		panel_2.add(label_1);

		readername = new JTextField();
		readername.setDocument(new MyDocument(10));
		panel_2.add(readername);

		final JLabel label_2 = new JLabel();
		label_2.setText("  ��    ��");
		panel_2.add(label_2);

		final JPanel panel_3 = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setVgap(0);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3);

		JRadioButton1 = new JRadioButton();
		JRadioButton1.setSelected(true);
		buttonGroup.add(JRadioButton1);
		panel_3.add(JRadioButton1);
		JRadioButton1.setText("��");

		JRadioButton2 = new JRadioButton();
		buttonGroup.add(JRadioButton2);
		panel_3.add(JRadioButton2);
		JRadioButton2.setText("Ů");

		final JLabel label_3 = new JLabel();
		label_3.setText("  ��    �䣺");
		panel_2.add(label_3);

		age = new JTextField();
		age.setDocument(new MyDocument(2));
		age.addKeyListener(new NumberListener());
		panel_2.add(age);

		final JLabel label_5 = new JLabel();
		label_5.setText("  ְ    ҵ��");
		panel_2.add(label_5);

		job = new JTextField();
		job.setDocument(new MyDocument(30));
		panel_2.add(job);

		final JLabel label = new JLabel();
		label.setText("  ��Ч֤����");
		panel_2.add(label);

		comboBox = new JComboBox();

		
		comboBox.setModel(new DefaultComboBoxModel(array));
		for(int i=1;i<array.length;i++){
			comboBox.setSelectedIndex(i);
			comboBox.setSelectedItem(array);
		}
		panel_2.add(comboBox);

		final JLabel label_6 = new JLabel();
		label_6.setText("  ֤�����룺");
		panel_2.add(label_6);

		identityCard = new JTextField();
		identityCard.setDocument(new MyDocument(13));
		identityCard.addKeyListener(new NumberListener());
		panel_2.add(identityCard);

		final JLabel label_7 = new JLabel();
		label_7.setText("  ��֤���ڣ�");
		panel_2.add(label_7);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");

		bztime = new JFormattedTextField(myfmt.getDateInstance());
		
		panel_2.add(bztime);

		final JLabel label_9 = new JLabel();
		label_9.setText("  ����������");
		panel_2.add(label_9);

		maxborrow = new JTextField();
		maxborrow.addKeyListener(new NumberListener());
		panel_2.add(maxborrow);

		final JLabel label_13 = new JLabel();
		label_13.setText("  ��Ա֤��Ч���ڣ�");
		panel_2.add(label_13);

		vipDate = new JFormattedTextField(myfmt.getDateInstance());
		
		panel_2.add(vipDate);

		final JLabel label_8 = new JLabel();
		label_8.setText("  ��    ����");
		panel_2.add(label_8);

		tel = new JFormattedTextField();
		tel.addKeyListener(new TelListener());
		tel.setDocument(new MyDocument(11));
		panel_2.add(tel);

		final JLabel label_14 = new JLabel();
		label_14.setText("  Ѻ    ��");
		panel_2.add(label_14);

		keepmoney = new JTextField();
		keepmoney.addKeyListener(new KeepmoneyListener());
		panel_2.add(keepmoney);

		final JLabel label_4 = new JLabel();
		label_4.setText("  ���߱�ţ�");
		panel_2.add(label_4);

		readerCode = new JTextField();
		readerCode.setEditable(false);
		readerCode.setDocument(new MyDocument(13));
		panel_2.add(readerCode);

		final JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(0, 0));
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(4);
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4);

		final JButton button = new JButton();
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_4.add(button);
		button.setText("�޸�");
		button.addActionListener(new ModiButtonListener(model));
		
		
		

		final JButton buttonDel = new JButton();
		panel_4.add(buttonDel);
		buttonDel.setText("ɾ��");
		buttonDel.addActionListener(new DelButtonListener(model));
		setVisible(true);
		//
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			//id=table.getValueAt(selRow, 0).toString().trim();
			readername.setText(table.getValueAt(selRow, 0).toString().trim());
			if(table.getValueAt(selRow, 1).toString().trim().equals("��"))
				JRadioButton1.setSelected(true);
			else
				JRadioButton2.setSelected(true);
			age.setText(table.getValueAt(selRow, 2).toString().trim());
			identityCard.setText(table.getValueAt(selRow, 3).toString().trim());
			vipDate.setText(table.getValueAt(selRow, 4).toString().trim());
			maxborrow.setText(table.getValueAt(selRow, 5).toString().trim());
			tel.setText(table.getValueAt(selRow, 6).toString().trim());
			keepmoney.setText(table.getValueAt(selRow, 7).toString().trim());
			comboBox.setSelectedItem(table.getValueAt(selRow, 8).toString().trim());
			job.setText(table.getValueAt(selRow, 9).toString().trim());
			readerCode.setText(table.getValueAt(selRow, 10).toString().trim());
			bztime.setText(table.getValueAt(selRow, 11).toString().trim());
			
		}
	}
	final class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	private final class DelButtonListener implements ActionListener {
		private final DefaultTableModel model;

		private DelButtonListener(DefaultTableModel model) {
			this.model = model;
		}

		public void actionPerformed(final ActionEvent e) {
			int i=Dao.DelReader(readerCode.getText().trim());
			if(i==1){
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				Object[][] results=getFileStates(Dao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	class ModiButtonListener implements ActionListener {
		private final DefaultTableModel model;

		ModiButtonListener(DefaultTableModel model) {
			this.model = model;
		}

		public void actionPerformed(final ActionEvent e) {
			if(readername.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if(age.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			
			if(identityCard.getText().length()==0){
				JOptionPane.showMessageDialog(null, "֤�������ı��򲻿�Ϊ��");
				return;
			}
			if(keepmoney.getText().length()==0){
				JOptionPane.showMessageDialog(null, "Ѻ���ı��򲻿�Ϊ��");
				return;
			}
			if(job.getText().length()==0){
				JOptionPane.showMessageDialog(null, "ְҵ�ı��򲻿�Ϊ��");
				return;
			}
			if(readerCode.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���߱�Ų���Ϊ��");
				return;
			}
			if(readerCode.getText().length()!=13){
				JOptionPane.showMessageDialog(null, "���߱��Ϊ13λ");
				return;
			}
			if(bztime.getText().length()==0){
				JOptionPane.showMessageDialog(null, "��֤ʱ���ı��򲻿�Ϊ��");
				return;
			}
			if(tel.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�绰�����ı��򲻿�Ϊ��");
				return;
			}
			if(tel.getText().length()>11||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "�绰����λ��С��11λ");
				return;
			}
			if(maxborrow.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if(maxborrow.getText().length()>2||tel.getText().length()<0){
				JOptionPane.showMessageDialog(null, "��������Ϊ��λ����");
				return;
			}
			String gender="1";
			if(!JRadioButton1.isSelected()){
				gender="2";}
			String zj=String.valueOf(comboBox.getSelectedIndex());
			System.out.println(comboBox.getSelectedIndex());
			
			//int i=Dao.UpdateReader(id, readername.getText().trim(), sex, age.getText().trim(), zjnumber.getText().trim(), Date.valueOf(date.getText().trim()), maxnumber.getText().trim(), tel.getText().trim(), Double.valueOf(keepmoney.getText().trim()), zj, zy.getText().trim(), Date.valueOf(bztime.getText().trim()), ISBN.getText().trim());
			int i=Dao.UpdateReader(readername.getText().trim(), gender, Integer.parseInt(age.getText().trim()), job.getText().trim(), identityCard.getText().trim(), vipDate.getText().trim(), tel.getText().trim(), Integer.parseInt(maxborrow.getText().trim()), keepmoney.getText().trim(), bztime.getText().trim(), readerCode.getText().trim());

			System.out.println(i);
			if(i==1){
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				Object[][] results=getFileStates(Dao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	class TelListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789-"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	class KeepmoneyListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;//ֻ���������������˸��
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
			if(keepmoney.getText().length()>2||keepmoney.getText().length()<0){
				e.consume();
			}
		}
	}

}
