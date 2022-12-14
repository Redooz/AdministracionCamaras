package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ArchPdf;
import modelo.ArrayListFactura;
import modelo.Camara;
import modelo.CamaraAnaloga;
import modelo.CamaraDAO;
import modelo.CamaraDigital;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Conexion;
import modelo.ExcepcionPersonalizada;
import modelo.Factura;
import modelo.FacturaDAO;
import modelo.Fecha;
import modelo.Hora;
import vista.Escritorio;
import vista.frmConsultar;
import vista.frmConsultarBD;
import vista.frmRegistrar;

/** La clase controlador es la encargada de realizar el proceso de manejar las clases del modelo y de la vista, 
 * implementa 2 interfaces ActionListener (Para escuchar los eventos que lanzan los usuarios) y 
 * Runnable (Para hacer el flujo de multiples hilos y haci optimizar los tiempos de ejecucion y un poco de "Asincronia")
* @author Nicolas Olmos
* @author Daniel Garcia 
*/ 
public class Controlador implements ActionListener, Runnable{

    /**
     * Atributo de la clase ArrayList que maneja el atributo Factura
     */
    public ArrayListFactura objLF;

    /** 
     * Atributo de la clase Escritorio el cual se encarga de manejar esta parte de la vista
     */
    public Escritorio frmE;

    /**
     * Atributo de la clase frmConsultar el cual se encarga de manejar la parte de visualizar la informacion de los archivos de la vista
     */
    public frmConsultar frmC;

    /**
     * Atributo de la clase frmRegistrar el cual se encarga de manejar la parte de registrar la informacion de la vista
     */
    public frmRegistrar frmR;

    /**
     * Atributo de la clase frmConsultarBD el cual se encarga de manejar la parte de visualizar la informacion de la base de datos de la vista
     */
    public frmConsultarBD frmCBD; 

    /**
     * Atributo de la clase FacturaDAO la cual se encarga de realizar las operaciones basicas CRUD, con relacion a las facturas
     */
    public FacturaDAO facturaDAO;

    /**
     * Atributo de la clase CamaraDAO la cual se encarga de realizar las operaciones basicas CRUD, con relacion a las Camaras
     */
    public CamaraDAO camaraDAO;

    /**
     * Atributo de la clase ClienteDAO la cual se encarga de realizar las operaciones basicas CRUD, con relacion a los clientes
     */
    public ClienteDAO clienteDAO;

    /**
     * Atributo de la clase Hora la cual se encarga de el flujo de la Hora 
     */
    public Hora objH;

    /**
     * Atributo de la clase Thread para manejar los hilos 
     */
    public Thread hilo;
    
    /**
     * Constructor que inicializa los atributos de la clase Controlador, hilo se inicializa enviandole el parametro this, para apuntar a la la clase actual 
     */
    public Controlador() {
        this.objLF = new ArrayListFactura();
        this.frmE = new Escritorio();
        this.frmR = new frmRegistrar();
        this.frmC = new frmConsultar();
        this.frmCBD = new frmConsultarBD();
        this.facturaDAO = new FacturaDAO();
        this.camaraDAO = new CamaraDAO();
        this.clienteDAO = new ClienteDAO();
        this.objH = new Hora();
        this.hilo = new Thread(this);
    }

    /**
     * Metodo que inicaliza el programa
     * Activando ActionListener
     * Enviando el titulo al la ventana de escritorios
     * Y inicializando el hilo
     */
    public void iniciar() {
        frmE.setTitle("Proyecto C??maras :)");
        //Listerners 
        //Menubar
        frmE.getMnuRegistrar().addActionListener(this);
        frmE.getMnuConsultar().addActionListener(this);
        frmE.getMnuSalir().addActionListener(this);
        //ToolBar
        frmE.getTbRegistrar().addActionListener(this);
        frmE.getTbConsultar().addActionListener(this);
        frmE.getTbConsultarBD().addActionListener(this);
        frmE.getTbSalir().addActionListener(this);
        
        frmE.getTbPDF().addActionListener(this);
        frmE.setLocationRelativeTo(null);
        frmE.setVisible(true);
        
        hilo.start();
    }

