package manager.window;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import manager.dialog.BaseDialog;
import manager.dialog.DBVariable.DBVarType;
import manager.dialog.ModularHandler;
import manager.dialog.ProvinceHandler;
import manager.dialog.popups.BaseDialogButton;
import manager.persistence.Clan;
import manager.persistence.PlayerType;
import manager.persistence.Province;
import manager.persistence.Resource;
import manager.persistence.Simday;
import manager.persistence.pulled.ProvinceLocal;
import manager.persistence.pulled.ProvinceNeighborsLocal;

public class MainManager {
    
    //poznamka
    private static ManagerWindow mw;

    private static EntityTransaction tx;
    private static EntityManager em;
    
    public static ModularHandler mhprovince, mhnrighbor, mhplayertype,
            mpresource, mhday, mhclan, mhplayer;

    public static void main(String[] args) {
        init();
        mw = new ManagerWindow();
        
        mhprovince = new ModularHandler("Province","province",true,Province.class);
        mhprovince.addVariable("id", "id_province", DBVarType.LONG);
        mhprovince.addVariable("name", "province_name", DBVarType.VARCHAR);
        mhprovince.addVariable("garrison", "army_units", DBVarType.LONG);
        mhprovince.addVariable("clan", "clan_id_clan", DBVarType.CLAN_FK);
        mhprovince.addVariable("x", "x", DBVarType.LONG);
        mhprovince.addVariable("y", "y", DBVarType.LONG);
        BaseDialog baseDialog = new BaseDialog(mhprovince);
        
        mhnrighbor = new ModularHandler("Neighbour","neighbour",false,null);
        mhnrighbor.addVariable("id1", "province_id_province1", DBVarType.PROVINCE_FK);
        mhnrighbor.addVariable("id2", "province_id_province2", DBVarType.PROVINCE_FK);
        BaseDialog baseDialog2 = new BaseDialog(mhnrighbor);
        
        mhplayertype = new ModularHandler("Player type","player_type",true,PlayerType.class);
        mhplayertype.addVariable("id", "id_player_type", DBVarType.LONG);
        mhplayertype.addVariable("name", "type_name", DBVarType.VARCHAR);
        BaseDialog baseDialog3 = new BaseDialog(mhplayertype);
        
        mpresource = new ModularHandler("Resource","resource",true,Resource.class);
        mpresource.addVariable("id", "id_resurce", DBVarType.LONG);
        mpresource.addVariable("name", "resource_name", DBVarType.VARCHAR);
        BaseDialog baseDialog4 = new BaseDialog(mpresource);
        
        mhday = new ModularHandler("Day","simday",false,Simday.class);
        mhday.addVariable("id", "id_simday", DBVarType.LONG);
        BaseDialog baseDialog5 = new BaseDialog(mhday);
        
        mhclan = new ModularHandler("Clan","clan",true,Clan.class);
        mhclan.addVariable("id", "id_clan", DBVarType.LONG);
        mhclan.addVariable("name", "nameclan", DBVarType.VARCHAR);
        BaseDialog baseDialog6 = new BaseDialog(mhclan);
        
        mhplayer = new ModularHandler("Player","player",true,Clan.class);
        mhplayer.addVariable("id", "id_player", DBVarType.LONG);
        mhplayer.addVariable("name", "name", DBVarType.VARCHAR);
        mhplayer.addVariable("login", "login", DBVarType.VARCHAR);
        mhplayer.addVariable("password", "password_2", DBVarType.VARCHAR);
        mhplayer.addVariable("type", "player_type_id_player_type", DBVarType.LONG);
        mhplayer.addVariable("clan", "clan_id_clan", DBVarType.CLAN_FK);
        BaseDialog baseDialog7 = new BaseDialog(mhplayer);
        
        mw.addButton(new BaseDialogButton(baseDialog));
        mw.addButton(new BaseDialogButton(baseDialog2));
        mw.addButton(new BaseDialogButton(baseDialog3));
        mw.addButton(new BaseDialogButton(baseDialog4));
        mw.addButton(new BaseDialogButton(baseDialog5));
        mw.addButton(new BaseDialogButton(baseDialog6));
        mw.addButton(new BaseDialogButton(baseDialog7));
    }
    
    public static EntityManager getEM() {
        return em;
    }

    /**
     * Connects to the database
     */
    public static void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAshogunPU");
        em = emf.createEntityManager();
        tx = em.getTransaction();

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
        System.out.println(a+" "+ida+" -> "+b+" "+idb);
        ProvinceLocal pa = em.find(ProvinceLocal.class, ida);
        ProvinceLocal pb = em.find(ProvinceLocal.class, idb);
        ProvinceNeighborsLocal n = ProvinceNeighborsLocal.createNeighbors(pa,pb);
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
