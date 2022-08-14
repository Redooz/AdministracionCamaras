package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ArchPdf;
import modelo.ArrayListFactura;
import modelo.CamaraAnaloga;
import modelo.CamaraDAO;
import modelo.CamaraDigital;
import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Conexion;
import modelo.Factura;
import modelo.FacturaDAO;
import modelo.Fecha;
import modelo.Hora;
import vista.Escritorio;
import vista.frmConsultar;
import vista.frmConsultarBD;
import vista.frmRegistrar;

/*
* @author SnakyDH
 */
public class Controlador implements ActionListener, Runnable{

    public ArrayListFactura objLF;
    public Escritorio frmE;
    public frmConsultar frmC;
    public frmRegistrar frmR;
    public frmConsultarBD frmCBD; 
    public FacturaDAO facturaDAO;
    public CamaraDAO camaraDAO;
    public ClienteDAO clienteDAO;
    public Hora objH;
    public Thread hilo;
    
    
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

    public void iniciar() {
        frmE.setTitle("Proyecto Cámaras :)");
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

    @Override
    public void run() {
        while (true) {            
            try {
                objH.incrementoSS();
                frmE.getLblHora().setText(objH.toString());
                hilo.sleep(1000);
                
                objH.incrementoMM();
                frmE.getLblHora().setText(objH.toString());
                
                objH.incrementoHH();
                frmE.getLblHora().setText(objH.toString());
                
                
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(frmE, ex.getMessage());
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frmE.getMnuRegistrar()) || e.getSource().equals(frmE.getTbRegistrar())) { //Frame Registrar
            frmR = new frmRegistrar();
            //Combo box
            frmR.getCmbRegistrar().addActionListener(this);

            //Registrar Action Listener
            frmR.getBtnRegistrar().addActionListener(this);

            //Añadir al escritorio
            frmE.getDesktopPane().add(frmR);

            frmR.getJpnAnaloga().setVisible(false);

            frmR.setVisible(true);
        }

        if (e.getSource().equals(frmE.getMnuConsultar()) || e.getSource().equals(frmE.getTbConsultar())) { //Frame Consultar
            frmC = new frmConsultar();
            
            frmE.getDesktopPane().add(frmC); //Añadiendo al escritorio el frame de registrar

            agregarDatosDesdeArchivo();

            frmC.setVisible(true);
        }

        if (e.getSource().equals(frmE.getTbPDF())) { //Generar PDF
            ArchPdf objPDF = new ArchPdf();
            objPDF.generar("Camaras", objLF.toString());
        }

        if (e.getSource().equals(frmE.getMnuSalir()) || e.getSource().equals(frmE.getTbSalir())) { //Salir
            int resp = JOptionPane.showConfirmDialog(frmE,
                    "Desea terminar la ejecucion?", "Salir",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (resp == JOptionPane.YES_OPTION) {
                frmE.dispose();
                hilo.stop();
            }
        }

        if (e.getSource().equals(frmR.getBtnRegistrar())) { //Boton registrar
            try {
                Factura objF = new Factura();
                Cliente objCli = new Cliente();
                Conexion objCon = new Conexion();
                
                objF.setCodigo(frmR.getTxtFactura().getText());
                
                String[] fecha = frmR.getTxtFecha().getText().split("/");
                objF.setObjFCom(new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));
                
                objCli.setCedula(frmR.getTxtCedula().getText());
                objCli.setNom(frmR.getTxtNombre().getText());
                objCli.setTel(frmR.getTxtTel().getText());
                
                clienteDAO.setObjC(objCli);
                JOptionPane.showMessageDialog(frmR, clienteDAO.insertar());
                
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
                        JOptionPane.showMessageDialog(frmR, camaraDAO.insertar());
                        
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
                        JOptionPane.showMessageDialog(frmR, camaraDAO.insertar());
                        
                        objCon.escribeDatos(objF.datos());
                        break;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frmE, "Error al escribir el archivo: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frmE, "Error : " + ex.getMessage());
            }
        }

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
        
        if(e.getSource().equals(frmCBD.getBtnActualizar())){ //Boton actualizar
            switch(frmCBD.getPanelTabs().getSelectedIndex()){ 
                case 0: //Facturas
                    
                    break;
                case 1: //Camaras
                    enviarDatosTablaCamaras();
                    JOptionPane.showMessageDialog(frmCBD, camaraDAO.actualizar());
                    break;
                case 2: //Clientes
                    break;
            }
        }
     
        //TO DO
        if(e.getSource().equals(frmCBD.getBtnEliminar())){ //Boton eliminar
            switch(frmCBD.getPanelTabs().getSelectedIndex()){ 
                case 0: //Facturas
                    
                    break;
                case 1: //Camaras
                    enviarDatosTablaCamaras();
                    JOptionPane.showMessageDialog(frmCBD, camaraDAO.eliminar());
                    borrarFila(frmCBD.getTblCamaras());
                    break;
                case 2: //Clientes
                    break;
            }
        }
    }

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
    
    public void borrarFila(JTable tabla){
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        int fila = tabla.getSelectedRow();
        
        plantilla.removeRow(fila);
    }
}
