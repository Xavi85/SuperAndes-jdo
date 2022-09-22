package uniandes.isis2304.SuperAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.SuperAndes.negocio.SuperAndes;
import uniandes.isis2304.SuperAndes.negocio.VOBodega;

/**
 * Clase con los métdos de prueba de funcionalidad sobre TIPOBEBIDA
 * @author Germán Bravo
 *
 */
public class BodegaTest
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(BodegaTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    //private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private SuperAndes SuperAndes;
	
    /* ****************************************************************
	 * 			Métodos de prueba para la tabla Bodega - Creación y borrado
	 *****************************************************************/
	/**
	 * Método que prueba las operaciones sobre la tabla Bodega
	 * 1. Adicionar un tipo de bebida
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un tipo de bebida por su identificador
	 * 4. Borrar un tipo de bebida por su nombre
	 */
    @Test
	public void CRDBodegaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Bodega");
			SuperAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Tipobebida incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Tipobebida incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de SuperAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOBodega> lista = SuperAndes.darVOBodegas();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			int volMax1 = 1;
			int pesoMax1 = 1;
			String tipoAlmacen1 = "Congelado";
			long id_Sucursal1 = 5;
			long id_TipoProducto1 = 6;
			VOBodega bodega1 = SuperAndes.adicionarBodega (volMax1, pesoMax1, tipoAlmacen1, id_Sucursal1, id_TipoProducto1);
			lista = SuperAndes.darVOBodegas();
			assertEquals ("Debe haber un tipo de bebida creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", bodega1, lista.get (0));

			// Lectura de las bodegas con dos bodegas adicionadas
			int volMax2 = 2;
			int pesoMax2 = 8;
			String tipoAlmacen2 = "Refrigerado";
			long id_Sucursal2 = 7;
			long id_TipoProducto2 = 5;
			VOBodega bodega2 = SuperAndes.adicionarBodega (volMax2, pesoMax2, tipoAlmacen2, id_Sucursal2, id_TipoProducto2);
			lista = SuperAndes.darVOBodegas();
			assertEquals ("Debe haber dos bodegas creadas !!", 2, lista.size ());
			assertTrue ("La primera bodega adicionada debe estar en la tabla", bodega1.equals (lista.get (0)) || bodega1.equals (lista.get (1)));
			assertTrue ("La segunda bodega adicionada debe estar en la tabla", bodega2.equals (lista.get (0)) || bodega2.equals (lista.get (1)));

			// Prueba de eliminación de una bodega, dado su identificador
			long tbEliminados = SuperAndes.eliminarBodegaPorId (bodega1.getId ());
			assertEquals ("Debe haberse eliminado una bodega !!", 1, tbEliminados);
			lista = SuperAndes.darVOBodegas();
			assertEquals ("Debe haber una sola bodega !!", 1, lista.size ());
			assertFalse ("La primera bodega adicionada No debe estar en la tabla", bodega1.equals (lista.get (0)));
			assertTrue ("La segunda bodega adicionada debe estar en la tabla", bodega2.equals (lista.get (0)));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Bodega.\n";
			msg += "Revise el log de SuperAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Bodega");
		}
		finally
		{
			SuperAndes.limpiarSuperAndes ();
    		SuperAndes.cerrarUnidadPersistencia ();    		
		}
	}

    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de Bodega
     */
	@Test
	public void unicidadBodegaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del nombre del tipo de bebida");
			SuperAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Tipobebida incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Tipobebida incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de SuperAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de las bodegas con la tabla vacía
			List <VOBodega> lista = SuperAndes.darVOBodegas();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de las bodegas
			int volMax = 5;
			int pesoMax = 5;
			String tipoAlmacen = "Refrigerado";
			long id_Sucursal = 1;
			long id_TipoProducto = 1;
			@SuppressWarnings("unused")
			VOBodega bodega1 = SuperAndes.adicionarBodega (volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto);
			lista = SuperAndes.darVOBodegas();
			assertEquals ("Debe haber un tipo de bebida creado !!", 1, lista.size ());
			
			VOBodega bodega2 = SuperAndes.adicionarBodega (volMax, pesoMax, tipoAlmacen, id_Sucursal, id_TipoProducto);
			assertNull ("No puede adicionar dos tipos de bebida con el mismo nombre !!", bodega2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Bodega.\n";
			msg += "Revise el log de SuperAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Bodega");
		}    				
		finally
		{
			SuperAndes.limpiarSuperAndes ();
    		SuperAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "BodegaTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