    /**
     * Metodo sobreescrito que maneja la logica del reloj con el uso de Hilos 
     */
    @Override
public void run() {
        while (true) {
            try {
                // Inicializar el reloj
                objH.incrementoSS();
                frmE.getLblHora().setText(objH.toString());
                // Evaluamos si el valor de los segundo es igual a 0 (Lo cual implica que hubo un avance en el segundero), se aumentara los Minutos
                if (objH.getSs() == 0) {
                    objH.incrementoMM();
                    frmE.getLblHora().setText(objH.toString());
                }
                // Evaluamos si el valor de los minutos es igual a 0 (Lo cual implica que hubo un avance en el minutero), se aumentara las horas
                if (objH.getMm() == 0) {
                    objH.incrementoHH();
                    frmE.getLblHora().setText(objH.toString());
                }
                hilo.sleep(1000);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(frmE, ex.getMessage());
            }
        }
    }
    
    /**
     * Metodo sobreescrito de la interface ActionListener, el cual tiene la logica que ser?? operada cada vez que el usuario realice algun tipo de accion en la aplicacion.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Condicional que inicializa los listeners del internalFrame frmRegistrar
        if (e.getSource().equals(frmE.getMnuRegistrar()) || e.getSource().equals(frmE.getTbRegistrar())) { //Frame Registrar
            frmR = new frmRegistrar();
            //Combo box
            frmR.getCmbRegistrar().addActionListener(this);

            //Registrar Action Listener
            frmR.getBtnRegistrar().addActionListener(this);

            //A??adir al escritorio
            frmE.getDesktopPane().add(frmR);

            frmR.getJpnAnaloga().setVisible(false);

            frmR.setVisible(true);
        }
        //Concicional que inicializa el frmConsultar de archivo
        if (e.getSource().equals(frmE.getMnuConsultar()) || e.getSource().equals(frmE.getTbConsultar())) { //Frame Consultar
            frmC = new frmConsultar();
            
            frmE.getDesktopPane().add(frmC); //A??adiendo al escritorio el frame de registrar

            agregarDatosDesdeArchivo();

            frmC.setVisible(true);
        }
        //Condicional que implementa la logica de generar los PDF de manera automatica
        if (e.getSource().equals(frmE.getTbPDF())) { //Generar PDF
            ArchPdf objPDF = new ArchPdf();
            objPDF.generar("Camaras", objLF.toString());
        }
        //Condicional que implementa la logica para el boton salir, creado por nosotros.
        if (e.getSource().equals(frmE.getMnuSalir()) || e.getSource().equals(frmE.getTbSalir())) { //Salir
            int resp = JOptionPane.showConfirmDialog(frmE,
                    "Desea terminar la ejecucion?", "Salir",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (resp == JOptionPane.YES_OPTION) {
                frmE.dispose();
                hilo.stop();
            }
        }
        //Condicional que implementa la logica de registrar una compra de camaras
        if (e.getSource().equals(frmR.getBtnRegistrar())) { //Boton registrar
            try {
                Factura objF = new Factura();
                Cliente objCli = new Cliente();
                Conexion objCon = new Conexion();
                
                objF.setCodigo(frmR.getTxtFactura().getText());
                //Date excepcion
                String[] fechaError = frmR.getTxtFecha().getText().split("/");
                if (fechaError.length > 1) {
                    ExcepcionPersonalizada fechaErrorFormat = new ExcepcionPersonalizada(301);
                    JOptionPane.showMessageDialog(frmR, "Error: "+ fechaErrorFormat);    
                }else{
                    String[] fecha = frmR.getTxtFecha().getText().split("-");
                    objF.setObjFCom(new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));
                }               
                objCli.setCedula(frmR.getTxtCedula().getText());
                objCli.setNom(frmR.getTxtNombre().getText());
                objCli.setTel(frmR.getTxtTel().getText());
                
                clienteDAO.setObjC(objCli);
                JOptionPane.showMessageDialog(frmR, "Cliente: "+clienteDAO.insertar());
                
                objF.setObjCli(objCli);
                
                switch (frmR.getCmbRegistrar().getSelectedIndex()) {
                    case 0://Camara digital
                        CamaraDigital objCD = new CamaraDigital();
                        objCD.setId(frmR.getTxtCod().getText());
                        objCD.setMarca(frmR.getTxtMar().getText());
                        objCD.setLente(frmR.getTxtLen().getText());
                        objCD.setPrecio(Double.parseDouble(frmR.getTxtPre().getText()));
                        objCD.setMemoria(frmR.getTxtMem().getText());
                        objCD.setPantalla(frmR.getTxtPan().getText());

                        JOptionPane.showMessageDialog(frmR, "Se ha agregado la Camara Digital :) .", "Resultado", JOptionPane.INFORMATION_MESSAGE);

                        objF.setObjCam(objCD);
                        objLF.getArrayFac().add(objF);
                        
                        camaraDAO.setObjC(objCD);
                        JOptionPane.showMessageDialog(frmR, "Camara Digital: "+camaraDAO.insertar());
                        
                        facturaDAO.setObjF(objF);
                        JOptionPane.showMessageDialog(frmR, facturaDAO.insertar());
                        
                        objCon.escribeDatos(objF.datos());
                        break;
                    case 1://Camara analoga
                        CamaraAnaloga objCA = new CamaraAnaloga();
                        objCA.setId(frmR.getTxtCod().getText());
                        objCA.setMarca(frmR.getTxtMar().getText());
                        objCA.setLente(frmR.getTxtLen().getText());
                        objCA.setPrecio(Double.parseDouble(frmR.getTxtPre().getText()));
                        objCA.setRollo(frmR.getTxtRol().getText());
                        objCA.setVisor(frmR.getTxtVis().getText());

                        JOptionPane.showMessageDialog(frmR, "Se ha agregado la Camara Analoga :) .", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        objF.setObjCam(objCA);
                        objLF.getArrayFac().add(objF);

                        camaraDAO.setObjC(objCA);
                        JOptionPane.showMessageDialog(frmR, "Camara Analoga: "+camaraDAO.insertar());
                        
                        facturaDAO.setObjF(objF);
                        JOptionPane.showMessageDialog(frmR, "Factura "+facturaDAO.insertar());
                        
                        objCon.escribeDatos(objF.datos());
                        break;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frmE, "Error al escribir el archivo: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println("Error numero");
                JOptionPane.showMessageDialog(frmE, "Error : " + ex.getMessage());
            } catch (ExcepcionPersonalizada error) {
                System.out.println("Error mio");
                JOptionPane.showMessageDialog(frmE, error.toString());
            }
        }
        // Condicional que activa o desactiva el panel necesario, dependiendo de si se escoge ingresar una camara digital o analoga 
        if (e.getSource().equals(frmR.getCmbRegistrar()) && frmR != null) { //Hacer visible o no paneles
            switch (frmR.getCmbRegistrar().getSelectedIndex()) {
                case 0: // Digital selected
                    frmR.getJpnAnaloga().setVisible(false);
                    frmR.getJpnDigital().setVisible(true);
                    break;
                case 1:
                    frmR.getJpnAnaloga().setVisible(true);
                    frmR.getJpnDigital().setVisible(false);
                    break;

            }
        }
        // Condicional que maneja el frmConsultarBD el cual es la misma tabla de consultar pero referente a los DAOs y por ende a la base de datos
        if(e.getSource().equals(frmE.getTbConsultarBD())){ //Consultar bd
            frmCBD = new frmConsultarBD();
            
            frmE.getDesktopPane().add(frmCBD);
            
            frmCBD.getTblLista().setModel(facturaDAO.consultar()); //Tabla facturas
            frmCBD.getTblCamaras().setModel(camaraDAO.consultar()); //Tabla camaras
            frmCBD.getTblClientes().setModel(clienteDAO.consultar()); //Tabla clientes
            
            frmCBD.getBtnActualizar().addActionListener(this);
            frmCBD.getBtnEliminar().addActionListener(this);
            
            
            calcularTotalFacturasBD();
            
            frmCBD.setVisible(true);
        }
        // Condicional que evalua si se le da al boton actualizar BD, y llama a las funciones necesarias para actualizar la informacion
        if(e.getSource().equals(frmCBD.getBtnActualizar())){ //Boton actualizar
            switch(frmCBD.getPanelTabs().getSelectedIndex()){ 
                case 0: //Facturas
                    JOptionPane.showMessageDialog(frmCBD, "Recuerde que solo se puede actualizar la fecha");
                    enviarDatosTablaFactura();
                    JOptionPane.showMessageDialog(frmCBD, facturaDAO.actualizar());
                    break;
                case 1: //Camaras
                    enviarDatosTablaCamaras();
                    JOptionPane.showMessageDialog(frmCBD, camaraDAO.actualizar());
                    break;
                case 2: //Clientes
                    enviarDatosTablaClientes();
                    JOptionPane.showMessageDialog(frmCBD, clienteDAO.actualizar());
                    break;
            }
        }
     
        // Condicional que evalua si se le da al boton eliminar BD, y llama a las funciones necesarias para eliminar la informacion
        if(e.getSource().equals(frmCBD.getBtnEliminar())){ //Boton eliminar
            System.out.println("Click en eliminar");
            switch(frmCBD.getPanelTabs().getSelectedIndex()){ 
                case 0: //Facturas
                    System.out.println("Seleccionado factura");
                    enviarDatosTablaFactura();
                    System.out.println("Selecionado factura 2");
                    JOptionPane.showMessageDialog(frmCBD, facturaDAO.eliminar());
                    borrarFila(frmCBD.getTblLista());
                    break;
                case 1: //Camaras
                    enviarDatosTablaCamaras();
                    JOptionPane.showMessageDialog(frmCBD, camaraDAO.eliminar());
                    borrarFila(frmCBD.getTblCamaras());
                    break;
                case 2: //Clientes
                    enviarDatosTablaClientes();
                    JOptionPane.showMessageDialog(frmCBD, clienteDAO.eliminar());
                    borrarFila(frmCBD.getTblClientes());
                    break;
            }
        }
    }

    /**
     * Mettodo que agrega los registros de el arrayFacturas a la tabla
     */
    public void agregarTabla() {
        DefaultTableModel plantilla = (DefaultTableModel) frmC.getTblLista().getModel();

        for (int i = 0; i < objLF.getArrayFac().size(); i++) {

            if (objLF.getArrayFac().get(i).getObjCam() instanceof CamaraDigital) { //Camara Digital

                CamaraDigital objCD = (CamaraDigital) objLF.getArrayFac().get(i).getObjCam();

                Object[] registro = {objCD.getId(), objCD.getMarca(), objCD.getLente(), objCD.getPrecio(), "--",
                    "--", objCD.getMemoria(), objCD.getPantalla()};
                plantilla.addRow(registro);

            } else { //Analoga
                CamaraAnaloga objCA = (CamaraAnaloga) objLF.getArrayFac().get(i).getObjCam();
                Object[] registro = {objCA.getId(), objCA.getMarca(), objCA.getLente(), objCA.getPrecio(), objCA.getRollo(),
                    objCA.getVisor(), "--", "--"};
                plantilla.addRow(registro);
            }

        }
    }

