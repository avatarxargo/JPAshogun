package manager.window;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import manager.dialog.BaseDialog;
import manager.dialog.DBVariable.DBVarType;
import manager.dialog.ModularHandler;
import manager.dialog.ProvinceHandler;
import manager.dialog.popups.BaseDialogButton;
import manager.dialog.popups.JNewDayButton;
import manager.map.GraphEditor;
import manager.map.GraphEditorTester;
import manager.persistence.Building;
import manager.persistence.Clan;
import manager.persistence.Neighbour;
import manager.persistence.OwnedBuildings;
import manager.persistence.OwnedResource;
import manager.persistence.PlayerType;
import manager.persistence.Province;
import manager.persistence.Resource;
import manager.persistence.Simday;
import manager.persistence.TransactionBuild;
import manager.persistence.TransactionMove;
import manager.persistence.TransactionTrain;
import manager.persistence.pulled.ProvinceLocal;
import manager.persistence.pulled.ProvinceNeighborsLocal;

public class MainManager {

    //poznamka
    private static ManagerWindow mw;

    private static EntityTransaction tx;
    private static EntityManager em;

    private static JFrame toolkit;
    private static GraphEditorTester map;

    public static boolean inited = false;
    
    public static ModularHandler mhprovince, mhnrighbor, mhplayertype,
            mpresource, mhday, mhclan, mhplayer, mhbuilding, mhownbuilding, mhownres,
            mhtransactions, mhtranb, mhtrant, mhtranmov;

