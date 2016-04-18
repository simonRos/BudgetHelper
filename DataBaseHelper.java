import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
  public static final String dbName = "BHDB.db";
  //Person Table
  public static final String personTable = "person";
  public static final String username = "username";
  public static final String password = "password";
  //IOU table
  public static final String iouTable = "IOU";
  public static final String iouUser = "IOU_Username";
  public static final String iouID = "iou_ID";
  public static final String description = "description";
  public static final String toFrom = "To_From";
  public static final String iouamount = "amount";
  public static final String ioudate = "date";
  //Reminder
  public static final String reminderTable = "reminder";
  public static final String reminderIOU = "reminder_iouID";
  public static final String message = "message";
  public static final String remDate = "date";
  //Spendings
  public static final String spendingsTable = "spendings";
  public static final String spendUser = "Spend_Username";
  public static final String spendingsID = "spendings_ID";
  public static final String spendingAmount = "spending_amount";
  public static final String spendingDate = "spending_date";
  //WeeklyBudget
  public static final String budgetTable = "weekly_budget";
  public static final String budgetUser = "budget_user";
  public static final String weeklyBudget = "budget_amount";
  
  
  
  //Constructor
  public DataBaseHelper(Context context){
    super(context, dbName, null, 1);
  }
  
  @Override
  public void onCreate(SQLiteDatabase db){
    db.excecSQLite("create table " + personTable + " (USERNAME TEXT PRIMARY KEY, USERNAME TEXT)");
    db.excecSQLite("create table " + iouTable + " (IOUID TEXT PRIMARY KEY, DESCRIPTION TEXT, TOFROM TEXT, IOUAMOUNT REAL, IOUDATE TEXT, IOUUSER TEXT, FOREIGN kEY(IOUUSER) REFERENCES PERSONTABLE(USERNAME)");
    db.excecSQLite("create table " + reminderTable + " (MESSAGE TEXT, REMDATE TEXT, REMINDIOU INT, FOREIGN kEY(REMINDIOU) REFERENCES IOUTABLE(IOUID))");
    db.excecSQLite("create table " + spendingsTable + " (SPENDINGSID INT PRIMARY KEY, SPENDINGSAMOUNT REAL, SPENDINGSDATE TEXT, SPENDUSER TEXT, FOREIGN kEY(SPENDUSER) REFERENCES PERSONTABLE(USERNAME))");
    db.excecSQLite("create table " + budgetTable + " (WEEKLYBUDGET REAL, BUDGETUSER, FOREIGN kEY(BUDGETUSER) REFERENCES PERSONTABLE(USERNAME))");
  }
  
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    db.excecSQLite("DROP TABLE IF EXISTS " + personTable);
    onCreate(db);
  }
  
  public void insertUserDate(String name, String pass){
    SQLiteDatabase.db = this.getWritableDatebase();
    ContentValue contentValue = new ContentValue();
    contentValue.put(username, name);
    contentValue.put(password, pass);
    db.insert(personTable, null, contentValue);
  }
}