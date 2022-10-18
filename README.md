# SuperAndes-jdo

En este apartado se incluyen los pasos a seguir para la instalación y ejecución de la presente aplicación:

1. Realizar Clone del repositorio en la máquina local.

2. Abrir el proyecto clonado en Elipse IDE o cualquier editor de texto compatible con Jave.

3. En el archivo llamado persistence.xml ubicado en ./src/main/resources/META-INF se deberán cambiar los datos de acceso a la base de datos de oracle que se usa en SQL DEVELOPER, específicamente los relacionados con usuario y contraseña.

4. En SQL DEVELOPER crear una nueva conexión asignando nombre, usuario y contraseña (estos últimos 2 datos deben ser iguales a los ingresados en el punto anterior). Para el nombre del host utilizar fn4.oracle.virtual.uniandes.edu.co, puerto: 1521 y nombre del servicio: host.

5. Establecer conexión a la base de datos.

6. En la carpeta data del proyecto se incluye el archivo EsquemaSuperAndes.sql el cual debe ser usado para crear el modelo y además ingresar algunos datos al mismo. Este archivo deberá ejecutarse como script en SQL DEVELOPER.

7. En caso de requerir realizar limpieza del esquema, se incluye un archivo denominado LimpiezaSuperAndes.sql el cual deberá ejecutarse en SQL DEVELOPER y en ese momento el esquema quedará en blanco.

8. Después de realizado el paso 6 se puede ingresar a Eclipse IDE y ejecutar como aplicación de java el paquete uniandes.isis2304.SuperAndes.InterfazApp que se encuentra en .srs/main/java.

9. Se generará la interfaz en la cual se deberá ingresar dependiendo del rol con los números de documento de cada usuario incluidos en el script de creación de usuario. 

10. En este momento la aplicación podrá navegarse.