    public static void main(String[] args) {
        init();
        //mw = new ManagerWindow();

        toolkit = new JFrame("Shogun Admin Toolkit");
        toolkit.setLayout(new GridLayout(14, 1));
        toolkit.setSize(350, 600);
        map = new GraphEditorTester();

        mhprovince = new ModularHandler("Province", "province", true, Province.class);
        mhprovince.addVariable("id", "id_province", DBVarType.LONG);
        mhprovince.addVariable("name", "name_province", DBVarType.VARCHAR);
        mhprovince.addVariable("garrison", "army_units", DBVarType.LONG);
        mhprovince.addVariable("clan", "clan_control_id", DBVarType.CLAN_FK);
        mhprovince.addVariable("x", "x", DBVarType.LONG);
        mhprovince.addVariable("y", "y", DBVarType.LONG);
        mhprovince.addVariable("train cost", "cost_one_army_unit_value", DBVarType.LONG);
        mhprovince.addVariable("train res", "cost_one_army_unit_resource_id", DBVarType.RESOURCE_FK);
        BaseDialog baseDialog = new BaseDialog(mhprovince);

        mhnrighbor = new ModularHandler("Neighbour", "neighbour", true, Neighbour.class);
        mhnrighbor.addVariable("id", "id_neighbour", DBVarType.LONG);
        mhnrighbor.addVariable("province 1", "first_province_id", DBVarType.PROVINCE_FK);
        mhnrighbor.addVariable("province 2", "second_province_id", DBVarType.PROVINCE_FK);
        BaseDialog baseDialog2 = new BaseDialog(mhnrighbor);

        mhplayertype = new ModularHandler("Player type", "player_type", true, PlayerType.class);
        mhplayertype.addVariable("id", "id_player_type", DBVarType.LONG);
        mhplayertype.addVariable("name", "type_name", DBVarType.VARCHAR);
        BaseDialog baseDialog3 = new BaseDialog(mhplayertype);

        mpresource = new ModularHandler("Resource", "resource", true, Resource.class);
        mpresource.addVariable("id", "id_resource", DBVarType.LONG);
        mpresource.addVariable("name", "name_resource", DBVarType.VARCHAR);
        BaseDialog baseDialog4 = new BaseDialog(mpresource);

        mhday = new ModularHandler("Simday", "simday", true, Simday.class);
        mhday.addVariable("id", "id_simday", DBVarType.LONG);
        mhday.addVariable("day number", "day_number", DBVarType.LONG);
        BaseDialog baseDialog5 = new BaseDialog(mhday);

        mhclan = new ModularHandler("Clan", "clan", true, Clan.class);
        mhclan.addVariable("id", "id_clan", DBVarType.LONG);
        mhclan.addVariable("name", "name_clan", DBVarType.VARCHAR);
        BaseDialog baseDialog6 = new BaseDialog(mhclan);

        mhplayer = new ModularHandler("Player", "player", true, Clan.class);
        mhplayer.addVariable("id", "id_player", DBVarType.LONG);
        mhplayer.addVariable("name", "name", DBVarType.VARCHAR);
        mhplayer.addVariable("login", "login", DBVarType.VARCHAR);
        mhplayer.addVariable("password", "password", DBVarType.VARCHAR);
        mhplayer.addVariable("type", "player_type_id", DBVarType.PLTYPE_FK);
        mhplayer.addVariable("clan", "clan_id", DBVarType.CLAN_FK);
        BaseDialog baseDialog7 = new BaseDialog(mhplayer);

        mhbuilding = new ModularHandler("Building", "building", true, Building.class);
        mhbuilding.addVariable("id", "id_building", DBVarType.LONG);
        mhbuilding.addVariable("name", "name_building", DBVarType.VARCHAR);
        mhbuilding.addVariable("cost", "cost_value", DBVarType.LONG);
        mhbuilding.addVariable("resource", "cost_resource_id", DBVarType.RESOURCE_FK);
        BaseDialog baseDialog8 = new BaseDialog(mhbuilding);

        mhownbuilding = new ModularHandler("Owned Building", "owned_buildings", true, OwnedBuildings.class);
        mhownbuilding.addVariable("id", "id_owned_buildings", DBVarType.LONG);
        mhownbuilding.addVariable("province", "province_id", DBVarType.PROVINCE_FK);
        mhownbuilding.addVariable("building", "building_id", DBVarType.BUILDING_FK);
        mhownbuilding.addVariable("count", "amount_buildings", DBVarType.LONG);
        BaseDialog baseDialog9 = new BaseDialog(mhownbuilding);

        mhownres = new ModularHandler("Owned Resource", "owned_resource", true, OwnedResource.class);
        mhownres.addVariable("id", "id_owned_resources", DBVarType.LONG);
        mhownres.addVariable("resource", "resource_id", DBVarType.RESOURCE_FK);
        mhownres.addVariable("clan", "clan_id", DBVarType.CLAN_FK);
        mhownres.addVariable("amount", "amount_resources", DBVarType.LONG);
        BaseDialog baseDialog10 = new BaseDialog(mhownres);
        /*
         mhtransactions = new ModularHandler("Transactions","transactions",true,Transactions.class);
         mhtransactions.addVariable("type", "type", DBVarType.VARCHAR);
         mhtransactions.addVariable("simday", "day", DBVarType.LONG);
         mhtransactions.addVariable("province 1", "province1", DBVarType.PROVINCE_FK);
         mhtransactions.addVariable("province 2", "province2", DBVarType.PROVINCE_FK);
         mhtransactions.addVariable("count", "count", DBVarType.LONG);
         mhtransactions.addVariable("building", "building", DBVarType.BUILDING_FK);   
         BaseDialog baseDialog10 = new BaseDialog(mhtransactions);
         */
        mhtranb = new ModularHandler("Transaction Build", "transaction_build", true, TransactionBuild.class);
        mhtranb.addVariable("id", "id_transaction_build", DBVarType.LONG);
        mhtranb.addVariable("day", "simday_number", DBVarType.SIMDAY_NUMBER);
        mhtranb.addVariable("province", "province_id", DBVarType.PROVINCE_FK);
        mhtranb.addVariable("building type", "building_type_id", DBVarType.BUILDING_FK);
        mhtranb.addVariable("count", "count_buildings", DBVarType.LONG);
        BaseDialog baseDialog11 = new BaseDialog(mhtranb);

        mhtrant = new ModularHandler("Transaction Train", "transaction_train", true, TransactionTrain.class);
        mhtrant.addVariable("id", "id_transaction_train", DBVarType.LONG);
        mhtrant.addVariable("day", "simday_number", DBVarType.SIMDAY_NUMBER);
        mhtrant.addVariable("province", "province_id", DBVarType.PROVINCE_FK);
        mhtrant.addVariable("count", "count_army", DBVarType.LONG);
        BaseDialog baseDialog12 = new BaseDialog(mhtrant);

        mhtranmov = new ModularHandler("Transaction Move", "transaction_move", true, TransactionMove.class);
        mhtranmov.addVariable("id", "id_transaction_move", DBVarType.LONG);
        mhtranmov.addVariable("day", "simday_number", DBVarType.SIMDAY_NUMBER);
        mhtranmov.addVariable("issuer", "clan_issue_id", DBVarType.CLAN_FK);
        mhtranmov.addVariable("from province", "province_from_id", DBVarType.PROVINCE_FK);
        mhtranmov.addVariable("to province", "province_to_id", DBVarType.PROVINCE_FK);
        mhtranmov.addVariable("number of troops", "army_units", DBVarType.LONG);
        BaseDialog baseDialog13 = new BaseDialog(mhtranmov);

        toolkit.add(new BaseDialogButton(baseDialog));
        toolkit.add(new BaseDialogButton(baseDialog2));
        toolkit.add(new BaseDialogButton(baseDialog3));
        toolkit.add(new BaseDialogButton(baseDialog4));
        toolkit.add(new BaseDialogButton(baseDialog5));
        toolkit.add(new BaseDialogButton(baseDialog6));
        toolkit.add(new BaseDialogButton(baseDialog7));
        toolkit.add(new BaseDialogButton(baseDialog8));
        toolkit.add(new BaseDialogButton(baseDialog9));
        toolkit.add(new BaseDialogButton(baseDialog10));
        toolkit.add(new BaseDialogButton(baseDialog11));
        toolkit.add(new BaseDialogButton(baseDialog12));
        toolkit.add(new BaseDialogButton(baseDialog13));
        toolkit.add(new JNewDayButton());
        toolkit.setVisible(true);
    }

