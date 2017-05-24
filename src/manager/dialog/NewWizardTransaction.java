/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.Query;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import manager.dialog.popups.JProvinceButton;
import manager.dialog.popups.ProvinceInspector;
import manager.persistence.Building;
import manager.persistence.Province;
import manager.persistence.TransactionBuild;
import manager.window.MainManager;

/**
 *
 * @author user
 */
public class NewWizardTransaction extends javax.swing.JFrame {

    Province prov;
    ProvinceInspector pi;

    /**
     * Creates new form NewWizardTransaction
     */
    public NewWizardTransaction(Province prov, ProvinceInspector pi) {
        initComponents();
        this.prov = prov;
        this.pi = pi;
        this.setLocation(100, 100);
        this.setTitle(prov.getNameProvince() + " " + this.getTitle());
        NewWizardTransaction me = this;
        buildcombo.loadProvinces();
        //
        submitbutton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        switch (tabs.getSelectedIndex()) {
                            case 0:
                                insertBuild();
                                break;
                            case 1:
                                insertTrain();
                                break;
                            default:
                                insertMove();
                                break;
                        }
                        me.repaint();
                    }
                }
        );
        // build info
        buildcombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refreshBuild();
            }
        });
        buildspin.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                refreshBuild();
            }
        });
        // train info
        trainspin.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                refreshTrain();
            }
        });
        movspin.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                refreshMove();
            }
        });
        refreshBuild();
        refreshTrain();
        refreshMove();
        this.setVisible(true);
    }

    private void refreshBuild() {
        String txt = "Resource Balance: ";
        Building b = MainManager.getEM().find(Building.class, buildcombo.getId(buildcombo.getSelectedIndex()));
        String transQ = "SELECT get_clan_res(?,?)";
        Query q = MainManager.getEM().createNativeQuery(transQ);
        q.setParameter(1, prov.getClanControlId().getIdClan());
        q.setParameter(2, b.getCostResourceId().getIdResource());
        Object res = q.getResultList().get(0);
        txt += "[" + b.getCostValue() + " " + b.getCostResourceId().getNameResource() + " per building ] " + (int) res + " -> " + ((int) res - (b.getCostValue() * (int) buildspin.getValue()));
        buildstat.setText(txt);
    }

    private void refreshMove() {
        String txt = "Garrison Status: ";
        txt += prov.getArmyUnits() + " -> " + (prov.getArmyUnits() - (int) movspin.getValue());
        movstat.setText(txt);
    }

    private void refreshTrain() {
        String txt = "Resource Balance: ";
        String transQ = "SELECT get_clan_res(?1,?2), name_resource FROM resource WHERE id_resource = ?2";
        Query q = MainManager.getEM().createNativeQuery(transQ);
        q.setParameter(1, prov.getClanControlId().getIdClan());
        q.setParameter(2, prov.getCostOneArmyUnitResourceId());
        List<Object[]> res = q.getResultList();
        txt += "[" + prov.getCostOneArmyUnitValue() + " " + res.get(0)[1] + " per unit ] " + res.get(0)[0] + " -> " + ((int) res.get(0)[0] - prov.getCostOneArmyUnitValue() * (int) trainspin.getValue());
        trainstat.setText(txt);
    }

    private void insertBuild() {
        dbstatus.setText("building " + buildcombo.getSelectedItem().toString() + " tiems " + buildspin.getValue());
        String transQ = "INSERT INTO transaction_build (simday_number,province_id,building_type_id,count_buildings) VALUES (?,?,?,?);";
        Query q = MainManager.getEM().createNativeQuery(transQ);
        q.setParameter(1, 1);
        q.setParameter(2, prov.getIdProvince());
        q.setParameter(3, buildcombo.getId(buildcombo.getSelectedIndex()));
        q.setParameter(4, buildspin.getValue());
        MainManager.getEM().getTransaction().begin();
        q.executeUpdate();
        MainManager.getEM().getTransaction().commit();
        pi.refresh();
    }

    private void insertTrain() {
        dbstatus.setText("training " + trainspin.getValue() + " troops");
        String transQ = "INSERT INTO transaction_train (simday_number,province_id,count_army) VALUES (?,?,?);";
        Query q = MainManager.getEM().createNativeQuery(transQ);
        q.setParameter(1, 1);
        q.setParameter(2, prov.getIdProvince());
        q.setParameter(3, trainspin.getValue());
        MainManager.getEM().getTransaction().begin();
        q.executeUpdate();
        MainManager.getEM().getTransaction().commit();
        pi.refresh();
    }

    private void insertMove() {
        dbstatus.setText("moving " + movspin.getValue() + " troops to " + movprov.getText() + ".");
        String transQ = "INSERT INTO transaction_move (simday_number,province_from_id,province_to_id,army_units) VALUES (?,?,?,?);";
        Query q = MainManager.getEM().createNativeQuery(transQ);
        q.setParameter(1, 1);
        q.setParameter(2, prov.getIdProvince());
        q.setParameter(3, movprov.getFK());
        q.setParameter(4, movspin.getValue());
        MainManager.getEM().getTransaction().begin();
        q.executeUpdate();
        MainManager.getEM().getTransaction().commit();
        pi.refresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        tabbuild = new javax.swing.JPanel();
        buildspin = new javax.swing.JSpinner();
        buildstat = new javax.swing.JLabel();
        buildcombo = new manager.dialog.popups.JBuildingCombo();
        tabtrain = new javax.swing.JPanel();
        trainspin = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        traincost = new javax.swing.JLabel();
        trainstat = new javax.swing.JLabel();
        tabmove = new javax.swing.JPanel();
        movspin = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        movstat = new javax.swing.JLabel();
        movprov = new manager.dialog.popups.JProvinceButton();
        submitbutton = new javax.swing.JButton();
        dbstatus = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transaction Wizard");

        buildspin.setValue(1);

        buildstat.setText("Resource Balance: 20 -> 10");

        javax.swing.GroupLayout tabbuildLayout = new javax.swing.GroupLayout(tabbuild);
        tabbuild.setLayout(tabbuildLayout);
        tabbuildLayout.setHorizontalGroup(
            tabbuildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabbuildLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabbuildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buildstat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabbuildLayout.createSequentialGroup()
                        .addComponent(buildcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buildspin, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabbuildLayout.setVerticalGroup(
            tabbuildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabbuildLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(tabbuildLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buildspin, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(buildcombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buildstat)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tabs.addTab("build", tabbuild);

        trainspin.setValue(1);

        jLabel4.setText("Train soldiers:");

        traincost.setText("Cost of one soldier:");

        trainstat.setText("Resource Balance: 20 -> 10");

        javax.swing.GroupLayout tabtrainLayout = new javax.swing.GroupLayout(tabtrain);
        tabtrain.setLayout(tabtrainLayout);
        tabtrainLayout.setHorizontalGroup(
            tabtrainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabtrainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabtrainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabtrainLayout.createSequentialGroup()
                        .addComponent(traincost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trainspin, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabtrainLayout.createSequentialGroup()
                        .addComponent(trainstat)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabtrainLayout.setVerticalGroup(
            tabtrainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabtrainLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(tabtrainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trainspin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(traincost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trainstat)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tabs.addTab("train", tabtrain);

        movspin.setValue(1);

        jLabel7.setText("Soldiers to move:");

        jLabel8.setText("Move to:");

        movstat.setText("Garrison status: 100 -> 60");

        movprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movprovActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabmoveLayout = new javax.swing.GroupLayout(tabmove);
        tabmove.setLayout(tabmoveLayout);
        tabmoveLayout.setHorizontalGroup(
            tabmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabmoveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabmoveLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movprov, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movspin, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabmoveLayout.createSequentialGroup()
                        .addComponent(movstat)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabmoveLayout.setVerticalGroup(
            tabmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabmoveLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(tabmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(movprov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabmoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(movspin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movstat)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tabs.addTab("move", tabmove);

        submitbutton.setText("Submit Transaction");
        submitbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitbuttonActionPerformed(evt);
            }
        });

        dbstatus.setText("Connection Status");
        dbstatus.setToolTipText("");
        dbstatus.setEnabled(false);
        dbstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbstatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(dbstatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabs, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitbutton, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(dbstatus))
                .addContainerGap())
        );

        tabs.getAccessibleContext().setAccessibleName("tabs");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitbuttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_submitbuttonActionPerformed

    private void dbstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dbstatusActionPerformed

    private void movprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movprovActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_movprovActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private manager.dialog.popups.JBuildingCombo buildcombo;
    private javax.swing.JSpinner buildspin;
    private javax.swing.JLabel buildstat;
    private javax.swing.JTextField dbstatus;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private manager.dialog.popups.JProvinceButton movprov;
    private javax.swing.JSpinner movspin;
    private javax.swing.JLabel movstat;
    private javax.swing.JButton submitbutton;
    private javax.swing.JPanel tabbuild;
    private javax.swing.JPanel tabmove;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JPanel tabtrain;
    private javax.swing.JLabel traincost;
    private javax.swing.JSpinner trainspin;
    private javax.swing.JLabel trainstat;
    // End of variables declaration//GEN-END:variables
}
