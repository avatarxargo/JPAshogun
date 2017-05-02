package manager.dialog;

public class DBVariable {

    public String dname, idname;
    public DBVarType type;

    public DBVariable(String displayName, String dbName, DBVarType type) {
        dname = displayName;
        idname = dbName;
        this.type = type;
    }

    public enum DBVarType {
        VARCHAR, LONG, PROVINCE_FK;
    }
}
