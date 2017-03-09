package br.edu.ifpb.utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import br.edu.ifpb.views.dialogs.FazerPedidoDialog;

public class ButtonColumn extends AbstractCellEditor  implements TableCellRenderer, TableCellEditor, ActionListener {  
    
	/**
* 
*/
private static final long serialVersionUID = 1L;
	JTable table;  
    JButton renderButton;  
    JButton editButton;  
    String text;  
    String imagem;

    public ButtonColumn(JTable table, int column,String imagem) {  
        super();  
        this.table = table;  
        renderButton = new JButton();  
        editButton = new JButton();  
        editButton.setFocusPainted( false );  
        editButton.addActionListener( this );  
        this.imagem = imagem;
        
        TableColumnModel columnModel = table.getColumnModel();  
        columnModel.getColumn(column).setCellRenderer( this );  
        columnModel.getColumn(column).setCellEditor( this );  
    }  

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){  
        if (hasFocus)  
        {  
            renderButton.setForeground(table.getForeground());  
            renderButton.setIcon(new ImageIcon(FazerPedidoDialog.class.getResource("/imagens/"+this.imagem)));
        }  
        else if (isSelected)  
        {  
            renderButton.setForeground(table.getSelectionForeground());  
            renderButton.setBackground(table.getSelectionBackground());
        }  
        else  
        {  
            renderButton.setForeground(table.getForeground());  
            renderButton.setIcon(new ImageIcon(FazerPedidoDialog.class.getResource("/imagens/"+this.imagem)));
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