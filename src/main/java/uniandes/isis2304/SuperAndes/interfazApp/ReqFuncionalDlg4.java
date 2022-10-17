package uniandes.isis2304.SuperAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ReqFuncionalDlg4 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReqFuncionalDlg4 dialog = new ReqFuncionalDlg4();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReqFuncionalDlg4() {
		setBounds(100, 100, 450, 327);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("<html>MOSTRAR LOS PRODUCTOS QUE CUMPLEN CON CIERTA CARACTERÍSTICA</html>");
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
				panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
				panel.add(panel_1);
				panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
				{
					JCheckBox chckbxNewCheckBox = new JCheckBox("Rango de precios");
					panel_1.add(chckbxNewCheckBox);
				}
				{
					JPanel panel_2 = new JPanel();
					FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
					panel_1.add(panel_2);
					{
						textField = new JTextField();
						panel_2.add(textField);
						textField.setColumns(10);
					}
					{
						JLabel lblNewLabel_1 = new JLabel(" - ");
						panel_2.add(lblNewLabel_1);
					}
					{
						textField_1 = new JTextField();
						panel_2.add(textField_1);
						textField_1.setColumns(10);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
				panel.add(panel_1);
				panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
				{
					JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Fecha de vencimiento");
					panel_1.add(chckbxNewCheckBox_1);
				}
				{
					JPanel panel_2 = new JPanel();
					FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
					panel_1.add(panel_2);
					{
						JLabel lblNewLabel_2 = new JLabel("Posterior a ");
						panel_2.add(lblNewLabel_2);
					}
					{
						JDateChooser dateChooser = new JDateChooser();
						panel_2.add(dateChooser);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
				panel.add(panel_1);
				panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
				{
					JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Peso");
					panel_1.add(chckbxNewCheckBox_2);
				}
				{
					JPanel panel_2 = new JPanel();
					FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
					panel_1.add(panel_2);
					{
						textField_2 = new JTextField();
						panel_2.add(textField_2);
						textField_2.setColumns(10);
					}
					{
						JLabel lblNewLabel_3 = new JLabel(" - ");
						panel_2.add(lblNewLabel_3);
					}
					{
						textField_3 = new JTextField();
						panel_2.add(textField_3);
						textField_3.setColumns(10);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
				panel.add(panel_1);
				panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
				{
					JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Volumen");
					panel_1.add(chckbxNewCheckBox_3);
				}
				{
					JPanel panel_2 = new JPanel();
					FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
					panel_1.add(panel_2);
					{
						textField_4 = new JTextField();
						panel_2.add(textField_4);
						textField_4.setColumns(10);
					}
					{
						JLabel lblNewLabel_4 = new JLabel(" - ");
						panel_2.add(lblNewLabel_4);
					}
					{
						textField_5 = new JTextField();
						panel_2.add(textField_5);
						textField_5.setColumns(10);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
