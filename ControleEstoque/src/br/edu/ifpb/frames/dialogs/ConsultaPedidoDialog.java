package br.edu.ifpb.frames.dialogs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class ConsultaPedidoDialog extends javax.swing.JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomeProduto;
	private JTable table;

	/**
	 * Create the application.
	 */
	public ConsultaPedidoDialog(JFrame frame) {
		super(frame, true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("113px:grow"),
				ColumnSpec.decode("199px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(66dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(27dlu;default):grow"),}));
		
		JLabel lblCadastroProdutos = new JLabel("Consultar Pedidos");
		lblCadastroProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProdutos.setBackground(Color.BLACK);
		lblCadastroProdutos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblCadastroProdutos, "4, 2, center, top");
		
		JLabel lblNewLabel = new JLabel("Produto:");
		getContentPane().add(lblNewLabel, "3, 6, left, default");
		
		textFieldNomeProduto = new JTextField();
		getContentPane().add(textFieldNomeProduto, "4, 6, fill, default");
		textFieldNomeProduto.setColumns(10);
		
		JButton btnNewButtonPesquisar = new JButton("Pesquisar");
		getContentPane().add(btnNewButtonPesquisar, "6, 6, center, default");

		JPanel painelFundo = new JPanel();
		painelFundo.setSize(500, 500);
        painelFundo.setLayout(new GridLayout(1, 1));
 
       
		getContentPane().add(painelFundo, "3, 8, 5, 5, fill, fill");
		
		criaJTable();

		JScrollPane barraRolagem = new JScrollPane(table);
		barraRolagem.setSize(500, 500);
		painelFundo.add(barraRolagem);
		
		
	}
	

	
    private void criaJTable() {

    	 Object [] colunas = {"Cod do Pedido", "Data do Pedido", "Fornecedor",""};

    	 Object [][] dados = {
    			{"1", "20/02/2017", "FULANO",""},
    			{"2", "20/02/2017", "FULANO",""},
    			{"3", "20/02/2017", "FULANO",""},
    			{"4", "20/02/2017", "FULANO",""},
    			{"4", "20/02/2017", "FULANO",""},
    			{"4", "20/02/2017", "FULANO",""},
    			{"4", "20/02/2017", "FULANO",""},
    			{"4", "20/02/2017", "FULANO",""},
    			{"4", "20/02/2017", "FULANO",""},
    			{"4", "20/02/2017", "FULANO",""},
    			{"5", "20/02/2017", "FULANO",""}
   
    		};

    	DefaultTableModel model = new DefaultTableModel(dados, colunas);  
        table = new JTable( model );
      
     	table.setEditingColumn(0);
    	table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.setRowHeight(25);
		
		new ButtonColumn(table, 3);  
    }
    
   private class ButtonColumn extends AbstractCellEditor  implements TableCellRenderer, TableCellEditor, ActionListener {  
    
			/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
			JTable table;  
		    JButton renderButton;  
		    JButton editButton;  
		    String text;  
		
		    public ButtonColumn(JTable table, int column) {  
		        super();  
		        this.table = table;  
		        renderButton = new JButton();  
		        editButton = new JButton();  
		        editButton.setFocusPainted( false );  
		        editButton.addActionListener( this );  
		
		        TableColumnModel columnModel = table.getColumnModel();  
		        columnModel.getColumn(column).setCellRenderer( this );  
		        columnModel.getColumn(column).setCellEditor( this );  
		    }  
		
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){  
		        if (hasFocus)  
		        {  
		            renderButton.setForeground(table.getForeground());  
		            renderButton.setIcon(new ImageIcon(FazerPedidoDialog.class.getResource("/imagens/search.png")));
		        }  
		        else if (isSelected)  
		        {  
		            renderButton.setForeground(table.getSelectionForeground());  
		            renderButton.setBackground(table.getSelectionBackground());
		        }  
		        else  
		        {  
		            renderButton.setForeground(table.getForeground());  
		            renderButton.setIcon(new ImageIcon(FazerPedidoDialog.class.getResource("/imagens/search.png")));
		        }  
		
		        renderButton.setText( (value == null) ? "" : value.toString() );  
		        return renderButton;  
		    }  
		
		    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){  
		        text = (value == null) ? "" : value.toString();  
		        editButton.setText( text );  
		        return editButton;  
		    }  
		
		    public Object getCellEditorValue(){  
		        return text;  
		    }  
		
		    public void actionPerformed(ActionEvent e){  
		        fireEditingStopped();  
		        System.out.println( e.getActionCommand() + "Linha : " + table.getSelectedRow());  
		        //TODO:
		        //Criar dialiog de detalhe
		    }  
		   }  
}