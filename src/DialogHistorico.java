import br.com.elearning.praticas.facade.Facade;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DialogHistorico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private final JButton btnAtualizar = new JButton("ATUALIZAR");
        private Facade facede;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new DialogHistorico();
	}

	/**
	 * Create the dialog.
	 */
	public DialogHistorico() {
		setSize(725, 487);
		setResizable(false);
                setModal(true);
                setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
                
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 24, 669, 369);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PERGUNTAS RESPONDIDAS", "ACERTOS"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            
			}
		});
		btnAtualizar.setBounds(579, 404, 114, 23);
		
		contentPanel.add(btnAtualizar);
                
//                private void ListarHistorico (){
//                    List<HistoricoJogador> historico = new facade
//                }
		
		setVisible(true);
		
	}
}
