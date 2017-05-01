package manager.dialog;

/**
 * Plugin for the Base Dialog class that makes it connect to various tables as needed.
 * @author David Hrusa
 */
public interface TableHandler {
    /** Returns the text for the dialog Frame neame line. */
    public String getDisplayName();
    /** Returns the name of the table in the databse. */
    public String getTableName();
    /** Calls the select to reflect all changes. */
    public void reloadSelect(BaseDialog bd);
    /** Creates an insertion wizard for the new element. */
    public void newWizard(BaseDialog bd);
}