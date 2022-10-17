package uniandes.isis2304.SuperAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ReqFuncionalDlg1 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser dateChooser1;
	private JDateChooser dateChooser2;
	private JRadioButton rdbtnNewRadioButton;
	private Date fechaInicio;
	private Date fechaFin;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ReqFuncionalDlg1 dialog = new ReqFuncionalDlg1();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	
	public Date getFechaIncio()
	{
		return this.fechaInicio;
	}
	
	public Date getFechaFin()
	{
		return this.fechaFin;
	}
	
	public void close()
	{
		this.setVisible(false);
        this.dispose();
	}
	
	/**
	 * Create the dialog.
	 */
	public ReqFuncionalDlg1() {
		//fechaInicio = new Date(0L);
		//fechaFin = new Date(0L);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Requerimiento funcional 1");
		setBounds(100, 100, 450, 277);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("<html>MOSTRAR EL DINERO RECOLECTADO POR VENTAS EN CADA SUCURSAL DURANTE UN PERIODO DE TIEMPO Y EN EL AÑO CORRIDO<br><br>\r\nSeleccione el rango de fechas</html>");
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			{
				rdbtnNewRadioButton = new JRadioButton("Rango de fechas");
				rdbtnNewRadioButton.setSelected(true);
				rdbtnNewRadioButton.setAlignmentY(Component.TOP_ALIGNMENT);
				buttonGroup.add(rdbtnNewRadioButton);
				rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(rdbtnNewRadioButton);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
				FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				panel.add(panel_1);
				{
					JLabel lblNewLabel_1 = new JLabel("Fecha inicio");
					panel_1.add(lblNewLabel_1);
				}
				{
					dateChooser1 = new JDateChooser();
					dateChooser1.setDateFormatString("dd/MM/yyyy");
					panel_1.add(dateChooser1);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
				FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				panel.add(panel_1);
				{
					JLabel lblNewLabel_2 = new JLabel("Fecha fin");
					panel_1.add(lblNewLabel_2);
				}
				{
					dateChooser2 = new JDateChooser();
					dateChooser2.setDateFormatString("dd/MM/yyyy");
					panel_1.add(dateChooser2);
				}
			}
			{
				JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Año corrido");
				buttonGroup.add(rdbtnNewRadioButton_1);
				panel.add(rdbtnNewRadioButton_1);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdbtnNewRadioButton.isSelected())
						{
							GregorianCalendar fecha = new GregorianCalendar();
							fecha.setTime(dateChooser1.getDate());
							GregorianCalendar fecha2 = new GregorianCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
							fechaInicio = fecha2.getTime();
							
							fecha.setTime(dateChooser2.getDate());
							fecha2 = new GregorianCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
							fechaFin = fecha2.getTime();
						}
						else
						{
							GregorianCalendar fecha = new GregorianCalendar();
							fechaFin = fecha.getTime();
							GregorianCalendar fecha2 = new GregorianCalendar(fecha.get(Calendar.YEAR), 0, 1, 0, 0, 0);
							fechaInicio = fecha2.getTime();
						}
						close();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		rdbtnNewRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnNewRadioButton.isSelected())
				{
					dateChooser1.setEnabled(true);
					dateChooser2.setEnabled(true);
				}
				else 
				{
					dateChooser1.setEnabled(false);
					dateChooser2.setEnabled(false);
				}
			}
		});
		
	}

}
