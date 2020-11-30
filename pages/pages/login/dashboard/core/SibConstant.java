package pages.login.dashboard.core;

public abstract class SibConstant {

  /* Constant for wait time in millisecond. */
  public static final int VERY_LOW = 500;

  public static final int LOW = 1000;

  public static final int MODERATE = 2500;

  public static final int HIGH = 3500;

  public static final int VERY_HIGH = 5000;
  /* Constant for wait time in millisecond ends here. */

  /* '_Mod' is for the module name */
  public static final String CUSTOMER_Mod = "customers";

  public static final String CUSTOMER_LIST = "CustomerList";

  public static final String CUSTOMER_GROUPS_Mod = "customergroups";

  public static final String CUTOMER_GROUP = "CustomerGroup";

  public static final String POINT_OF_CONTACT_Mod = "pointofcontacts";

  public static final String POINT_OF_CONTACT = "PointOfContact";

  public static final String HOARDING_Mod = "hoardings";

  public static final String PLAN_Mod = "plans";

  public static final String PLANS = "Plans";

  public static final String CAMPAIGN_Mod = "campaigns";

  public static final String CAMPAIGNS = "Campaigns";

  public static final String CONTRACT_Mod = "contract";
  
  public static final String CONTRACT_Details = "Contract Details";

  public static final String CONTRACTS = "Contracts";

  public static final String OPERATION_GRID_Mod = "hoardings";

  public static final String OPERATION_GRID = "OperationsGrid";

  public static final String OPERATION_KANBAN_Mod = "hoardings-operation";

  public static final String OPERATION_KANBAN = "OperationsKanban";

  public static final String CONTRACT_TYPE_Mod = "contract-type";

  public static final String CONTRACT_TYPE = "ContractType";

  public static final String CUSTOM_AREA_Mod = "area";

  public static final String CUSTOM_AREA = "CustomerArea";

  public static final String INDUSTRY_Mod = "industry";

  public static final String INDUSTRY_SEGMENT = "IndustrySegment";

  public static final String SETTINGS_Mod = "settings";

  public static final String SETTINGS = "Settings";

  // public static final String CONFIGURATION = "Configuration";
  public static final String DASHBOARD = "Dashboard";

  public static final String COMMON_FUNCTIONS = "CommonFunctions";

  public static final String BILLING_Mod = "billings";

  public static final String BILLING = "Billings";

  public static final String HOARDINGS = "Hoardings";

  public static final String DISTRICTS_Mod = "district";

  public static final String DISTRICT = "CustomDistrict";

  // Constants for plan
  public static final String HID = "HID";

  public static final String STATUS = "Status";

  public static final String START_DATE = "Start Date";

  public static final String END_DATE = "End Date";

  public static final String MONTHLY_RATE = "Monthly Rate";

  public static final String HEIGHT = "H";

  public static final String WIDTH = "W";

  public static final String SQFT = "SQFT";

  public static final String PRINT_PRICE_PER_SQFT = "Printing Price/SqFt";

  public static final String PRINT_CHARGE = "Printing Charge";

  public static final String MOUNT_PRICE_PER_SQFT = "Mounting Price/SqFt";

  public static final String MOUNT_CHARGE = "Mounting Charge";

  public static final String COST = "Cost";

  public static final String PENDING_REASON = "Pending Reason";

  public static final String RSD = "RSD";

  // Campaign item status
  public static final String RUNNING = "Running";

  public static final String PENDING = "Pending";

  public static final String APPROVED = "Approved";

  
  
  //expected conditions for wait
  public static final String isElementDisplayed = "isElementDisplayed";
  
  public static final String isElementEnabled = "isElementEnabled";

}
