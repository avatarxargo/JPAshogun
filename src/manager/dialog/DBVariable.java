package manager.dialog;

import manager.persistence.Building;
import manager.persistence.Clan;
import manager.persistence.PlayerType;
import manager.persistence.Province;
import manager.persistence.Resource;
import manager.persistence.Simday;
import manager.persistence.TransactionBuild;
import manager.persistence.TransactionMove;
import manager.persistence.TransactionTrain;
import manager.window.MainManager;

public class DBVariable {

    public String dname, idname;
    public DBVarType type;

    public DBVariable(String displayName, String dbName, DBVarType type) {
        dname = displayName;
        idname = dbName;
        this.type = type;
    }

    public enum DBVarType {
        VARCHAR, LONG, PROVINCE_FK, CLAN_FK, RESOURCE_FK, PLTYPE_FK, BUILDING_FK,
        SIMDAY_NUMBER, TRANSACTION_BUILD_FK, TRANSACTION_TRAIN_FK, TRANSACTION_MOVE_FK;
    }
    
    public static Object parseValue(Object obj, DBVarType vartype) {
        if(obj==null) {return obj;}
        switch(vartype) {
            case PROVINCE_FK:
                Province prv = MainManager.getEM().find(Province.class, obj);
                return prv.getNameProvince();
            case CLAN_FK:
                Clan cl = MainManager.getEM().find(Clan.class, obj);
                return cl.getNameClan();
            case RESOURCE_FK:
                Resource res = MainManager.getEM().find(Resource.class, obj);
                return res.getNameResource();
            case PLTYPE_FK:
                PlayerType pt = MainManager.getEM().find(PlayerType.class, obj);
                return pt.getTypeName();
            case BUILDING_FK:
                Building bui = MainManager.getEM().find(Building.class, obj);
                return bui.getNameBuilding();
            case TRANSACTION_BUILD_FK:
                TransactionBuild tb = MainManager.getEM().find(TransactionBuild.class, obj);
                return tb.getCountBuildings()+"x "+tb.getBuildingTypeId().getNameBuilding()+" in "+tb.getProvinceId().getNameProvince();
            case TRANSACTION_TRAIN_FK:
                TransactionTrain tt = MainManager.getEM().find(TransactionTrain.class, obj);
                return tt.getCountArmy()+"x in "+tt.getProvinceId().getNameProvince();
            case TRANSACTION_MOVE_FK:
                TransactionMove tm = MainManager.getEM().find(TransactionMove.class, obj);
                return tm.getArmyUnits()+"x from "+tm.getProvinceFromId().getNameProvince()+" to "+tm.getProvinceToId().getNameProvince();
            case SIMDAY_NUMBER:
                Simday sd = MainManager.getEM().find(Simday.class, obj);
                return sd.getDayNumber();
            default:
                return obj;
                
        //TRANSACTION_BUILD_FK, TRANSACTION_TRAIN_FK, TRANSACTION_MOVE_FK;
        }
    }
}
