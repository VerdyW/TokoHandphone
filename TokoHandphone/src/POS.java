import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class POS extends JFrame implements ActionListener, MouseListener{

		Vector<Handphone> handphone = new Vector<>();
		Vector<Transaksi> transaksi = new Vector<>();
		Vector<DetailTransaksi> detailTransaksi = new Vector<>();
	
		JFrame FSalesEntry = new JFrame();
	
		JMenuBar menuBar = new JMenuBar();
		JMenu sales, inventory;
		JMenuItem salesEntry, salesReport, handphoneStock, supplierMenu;
		DefaultTableModel dtm, dtmCart;
		JTable tableSalesEntry, tableCart;
		JScrollPane scrollPaneSalesEntry, scrollPaneCart;
		JLabel lblListHP, lblCart, lblQuantity, lblSalesEntry, lblMemberID;
		JTextField txtQuantity, txtMemberID;
		JButton buttonAddToCart, buttonHelp;
		JPanel pnlLeftSE, pnlRightSE, pnlQntySE, pnlMemberIDSE;
		
		JFrame FStockHP = new JFrame();
		JLabel lblStockHP;
		JTable tableStockHP;								
		DefaultTableModel dtmStockHP;
		JScrollPane spStockHp;
		JPanel pnlTableInventory;
		
		Vector<Supplier> supplier = new Vector<>();
		JFrame FSupplier = new JFrame();
		JLabel lblSupplier;
		JTable tableSupplier;
		DefaultTableModel dtmSupplier;
		JScrollPane spSupplier;
		
		JLabel lblCakeTH, lblTH, lblSelectedID, lblTotalTH;
		JTextField txtSelectedID, txtTotalTH;
		JTable tableHeaderTH, tableDetailTH;
		JButton backToMenuTH;
		JScrollPane scrollPaneHeader, scrollPaneDetail ;
		JPanel pnlTableHeaderTH, pnlTableDetailTH;
		String userID;
		DefaultTableModel dtmHeader;
		JFrame TransactionHistory = new JFrame();
		
		public Vector<Supplier> getAllSupplier(){
			
			
			PreparedStatement ps = Connect.getConnection().prepareStatement(
					"SELECT * FROM handphone"
					+ " INNER JOIN supplier on supplier.IDSupplier = handphone.IDSupplier "
				
				);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String merekHandphone = rs.getString("MerekHandphone");
					String typeHandphone = rs.getString("TypeHandphone");
					String namaSupplier = rs.getString("NamaSupplier");
					String alamatSupplier = rs.getString("AlamatSupplier");
					supplier.add(new Supplier(merekHandphone, typeHandphone, namaSupplier, alamatSupplier));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			return supplier;
		}
		
		public Vector<Handphone> getAllHandphone(){
			
			
			PreparedStatement ps = Connect.getConnection().prepareStatement(
					"SELECT * FROM handphone"
				
				);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String merekHandphone = rs.getString("MerekHandphone");
					String typeHandphone = rs.getString("TypeHandphone");
					String harga = rs.getString("Harga");
					String stock = rs.getString("Stock");
					handphone.add(new Handphone(merekHandphone, typeHandphone, harga, stock));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			return handphone;
		}
		
		public Vector<Transaksi> getAllTransaction(){
			
			
			PreparedStatement ps = Connect.getConnection().prepareStatement(
					"SELECT * FROM transaksi"
				
				);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String idTransaksi = rs.getString("IDTransaksi");
					String idMember = rs.getString("IDMember");
					String tanggalTransaksi = rs.getString("TanggalTransaksi");
					transaksi.add(new Transaksi(idTransaksi, idMember, tanggalTransaksi));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			return transaksi;
		}
		
		public Vector<DetailTransaksi> getAllTransactionDetail(String TransactionID){
			
			
			PreparedStatement ps = Connect.getConnection().prepareStatement(
					"SELECT * FROM detailtransaksi WHERE IDTransaksi = '" + TransactionID + "'"
				
				);
			try {
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					String idTransaksi = rs.getString("IDTransaksi");
					String idHandphone = rs.getString("IDHandphone");
					String quantity = rs.getString("Quantity");
					String harga = rs.getString("TotalHarga");
					detailTransaksi.add(new DetailTransaksi(idTransaksi, idHandphone, quantity, harga));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			return detailTransaksi;
		}
		
		
		
		
		void setupTableSalesEntry() {
			Vector<Object> headerTable, dataTable;
			Vector<Handphone>  viewCake = getAllHandphone();

			headerTable = new Vector<>();
			headerTable.add("Merek Handphone");
			headerTable.add("Type Handphone");
			headerTable.add("Harga");
			
			dtm = new DefaultTableModel(headerTable, 0);
			
			
			for (Handphone handphone : viewCake) {
				dataTable = new Vector<>();
				dataTable.add(handphone.getMerekHandphone());
				dataTable.add(handphone.getTypeHandphone());
				dataTable.add(handphone.getHarga());
				dtm.addRow(dataTable);
			}
			
			tableSalesEntry = new JTable(dtm);
			tableSalesEntry.setSize(250,250);
			tableSalesEntry.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
			tableSalesEntry.setFont(new Font("Arial", Font.PLAIN, 16));
			tableSalesEntry.setRowHeight(35);
			scrollPaneSalesEntry = new JScrollPane(tableSalesEntry);

		}
		
		void setupTableSupplier() { 
			Vector<Object> headerTable, dataTable;
			Vector<Supplier>  viewSupplier = getAllSupplier();

			headerTable = new Vector<>();
			headerTable.add("Merek Handphone");
			headerTable.add("Type Handphone");
			headerTable.add("Nama Supplier");
			headerTable.add("Alamat Supplier");
			
			dtmSupplier = new DefaultTableModel(headerTable, 0);
			
			
			for (Supplier supplier : viewSupplier) {
				dataTable = new Vector<>();
				dataTable.add(supplier.getMerekHandphone());
				dataTable.add(supplier.getTypeHandphone());
				dataTable.add(supplier.getNamaSupplier());
				dataTable.add(supplier.getAlamatSupplier());
				dtmSupplier.addRow(dataTable);
			}
			
			tableSupplier = new JTable(dtmSupplier);
			tableSupplier.setSize(250,250);
			tableSupplier.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
			tableSupplier.setFont(new Font("Arial", Font.PLAIN, 16));
			tableSupplier.setRowHeight(35);
			spSupplier = new JScrollPane(tableSupplier);

		}
	
		void setupTableTransactionHeader() {
			Vector<Object> headerTable, dataTable = null;
			

			headerTable = new Vector<>();
			headerTable.add("Transaction ID");
			headerTable.add("Member ID");
			headerTable.add("Transaction Date");
			
			dtmHeader = new DefaultTableModel(headerTable, 0);

			transaksi.removeAllElements();
				Vector<Transaksi>  viewTransactionHeader = getAllTransaction();
				for (Transaksi th : viewTransactionHeader) {
					dataTable = new Vector<>();
					dataTable.add(th.getIdTransaksi());
					dataTable.add(th.getIdMember());
					dataTable.add(th.getTanggalTransaksi());
					dtmHeader.addRow(dataTable);
				}

			tableHeaderTH = new JTable(dtmHeader);
			tableHeaderTH.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
			tableHeaderTH.setFont(new Font("Arial", Font.PLAIN, 16));
			tableHeaderTH.setRowHeight(35);
			scrollPaneHeader = new JScrollPane(tableHeaderTH);
			pnlTableHeaderTH.setSize(680,200);
			pnlTableHeaderTH.setLocation(75, 225);
			pnlTableHeaderTH.setLayout(new GridLayout(1,1));
			pnlTableHeaderTH.add(scrollPaneHeader);
		
				}
		
		void setupTableDetail() {
			int index = tableHeaderTH.getSelectedRow();
			TableModel model = tableHeaderTH.getModel();

			Vector<Object> headerTable, dataTable;

			headerTable = new Vector<>();
			headerTable.add("Transaksi ID");
			headerTable.add("Handphone ID");
			headerTable.add("Quantity");
			headerTable.add("Total Harga");

			
			dtm = new DefaultTableModel(headerTable, 0);
			
			if (index != -1) {
				
				String TransactionID = model.getValueAt(index, 0).toString();
				Vector<DetailTransaksi>  viewTransactionDetail = getAllTransactionDetail(TransactionID);
				
				for (DetailTransaksi tcd : viewTransactionDetail) {
				dataTable = new Vector<>();
				dataTable.add(tcd.getIdTransaksi());
				dataTable.add(tcd.getIdHandphone());
				dataTable.add(tcd.getQuantity());
				dataTable.add(tcd.getHarga());			
				dtm.addRow(dataTable);
			}
				
				
			}
			tableDetailTH = new JTable(dtm);
			tableDetailTH.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
			tableDetailTH.setFont(new Font("Arial", Font.PLAIN, 16));
			tableDetailTH.setRowHeight(35);
			scrollPaneDetail = new JScrollPane(tableDetailTH);
			
			pnlTableDetailTH.setSize(680,200);
			pnlTableDetailTH.setLocation(75, 500);
			pnlTableDetailTH.setLayout(new GridLayout(1,1));
			pnlTableDetailTH.add(scrollPaneDetail);
		}
		
		
	public POS() {
		
		sales = new JMenu("Sales");
		inventory = new JMenu("Inventory");
		menuBar.add(sales);
		menuBar.add(inventory);
		
		salesEntry = new JMenuItem("Sales Entry");
		salesEntry.addActionListener(this);
		salesReport = new JMenuItem("Sales Report");
		salesReport.addActionListener(this);
		handphoneStock = new JMenuItem("Handphone Stock");
		handphoneStock.addActionListener(this);
		supplierMenu = new JMenuItem("Supplier");
		supplierMenu.addActionListener(this);
		
		sales.add(salesEntry);
		sales.add(salesReport);
		inventory.add(handphoneStock);
		inventory.add(supplierMenu);
		
		JLabel lblTokoHandphone = new JLabel("Toko Handphone");
		lblTokoHandphone.setFont(new Font("Arial", Font.BOLD, 72));
		lblTokoHandphone.setSize(2000,200);
		lblTokoHandphone.setHorizontalAlignment(JLabel.CENTER);
		
		setJMenuBar(menuBar);
		add(lblTokoHandphone);
		
		setupSalesEntry();
		
		setSize(1200,900);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	void setupTableInventory() {
		Vector<Object> headerTable, dataTable;
		Vector<Handphone>  viewCake = getAllHandphone();

		headerTable = new Vector<>();
		headerTable.add("Merek Handphone");
		headerTable.add("Type Handphone");
		headerTable.add("Harga");
		headerTable.add("Stock");
		
		dtmStockHP = new DefaultTableModel(headerTable, 0);
		
		
		for (Handphone handphone : viewCake) {
			dataTable = new Vector<>();
			dataTable.add(handphone.getMerekHandphone());
			dataTable.add(handphone.getTypeHandphone());
			dataTable.add(handphone.getHarga());
			dataTable.add(handphone.getStock());
			dtmStockHP.addRow(dataTable);
		}
		
		tableStockHP = new JTable(dtmStockHP);
		tableStockHP.setSize(250,250);
		tableStockHP.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableStockHP.setFont(new Font("Arial", Font.PLAIN, 16));
		tableStockHP.setRowHeight(35);
		spStockHp = new JScrollPane(tableStockHP);

	}
	
	void setupSalesEntry() {
		
		pnlLeftSE = new JPanel();
		pnlRightSE = new JPanel();
		
		pnlLeftSE.setLayout(new GridLayout(1,1));
		pnlRightSE.setLayout(null);
		
		setupTableSalesEntry();

		pnlLeftSE.add(scrollPaneSalesEntry);

		
		lblSalesEntry = new JLabel("Sales Entry");
		lblSalesEntry.setFont(new Font("Arial", Font.BOLD, 32));
		lblSalesEntry.setSize(300,300);
		lblSalesEntry.setLocation(150,0);
		
		pnlMemberIDSE = new JPanel();
		pnlMemberIDSE.setLayout(new GridLayout(1,2,10,10));
		lblMemberID = new JLabel("IDMember");
		lblMemberID.setFont(new Font("Arial", Font.BOLD, 20));
		txtMemberID = new JTextField();
		pnlMemberIDSE.add(lblMemberID);
		pnlMemberIDSE.add(txtMemberID);
		pnlMemberIDSE.setSize(200,25);
		pnlMemberIDSE.setLocation(150,250);
		
		pnlQntySE = new JPanel();
		pnlQntySE.setLayout(new GridLayout(1,2,10,10));
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Arial", Font.BOLD, 20));
		txtQuantity = new JTextField();
		pnlQntySE.add(lblQuantity);
		pnlQntySE.add(txtQuantity);
		pnlQntySE.setSize(200,25);
		pnlQntySE.setLocation(150,300);
		buttonAddToCart = new JButton("Checkout");
		buttonAddToCart.setFont(new Font("Arial", Font.BOLD, 18));
		buttonAddToCart.setSize(200,100);
		buttonAddToCart.setLocation(150,375);
		buttonHelp = new JButton("Request Help");
		buttonHelp.setFont(new Font("Arial", Font.BOLD, 18));
		buttonHelp.setSize(200,100);
		buttonHelp.setLocation(150,500);
		
		
		pnlRightSE.add(pnlQntySE);
		pnlRightSE.add(buttonAddToCart);
		pnlRightSE.add(buttonHelp);
		pnlRightSE.add(lblSalesEntry);
		pnlRightSE.add(pnlMemberIDSE);
		
		FSalesEntry.setLayout(new GridLayout(1,2));
		FSalesEntry.add(pnlLeftSE);
		FSalesEntry.add(pnlRightSE);
		
		buttonAddToCart.addActionListener(this);
		buttonHelp.addActionListener(this);
		
		FSalesEntry.setSize(1000,700);	
		FSalesEntry.setLocationRelativeTo(null);
		
	}
	
	void Inventory() {
		
		FStockHP.setLayout(new BorderLayout());
		
		lblStockHP = new JLabel("Stock Inventory HP");
		lblStockHP.setFont(new Font("Arial", Font.BOLD, 32));
		lblStockHP.setHorizontalAlignment(JLabel.CENTER);
		
		setupTableInventory();
		
		
		FStockHP.add(lblStockHP, BorderLayout.NORTH);
		FStockHP.add(spStockHp, BorderLayout.CENTER);
		FStockHP.setSize(700,500);	
		FStockHP.setLocationRelativeTo(null);
		FStockHP.setVisible(true);	
		
		
	}
	
	void Supplier() {
		
		FSupplier.setLayout(new BorderLayout());
		
		lblSupplier = new JLabel("Supplier");
		lblSupplier.setFont(new Font("Arial", Font.BOLD, 32));
		lblSupplier.setHorizontalAlignment(JLabel.CENTER);
		
		setupTableSupplier();
		FSupplier.add(lblSupplier, BorderLayout.NORTH);
		FSupplier.add(spSupplier, BorderLayout.CENTER);
		FSupplier.setSize(700,500);	
		FSupplier.setLocationRelativeTo(null);
		FSupplier.setVisible(true);	
	}
	
	void TransactionHistory() {
		pnlTableDetailTH = new JPanel();
		pnlTableHeaderTH = new JPanel();

		TransactionHistory.setLayout(null);

		setupTableTransactionHeader();
		setupTableDetail();
		
		lblSelectedID = new JLabel("Transaksi");
		lblSelectedID.setFont(new Font("Arial", Font.BOLD, 28));
		lblSelectedID.setLocation(350, 100);
		lblSelectedID.setSize(200,200);
		TransactionHistory.add(lblSelectedID);
		
		lblTotalTH = new JLabel("Detail Transaksi");
		lblTotalTH.setFont(new Font("Arial", Font.BOLD, 28));
		lblTotalTH.setLocation(315, 375);
		lblTotalTH.setSize(300,200);
		TransactionHistory.add(lblTotalTH);
		
		lblCakeTH = new JLabel("Toko Handphone");
		lblCakeTH.setFont(new Font("Arial", Font.BOLD, 33));
		lblCakeTH.setSize(300, 30);
		lblCakeTH.setLocation(284, 60);
		TransactionHistory.add(lblCakeTH);
		
		lblTH = new JLabel("Transaction History");
		lblTH.setFont(new Font("Arial", Font.BOLD, 33));
		lblTH.setSize(330, 35);
		lblTH.setLocation(265, 115);
		TransactionHistory.add(lblTH);
		
	
		
		TransactionHistory.setSize(850,900);
		TransactionHistory.setLocationRelativeTo(null);
		TransactionHistory.setVisible(true);
		TransactionHistory.setResizable(false);
		
		TransactionHistory.add(pnlTableHeaderTH);
		TransactionHistory.add(pnlTableDetailTH);
		
		tableHeaderTH.addMouseListener(this);
	}
	
	void refreshTransactionHistoryDetailTable() {		
		pnlTableDetailTH.removeAll();
		setupTableDetail();
		pnlTableDetailTH.repaint();
		pnlTableDetailTH.revalidate();
	}
	
	public static void main(String args[]) {
		new POS();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == salesEntry) {
			handphone.removeAll(handphone);
			FSalesEntry.repaint();
			FSalesEntry.setVisible(true);
		}
	
		if(e.getSource() == buttonAddToCart) {
			JOptionPane.showMessageDialog(this, "Transaksi Berhasil!");
		}
		
		if(e.getSource() == buttonHelp) {
			JOptionPane.showMessageDialog(this, "Pemberitahuan sudah diberikan kepada supervisor!");
		}
		
		if (e.getSource() == handphoneStock) {
			handphone.removeAll(handphone);
			Inventory();
		}
		
		if(e.getSource() == supplierMenu) {
			Supplier();
		}
		
		if(e.getSource() == salesReport) {
			TransactionHistory();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() == tableHeaderTH) {
			detailTransaksi.removeAll(detailTransaksi);
			refreshTransactionHistoryDetailTable();
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
