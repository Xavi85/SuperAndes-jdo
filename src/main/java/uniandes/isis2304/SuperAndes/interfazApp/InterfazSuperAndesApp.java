package uniandes.isis2304.SuperAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.SuperAndes.negocio.SuperAndes;
import uniandes.isis2304.SuperAndes.negocio.VOProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOTipoProducto;

@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/

	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json";
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

    private JsonObject tableConfig;
    private SuperAndes SuperAndes;
    private JsonObject guiConfig;
    private PanelDatos panelDatos;
    private JMenuBar menuBar;

    
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

    public InterfazSuperAndesApp() {
    	
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        configurarFrame ();
        if (guiConfig != null) {
        	
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        SuperAndes = new SuperAndes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
    
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/

    private JsonObject openConfig (String tipo, String archConfig) {
    	
    	JsonObject config = null;
		try {
			Gson gson = new Gson();
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e) {
			
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }

    private void configurarFrame() {
    	
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null ) {
    		
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else {
    		
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );
        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    private void crearMenu(  JsonArray jsonMenu ) {    	

        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu) {
        	
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones) {
        		
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
    
	/* ****************************************************************
	 * 			CRUD de Proveedor
	 *****************************************************************/

    public void listarProveedor() {
    	
    	try {
			List <VOProveedor> lista = SuperAndes.darVOProveedores();
			String resultado = "En listar Bodega";
			resultado +=  "\n" + listarProveedores (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) {
    		
    		String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    public void eliminarProveedorPorId() {
    	
    	try {
    		String idProveedorStr = JOptionPane.showInputDialog (this, "Id del Proveedor?", "Borrar el Proveedor por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idProveedorStr != null) {
    			
    			long idProveedor = Long.valueOf (idProveedorStr);
    			long tbEliminados = SuperAndes.eliminarBodegaPorId (idProveedor);

    			String resultado = "En eliminar Proveedor\n\n";
    			resultado += tbEliminados + " Proveedor eliminado\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else {
    			
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) {
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
	/* ****************************************************************
	 * 			CRUD de Tipo de Productos
	 *****************************************************************/

    public void listarTipoProducto() {
    	
    	try {
			List <VOTipoProducto> lista = SuperAndes.darVOTiposProductos();
			String resultado = "En listar Bodega";
			resultado +=  "\n" + listarTiposProductos (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) {
    		
    		String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    
	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de SuperAndes
	 */
	public void mostrarLogSuperAndes ()
	{
		mostrarArchivo ("SuperAndes.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de SuperAndes
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogSuperAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("SuperAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de SuperAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de SuperAndes
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = SuperAndes.limpiarSuperAndes();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Bodegas eliminadas\n";
			resultado += eliminados [1] + " Estantes eliminados\n";
			resultado += eliminados [2] + " Facturas Electronicas eliminadas\n";
			resultado += eliminados [3] + " Ordenes de Pedidos eliminados\n";
			resultado += eliminados [4] + " Ordenes de Pedidos y Productos eliminados\n";
			resultado += eliminados [5] + " Productos eliminados\n";
			resultado += eliminados [6] + " Promociones eliminadas\n";
			resultado += eliminados [7] + " Promociones y Productos eliminados\n";
			resultado += eliminados [8] + " Proveedores eliminados\n";
			resultado += eliminados [9] + " Sucursales eliminadas\n";
			resultado += eliminados [10] + " Supermercados eliminados\n";
			resultado += eliminados [11] + " Tipos de Productos eliminados\n";
			resultado += eliminados [12] + " Usuarios eliminados\n";
			resultado += eliminados [13] + " Ventas eliminadas\n";
			resultado += eliminados [14] + " Ventas y Porductos eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-SuperAndesJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de SuperAndes
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual SuperAndes.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de SuperAndes
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD SuperAndes.pdf");
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaSuperAndes.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para SuperAndes
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	/**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
    }
    

	/* **********************************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 ***********************************************************************************/
    /**
     * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los tipos de bebida
     * @return La cadena con una líea para cada tipo de bebida recibido
     */
	private String listarProveedores(List<VOProveedor> lista) 
    {
    	String resp = "Los proveedores existentes son:\n";
    	int i = 1;
        for (VOProveedor tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
	
	private String listarTiposProductos(List<VOTipoProducto> lista) 
    {
    	String resp = "Los tipos de productos existentes son:\n";
    	int i = 1;
        for (VOTipoProducto tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
     */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y SuperAndes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
