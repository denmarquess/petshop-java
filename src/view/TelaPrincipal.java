/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import model.JDesktopPaneComImage;

/**
 *
 * @author bruno
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
               
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPmenuP1 = new javax.swing.JPopupMenu();
        desktop = new JDesktopPaneComImage("/imagens/JonesPS.png");
        jbClientes = new javax.swing.JButton();
        jbAgenda = new javax.swing.JButton();
        jbProdutos = new javax.swing.JButton();
        jbServicos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuCad = new javax.swing.JMenu();
        itemCadUser = new javax.swing.JMenuItem();
        MenuGerenciar = new javax.swing.JMenu();
        ItemGerUser = new javax.swing.JMenuItem();
        itemCadProduto = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktop.setBackground(new java.awt.Color(207, 202, 196));
        desktop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cliente_color.png"))); // NOI18N
        jbClientes.setText("Clientes");
        jbClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbClientesActionPerformed(evt);
            }
        });

        jbAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/agenda_collor.png"))); // NOI18N
        jbAgenda.setText("Agenda");
        jbAgenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbAgenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAgendaActionPerformed(evt);
            }
        });

        jbProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/produtos.png"))); // NOI18N
        jbProdutos.setText("Produtos");
        jbProdutos.setAutoscrolls(true);
        jbProdutos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbProdutos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbProdutosMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbProdutosMouseClicked(evt);
            }
        });
        jbProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProdutosActionPerformed(evt);
            }
        });

        jbServicos.setText("Serviços");
        jbServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbServicosMouseClicked(evt);
            }
        });
        jbServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbServicosActionPerformed(evt);
            }
        });

        jButton1.setText("animal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenuBar1.setPreferredSize(new java.awt.Dimension(77, 30));

        MenuCad.setText("Cadastro");

        itemCadUser.setText("Usuário");
        itemCadUser.setEnabled(false);
        itemCadUser.setPreferredSize(new java.awt.Dimension(75, 22));
        itemCadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCadUserActionPerformed(evt);
            }
        });
        MenuCad.add(itemCadUser);

        jMenuBar1.add(MenuCad);

        MenuGerenciar.setText("Gerenciar");

        ItemGerUser.setText("Usuários");
        ItemGerUser.setEnabled(false);
        ItemGerUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemGerUserActionPerformed(evt);
            }
        });
        MenuGerenciar.add(ItemGerUser);

        itemCadProduto.setText("Produto");
        MenuGerenciar.add(itemCadProduto);

        jMenuBar1.add(MenuGerenciar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbProdutos))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jbServicos))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jbAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jbServicos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jbProdutos)))
                .addGap(0, 423, Short.MAX_VALUE))
            .addComponent(desktop)
        );

        setSize(new java.awt.Dimension(1450, 835));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemCadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCadUserActionPerformed
        TelaCadastoUsuario tcu = new TelaCadastoUsuario();
        tcu.setVisible(true);
    }//GEN-LAST:event_itemCadUserActionPerformed

    private void ItemGerUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemGerUserActionPerformed
        new TelaGerenciaUsuario().setVisible(true);
    }//GEN-LAST:event_ItemGerUserActionPerformed

    private void jbProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProdutosActionPerformed
  
    }//GEN-LAST:event_jbProdutosActionPerformed

    private void jbAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAgendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbAgendaActionPerformed

    private void jbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbClientesActionPerformed

    private void jbServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbServicosActionPerformed
       TelaServico ts = new TelaServico();
       desktop.add(ts);
       ts.setVisible(true);
    }//GEN-LAST:event_jbServicosActionPerformed

    private void jbProdutosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbProdutosMousePressed

    }//GEN-LAST:event_jbProdutosMousePressed

    private void jbProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbProdutosMouseClicked
       
        TelaProdutos tb = new TelaProdutos();
        desktop.add(tb);
        tb.setVisible(true);
    }//GEN-LAST:event_jbProdutosMouseClicked

    private void jbServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbServicosMouseClicked
   
    }//GEN-LAST:event_jbServicosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            TelaAnimal ta = new TelaAnimal();
           //desktop.add(ta);
            ta.setVisible(true);
            ta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
            
            
    }//GEN-LAST:event_jButton1ActionPerformed

    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JMenuItem ItemGerUser;
    private javax.swing.JMenu MenuCad;
    private javax.swing.JMenu MenuGerenciar;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem itemCadProduto;
    public static javax.swing.JMenuItem itemCadUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPmenuP1;
    private javax.swing.JButton jbAgenda;
    private javax.swing.JButton jbClientes;
    private javax.swing.JButton jbProdutos;
    private javax.swing.JButton jbServicos;
    // End of variables declaration//GEN-END:variables
}
