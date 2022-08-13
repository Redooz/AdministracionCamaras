package modelo;

public class Factura {
    private String codigo;
    private Cliente objCli;
    private Camara objCam;
    private Fecha objFCom;
    
    public Factura() {
        this.codigo = "";
        this.objCli = new Cliente();
        this.objCam = null;
        this.objFCom = new Fecha();
    }

    public Factura(String codigo, Cliente objCli, Camara objCam, Fecha objFCom) {
        this.codigo = codigo;
        this.objCli = objCli;
        this.objCam = objCam;
        this.objFCom = objFCom;
    }

    public double precioTotal(){
        if (objFCom.diaSemanaDesdeAtributos() == 1) { //Miercoles = 1
            return objCam.getPrecio()-objCam.descuento();
        } else {
            return objCam.getPrecio();
        }
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getObjCli() {
        return objCli;
    }

    public void setObjCli(Cliente objCli) {
        this.objCli = objCli;
    }

    public Camara getObjCam() {
        return objCam;
    }

    public void setObjCam(Camara objCam) {
        this.objCam = objCam;
    }

    public Fecha getObjFCom() {
        return objFCom;
    }

    public void setObjFCom(Fecha objFCom) {
        this.objFCom = objFCom;
    }

    @Override
    public String toString() {
        return "\nCodigo: " + codigo + "\nCliente:" + objCli.toString() + "\nCamara: " + objCam.toString() + "\nFecha: " + objFCom.toString();
    }
    
    public String datos(){
        return codigo + ";" + objCli.datos() + ";" + objCam.datos() + ";" + precioTotal();
    }
}
