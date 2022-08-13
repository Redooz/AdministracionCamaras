/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package vista;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

/**
 *
 * @author SnakyDH
 */
public class Escritorio extends javax.swing.JFrame {
    
    public Escritorio() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        desktopPane = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        lblHora = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        tbRegistrar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        tbConsultar = new javax.swing.JButton();
        tbConsultarBD = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        tbPDF = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        tbSalir = new javax.swing.JButton();
        mnuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        mnuRegistrar = new javax.swing.JMenuItem();
        mnuConsultar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        lblHora.setText("00:00:00");
        jToolBar1.add(lblHora);

        desktopPane.add(jToolBar1);
        jToolBar1.setBounds(0, 670, 1190, 40);

        toolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        toolBar.setRollover(true);

        tbRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/digital-camara.png"))); // NOI18N
        tbRegistrar.setText("Registrar");
        tbRegistrar.setFocusable(false);
        tbRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbRegistrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(tbRegistrar);
        toolBar.add(jSeparator5);

        tbConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/analoga-camara.png"))); // NOI18N
        tbConsultar.setText("Consultar");
        tbConsultar.setFocusable(false);
        tbConsultar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbConsultar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(tbConsultar);

        tbConsultarBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/analoga-camara.png"))); // NOI18N
        tbConsultarBD.setText("Consultar BD");
        tbConsultarBD.setFocusable(false);
        tbConsultarBD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbConsultarBD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(tbConsultarBD);
        toolBar.add(jSeparator4);

        tbPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N
        tbPDF.setText("Generar PDF");
        tbPDF.setFocusable(false);
        tbPDF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbPDF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(tbPDF);
        toolBar.add(jSeparator2);

        tbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn-salir.png"))); // NOI18N
        tbSalir.setFocusable(false);
        tbSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tbSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(tbSalir);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Acciones");

        mnuRegistrar.setMnemonic('o');
        mnuRegistrar.setText("Registrar");
        fileMenu.add(mnuRegistrar);

        mnuConsultar.setMnemonic('s');
        mnuConsultar.setText("Consultar");
        fileMenu.add(mnuConsultar);
        fileMenu.add(jSeparator3);

        mnuSalir.setMnemonic('x');
        mnuSalir.setText("Salir");
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        fileMenu.add(mnuSalir);

        mnuBar.add(fileMenu);

        setJMenuBar(mnuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
    }//GEN-LAST:event_mnuSalirActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblHora;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenuItem mnuConsultar;
    private javax.swing.JMenuItem mnuRegistrar;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JButton tbConsultar;
    private javax.swing.JButton tbConsultarBD;
    private javax.swing.JButton tbPDF;
    private javax.swing.JButton tbRegistrar;
    private javax.swing.JButton tbSalir;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public void setDesktopPane(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public void setFileMenu(JMenu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public JSeparator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JSeparator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JToolBar.Separator getjSeparator2() {
        return jSeparator2;
    }

    public void setjSeparator2(JToolBar.Separator jSeparator2) {
        this.jSeparator2 = jSeparator2;
    }

    public JPopupMenu.Separator getjSeparator3() {
        return jSeparator3;
    }

    public void setjSeparator3(JPopupMenu.Separator jSeparator3) {
        this.jSeparator3 = jSeparator3;
    }

    public JMenuBar getMnuBar() {
        return mnuBar;
    }

    public void setMnuBar(JMenuBar mnuBar) {
        this.mnuBar = mnuBar;
    }

    public JMenuItem getMnuConsultar() {
        return mnuConsultar;
    }

    public void setMnuConsultar(JMenuItem mnuConsultar) {
        this.mnuConsultar = mnuConsultar;
    }

    public JMenuItem getMnuRegistrar() {
        return mnuRegistrar;
    }

    public void setMnuRegistrar(JMenuItem mnuRegistrar) {
        this.mnuRegistrar = mnuRegistrar;
    }

    public JMenuItem getMnuSalir() {
        return mnuSalir;
    }

    public void setMnuSalir(JMenuItem mnuSalir) {
        this.mnuSalir = mnuSalir;
    }

    public JButton getTbConsultar() {
        return tbConsultar;
    }

    public void setTbConsultar(JButton tbConsultar) {
        this.tbConsultar = tbConsultar;
    }

    public JButton getTbRegistrar() {
        return tbRegistrar;
    }

    public void setTbRegistrar(JButton tbRegistrar) {
        this.tbRegistrar = tbRegistrar;
    }

    public JButton getTbSalir() {
        return tbSalir;
    }

    public void setTbSalir(JButton tbSalir) {
        this.tbSalir = tbSalir;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public JToolBar.Separator getjSeparator4() {
        return jSeparator4;
    }

    public void setjSeparator4(JToolBar.Separator jSeparator4) {
        this.jSeparator4 = jSeparator4;
    }

    public JButton getTbPDF() {
        return tbPDF;
    }

    public void setTbPDF(JButton tbPDF) {
        this.tbPDF = tbPDF;
    }

    public JButton getTbConsultarBD() {
        return tbConsultarBD;
    }

    public void setTbConsultarBD(JButton tbConsultarBD) {
        this.tbConsultarBD = tbConsultarBD;
    }

    public JLabel getLblHora() {
        return lblHora;
    }

    public void setLblHora(JLabel lblHora) {
        this.lblHora = lblHora;
    }
    
    
}
