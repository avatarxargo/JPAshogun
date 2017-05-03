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
import manager.persistence.ProvinceLocal;
import manager.persistence.ProvinceNeighborsLocal;

public class MainManager {
    
    //poznamka
    private static ManagerWindow mw;

    private static EntityTransaction tx;
    private static EntityManager em;

    public static void main(String[] args) {
        init();
        mw = new ManagerWindow();
        ModularHandler mh = new ModularHandler("Province","province");
        mh.addVariable("id", "province_id", DBVarType.LONG);
        mh.addVariable("name", "province_name", DBVarType.VARCHAR);
        mh.addVariable("x", "x", DBVarType.LONG);
        mh.addVariable("y", "y", DBVarType.LONG);
        BaseDialog baseDialog = new BaseDialog(mh);
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
