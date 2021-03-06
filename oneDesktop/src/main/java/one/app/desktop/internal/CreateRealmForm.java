/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package one.app.desktop.internal;

import one.client.jre.OneJre;
import one.common.One;
import one.core.dsl.CoreDsl;
import one.core.dsl.callbacks.WhenRealmCreated;
import one.core.dsl.callbacks.WhenShutdown;
import one.core.dsl.callbacks.results.WithRealmCreatedResult;

/**
 *
 * @author mroh004
 */
public class CreateRealmForm extends javax.swing.JPanel {

    /**
     * Creates new form CreateRealmForm
     */
    public CreateRealmForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tilteField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        apiKeyField = new javax.swing.JTextField();
        createRealmButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputLog = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Title");

        jLabel2.setText("API-Key");

        createRealmButton.setText("Create");
        createRealmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRealmButtonActionPerformed(evt);
            }
        });

        outputLog.setColumns(20);
        outputLog.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        outputLog.setRows(5);
        jScrollPane1.setViewportView(outputLog);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(apiKeyField)
                            .addComponent(tilteField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(createRealmButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tilteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(apiKeyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createRealmButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void createRealmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createRealmButtonActionPerformed
        String apiKey = apiKeyField.getText();
        String title = tilteField.getText();

        CoreDsl dsl = OneJre.init(apiKey);

        createRealmButton.setEnabled(false);
        
        dsl.createRealm(title).and(new WhenRealmCreated() {

            @Override
            public void thenDo(WithRealmCreatedResult wrcr) {
                outputLog.setText( "Realm created successfully.\n"
                        + "  root            : " + wrcr.root().getId() + "\n"
                        + "  secret          : " + wrcr.secret() + "\n"
                        + "  secret (partner): " + wrcr.partnerSecret() + "\n"
                        + "  direct link     : \n"
                        + "           https://token:" + wrcr.secret() + "@" + wrcr.root().getId().replaceFirst("https://", "") +outputLog.getText());

                One.shutdown(wrcr.client()).and(new WhenShutdown() {

                    @Override
                    public void thenDo() {
                        createRealmButton.setEnabled(true);
                    }
                });

            }
        });
    }//GEN-LAST:event_createRealmButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apiKeyField;
    private javax.swing.JButton createRealmButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea outputLog;
    private javax.swing.JTextField tilteField;
    // End of variables declaration//GEN-END:variables
}