    /**
     * Metodo que trae los datos del archivo, los separa y los muestra en la tabla
     */
    public void agregarDatosDesdeArchivo() {
        try {
            Conexion objCon = new Conexion();
            DefaultTableModel plantilla = (DefaultTableModel) frmC.getTblLista().getModel();
            Double total = 0.0;
            //Separando por lineas
            String[] archivoLeido = objCon.leerDatos().split("\n");

            for (int i = 0; i < archivoLeido.length; i++) {
                //Separando por comas
                String[] datos = archivoLeido[i].split(";");
                
                Object[] registro = {datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6],Double.parseDouble(datos[7]),
                                     datos[8],datos[9],datos[10],datos[11],Double.parseDouble(datos[12])};
                
                total += Double.parseDouble(datos[12]);
                plantilla.addRow(registro);
            }
            
            frmC.getLblTotal().setText(total.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frmE, "Error con la lectura de datos: " + ex.getMessage());
        }
    }
    
    /**
     * Metodo para calcular el valor total de las camaras compradas 
     */
    public void calcularTotalFacturasBD(){
        JTable tabla = frmCBD.getTblLista();
        int columnaTotales = 13;
        Double total = 0.0;
        
        //Recorre la tabla en la columna de los totales
        for (int i = 0; i < tabla.getRowCount(); i++) {
            total += Double.parseDouble(tabla.getValueAt(i, columnaTotales).toString());
        }
        
        frmCBD.getLblTotal().setText(total.toString());
    }
    
    /**
     * Metodo para enviar los datos de la JTable a la tabla CAMARAS de la base de datos
     */
    public void enviarDatosTablaCamaras(){
        JTable tabla = frmCBD.getTblCamaras();
        int fila = tabla.getSelectedRow();
        
        if(tabla.getValueAt(fila, 4) == null && tabla.getValueAt(fila, 5) == null){ //Camara digital
            CamaraDigital objCD = new CamaraDigital(tabla.getValueAt(fila, 6).toString(),
                    tabla.getValueAt(fila, 7).toString(), 
                    tabla.getValueAt(fila, 0).toString(), 
                    tabla.getValueAt(fila, 1).toString(),
                    tabla.getValueAt(fila, 2).toString(),
                    Double.parseDouble(tabla.getValueAt(fila, 3).toString())); 
            
            camaraDAO.setObjC(objCD);
        } else if(tabla.getValueAt(fila, 6) == null && tabla.getValueAt(fila, 7) == null){ //Camara analoga
            CamaraAnaloga objCA = new CamaraAnaloga(
                    tabla.getValueAt(fila, 4).toString(), 
                    tabla.getValueAt(fila, 5).toString(), 
                    tabla.getValueAt(fila, 0).toString(), 
                    tabla.getValueAt(fila, 1).toString(), 
                    tabla.getValueAt(fila, 2).toString(), 
                    Double.parseDouble(tabla.getValueAt(fila, 3).toString()));
            
            camaraDAO.setObjC(objCA);
        }
        
    }
    /**
     * Metodo para enviar los datos de la JTable a la tabla Facturas de la base de datos
     */
    public void enviarDatosTablaFactura(){
        System.out.println("Eviar datos a tabla");
        JTable tabla = frmCBD.getTblLista();
        int fila = tabla.getSelectedRow();
        //Se crea un cliente con la informacion mostrada en la tabla
        Cliente objClienteLocal = new Cliente(tabla.getValueAt(fila, 2).toString(), //cedula cliente
                                            tabla.getValueAt(fila, 3).toString(), //Nombre cliente
                                            tabla.getValueAt(fila, 4).toString() //Telefono del cliente
                                        );
        
        String arrayFechaLocal[] = tabla.getValueAt(fila, 1).toString().split("-");
        
        Fecha objFechaLocal = new Fecha(Integer.parseInt(arrayFechaLocal[0]), //A??o
                                        Integer.parseInt(arrayFechaLocal[1]), //Mes
                                        Integer.parseInt(arrayFechaLocal[2]) //Dia
                                    );
        //Se identifica si la camara es analoga o es digital
        if (tabla.getValueAt(fila, 9) == null && tabla.getValueAt(fila, 10) == null) { //Como NO tiene contenido en rollo y visor -> la CAMARA es DIGITAL
            CamaraDigital objCamaraDigitalLocal = new CamaraDigital(
                                                    tabla.getValueAt(fila, 11).toString(), //Memoria de la camara digital
                                                    tabla.getValueAt(fila, 12).toString(), //Pantalla de la camara digital
                                                    tabla.getValueAt(fila, 5).toString(), //ID de la camara digital
                                                    tabla.getValueAt(fila, 6).toString(), //Marca de la camara digital
                                                    tabla.getValueAt(fila, 7).toString(), //Lente de la camara digital
                                                    Double.parseDouble(tabla.getValueAt(fila, 8).toString())); //Precio de la camara digital
            
            Factura objFacturaLocal = new Factura(
                                            tabla.getValueAt(fila, 0).toString(), // Codigo de la factura
                                            objClienteLocal, // Objeto Cliente con info de la tabla
                                            objCamaraDigitalLocal, // Camara digital con info de la tabla
                                            objFechaLocal // Fecha con info de la tabla
                                             );
            facturaDAO.setObjF(objFacturaLocal);
        }else if(tabla.getValueAt(fila, 11) == null && tabla.getValueAt(fila, 12) == null){// Como NO tiene contenido en memoria y pantalla -> la CAMARA es ANALOGA
            CamaraAnaloga objCamaraAnalogaLocal = new CamaraAnaloga(
                                                    tabla.getValueAt(fila, 9).toString(),//Rollo de la camara analoga
                                                    tabla.getValueAt(fila, 10).toString(),//Visor de la camara analoga
                                                    tabla.getValueAt(fila, 5).toString(),//ID de la camara analoga
                                                    tabla.getValueAt(fila, 6).toString(),//Marca de la camara analoga
                                                    tabla.getValueAt(fila, 7).toString(),//Lente de la camara analoga
                                                    Double.parseDouble(tabla.getValueAt(fila, 8).toString()));//Precio de la camara analoga                                         
            Factura objFacturaLocal = new Factura(
                                            tabla.getValueAt(fila, 0).toString(), // Codigo de la factura
                                            objClienteLocal, // Objeto Cliente con info de la tabla
                                            objCamaraAnalogaLocal, // Camara analoga con info de la tabla
                                            objFechaLocal // Fecha con info de la tabla
                                             );
            facturaDAO.setObjF(objFacturaLocal);
        }
    }
    
    /**
     * Metodo para enviar los datos a la tabla CLIENTES de la base de datos
     */
    public void enviarDatosTablaClientes(){
        JTable tabla = frmCBD.getTblClientes();
        int fila = tabla.getSelectedRow();
        
        Cliente objC = new Cliente(
                tabla.getValueAt(fila, 0).toString(),
                tabla.getValueAt(fila, 1).toString(),
                tabla.getValueAt(fila, 2).toString());
        
        clienteDAO.setObjC(objC);
    }
    
    /**
     * Metodo el cual borra la fila de la tabla que fue seleccionada por el usuario
     * @param tabla tabla en la cual se desea eliminar una fila
     */
    public void borrarFila(JTable tabla){
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        int fila = tabla.getSelectedRow();
        
        plantilla.removeRow(fila);
    }
}