    public static EntityManager getEM() {
        return em;
    }

    public static GraphEditor getGraphEditor() {
        return map.getEditor();
    }

    public static void reeditor() {
        init();
        if(map!=null) {
            map.setVisible(false);
            map.dispose();
        }
        map = new GraphEditorTester();
    }

    /**
     * Connects to the database
     */
    public static void init() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAshogunPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
        } catch (final PersistenceException e) {
            JOptionPane.showMessageDialog(map, "Trouble connecting to server",
                    "Connection Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        inited = true;
        //loadProvinces("province.txt");
    }

    public static void loadProvinces(String provinces) {
        File file = new File(provinces);
        if (file.exists()) {
            try {
                Scanner scan = new Scanner(file);
                String line = "";
                String[] split;
                String prov1, prov2;
                while (scan.hasNextLine()) {
                    line = scan.nextLine();
                    if (line.equals("#")) {
                        break;
                    }
                    int mx, my;
                    split = line.split(" ");
                    mx = Integer.parseInt(split[1].trim());
                    my = Integer.parseInt(split[2].trim());
                    prov1 = split[0].trim();
                    addProvince(mx, my, prov1);
                }
                while (scan.hasNextLine()) {
                    line = scan.nextLine();
                    split = line.split(" ");
                    prov1 = split[0].trim();
                    prov2 = split[1].trim();
                    connectProvinces(prov1, prov2);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addProvince(float x, float y, String name) {
        ProvinceLocal p = ProvinceLocal.createProvince(name, x, y);
        tx.begin();
        em.persist(p);
        tx.commit();
    }

    public static void connectProvinces(String a, String b) {
        long ida = pullIdByProvinceName(a);
        long idb = pullIdByProvinceName(b);
        System.out.println(a + " " + ida + " -> " + b + " " + idb);
        ProvinceLocal pa = em.find(ProvinceLocal.class, ida);
        ProvinceLocal pb = em.find(ProvinceLocal.class, idb);
        ProvinceNeighborsLocal n = ProvinceNeighborsLocal.createNeighbors(pa, pb);
        tx.begin();
        em.persist(n);
        tx.commit();
    }

    public static long pullIdByProvinceName(String a) {
        Query queryN = MainManager.getEM().createNativeQuery("SELECT province_id FROM province where province_name=?");
        queryN.setParameter(1, a);
        List<Object> listN = queryN.getResultList();
        if (listN.isEmpty()) {
            return -1;
        } else {
            return (long) listN.get(0);
        }
    }
}
