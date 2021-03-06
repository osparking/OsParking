/* 
 * Copyright (C) 2015, 2016  Open Source Parking, Inc.(www.osparking.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.osparking.osparking;

import com.osparking.attendant.AttListForm;
import com.osparking.attendant.LoginDialog;
import com.osparking.attendant.LoginEventListener;
import com.osparking.attendant.LoginWindowEvent;
import static com.osparking.global.CommonData.ADMIN_ID;
import static com.osparking.global.CommonData.ImgHeight;
import static com.osparking.global.CommonData.ImgWidth;
import static com.osparking.global.CommonData.appendOdsLine;
import static com.osparking.global.CommonData.cameraOneIsButton;
import static com.osparking.global.CommonData.checkOdsExistance;
import static com.osparking.global.CommonData.dummyMessages;
import static com.osparking.global.CommonData.metaKeyLabel;
import static com.osparking.global.CommonData.resizeComponentFor;
import static com.osparking.global.CommonData.tipColor;
import com.osparking.global.Globals;
import static com.osparking.global.Globals.*;
import com.osparking.global.IMainGUI;
import com.osparking.global.names.CarAdmission;
import com.osparking.global.names.ControlEnums;
import com.osparking.global.names.ControlEnums.BarOperation;
import static com.osparking.global.names.ControlEnums.BarOperation.AUTO_OPENED;
import static com.osparking.global.names.ControlEnums.BarOperation.REGISTERED_CAR_OPENED;
import static com.osparking.global.names.ControlEnums.ButtonTypes.ARRIVALS_BTN;
import static com.osparking.global.names.ControlEnums.ButtonTypes.CAR_ARRIVAL_BTN;
import static com.osparking.global.names.ControlEnums.ButtonTypes.LicenseButton;
import static com.osparking.global.names.ControlEnums.ButtonTypes.STATISTICS_BTN;
import static com.osparking.global.names.ControlEnums.ButtonTypes.USERS_BTN;
import static com.osparking.global.names.ControlEnums.ButtonTypes.VEHICLES_BTN;
import static com.osparking.global.names.ControlEnums.DialogMessages.ARTIF_ERROR_RATE;
import static com.osparking.global.names.ControlEnums.DialogMessages.AUTO_LOGOUT;
import static com.osparking.global.names.ControlEnums.DialogMessages.DEVICE_DISCONN_COUNT;
import static com.osparking.global.names.ControlEnums.DialogMessages.FIRST_RUN_MSG;
import static com.osparking.global.names.ControlEnums.DialogMessages.OSPARKING_STOPS;
import static com.osparking.global.names.ControlEnums.DialogMessages.PASSING_DELAY_AVG;
import static com.osparking.global.names.ControlEnums.DialogMessages.SHUT_DOWN_CONFIRM_DIALOG;
import static com.osparking.global.names.ControlEnums.DialogTitleTypes.CONFIRM_LOGOUT;
import static com.osparking.global.names.ControlEnums.DialogTitleTypes.ERROR_DIALOGTITLE;
import static com.osparking.global.names.ControlEnums.DialogTitleTypes.MAIN_GUI_TITLE;
import static com.osparking.global.names.ControlEnums.DialogTitleTypes.SYSTEM_SHUTDOWN_CONFIRM;
import static com.osparking.global.names.ControlEnums.LabelContent.ARTI_CURR_ERR_LIMIT_1;
import static com.osparking.global.names.ControlEnums.LabelContent.ARTI_CURR_ERR_LIMIT_2;
import static com.osparking.global.names.ControlEnums.LabelContent.ARTI_CURR_ERR_LIMIT_b;
import static com.osparking.global.names.ControlEnums.LabelContent.CAMERA_LABEL;
import static com.osparking.global.names.ControlEnums.LabelContent.E_BOARD_LABEL;
import static com.osparking.global.names.ControlEnums.LabelContent.GATE_BAR_LABEL;
import static com.osparking.global.names.ControlEnums.LabelContent.GATE_LABEL;
import static com.osparking.global.names.ControlEnums.LabelContent.OPEN_LABEL;
import static com.osparking.global.names.ControlEnums.LabelContent.RATE_LABEL;
import static com.osparking.global.names.ControlEnums.LabelContent.STATUS_LABEL;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.AFFILIATION_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.ARRIVAL_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.BOOTING_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.BUILDING_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.DRIVERS_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.ID_DEFAULT;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.ID_LABEL_STR;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.LOGIN_MENU;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.LOGIN_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.LOGIN_RECORD_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.LOGOUT_MENU;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.LOGOUT_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.MANAGER_MANU;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.USERS_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.META_KEY_LABEL;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.QUIT_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.QUIT_MENU_ITEM_SC;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.RECORD_MENU;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.SETTING_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.SYSTEM_MENU;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.VEHICLE_MANAGE_MENU_ITEM;
import static com.osparking.global.names.ControlEnums.MenuITemTypes.VEHICLE_MENU;
import static com.osparking.global.names.ControlEnums.MsgContent.ASK_LOGOUT;
import static com.osparking.global.names.ControlEnums.MsgContent.CAMERA_SIM;
import static com.osparking.global.names.ControlEnums.MsgContent.DISPLAY_CMD;
import static com.osparking.global.names.ControlEnums.MsgContent.E_BOARD_SIM;
import static com.osparking.global.names.ControlEnums.MsgContent.GATEBAR_SIM;
import static com.osparking.global.names.ControlEnums.MsgContent.LOG_IN;
import static com.osparking.global.names.ControlEnums.MsgContent.LOG_OUT;
import static com.osparking.global.names.ControlEnums.MsgContent.OPEN_CMD;
import static com.osparking.global.names.ControlEnums.MsgContent.P_DELAY_DEF_1;
import static com.osparking.global.names.ControlEnums.MsgContent.P_DELAY_DEF_2;
import static com.osparking.global.names.ControlEnums.MsgContent.P_DELAY_DEF_3;
import static com.osparking.global.names.ControlEnums.MsgContent.SYSTEM_START;
import static com.osparking.global.names.ControlEnums.MsgContent.SYSTEM_STOP;
import static com.osparking.global.names.ControlEnums.TextType.NO_APP_MSG;
import static com.osparking.global.names.ControlEnums.TextType.ON_ARTIFI_ERROR_MSG;
import static com.osparking.global.names.ControlEnums.ToolTipContent.CAR_ENTRY_TOOLTIP;
import com.osparking.global.names.ControlEnums.TopForms;
import static com.osparking.global.names.ControlEnums.TopForms.Arrivals;
import static com.osparking.global.names.ControlEnums.TopForms.Attendant;
import static com.osparking.global.names.ControlEnums.TopForms.Settings;
import static com.osparking.global.names.ControlEnums.TopForms.Vehicle;
import com.osparking.global.names.DB_Access;
import static com.osparking.global.names.DB_Access.deviceType;
import static com.osparking.global.names.DB_Access.enteranceAllowed;
import static com.osparking.global.names.DB_Access.gateCount;
import static com.osparking.global.names.DB_Access.gateNames;
import static com.osparking.global.names.DB_Access.getDisallowReason;
import static com.osparking.global.names.DB_Access.initEBoardSettings;
import static com.osparking.global.names.DB_Access.initSystemSettings;
import static com.osparking.global.names.DB_Access.parkingPermitted;
import static com.osparking.global.names.DB_Access.readEBoardSettings;
import static com.osparking.global.names.DB_Access.readSettings;
import com.osparking.global.names.EBD_DisplaySetting;
import com.osparking.global.names.ImageDisplay;
import com.osparking.global.names.JDBCMySQL;
import com.osparking.global.names.ManagerGUI;
import com.osparking.global.names.OSP_enums;
import com.osparking.global.names.OSP_enums.DeviceType;
import static com.osparking.global.names.OSP_enums.DeviceType.Camera;
import static com.osparking.global.names.OSP_enums.DeviceType.E_Board;
import static com.osparking.global.names.OSP_enums.DeviceType.GateBar;
import static com.osparking.global.names.OSP_enums.EBD_ContentType.GATE_NAME;
import static com.osparking.global.names.OSP_enums.EBD_ContentType.REGISTRATION_STAT;
import static com.osparking.global.names.OSP_enums.EBD_ContentType.VEHICLE_TAG;
import static com.osparking.global.names.OSP_enums.EBD_ContentType.VERBATIM;
import static com.osparking.global.names.OSP_enums.EBD_DisplayUsage.CAR_ENTRY_BOTTOM_ROW;
import static com.osparking.global.names.OSP_enums.EBD_DisplayUsage.CAR_ENTRY_TOP_ROW;
import com.osparking.global.names.OSP_enums.EBD_Row;
import com.osparking.global.names.OSP_enums.E_BoardType;
import static com.osparking.global.names.OSP_enums.MsgCode.EBD_INTERRUPT1;
import static com.osparking.global.names.OSP_enums.MsgCode.EBD_INTERRUPT2;
import com.osparking.global.names.OSP_enums.OpLogLevel;
import com.osparking.global.names.OSP_enums.PermissionType;
import static com.osparking.global.names.OSP_enums.PermissionType.ALLOWED;
import static com.osparking.global.names.OSP_enums.PermissionType.BADTAGFORMAT;
import static com.osparking.global.names.OSP_enums.PermissionType.DISALLOWED;
import static com.osparking.global.names.OSP_enums.PermissionType.UNREGISTERED;
import com.osparking.global.names.ParkingTimer;
import com.osparking.global.names.SocketConnStat;
import com.osparking.global.names.ToleranceLevel;
import com.osparking.osparking.device.CameraManager;
import com.osparking.osparking.device.ConnectDeviceTask;
import com.osparking.osparking.device.EBoardManager;
import com.osparking.osparking.device.GateBarManager;
import com.osparking.global.names.IDevice;
import com.osparking.global.names.IDevice.IE_Board;
import com.osparking.global.names.IDevice.ISocket;
import com.osparking.global.names.OSP_enums.CameraType;
import static com.osparking.global.names.OSP_enums.CameraType.CarButton;
import static com.osparking.global.names.OSP_enums.CameraType.Simulator;
import static com.osparking.global.names.OSP_enums.EBD_DisplayMessage.*;
import static com.osparking.global.names.OSP_enums.GateBarType.NaraBar;
import static com.osparking.osparking.Common.RECENT_ROW_HEIGHT;
import static com.osparking.osparking.Common.formMessageExceptCheckShort;
import static com.osparking.osparking.Common.os_FreeBytes;
import com.osparking.osparking.device.BlackFly.BlackFlyManager;
import com.osparking.osparking.device.LED_Task;
import com.osparking.osparking.device.LEDnotice.FinishLEDnoticeIntrTask;
import com.osparking.osparking.device.LEDnotice.LEDnoticeManager;
import static com.osparking.osparking.device.LEDnotice.LEDnoticeManager.ledNoticeSettings;
import com.osparking.osparking.device.LEDnotice.LedProtocol;
import com.osparking.osparking.device.NaraBar.NaraBarMan;
import com.osparking.osparking.device.NaraBar.NaraEnums.Nara_MsgType;
import com.osparking.osparking.device.NaraBar.NaraMessageQueue.NaraMsgItem;
import com.osparking.osparking.device.SendEBDMessageTask;
import com.osparking.osparking.device.SendGateOpenTask;
import com.osparking.osparking.statistics.DeviceCommand;
import com.osparking.osparking.statistics.PassingDelayStat;
import com.osparking.statistics.CarArrivals;
import com.osparking.vehicle.Affiliations;
import com.osparking.vehicle.Buildings;
import com.osparking.vehicle.VehiclesForm;
import com.osparking.vehicle.driver.CarOwners;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_BYTE_GRAY;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.bytedeco.javacpp.FlyCapture2;

/**
 * Main GUI of OsParking -- Open Source Parking Lot Management Program which is
 * developed by Open Source Parking Inc.
 * <p>Company Web Site : <a href="http://www.osparking.com">http://www.osparking.com</a></p>
 * <p>(Company logo: <img src ="doc-files/64px.png"/>)</p>
 * 
 * @author Open Source Parking Inc.
 */
public final class ControlGUI extends javax.swing.JFrame implements ActionListener, ManagerGUI, IMainGUI {

    private static boolean listFileNotFound() {
        String listFilePath = "log" + File.separator + "MessageList.txt";
        File listFile = new File(listFilePath);
        
        if (!listFile.exists() || listFile.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    static boolean IS_FIRST_FUN = false;
    
    private static JFrame[] topForms = new JFrame[ControlEnums.TopForms.values().length]; 
    static {
        for (int i = 0; i < ControlEnums.TopForms.values().length; i++) {
            topForms[i] = null;
        }
    }
    
    private static void showBufferedImage(int gateNo, JLabel picLabel, BufferedImage imageRead) {
        picLabel.setText(null);
        picLabel.setIcon(createStretchedIcon(picLabel.getSize(), imageRead, false));
        originalImgWidth[gateNo] = imageRead.getWidth(); 
        gatePanel.setGateImage((byte)gateNo, imageRead);
    }

    /**
     * @return the topForms
     */
    public JFrame[] getTopForms() {
        return topForms;
    }
    
    private LoginDialog loginDialog = null;
    Rectangle rect = new Rectangle();
    
    RunRecordList showRunRecordForm = null;
    LoginRecordList showLoginRecordForm = null;
    ListSelectionModel[] listSelectionModel = null;
    private static int[] shownImageRow = new int[5];
    java.util.Timer hwSimulationTimer = null;
    private boolean[] gateBusy = {false, false, false, false, false};
        // for simulated camera (process) only
    private JPanel [] statusPanels = null;
    private JLabel [] e_boardLEDs = null;
    private JLabel [] gateBarLEDs = null;
    private boolean SHUT_DOWN = false;  
    private static final int BOTTOM_DISPLAY_MS = 30000;
    
    /**
     * Data members for peripheral devices (camera, gate bar, e-board)
     */
    private IDevice.IManager[][] deviceManagers = null;
    private JLabel[][] deviceConnectionLabels;
    public ToleranceLevel[][] tolerance = null;
    private Object[][] socketMutex = null;
    private SocketConnStat[][] sockConnStat = null; // Socket Connection Status
    private DeviceCommand[][] perfomStatistics = null;    
    private ParkingTimer[] openGateCmdTimer = null;
    public static EBD_DisplaySetting[] EBD_DisplaySettings = null;
    public int[] prevImgSN = null; // the ID of the most recently processed car entry image    
    
    // data items for gate bars
    public int[] openCommandIDs = null;    
    public boolean[] openCommAcked = null;
    public long[] openCommandIssuedMs = null; // time when a gate open command issued.
    
    /**
     * text file used to store open command IDs acked from the gate bar.
     */
    private FileWriter[][] IDLogFile = null; 
    
    /**
     * Data relating to E-Board interrupt messages.
     */
    boolean[] interruptsAcked = null;
    
    /** 
     * Storage for vehicle processing performance by this system(Parking Manager).
     */
    private PassingDelayStat[] passingDelayStat = null;
        
    final static int gateCSHt = 20;
    final static int gateCSGap = 8;
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    
    /**
     * @return the shownImageRow
     */
    public static int[] getShownImageRow() {
        return shownImageRow;
    }

    /**
     * @param aShownImageRow the shownImageRow to set
     */
    public static void setShownImageRow(int[] aShownImageRow) {
        shownImageRow = aShownImageRow;
    }

    /**
     * @return the gatePanel
     */
    public static GatePanel getGatePanel() {
        return gatePanel;
    }
    
    /**
     * used to notify user intention to shut down the manager to all threads 
     * and make them gracefully finish their jobs
     */
    public DeviceType deviceToContinue = null;
    public Object[] BarConnection = null;
    Timer periodicallyCheckSystemTimer = new Timer("ospMaxRecordChecker");
    
    private ParkingTimer[][] connectDeviceTimer = null;
    public long[][] eBoardMsgSentMs = null;
    private ParkingTimer[][] sendEBDmsgTimer = null;
    
    public int[] msgSNs = null; 
    public Timer LED_Timer = null;

    static Random gateRandomOpen = new Random(System.currentTimeMillis());
    
    Properties prop = null;
            HashMap<String, Component> connStatusMap = new HashMap<String, Component>();

    /**
     * Creates new form MainForm
     */
    public ControlGUI(boolean eBoardTest) {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        for (int i = 1; i <= gateCount; i++) {
            admissionListModel[i] = new DefaultListModel<CarAdmission>();
        }
            
        initComponents();   
        
        /**
         * Hide unnecessary status panels
         */
        statusPanels = new JPanel[MAX_GATES + 1];
        statusPanels[1] = statusPanelGate1;
        statusPanels[2] = statusPanelGate2;
        statusPanels[3] = statusPanelGate3;
        statusPanels[4] = statusPanelGate4;
        for (int i = gateCount + 1; i <=MAX_GATES; i++) {
            statusPanels[i].setVisible(false);
        }
        reduceConnPanHt();
        connStatusPanel.setPreferredSize(connStatusPanel.getPreferredSize());

        deviceConnectionLabels = new JLabel[DeviceType.values().length][MAX_GATES + 1];
        deviceConnectionLabels[DeviceType.Camera.ordinal()][1] = labelCamera1;
        deviceConnectionLabels[DeviceType.Camera.ordinal()][2] = labelCamera2;
        deviceConnectionLabels[DeviceType.Camera.ordinal()][3] = labelCamera3;
        
        deviceConnectionLabels[DeviceType.E_Board.ordinal()][1] = labelE_Board1;
        deviceConnectionLabels[DeviceType.E_Board.ordinal()][2] = labelE_Board2;
        deviceConnectionLabels[DeviceType.E_Board.ordinal()][3] = labelE_Board3;
        
        deviceConnectionLabels[DeviceType.GateBar.ordinal()][1] = labelBar1;
        deviceConnectionLabels[DeviceType.GateBar.ordinal()][2] = labelBar2;
        deviceConnectionLabels[DeviceType.GateBar.ordinal()][3] = labelBar3;
        
        // Enable car entry button when it is used for #1 gate
        if (cameraOneIsButton()) {
            carEntryButton.setEnabled(true);
            // Change button label to the gate name
            carEntryButton.setText(gateNames[1] + "(C)");
            carEntryButton.setToolTipText(CAR_ENTRY_TOOLTIP.getContent());
        }
        /**
         * Set gate connection status panel row headings to given gate names
         */
        augmentComponentMap(connStatusPanel, connStatusMap);
        
        for (int gateNo = 1; gateNo <= gateCount; gateNo++) {
            String labelControlName = "row" + gateNo + "Heading";
            Component comp = getComponentByName(connStatusMap, labelControlName);
            JLabel connStatLabel = (JLabel)comp;
            connStatLabel.setText(gateNames[gateNo]);
        }
        /**
         * Set icon for the simulated camera program
         */
        setIconImages(OSPiconList);
        
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/OsParking.properties");
        this.prop = new Properties();
        try
        {
            this.prop.load( resourceAsStream );
        } catch (IOException e){
        }
        setTitle("OsParking(" + this.prop.getProperty("osparking.current.version") 
                + ")--" + MAIN_GUI_TITLE.getContent() + "(http://www.osparking.com)");
        
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        PID_Label.setText("(PID:" + processName.substring(0, processName.indexOf("@")) + ")");        
        
        // Create Panels for gates on the major lower right area
        setGatesAndRestPanel();
        
        Container wholePane = this.getContentPane();
        wholePane.setBackground(MainBackground);
        
        startClock();
        initMessagelLines();
        initCarEntryList();
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // Put the window at the top left corner
        setLocation(new Point(0, 0));
        setSize(screen.width - CAMERA_GUI_WIDTH, screen.height - GATE_BAR_HEIGHT - TASK_BAR_HEIGHT);
        
        // Info' make below lines comments after ths camera-less simulation phase completes
        addMessageLine(MessageTextArea, "" );
        if (!IS_FIRST_FUN) {
            addMessageLine(MessageTextArea, SYSTEM_START.getContent());
        }
        logParkingOperation(OpLogLevel.LogAlways, SYSTEM_START.getContent());
        
        int deviceCount = DeviceType.values().length; // dc: Device (type)  Count
        tolerance = new ToleranceLevel[deviceCount][gateCount + 1];
        socketMutex = new Object[deviceCount][gateCount + 1];
        
        for (int di = 0; di <deviceCount; di++ ) {
            for (int gi = 1; gi <= gateCount; gi++) {
                tolerance[di][gi] = new ToleranceLevel();
                socketMutex[di][gi] = new Object();            
            }
        }

        perfomStatistics = new DeviceCommand[DeviceType.values().length][gateCount + 1]; // column 0 is unused
        openGateCmdTimer = new ParkingTimer[gateCount + 1];
        IDLogFile = new FileWriter[DeviceType.values().length][gateCount + 1]; 
        
        sockConnStat = new SocketConnStat[DeviceType.values().length][gateCount + 1];
        connectDeviceTimer = new ParkingTimer[DeviceType.values().length][gateCount + 1];
        deviceManagers = new IDevice.IManager[DeviceType.values().length][gateCount + 1]; 
        sendEBDmsgTimer = new ParkingTimer[gateCount + 1][4]; // 2: for two rows of each elec' board.
        
        openCommandIDs = new int[gateCount + 1];        
        openCommAcked = new boolean[gateCount + 1];        
        BarConnection = new Object[gateCount + 1];
        passingDelayStat = new PassingDelayStat[gateCount + 1];
        
        openCommAcked[0] = true;        
        
        //<editor-fold desc="-- Initialize various kinds of timers">
        for (byte gateNo = 1; gateNo <= gateCount; gateNo++) {
            openCommandIDs[gateNo] = 0;
            openCommAcked[gateNo] = true;    
            openGateCmdTimer[gateNo] 
                    = new ParkingTimer("ospOpenBar" + gateNo + "Timer", false, null, 0, RESEND_PERIOD);            
            BarConnection[gateNo] = new Object();
            passingDelayStat[gateNo] = new PassingDelayStat();
            
            for (DeviceType type : DeviceType.values()) {
                if (type == Camera && gateNo == 1 && cameraOneIsButton())
                {
                    ; // Do nothing.
                } else {
                    connectDeviceTimer[type.ordinal()][gateNo] = new ParkingTimer(
                            "ospConnect_" + type + "_" + gateNo + "_timer", false);
                }
            }
            
            for (EBD_Row row : EBD_Row.values()) {
                String timerName = "ospEBD" + gateNo + "_R" + row.getValue() + "_msgTimer";
                sendEBDmsgTimer[gateNo][row.ordinal()] 
                        = new ParkingTimer(timerName, false, null, 0L, RESEND_PERIOD); 
            }            
        }     
        //</editor-fold>
        
        //<editor-fold desc="-- Create device managers for each gate.">
        for (DeviceType type : DeviceType.values()) {     
            for (int gNo = 1; gNo <= gateCount; gNo++) {
                String command = null;
                if (type == GateBar)
                    command = OPEN_CMD.getContent();
                else 
                    command = DISPLAY_CMD.getContent();
                perfomStatistics[type.ordinal()][gNo] = new DeviceCommand(command);
            }  
            
            for (byte gateNo = 1; gateNo <= gateCount; gateNo++) {
                sockConnStat[type.ordinal()][gateNo] = new SocketConnStat(this, type, gateNo);
                
                switch (type) {
                    case Camera: 
                        //<editor-fold desc="-- Camera manager">
                        switch (Globals.gateDeviceTypes[gateNo].cameraType) {
                            case Blackfly:
                                deviceManagers[type.ordinal()][gateNo]
                                        = (IDevice.IManager)new BlackFlyManager(this, gateNo);
                                break;
                                
                            case Simulator:
                                deviceManagers[type.ordinal()][gateNo] 
                                        = (IDevice.IManager)new CameraManager(this, gateNo);
                                break;
                                
                            default:
                                break;
                        }                        
                        //</editor-fold>
                        break;
                    case E_Board: 
                        //<editor-fold desc="-- E-board manager">
                        switch (Globals.gateDeviceTypes[gateNo].eBoardType) {
                            case LEDnotice:
                                deviceManagers[type.ordinal()][gateNo]
                                        = (IDevice.IManager)new LEDnoticeManager(this, gateNo);
                                break;
                                
                            default:
                                deviceManagers[type.ordinal()][gateNo] 
                                        = (IDevice.IManager)new EBoardManager(this, gateNo);
                                break;
                        }
                        //</editor-fold>
                        break;
                    case GateBar: // deviceType[GateBar.ordinal()][gateID]
                        //<editor-fold desc="-- Gate bar manager">
                        switch (Globals.gateDeviceTypes[gateNo].gateBarType) {
                            case NaraBar:
                                deviceManagers[type.ordinal()][gateNo] 
                                        = (IDevice.IManager)new NaraBarMan(this, gateNo);
                                break;
                                
                            default:
                                deviceManagers[type.ordinal()][gateNo] 
                                        = (IDevice.IManager)new GateBarManager(this, gateNo);
                                break;
                        }
                        //</editor-fold>
                        break;
                }
                // start server socket listeners for all types of devices
                ParkingTimer devConnTimer = connectDeviceTimer[type.ordinal()][gateNo];
                if (devConnTimer != null) {
                    devConnTimer.runOnce(new ConnectDeviceTask(this, type, gateNo));
                }
                
                if (deviceManagers[type.ordinal()][gateNo] != null) {
                    deviceManagers[type.ordinal()][gateNo].start();
                }
            }
        }
        //</editor-fold>

        openCommandIssuedMs = new long[gateCount + 1];
        
        /**
         * Delete car arrival records older than some designated number of days.
         * Old car arrival records are checked once every 6 hours after the initial check when system boots.
         */
        periodicallyCheckSystemTimer.schedule(new SystemChecker(this), 1000, SIX_HOURS);        
        
        prevImgSN = new int[gateCount + 1];
        
        msgSNs = new int[gateCount + 1];        
        interruptsAcked = new boolean[gateCount + 1];        
        eBoardMsgSentMs = new long[gateCount + 1][2];
        
        interruptsAcked[0] = true;           
        for (int gateNo = 1; gateNo <= gateCount; gateNo++) {
            interruptsAcked[gateNo] = true;
        }             
        
        /**
         * Start socket connection status display timer and schedule it.
         */
        LED_Timer = new Timer("ospLEDtimer", true);
        LED_Timer.schedule(new LED_Task(this, getDeviceManagers()), 0, LED_PERIOD);
        
        DB_Access.makeSureBasicUserExistance(this);
        
        if (!eBoardTest) {
            processLogIn(null);
        }        
    }
    
//    private void prepareIDLogFile(DeviceType devType, int gateNo) {
//        StringBuilder pathname = new StringBuilder();
//        StringBuilder daySB = new StringBuilder();
//
//        getPathAndDay("operation", pathname, daySB);
//
//        // full path name of the today's text file for Open command ID logging
//        String barOpenLogFilePathname = pathname + File.separator 
//                + daySB.toString() + "_" + devType + "_" + gateNo + ".txt";
//        try {
//            IDLogFile[devType.ordinal()][gateNo] = new FileWriter(barOpenLogFilePathname, false); 
//            String header = "";
//            
//            switch (devType) {
////                case Camera:
////                    header = "* IDs of images came from Camera #" + gateNo
////                            + System.lineSeparator() + "<current> <previous>";
////                    break;
//                case E_Board:
//                    header = "* Seq' numbers of display INTERRUPT messages sent to E-Board #" + gateNo
//                            + System.lineSeparator() + "<Sequence number>";
//                    break;
//                case GateBar:
//                    header = "* ID numbers of Open commands sent to Gate #" + gateNo 
//                            + System.lineSeparator() + "<ID number>(negative: random attendant)";
//                    break;
//            }
//            IDLogFile[devType.ordinal()][gateNo].write(header + System.lineSeparator());
//            IDLogFile[devType.ordinal()][gateNo].flush();
//        } catch (FileNotFoundException ex) {
//            logParkingExceptionStatus(Level.SEVERE, ex, "prepare logging file", getStatusTextField(), GENERAL_DEVICE);
//        } catch (IOException ex) {
//            logParkingExceptionStatus(Level.SEVERE, ex, "prepare logging file", getStatusTextField(), GENERAL_DEVICE);
//        }                 
//    }      
    
    /**
     * @return the perfomStatistics
     */
    public DeviceCommand[][] getPerfomStatistics() {
        return perfomStatistics;
    }

    static void show100percentSizeImageOfGate(int gateNo, BufferedImage originalImage) {
        if (getGatePanel().getCarPicLabels()[gateNo].getIcon() != null)
        {
            int picIconWidth = getGatePanel().getCarPicLabels()[gateNo].getIcon().getIconWidth();
            if (originalImgWidth[gateNo] * 0.95 > picIconWidth)
            {
                ImageDisplay bigImage = new ImageDisplay(originalImage,
                    gateNames[gateNo] + "--100% car arrival image");
                bigImage.setVisible(true);               
            }        
        }
    }
    
    /**
     * Changes login menu item, login userID, whether user is a administrator 
     * or not.
     */
    private void changeUserID_etc() {
        String loginID;
        
        if (Globals.loginID == null) {
            MenuItems_setEnabled(false);
            LogInOutMenu.setText(LOGIN_MENU.getContent()); 
            IsManagerLabelMenu.setText("N");
            AttendantTask_setEnabled(false);
            loginID = ID_DEFAULT.getContent();
        } else {
            MenuItems_setEnabled(true);
            LogInOutMenu.setText(LOGOUT_MENU.getContent());
            IsManagerLabelMenu.setText(isManager ? "Y" : "N");
            AttendantTask_setEnabled(true);
            loginID = Globals.loginID;
        }
        
        resizeComponentFor(UserIDLabelMenu, loginID);
        UserIDLabelMenu.setText(loginID);        
    }    
    
    public void actionPerformed(ActionEvent e) {
    }
    
    private void enableManagerItem(boolean flag) {
        RunRecordItem.setEnabled(flag);
        LoginRecordItem.setEnabled(flag);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manager_ID_gap = new javax.swing.Box.Filler(new java.awt.Dimension(45, 0), new java.awt.Dimension(45, 0), new java.awt.Dimension(45, 32767));
        fullPanel = new javax.swing.JPanel();
        PanelMainTop = new javax.swing.JPanel();
        MainToolBar = new javax.swing.JToolBar();
        fillerLeft = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0));
        CarIOListButton = new javax.swing.JButton();
        VehiclesButton = new javax.swing.JButton();
        UsersButton = new javax.swing.JButton();
        Quit = new javax.swing.JButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(32767, 0));
        myMetaKeyLabel = new javax.swing.JLabel();
        autoOpenButton = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(32767, 0));
        autoGateOpenCheckBox = new javax.swing.JCheckBox();
        ClockLabel = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0));
        WholePanel = new javax.swing.JPanel();
        Panel_MainMsgList = new javax.swing.JPanel();
        LeftSide_Label = new javax.swing.JLabel();
        MainScrollPane = new javax.swing.JScrollPane();
        MessageTextArea = new javax.swing.JTextArea();
        Status_Panel = new javax.swing.JPanel();
        debugPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        PID_Label = new javax.swing.JLabel();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel1 = new javax.swing.JPanel();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        errorCheckBox = new javax.swing.JCheckBox();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        errIncButton = new javax.swing.JButton();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        errDecButton = new javax.swing.JButton();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel4 = new javax.swing.JPanel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        errorLabel = new javax.swing.JLabel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        jPanel2 = new javax.swing.JPanel();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        carEntryButton = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        showStatisticsBtn = new javax.swing.JButton();
        entryPanel = new javax.swing.JPanel();
        connStatusPanel = new javax.swing.JPanel();
        statusPanelGate1 = new javax.swing.JPanel();
        filler32 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        row1Heading = new javax.swing.JLabel();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelCamera1 = new javax.swing.JLabel();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelE_Board1 = new javax.swing.JLabel();
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelBar1 = new javax.swing.JLabel();
        statusPanelGate2 = new javax.swing.JPanel();
        filler30 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        row2Heading = new javax.swing.JLabel();
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelCamera2 = new javax.swing.JLabel();
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelE_Board2 = new javax.swing.JLabel();
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelBar2 = new javax.swing.JLabel();
        statusPanelGate3 = new javax.swing.JPanel();
        filler31 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        row3Heading = new javax.swing.JLabel();
        filler24 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelCamera3 = new javax.swing.JLabel();
        filler25 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelE_Board3 = new javax.swing.JLabel();
        filler26 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelBar3 = new javax.swing.JLabel();
        statusPanelGate4 = new javax.swing.JPanel();
        filler33 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        row4Heading = new javax.swing.JLabel();
        filler28 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelCamera4 = new javax.swing.JLabel();
        filler29 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelE_Board4 = new javax.swing.JLabel();
        filler34 = new javax.swing.Box.Filler(new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 0), new java.awt.Dimension(7, 32767));
        labelBar4 = new javax.swing.JLabel();
        filler27 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 3), new java.awt.Dimension(0, 3), new java.awt.Dimension(32767, 3));
        statusTextPanel = new javax.swing.JPanel();
        statusTextField = new javax.swing.JTextField();
        visibleMenuBar = new javax.swing.JMenuBar();
        RecordsMenu = new javax.swing.JMenu();
        EntryRecordItem = new javax.swing.JMenuItem();
        RunRecordItem = new javax.swing.JMenuItem();
        LoginRecordItem = new javax.swing.JMenuItem();
        VehicleMenu = new javax.swing.JMenu();
        VehicleListItem = new javax.swing.JMenuItem();
        DriverListItem = new javax.swing.JMenuItem();
        AffiliationItem = new javax.swing.JMenuItem();
        BuildingItem = new javax.swing.JMenuItem();
        CommandMenu = new javax.swing.JMenu();
        AttendantListItem = new javax.swing.JMenuItem();
        SettingsItem = new javax.swing.JMenuItem();
        CloseProgramItem = new javax.swing.JMenuItem();
        licenseMenuItem = new javax.swing.JMenuItem();
        LogInOutMenu = new javax.swing.JMenu();
        LoginUser = new javax.swing.JMenuItem();
        LogoutUser = new javax.swing.JMenuItem();
        IsManagerLabelMenu = new javax.swing.JMenu();
        UserIDLabelMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(MainBackground);
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(930, 640));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeButtonClicked(evt);
            }
        });

        fullPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        fullPanel.setLayout(new java.awt.BorderLayout(0, 2));

        PanelMainTop.setBackground(MainBackground);
        PanelMainTop.setAlignmentY(0.0F);
        PanelMainTop.setMaximumSize(new Dimension(Short.MAX_VALUE, 32));
        PanelMainTop.setPreferredSize(new java.awt.Dimension(840, 32));
        PanelMainTop.setLayout(new java.awt.BorderLayout());

        MainToolBar.setBackground(MainBackground);
        MainToolBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        MainToolBar.setRollover(true);
        MainToolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        MainToolBar.setBorderPainted(false);
        MainToolBar.setMaximumSize(new Dimension(Short.MAX_VALUE, 35));
        MainToolBar.setMinimumSize(new java.awt.Dimension(473, 35));
        MainToolBar.setPreferredSize(new java.awt.Dimension(483, 35));
        MainToolBar.add(fillerLeft);

        CarIOListButton.setBackground(MainBackground);
        CarIOListButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        CarIOListButton.setText(ARRIVALS_BTN.getContent());
        CarIOListButton.setAlignmentY(0.0F);
        CarIOListButton.setEnabled(false);
        CarIOListButton.setFocusable(false);
        CarIOListButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CarIOListButton.setMaximumSize(new java.awt.Dimension(120, 30));
        CarIOListButton.setMinimumSize(new java.awt.Dimension(120, 30));
        CarIOListButton.setPreferredSize(new java.awt.Dimension(120, 30));
        CarIOListButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CarIOListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarIOListButtonActionPerformed(evt);
            }
        });
        MainToolBar.add(CarIOListButton);

        VehiclesButton.setBackground(MainBackground);
        VehiclesButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        VehiclesButton.setText(VEHICLES_BTN.getContent());
        VehiclesButton.setAlignmentY(0.0F);
        VehiclesButton.setEnabled(false);
        VehiclesButton.setFocusable(false);
        VehiclesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        VehiclesButton.setMaximumSize(new java.awt.Dimension(120, 30));
        VehiclesButton.setMinimumSize(new java.awt.Dimension(120, 30));
        VehiclesButton.setPreferredSize(new java.awt.Dimension(120, 30));
        VehiclesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        VehiclesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehiclesButtonActionPerformed(evt);
            }
        });
        MainToolBar.add(VehiclesButton);

        UsersButton.setBackground(MainBackground);
        UsersButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        UsersButton.setText(USERS_BTN.getContent());
        UsersButton.setAlignmentY(0.0F);
        UsersButton.setEnabled(false);
        UsersButton.setFocusable(false);
        UsersButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        UsersButton.setMaximumSize(new java.awt.Dimension(120, 30));
        UsersButton.setMinimumSize(new java.awt.Dimension(120, 30));
        UsersButton.setPreferredSize(new java.awt.Dimension(120, 30));
        UsersButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        UsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsersButtonActionPerformed(evt);
            }
        });
        MainToolBar.add(UsersButton);

        Quit.setBackground(MainBackground);
        Quit.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        Quit.setMnemonic('Q');
        Quit.setText(QUIT_MENU_ITEM_SC.getContent());
        Quit.setAlignmentY(0.0F);
        Quit.setFocusable(false);
        Quit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Quit.setMaximumSize(new java.awt.Dimension(120, 30));
        Quit.setMinimumSize(new java.awt.Dimension(120, 30));
        Quit.setPreferredSize(new java.awt.Dimension(120, 30));
        Quit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitActionPerformed(evt);
            }
        });
        MainToolBar.add(Quit);
        MainToolBar.add(Box.createHorizontalGlue());
        MainToolBar.add(filler4);

        myMetaKeyLabel.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        myMetaKeyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        myMetaKeyLabel.setText(META_KEY_LABEL.getContent());
        myMetaKeyLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        myMetaKeyLabel.setMaximumSize(new java.awt.Dimension(100, 25));
        myMetaKeyLabel.setMinimumSize(new java.awt.Dimension(100, 25));
        myMetaKeyLabel.setName(""); // NOI18N
        myMetaKeyLabel.setPreferredSize(new java.awt.Dimension(100, 25));
        myMetaKeyLabel.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        myMetaKeyLabel.setForeground(tipColor);
        MainToolBar.add(myMetaKeyLabel);

        autoOpenButton.setMnemonic('P');
        autoOpenButton.setText("jButton1");
        autoOpenButton.setFocusable(false);
        autoOpenButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        autoOpenButton.setPreferredSize(new java.awt.Dimension(0, 0));
        autoOpenButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        autoOpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoOpenButtonActionPerformed(evt);
            }
        });
        MainToolBar.add(autoOpenButton);
        MainToolBar.add(filler2);

        autoGateOpenCheckBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        autoGateOpenCheckBox.setText(OPEN_LABEL.getContent());
        autoGateOpenCheckBox.setAlignmentY(0.0F);
        autoGateOpenCheckBox.setFocusable(false);
        autoGateOpenCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        autoGateOpenCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        autoGateOpenCheckBox.setMaximumSize(new java.awt.Dimension(77, 23));
        autoGateOpenCheckBox.setMinimumSize(new java.awt.Dimension(77, 23));
        autoGateOpenCheckBox.setPreferredSize(new java.awt.Dimension(130, 23));
        MainToolBar.add(autoGateOpenCheckBox);

        ClockLabel.setBackground(MainBackground);
        ClockLabel.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        ClockLabel.setForeground(new java.awt.Color(255, 51, 51));
        ClockLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ClockLabel.setText("(clock)");
        ClockLabel.setAlignmentY(0.0F);
        ClockLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ClockLabel.setMaximumSize(new java.awt.Dimension(100, 23));
        ClockLabel.setMinimumSize(new java.awt.Dimension(100, 23));
        ClockLabel.setPreferredSize(new java.awt.Dimension(100, 23));
        MainToolBar.add(ClockLabel);
        MainToolBar.add(Box.createRigidArea(new Dimension(50, 0)));
        MainToolBar.add(filler1);

        PanelMainTop.add(MainToolBar, java.awt.BorderLayout.CENTER);

        fullPanel.add(PanelMainTop, java.awt.BorderLayout.PAGE_START);

        WholePanel.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        WholePanel.setPreferredSize(new java.awt.Dimension(864, 497));
        WholePanel.setLayout(new java.awt.BorderLayout());

        Panel_MainMsgList.setBackground(MainBackground);
        Panel_MainMsgList.setAlignmentX(Component.LEFT_ALIGNMENT);
        Panel_MainMsgList.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        Panel_MainMsgList.setPreferredSize(new java.awt.Dimension(300, 550));
        Panel_MainMsgList.setLayout(new java.awt.BorderLayout(0, 5));

        LeftSide_Label.setBackground(MainBackground);
        LeftSide_Label.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        LeftSide_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LeftSide_Label.setText(STATUS_LABEL.getContent());
        LeftSide_Label.setToolTipText("");
        LeftSide_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LeftSide_Label.setMaximumSize(new java.awt.Dimension(280, 17));
        LeftSide_Label.setMinimumSize(new java.awt.Dimension(80, 17));
        LeftSide_Label.setName(""); // NOI18N
        LeftSide_Label.setOpaque(true);
        LeftSide_Label.setPreferredSize(new java.awt.Dimension(80, 17));
        Panel_MainMsgList.add(LeftSide_Label, java.awt.BorderLayout.PAGE_START);

        MainScrollPane.setAlignmentX(0.0F);
        MainScrollPane.setHorizontalScrollBar(null);
        MainScrollPane.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        MainScrollPane.setMinimumSize(new java.awt.Dimension(280, 6));
        MainScrollPane.setPreferredSize(new java.awt.Dimension(300, 225));

        MessageTextArea.setEditable(false);
        MessageTextArea.setColumns(35);
        MessageTextArea.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        MessageTextArea.setLineWrap(true);
        MessageTextArea.setToolTipText("");
        MessageTextArea.setWrapStyleWord(true);
        MessageTextArea.setAlignmentX(0.0F);
        MessageTextArea.setAlignmentY(0.0F);
        MessageTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MessageTextArea.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        MessageTextArea.setMinimumSize(new java.awt.Dimension(280, 180));
        MessageTextArea.setName(""); // NOI18N
        MessageTextArea.setOpaque(false);
        MainScrollPane.setViewportView(MessageTextArea);
        MessageTextArea.getAccessibleContext().setAccessibleName("");

        Panel_MainMsgList.add(MainScrollPane, java.awt.BorderLayout.CENTER);

        Status_Panel.setBackground(MainBackground);
        Status_Panel.setAlignmentX(0.0F);
        Status_Panel.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        Status_Panel.setMinimumSize(new java.awt.Dimension(280, 180));
        Status_Panel.setPreferredSize(new java.awt.Dimension(300, 230));
        Status_Panel.setLayout(new javax.swing.BoxLayout(Status_Panel, javax.swing.BoxLayout.Y_AXIS));

        debugPanel.setBackground(MainBackground);
        debugPanel.setMinimumSize(new java.awt.Dimension(280, 110));
        debugPanel.setPreferredSize(new java.awt.Dimension(300, 110));
        debugPanel.setLayout(new javax.swing.BoxLayout(debugPanel, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setBackground(MainBackground);
        jPanel3.setMaximumSize(new java.awt.Dimension(32882, 32767));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        jPanel5.setBackground(MainBackground);
        jPanel5.setMaximumSize(new java.awt.Dimension(32882, 32767));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));
        jPanel5.add(filler12);

        PID_Label.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        PID_Label.setText("(PID)");
        PID_Label.setMaximumSize(new java.awt.Dimension(40, 15));
        jPanel5.add(PID_Label);
        jPanel5.add(filler13);

        jPanel3.add(jPanel5);

        jPanel1.setBackground(MainBackground);
        jPanel1.setMaximumSize(new java.awt.Dimension(32882, 200));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(filler15);

        errorCheckBox.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        errorCheckBox.setText(ERROR_DIALOGTITLE.getContent());
        errorCheckBox.setEnabled(false);
        errorCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        errorCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        errorCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errorCheckBoxActionPerformed(evt);
            }
        });
        jPanel1.add(errorCheckBox);
        jPanel1.add(filler8);

        errIncButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        errIncButton.setIcon(getPlusIcon());
        errIncButton.setToolTipText("Inc by 0.1");
        errIncButton.setAlignmentX(0.5F);
        errIncButton.setBorder(null);
        errIncButton.setBorderPainted(false);
        errIncButton.setContentAreaFilled(false);
        errIncButton.setEnabled(false);
        errIncButton.setIconTextGap(0);
        errIncButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        errIncButton.setMinimumSize(new java.awt.Dimension(16, 16));
        errIncButton.setPreferredSize(new java.awt.Dimension(16, 16));
        errIncButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errIncButtonActionPerformed(evt);
            }
        });
        jPanel1.add(errIncButton);
        jPanel1.add(filler16);

        errDecButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        errDecButton.setIcon(getMinusIcon());
        errDecButton.setToolTipText("Dec by 0.1");
        errDecButton.setAlignmentX(0.5F);
        errDecButton.setBorder(null);
        errDecButton.setBorderPainted(false);
        errDecButton.setContentAreaFilled(false);
        errDecButton.setEnabled(false);
        errDecButton.setIconTextGap(0);
        errDecButton.setMargin(new java.awt.Insets(0, 14, 0, 14));
        errDecButton.setMinimumSize(new java.awt.Dimension(16, 16));
        errDecButton.setPreferredSize(new java.awt.Dimension(16, 16));
        errDecButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                errDecButtonActionPerformed(evt);
            }
        });
        jPanel1.add(errDecButton);
        jPanel1.add(filler14);

        jPanel3.add(jPanel1);

        jPanel4.setBackground(MainBackground);
        jPanel4.setToolTipText("");
        jPanel4.setMaximumSize(new java.awt.Dimension(32882, 200));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));
        jPanel4.add(filler11);

        errorLabel.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        errorLabel.setToolTipText("");
        errorLabel.setMaximumSize(new java.awt.Dimension(100, 23));
        errorLabel.setMinimumSize(new java.awt.Dimension(100, 23));
        errorLabel.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(errorLabel);
        jPanel4.add(filler10);

        jPanel3.add(jPanel4);
        jPanel3.add(filler9);

        debugPanel.add(jPanel3);

        jPanel2.setBackground(MainBackground);
        jPanel2.setMaximumSize(new java.awt.Dimension(32882, 12000));
        jPanel2.setMinimumSize(new java.awt.Dimension(120, 110));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(120, 110));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));
        jPanel2.add(filler17);

        carEntryButton.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        carEntryButton.setMnemonic('C');
        carEntryButton.setText(CAR_ARRIVAL_BTN.getContent());
        carEntryButton.setToolTipText("활성화 : ...");
        carEntryButton.setEnabled(false);
        carEntryButton.setMaximumSize(new java.awt.Dimension(120, 40));
        carEntryButton.setMinimumSize(new java.awt.Dimension(120, 40));
        carEntryButton.setPreferredSize(new java.awt.Dimension(120, 40));
        carEntryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carEntryButtonActionPerformed(evt);
            }
        });
        jPanel2.add(carEntryButton);
        jPanel2.add(filler7);

        showStatisticsBtn.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        showStatisticsBtn.setMnemonic('T');
        showStatisticsBtn.setText(STATISTICS_BTN.getContent());
        showStatisticsBtn.setMaximumSize(new java.awt.Dimension(120, 40));
        showStatisticsBtn.setMinimumSize(new java.awt.Dimension(120, 40));
        showStatisticsBtn.setPreferredSize(new java.awt.Dimension(120, 40));
        showStatisticsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStatisticsBtnActionPerformed(evt);
            }
        });
        jPanel2.add(showStatisticsBtn);

        entryPanel.setAlignmentX(0.0F);
        entryPanel.setMaximumSize(new java.awt.Dimension(120, 40));
        entryPanel.setMinimumSize(new java.awt.Dimension(120, 40));
        entryPanel.setOpaque(false);
        entryPanel.setPreferredSize(new java.awt.Dimension(120, 40));
        entryPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));
        jPanel2.add(entryPanel);
        entryPanel.getAccessibleContext().setAccessibleDescription("");

        debugPanel.add(jPanel2);
        jPanel2.getAccessibleContext().setAccessibleName("");

        Status_Panel.add(debugPanel);
        removeDebugPanel();

        connStatusPanel.setBackground(MainBackground);
        connStatusPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        connStatusPanel.setMaximumSize(new java.awt.Dimension(65000, 65000));
        connStatusPanel.setMinimumSize(new java.awt.Dimension(280, 20));
        connStatusPanel.setPreferredSize(new java.awt.Dimension(300, 120));
        connStatusPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, gateCSGap));

        statusPanelGate1.setBackground(MainBackground);
        statusPanelGate1.setMaximumSize(new java.awt.Dimension(284, 32767));
        statusPanelGate1.setMinimumSize(new java.awt.Dimension(284, 20));
        statusPanelGate1.setName("statusPanelGate1"); // NOI18N
        statusPanelGate1.setPreferredSize(new Dimension(284, gateCSHt));
        statusPanelGate1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        statusPanelGate1.add(filler32);

        row1Heading.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        row1Heading.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        row1Heading.setText("Gate1");
        row1Heading.setToolTipText("");
        row1Heading.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        row1Heading.setMaximumSize(new java.awt.Dimension(74, 18));
        row1Heading.setMinimumSize(new java.awt.Dimension(74, 18));
        row1Heading.setName("row1Heading"); // NOI18N
        row1Heading.setPreferredSize(new java.awt.Dimension(74, 18));
        statusPanelGate1.add(row1Heading);
        statusPanelGate1.add(filler18);

        labelCamera1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelCamera1.setForeground(new java.awt.Color(255, 0, 0));
        labelCamera1.setText(CAMERA_LABEL.getContent());
        labelCamera1.setMaximumSize(new java.awt.Dimension(55, 18));
        labelCamera1.setMinimumSize(new java.awt.Dimension(55, 18));
        labelCamera1.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate1.add(labelCamera1);
        statusPanelGate1.add(filler19);

        labelE_Board1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelE_Board1.setForeground(new java.awt.Color(255, 0, 0));
        labelE_Board1.setText(E_BOARD_LABEL.getContent());
        labelE_Board1.setMaximumSize(new java.awt.Dimension(55, 18));
        labelE_Board1.setMinimumSize(new java.awt.Dimension(55, 18));
        labelE_Board1.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate1.add(labelE_Board1);
        statusPanelGate1.add(filler20);

        labelBar1.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelBar1.setForeground(new java.awt.Color(255, 0, 0));
        labelBar1.setText(GATE_BAR_LABEL.getContent());
        labelBar1.setMaximumSize(new java.awt.Dimension(55, 18));
        labelBar1.setMinimumSize(new java.awt.Dimension(55, 18));
        labelBar1.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate1.add(labelBar1);

        connStatusPanel.add(statusPanelGate1);

        statusPanelGate2.setBackground(MainBackground);
        statusPanelGate2.setMaximumSize(new java.awt.Dimension(284, 32767));
        statusPanelGate2.setMinimumSize(new java.awt.Dimension(284, 20));
        statusPanelGate2.setPreferredSize(new Dimension(284, gateCSHt));
        statusPanelGate2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        statusPanelGate2.add(filler30);

        row2Heading.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        row2Heading.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        row2Heading.setText("Gate2");
        row2Heading.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        row2Heading.setMaximumSize(new java.awt.Dimension(74, 18));
        row2Heading.setMinimumSize(new java.awt.Dimension(74, 18));
        row2Heading.setName("row2Heading"); // NOI18N
        row2Heading.setPreferredSize(new java.awt.Dimension(74, 18));
        statusPanelGate2.add(row2Heading);
        statusPanelGate2.add(filler21);

        labelCamera2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelCamera2.setForeground(new java.awt.Color(255, 0, 0));
        labelCamera2.setText(CAMERA_LABEL.getContent());
        labelCamera2.setMaximumSize(new java.awt.Dimension(55, 18));
        labelCamera2.setMinimumSize(new java.awt.Dimension(55, 18));
        labelCamera2.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate2.add(labelCamera2);
        statusPanelGate2.add(filler22);

        labelE_Board2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelE_Board2.setForeground(new java.awt.Color(255, 0, 0));
        labelE_Board2.setText(E_BOARD_LABEL.getContent());
        labelE_Board2.setMaximumSize(new java.awt.Dimension(55, 18));
        labelE_Board2.setMinimumSize(new java.awt.Dimension(55, 18));
        labelE_Board2.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate2.add(labelE_Board2);
        statusPanelGate2.add(filler23);

        labelBar2.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelBar2.setForeground(new java.awt.Color(255, 0, 0));
        labelBar2.setText(GATE_BAR_LABEL.getContent());
        labelBar2.setMaximumSize(new java.awt.Dimension(55, 18));
        labelBar2.setMinimumSize(new java.awt.Dimension(55, 18));
        labelBar2.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate2.add(labelBar2);

        connStatusPanel.add(statusPanelGate2);

        statusPanelGate3.setBackground(MainBackground);
        statusPanelGate3.setMaximumSize(new java.awt.Dimension(284, 32767));
        statusPanelGate3.setMinimumSize(new java.awt.Dimension(284, 20));
        statusPanelGate3.setPreferredSize(new Dimension(284, gateCSHt));
        statusPanelGate3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        statusPanelGate3.add(filler31);

        row3Heading.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        row3Heading.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        row3Heading.setText("Gate3");
        row3Heading.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        row3Heading.setMaximumSize(new java.awt.Dimension(74, 18));
        row3Heading.setMinimumSize(new java.awt.Dimension(74, 18));
        row3Heading.setName("row3Heading"); // NOI18N
        row3Heading.setPreferredSize(new java.awt.Dimension(74, 18));
        statusPanelGate3.add(row3Heading);
        statusPanelGate3.add(filler24);

        labelCamera3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelCamera3.setForeground(new java.awt.Color(255, 0, 0));
        labelCamera3.setText(CAMERA_LABEL.getContent());
        labelCamera3.setMaximumSize(new java.awt.Dimension(55, 18));
        labelCamera3.setMinimumSize(new java.awt.Dimension(55, 18));
        labelCamera3.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate3.add(labelCamera3);
        statusPanelGate3.add(filler25);

        labelE_Board3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelE_Board3.setForeground(new java.awt.Color(255, 0, 0));
        labelE_Board3.setText(E_BOARD_LABEL.getContent());
        labelE_Board3.setMaximumSize(new java.awt.Dimension(55, 18));
        labelE_Board3.setMinimumSize(new java.awt.Dimension(55, 18));
        labelE_Board3.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate3.add(labelE_Board3);
        statusPanelGate3.add(filler26);

        labelBar3.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelBar3.setForeground(new java.awt.Color(255, 0, 0));
        labelBar3.setText(GATE_BAR_LABEL.getContent());
        labelBar3.setMaximumSize(new java.awt.Dimension(55, 18));
        labelBar3.setMinimumSize(new java.awt.Dimension(55, 18));
        labelBar3.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate3.add(labelBar3);

        connStatusPanel.add(statusPanelGate3);

        statusPanelGate4.setBackground(MainBackground);
        statusPanelGate4.setMaximumSize(new java.awt.Dimension(284, 32767));
        statusPanelGate4.setMinimumSize(new java.awt.Dimension(284, 20));
        statusPanelGate4.setPreferredSize(new Dimension(284, gateCSHt));
        statusPanelGate4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        statusPanelGate4.add(filler33);

        row4Heading.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        row4Heading.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        row4Heading.setText("Gate4");
        row4Heading.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        row4Heading.setMaximumSize(new java.awt.Dimension(74, 18));
        row4Heading.setMinimumSize(new java.awt.Dimension(74, 18));
        row4Heading.setName("row3Heading"); // NOI18N
        row4Heading.setPreferredSize(new java.awt.Dimension(74, 18));
        statusPanelGate4.add(row4Heading);
        statusPanelGate4.add(filler28);

        labelCamera4.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelCamera4.setForeground(new java.awt.Color(255, 0, 0));
        labelCamera4.setText(CAMERA_LABEL.getContent());
        labelCamera4.setMaximumSize(new java.awt.Dimension(55, 18));
        labelCamera4.setMinimumSize(new java.awt.Dimension(55, 18));
        labelCamera4.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate4.add(labelCamera4);
        statusPanelGate4.add(filler29);

        labelE_Board4.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelE_Board4.setForeground(new java.awt.Color(255, 0, 0));
        labelE_Board4.setText(E_BOARD_LABEL.getContent());
        labelE_Board4.setMaximumSize(new java.awt.Dimension(55, 18));
        labelE_Board4.setMinimumSize(new java.awt.Dimension(55, 18));
        labelE_Board4.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate4.add(labelE_Board4);
        statusPanelGate4.add(filler34);

        labelBar4.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        labelBar4.setForeground(new java.awt.Color(255, 0, 0));
        labelBar4.setText(GATE_BAR_LABEL.getContent());
        labelBar4.setMaximumSize(new java.awt.Dimension(55, 18));
        labelBar4.setMinimumSize(new java.awt.Dimension(55, 18));
        labelBar4.setPreferredSize(new java.awt.Dimension(55, 18));
        statusPanelGate4.add(labelBar4);

        connStatusPanel.add(statusPanelGate4);

        Status_Panel.add(connStatusPanel);

        Panel_MainMsgList.add(Status_Panel, java.awt.BorderLayout.PAGE_END);

        WholePanel.add(Panel_MainMsgList, java.awt.BorderLayout.WEST);

        fullPanel.add(WholePanel, java.awt.BorderLayout.CENTER);
        fullPanel.add(filler27, java.awt.BorderLayout.LINE_START);
        fullPanel.add(filler6, java.awt.BorderLayout.LINE_START);

        statusTextPanel.setBackground(MainBackground);
        statusTextPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 1, 5, 1));
        statusTextPanel.setLayout(new java.awt.BorderLayout());

        statusTextField.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        statusTextField.setText("<Critical Status Information>");
        statusTextField.setToolTipText("");
        statusTextField.setMargin(new java.awt.Insets(0, 5, 0, 5));
        statusTextField.setMinimumSize(new java.awt.Dimension(100, 32));
        statusTextField.setPreferredSize(new java.awt.Dimension(166, 32));
        statusTextPanel.add(statusTextField, java.awt.BorderLayout.PAGE_END);

        fullPanel.add(statusTextPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(fullPanel, java.awt.BorderLayout.CENTER);

        visibleMenuBar.setBackground(MainBackground);
        visibleMenuBar.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        visibleMenuBar.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        visibleMenuBar.setMinimumSize(new java.awt.Dimension(110, 32));
        visibleMenuBar.setOpaque(false);
        visibleMenuBar.setPreferredSize(new java.awt.Dimension(660, 32));

        RecordsMenu.setBackground(MainBackground);
        RecordsMenu.setText(RECORD_MENU.getContent());
        RecordsMenu.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        RecordsMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RecordsMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RecordsMenu.setMaximumSize(new java.awt.Dimension(120, 32767));
        RecordsMenu.setPreferredSize(new java.awt.Dimension(100, 24));

        EntryRecordItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        EntryRecordItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        EntryRecordItem.setText(ARRIVAL_MENU_ITEM.getContent());
        EntryRecordItem.setEnabled(false);
        EntryRecordItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntryRecordItemActionPerformed(evt);
            }
        });
        RecordsMenu.add(EntryRecordItem);

        RunRecordItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        RunRecordItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        RunRecordItem.setText(BOOTING_MENU_ITEM.getContent());
        RunRecordItem.setEnabled(false);
        RunRecordItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunRecordItemActionPerformed(evt);
            }
        });
        RecordsMenu.add(RunRecordItem);

        LoginRecordItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        LoginRecordItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        LoginRecordItem.setText(LOGIN_RECORD_MENU_ITEM.getContent());
        LoginRecordItem.setEnabled(false);
        LoginRecordItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginRecordItemActionPerformed(evt);
            }
        });
        RecordsMenu.add(LoginRecordItem);

        visibleMenuBar.add(Box.createRigidArea(new Dimension(20, 0)));

        visibleMenuBar.add(RecordsMenu);

        VehicleMenu.setBackground(MainBackground);
        VehicleMenu.setText(VEHICLE_MENU.getContent());
        VehicleMenu.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        VehicleMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        VehicleMenu.setMaximumSize(new java.awt.Dimension(120, 32767));
        VehicleMenu.setPreferredSize(new java.awt.Dimension(100, 24));

        VehicleListItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        VehicleListItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        VehicleListItem.setText(VEHICLE_MANAGE_MENU_ITEM.getContent());
        VehicleListItem.setEnabled(false);
        VehicleListItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processVehicleList(evt);
            }
        });
        VehicleMenu.add(VehicleListItem);

        DriverListItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        DriverListItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        DriverListItem.setText(DRIVERS_MENU_ITEM.getContent());
        DriverListItem.setEnabled(false);
        DriverListItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DriverListItemActionPerformed(evt);
            }
        });
        VehicleMenu.add(DriverListItem);

        AffiliationItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        AffiliationItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        AffiliationItem.setText(AFFILIATION_MENU_ITEM.getContent());
        AffiliationItem.setEnabled(false);
        AffiliationItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AffiliationItemActionPerformed(evt);
            }
        });
        VehicleMenu.add(AffiliationItem);

        BuildingItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        BuildingItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        BuildingItem.setText(BUILDING_MENU_ITEM.getContent());
        BuildingItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuildingItemActionPerformed(evt);
            }
        });
        VehicleMenu.add(BuildingItem);

        visibleMenuBar.add(VehicleMenu);

        CommandMenu.setBackground(MainBackground);
        CommandMenu.setText(SYSTEM_MENU.getContent());
        CommandMenu.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        CommandMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CommandMenu.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        CommandMenu.setMaximumSize(new java.awt.Dimension(120, 32767));
        CommandMenu.setPreferredSize(new java.awt.Dimension(100, 24));

        AttendantListItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        AttendantListItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        AttendantListItem.setText(USERS_ITEM.getContent());
        AttendantListItem.setEnabled(false);
        AttendantListItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processAttendantList(evt);
            }
        });
        CommandMenu.add(AttendantListItem);

        SettingsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        SettingsItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        SettingsItem.setText(SETTING_MENU_ITEM.getContent());
        SettingsItem.setEnabled(false);
        SettingsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsItemActionPerformed(evt);
            }
        });
        CommandMenu.add(SettingsItem);

        CloseProgramItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        CloseProgramItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        CloseProgramItem.setText(QUIT_MENU_ITEM.getContent());
        CloseProgramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processCloseProgram(evt);
            }
        });
        CommandMenu.add(CloseProgramItem);

        licenseMenuItem.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        licenseMenuItem.setText(LicenseButton.getContent());
        licenseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                licenseMenuItemActionPerformed(evt);
            }
        });
        CommandMenu.add(licenseMenuItem);

        visibleMenuBar.add(CommandMenu);

        LogInOutMenu.setBackground(MainBackground);
        LogInOutMenu.setText(LOGIN_MENU.getContent());
        LogInOutMenu.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        LogInOutMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LogInOutMenu.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        LogInOutMenu.setMaximumSize(new java.awt.Dimension(110, 22));
        LogInOutMenu.setMinimumSize(new java.awt.Dimension(110, 22));
        LogInOutMenu.setPreferredSize(new java.awt.Dimension(110, 22));
        LogInOutMenu.setRequestFocusEnabled(false);

        LoginUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        LoginUser.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        LoginUser.setMnemonic('I');
        LoginUser.setText(LOGIN_MENU_ITEM.getContent());
        LoginUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processLogIn(evt);
            }
        });
        LogInOutMenu.add(LoginUser);

        LogoutUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        LogoutUser.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        LogoutUser.setText(LOGOUT_MENU_ITEM.getContent());
        LogoutUser.setEnabled(false);
        LogoutUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutUserActionPerformed(evt);
            }
        });
        LogInOutMenu.add(LogoutUser);

        visibleMenuBar.add(Box.createHorizontalGlue());
        visibleMenuBar.add(metaKeyLabel);
        visibleMenuBar.add(Box.createHorizontalGlue());

        visibleMenuBar.add(LogInOutMenu);

        IsManagerLabelMenu.setText("N");
        IsManagerLabelMenu.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        IsManagerLabelMenu.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        IsManagerLabelMenu.setMaximumSize(new java.awt.Dimension(20, 22));
        IsManagerLabelMenu.setMinimumSize(new java.awt.Dimension(20, 22));
        IsManagerLabelMenu.setPreferredSize(new java.awt.Dimension(20, 22));

        managerLabel.setFont(new java.awt.Font(font_Type, font_Style, font_Size));

        visibleMenuBar.add(managerLabel);

        visibleMenuBar.add(IsManagerLabelMenu);
        visibleMenuBar.add(manager_ID_gap);

        UserIDLabelMenu.setBackground(MainBackground);
        UserIDLabelMenu.setText(ID_DEFAULT.getContent());
        UserIDLabelMenu.setToolTipText("");
        UserIDLabelMenu.setAlignmentX(0.0F);
        UserIDLabelMenu.setFocusPainted(true);
        UserIDLabelMenu.setFont(new java.awt.Font(font_Type, font_Style, font_Size));
        UserIDLabelMenu.setHideActionText(true);
        UserIDLabelMenu.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        UserIDLabelMenu.setInheritsPopupMenu(true);
        UserIDLabelMenu.setMaximumSize(new java.awt.Dimension(120, 22));
        UserIDLabelMenu.setMinimumSize(new java.awt.Dimension(90, 22));
        UserIDLabelMenu.setPreferredSize(new java.awt.Dimension(90, 22));

        ID_Label.setFont(new java.awt.Font(font_Type, font_Style, font_Size));

        visibleMenuBar.add(ID_Label);

        visibleMenuBar.add(UserIDLabelMenu);

        setJMenuBar(visibleMenuBar);

        setSize(new java.awt.Dimension(895, 667));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void closeButtonClicked(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeButtonClicked
        askUserIntentionOnProgramStop(false);
    }//GEN-LAST:event_closeButtonClicked

    private void processAttendantList(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processAttendantList
        displayTopForm(Attendant);
    }//GEN-LAST:event_processAttendantList

    private void processLogIn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processLogIn
        if (getLoginDialog() == null) {
            try {
                setLoginDialog(new LoginDialog(null, true));
                getLoginDialog().setLocationRelativeTo(null);
                getLoginDialog().addLoginEventListener(new LoginEventListener() {
                    public void loginEventOccurred(LoginWindowEvent e) {
                        Globals.loginID = e.getUID();
                        Globals.loginPW = e.getPW();
                        Globals.isManager = e.getIsManager();
                        Globals.isAdmin = loginID.equals(ADMIN_ID);
                        
                        if (Globals.isAdmin) {
                            if (DEBUG_FLAG) {
                                DEBUG = true;
                            }
                            if (RANDOM_FLAG) {
                                RANDOM_ATTENDANT = true;
                            }
                        }                        
                        if (DEBUG) {
                            insertDebugPanel();
                            errorCheckBox.setEnabled(DEBUG);
                        } else {
                            removeDebugPanel();
                        }                        
                        changeUserID_etc();
                        recordLogin();
                        if (Globals.isManager) {
                            enableManagerItem(true);
                        }
                        addMessageLine(MessageTextArea, Globals.loginID + " " + LOG_IN.getContent());
                    }
                });
            } catch (Exception ex) {
                logParkingException(Level.SEVERE, ex, "(user login processing)");
            }
        }
        getLoginDialog().setVisible(true); // Pop open login window
    }//GEN-LAST:event_processLogIn
    
    private void processCloseProgram(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processCloseProgram
        askUserIntentionOnProgramStop(false);
    }//GEN-LAST:event_processCloseProgram

    public void askUserIntentionOnProgramStop(boolean forced) {
        if (Globals.loginID != null) {
            if (! processLogOut(forced))
                return;
        }
        
        int result = 0;
        if (forced) {
            JOptionPane.showMessageDialog(this, OSPARKING_STOPS.getContent());
            result = JOptionPane.YES_OPTION;
        } else {
            result = JOptionPane.showConfirmDialog(this, 
                    SHUT_DOWN_CONFIRM_DIALOG.getContent(), 
                    SYSTEM_SHUTDOWN_CONFIRM.getContent(), JOptionPane.YES_NO_OPTION);
        }
        
        if (result == JOptionPane.YES_OPTION) {
            stopRunningTheProgram();
        }   
    }
    
    private void processVehicleList(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processVehicleList
        displayTopForm(Vehicle);
    }//GEN-LAST:event_processVehicleList

    private void VehiclesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VehiclesButtonActionPerformed
        (new VehiclesForm(this)).setVisible(true);                
    }//GEN-LAST:event_VehiclesButtonActionPerformed

    private void UsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsersButtonActionPerformed
        processAttendantList(evt);
    }//GEN-LAST:event_UsersButtonActionPerformed

    private void SettingsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsItemActionPerformed
        displayTopForm(Settings);
    }//GEN-LAST:event_SettingsItemActionPerformed

    private void RunRecordItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunRecordItemActionPerformed
        if (showRunRecordForm == null) 
        {
            showRunRecordForm = new RunRecordList();
        }
        showRunRecordForm.setVisible(true);
    }//GEN-LAST:event_RunRecordItemActionPerformed

    private void LoginRecordItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginRecordItemActionPerformed
        if (showLoginRecordForm == null) 
        {
            showLoginRecordForm = new LoginRecordList();
        }
        showLoginRecordForm.getDatesRefreshTable();
        showLoginRecordForm.setVisible(true);        
    }//GEN-LAST:event_LoginRecordItemActionPerformed
           
    public static int manualSimulationImageID = 0;
    private void carEntryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carEntryButtonActionPerformed
        Random randomInteger = new Random();
        byte gateNo = (byte) 1;
        
        getPassingDelayStat()[gateNo].setICodeArrivalTime(System.currentTimeMillis());
        
        while (isGateBusy(gateNo)) {
            gateNo = (byte) (randomInteger.nextInt(gateCount) + 1);
            getPassingDelayStat()[gateNo].setICodeArrivalTime(System.currentTimeMillis());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                logParkingException(Level.SEVERE, ex, "Car entry simulation button");
            }
        }
        
        if (gateDeviceTypes[gateNo].cameraType == OSP_enums.CameraType.Blackfly) {
            // Given the camera is connected, order it to take a picture
            if ((getSockConnStat()[Camera.ordinal()][gateNo]).isConnected()) {
                BlackFlyManager camMan = 
                        (BlackFlyManager)deviceManagers[DeviceType.Camera.ordinal()][gateNo];
                synchronized (camMan.takePicture) {
                    camMan.takePicture.notify();
                }
                try {
                    synchronized(camMan.processTag) {
                        camMan.processTag.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControlGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!(gateBusy[gateNo])) {
                    processCarArrival(gateNo, --manualSimulationImageID,
                            camMan.carTagNumber, null, camMan.rawImage); 
                }   
            } else {
                System.out.println("not connected");
                addMessageLine(getMessageTextArea(), "카메라 #" + gateNo +" 단절 상태임");
            }
        } else {
            int imageNo = randomInteger.nextInt(6) + 1;
            String tagNumber = dummyMessages[imageNo].getCarNumber();
            BufferedImage carImage = dummyMessages[imageNo].getBufferedImg();
            
            processCarArrival(gateNo, --manualSimulationImageID, tagNumber, carImage, null);
        }
    }//GEN-LAST:event_carEntryButtonActionPerformed

    private void DriverListItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DriverListItemActionPerformed
        displayTopForm(TopForms.CarOwner);
    }//GEN-LAST:event_DriverListItemActionPerformed

    private void EntryRecordItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntryRecordItemActionPerformed
        new ManageArrivalList().run();
    }//GEN-LAST:event_EntryRecordItemActionPerformed

    private void errorCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errorCheckBoxActionPerformed
        if (getErrorCheckBox().isSelected()) {
            addMessageLine(MessageTextArea, ON_ARTIFI_ERROR_MSG.getContent());
            addMessageLine(MessageTextArea, 
                    "\t" + ARTIF_ERROR_RATE.getContent() + getPercentString(ERROR_RATE));
            errorLabel.setText(RATE_LABEL.getContent() + getPercentString(ERROR_RATE));
            
            // Enable error rate change buttons
            errIncButton.setEnabled(true);
            errDecButton.setEnabled(true);
        } else {
            addMessageLine(MessageTextArea, NO_APP_MSG.getContent());
            errorLabel.setText("");
            
            errIncButton.setEnabled(false);
            errDecButton.setEnabled(false);
        }
    }//GEN-LAST:event_errorCheckBoxActionPerformed

    /**
     * @return the getErrorCheckBox control
     */
    public javax.swing.JCheckBox getErrorCheckBox() {
        return errorCheckBox;
    }

    /**
     * @param errorCheckBox the ErrorCheckBox to set
     */
    public void setErrorCheckBox(javax.swing.JCheckBox errorCheckBox) {
        this.errorCheckBox = errorCheckBox;
    }    
    
    public Object MsgAreaMutex  = new Object();
      
    private void errIncButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errIncButtonActionPerformed
        if (ERROR_RATE < 0.9) {
             ERROR_RATE += 0.1f;
            errorLabel.setText(RATE_LABEL.getContent() + getPercentString(ERROR_RATE));
         } else {
            displayRateLimit(ARTI_CURR_ERR_LIMIT_2.getContent());            
         }
    }//GEN-LAST:event_errIncButtonActionPerformed

    private void errDecButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_errDecButtonActionPerformed
        if (ERROR_RATE > 0.10) {
            ERROR_RATE -= 0.1f;
            errorLabel.setText(RATE_LABEL.getContent() + getPercentString(ERROR_RATE));
        } else {
            displayRateLimit(ARTI_CURR_ERR_LIMIT_b.getContent());            
        }
    }//GEN-LAST:event_errDecButtonActionPerformed

    static String indent4 = "    ";
    private void showStatisticsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showStatisticsBtnActionPerformed
        // show camera sockConnStat statistics
        if (getStatPan() != null) {
            getStatPan().setVisible(true);
            return;
        } 
        StringBuffer perfDesc = new StringBuffer();

        //<editor-fold desc="-- Show detailed performance statistics">
        perfDesc.append("[" + DEVICE_DISCONN_COUNT.getContent() + "]" + System.lineSeparator());
        for (DeviceType type : DeviceType.values()) {
            for (int gateNo = 1; gateNo <= gateCount; gateNo++ ) {
                perfDesc.append(indent4);
                perfDesc.append(type.getContent() + " #" + gateNo 
                        + getSockConnStat()[type.ordinal()][gateNo].getPerformanceDescription());
            }
            perfDesc.append(System.lineSeparator());
        }

        StringBuffer gateBar = new StringBuffer();
        for (DeviceType type : DeviceType.values()) {
            for (int gateNo = 1; gateNo <= gateCount; gateNo++) {
                DeviceCommand deviceCommand = getPerfomStatistics()[type.ordinal()][gateNo];

                if (deviceCommand != null && deviceCommand.hasData()) 
                {
                    gateBar.append(indent4);
                    gateBar.append(getDevType(type, (byte)gateNo) + "#" + gateNo + "- "
                            + getPerfomStatistics()[type.ordinal()][gateNo].getPerformanceDescription());
                }
            }   
        }
        perfDesc.append(gateBar);
        //</editor-fold>
        
        /**
         * Display major performance statistics.
         */
        perfDesc.append("[" + PASSING_DELAY_AVG.getContent() + "(ms)]" + System.lineSeparator());
        
        fetchPassingDelay();
        for (int gateNo = 1; gateNo <= gateCount; gateNo++) {
            perfDesc.append(indent4 + GATE_LABEL.getContent() + "#" + gateNo + " :");
            perfDesc.append(getPassingDelayStat()[gateNo].getPassingDelayAvg());
            perfDesc.append(System.lineSeparator());        
        }  
        perfDesc.append(System.lineSeparator());        
        
        perfDesc.append("* " + ARTIF_ERROR_RATE.getContent());
        if (errorCheckBox.isSelected()) {
            perfDesc.append(getPercentString(ERROR_RATE));
        } else {
            perfDesc.append("N/A(=0%)");
        }
        perfDesc.append(System.lineSeparator());
        perfDesc.append(P_DELAY_DEF_1.getContent() + System.lineSeparator());
        perfDesc.append(indent4 + "<" + P_DELAY_DEF_2.getContent() + "> ~ ");
        perfDesc.append("<" + P_DELAY_DEF_3.getContent() + ">" + System.lineSeparator());
        
        setStatPan(new DisplayStatFrame(perfDesc.toString(), this));
        getStatPan().setVisible(true);
    }//GEN-LAST:event_showStatisticsBtnActionPerformed

    private DisplayStatFrame statPan = null;
    
    private void LogoutUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutUserActionPerformed
        processLogOut(false);
    }//GEN-LAST:event_LogoutUserActionPerformed

    private void licenseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_licenseMenuItemActionPerformed
        showLicensePanel(this, "License Notice on OsParking program");
    }//GEN-LAST:event_licenseMenuItemActionPerformed

    private void CarIOListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarIOListButtonActionPerformed
        new ManageArrivalList().run();
    }//GEN-LAST:event_CarIOListButtonActionPerformed

    private void autoOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoOpenButtonActionPerformed
        autoGateOpenCheckBox.setSelected(!autoGateOpenCheckBox.isSelected());
    }//GEN-LAST:event_autoOpenButtonActionPerformed

    private void QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitActionPerformed
        askUserIntentionOnProgramStop(false);
    }//GEN-LAST:event_QuitActionPerformed

    private void AffiliationItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AffiliationItemActionPerformed
        displayTopForm(TopForms.Affiliation);
    }//GEN-LAST:event_AffiliationItemActionPerformed

    private void BuildingItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuildingItemActionPerformed
        displayTopForm(TopForms.Building);
    }//GEN-LAST:event_BuildingItemActionPerformed

    LedProtocol ledNoticeProtocol = new LedProtocol(); 

    private String getDevType(DeviceType type, byte gateNo) {
        String typeName = "";
        switch (type) {
            case Camera:
                switch (Globals.gateDeviceTypes[gateNo].cameraType) {
                    case Simulator:
                        typeName = CAMERA_SIM.getContent();
                        break;
                    default:
                        typeName = Globals.gateDeviceTypes[gateNo].cameraType.toString();
                        break;
                } 
                break;
                
            case E_Board:
                switch (Globals.gateDeviceTypes[gateNo].eBoardType) {
                    case Simulator:
                        typeName = E_BOARD_SIM.getContent();
                        break;
                    default:
                        typeName = Globals.gateDeviceTypes[gateNo].eBoardType.toString();
                        break;
                } 
                break;
                
            case GateBar:
                switch (Globals.gateDeviceTypes[gateNo].gateBarType) {
                    case Simulator:
                        typeName = GATEBAR_SIM.getContent();
                        break;
                    default:
                        typeName = Globals.gateDeviceTypes[gateNo].gateBarType.toString();
                        break;
                } 
                break;
        }
        return typeName;
    }

    /**
     * Store a car arrival information in the DB and show car image on GUI.
     * 
     * @param permission Vehicle entry permission status
     * @param cameraID Camera ID(1,2,3,...), equivalent to the gate ID
     * @param arrivalTime Car arrival time at the gate
     * @param tagRecognized Car tag number recognized by the LPR module
     * @param tagRegistered Given it is a registered car, tag number in the DB, or empty string
     * @param filenameModified 
     * @param image Car arrival image
     * @param isOpen True, if car is not eligible to park but bar is simulated 
     * to open by the attendant.
     */
    private void insertDisplayImage(PermissionType permission, byte cameraID, 
            Date arrivalTime, String tagRecognized, String tagRegistered, 
            BufferedImage image, boolean isOpen) 
    {
        BarOperation barOptn;
        long arrSeqNo = -1;
        
        if (autoGateOpenCheckBox.isSelected()) {
            //<editor-fold desc="-- Automatic Gate Open"">
            if (permission == PermissionType.ALLOWED)
            {
                barOptn = BarOperation.REGISTERED_CAR_OPENED;
            } else {
                // Disallowed or visiting cars
                barOptn = BarOperation.AUTO_OPENED;
            }
            //</editor-fold>
        } else {
            if (permission == ALLOWED) {
                barOptn = BarOperation.REGISTERED_CAR_OPENED;
            } else {
                //<editor-fold desc="-- Handle Not allowed cars ">
                if (Globals.RANDOM_ATTENDANT && isOpen) {
                    // Handle as if [Open] button were pressed in the "DisAllowedCar" form
                    barOptn = BarOperation.MANUAL;
                } else {
                    // Handle as if [Close bar] button were pressed in the "DisAllowedCar" form
                    barOptn = BarOperation.REMAIN_CLOSED;
                }
                //</editor-fold>
            }
        }
        arrSeqNo = insertDBrecord(cameraID, arrivalTime, tagRecognized, tagRegistered, 
                image,  -1, -1, null, barOptn);
        updateMainForm(cameraID, tagRecognized, arrSeqNo, permission, barOptn);
    }

    private void controlStoppedCar(byte cameraID, int imageSN, String tagRecognized, 
            BufferedImage image, Date arrivalTime, String tagEnteredAs, 
            String remark, PermissionType permission, int carPassingDelayMs) 
    {
        switch (permission) {

            case DISALLOWED:
                //<editor-fold desc="-- Handle Parking Disallowed Car"">
                new DisAllowedCar(this, true, tagRecognized, arrivalTime,
                        tagEnteredAs, remark, cameraID, imageSN, 
                        image, carPassingDelayMs).setVisible(true);
                break;   
                //</editor-fold> 

            case UNREGISTERED:
                //<editor-fold desc="-- Handle Unregistered Car"">
                new VisitingCar(this, true, tagRecognized, arrivalTime, cameraID,
                        imageSN, image, carPassingDelayMs).setVisible(true);
                break;
                //</editor-fold> 

            case BADTAGFORMAT:
                // popup form, allow parking or not, store the result
                break;

            default:
                break;  
            }
    }

    public void processCarArrival(byte gateNo, int imageID, String tagRecognized, BufferedImage carImage, 
            FlyCapture2.Image blackFlyImage) 
    {
        Date arrivalTime = Calendar.getInstance().getTime();
        StringBuffer tagRegistered = new StringBuffer();
        StringBuffer remark = new StringBuffer();    
        
        PermissionType permission = enteranceAllowed(tagRecognized, tagRegistered, remark);
        
        int carPassingDelayMs = rand.nextInt(MAX_PASSING_DELAY)  + CAR_PERIOD;        

        setGateBusy(gateNo, true);

        if (permission == ALLOWED 
                || autoGateOpenCheckBox.isSelected()
                || Globals.RANDOM_ATTENDANT) 
        {
            getPassingDelayStat()[gateNo].setAccumulatable(true);
        } else {
            getPassingDelayStat()[gateNo].setAccumulatable(false);
        }

        float fValue = gateRandomOpen.nextFloat();
        boolean isOpen = fValue < 0.8;
        
        if (autoGateOpenCheckBox.isSelected() || 
                permission == ALLOWED || 
                Globals.RANDOM_ATTENDANT)
        {
            getPassingDelayStat()[gateNo].setAccumulatable(true);
            
            if (!autoGateOpenCheckBox.isSelected() && permission != ALLOWED) {
                imageID = -imageID;
            }
            raiseGateBar(gateNo, imageID, carPassingDelayMs);
            
            // Moved after bar open command to experdite car processing
            interruptEBoardDisplay(gateNo, tagRecognized, 
                    permission, remark.toString(), tagRegistered.toString(),
                    imageID, carPassingDelayMs);
            
            if (carImage == null) {
                carImage = convertRaw2Buffered(blackFlyImage);
            }
            insertDisplayImage(permission, gateNo, arrivalTime, tagRecognized,
                    tagRegistered.toString(), carImage, isOpen);
            setGateBusy(gateNo, false);
        } else {
            // Handle unregistered or not permitted cars
            getPassingDelayStat()[gateNo].setAccumulatable(false);
            
            interruptEBoardDisplay(gateNo, tagRecognized, 
                    permission, remark.toString(), tagRegistered.toString(),
                    imageID, carPassingDelayMs);
            
            if (carImage == null) {
                carImage = convertRaw2Buffered(blackFlyImage);
            }
            showBufferedImage(gateNo, getGatePanel().getCarPicLabels()[gateNo], carImage);
            
            controlStoppedCar(gateNo, imageID, tagRecognized, carImage,
                    arrivalTime, tagRegistered.toString(),
                    remark.toString(), permission, carPassingDelayMs);
        }
    }

    private BufferedImage convertRaw2Buffered(FlyCapture2.Image rawImage) {
        int width = ImgWidth;
        int height = ImgHeight;
        BufferedImage bufferedImage = new BufferedImage(width, height, TYPE_BYTE_GRAY);
        byte[] pxBytes = new byte[width * height];
        int [] pxInts = new int[width * height];
        int[] matrix = new int[width * height];
        DataBufferInt buffer = new DataBufferInt(matrix, matrix.length);
        int[] bandMasks = {0xFF};
        WritableRaster ras = Raster.createPackedRaster(
                buffer, width, height, width, bandMasks, null);
                
        rawImage.GetData().get(pxBytes);
        for (int i = 0; i < pxBytes.length; i++) {
            pxInts[i] = pxBytes[i];
        }
        ras.setPixels(0, 0, width, height, pxInts);
        bufferedImage.setData(ras);   
        return bufferedImage;
    }
    
    void setStatusPan(Dimension dim) {
        Status_Panel.setPreferredSize(dim);
        Status_Panel.setSize(dim);         
    }
    
    private void reduceConnPanHt() {
        Dimension dim = connStatusPanel.getPreferredSize();
        Dimension dimNew = new Dimension(dim.width, 
                dim.height - (gateCSHt + gateCSGap) * (MAX_GATES - gateCount) + 5);
        connStatusPanel.setPreferredSize(dimNew);
        connStatusPanel.setSize(dimNew);
        
        if (debugPanel.isVisible()) {
            setStatusPan(addHeight(debugPanel.getPreferredSize(),
                    connStatusPanel.getPreferredSize()));
        } else {
            setStatusPan(connStatusPanel.getPreferredSize());
        }          
    }
    
    private Dimension addHeight(Dimension panel1, Dimension panel2) {
        return new Dimension(panel1.width, panel1.height + panel2.height);
    }    

    private void displayTopForm(ControlEnums.TopForms form) {
        if (getTopForms()[form.ordinal()] == null) {
            topForms[form.ordinal()] = createTopForm(form);
        }
        getTopForms()[form.ordinal()].setVisible(true);     
    }    

    private void displayRateLimit(String limitDescription) {
        toolkit.beep();
        addMessageLine(MessageTextArea, ARTI_CURR_ERR_LIMIT_1.getContent()
                + getPercentString(ERROR_RATE) + limitDescription);
    }

    /**
     * @return the gateBusy
     */
    public boolean isGateBusy(int gateNo) {
        return gateBusy[gateNo];
    }

    /**
     * @param gateBusy the gateBusy to set
     */
    public void setGateBusy(int gate, boolean busy) {
        if (!busy) {
            // Camera is a simulator and when it is connected
            if (deviceType[Camera.ordinal()][gate] == CameraType.Simulator.ordinal()) {
                OutputStream outStream = null;
                try {
                    outStream = ((ISocket)deviceManagers[Camera.ordinal()][gate])
                            .getSocket().getOutputStream();
                    outStream.write(os_FreeBytes);
                } catch (IOException ex) {
                    gfinishConnection(Camera, null,
                            "sending gate free message",
                            (byte)gate,
                            getSocketMutex()[Camera.ordinal()][gate],
                            ((ISocket)deviceManagers[Camera.ordinal()][gate]).getSocket(),
                            getMessageTextArea(), 
                            getSockConnStat()[Camera.ordinal()][gate],
                            getConnectDeviceTimer()[Camera.ordinal()][gate],
                            isSHUT_DOWN()
                    );
                }
            }
        }
        this.gateBusy[gate] = busy;
    }

    private void increaseListHeightBeforehand(int gateNo) {
        JList arrivalList = getGatePanel().getEntryList(gateNo);
        int rowCount = ((DefaultListModel<CarAdmission>)admissionListModel[gateNo]).getSize();
        Dimension tableDim = new Dimension(arrivalList.getSize().width, 
                RECENT_ROW_HEIGHT * (rowCount + 1) + 8); 
        
        arrivalList.setSize(tableDim);
        arrivalList.setPreferredSize(tableDim);                  
    }

    private JFrame createTopForm(ControlEnums.TopForms form) {
        JFrame topForm = null;
        
        switch (form) {
                
            case Vehicle:
                topForm = new VehiclesForm(this);
                break;
                
            case CarOwner:
                topForm = new CarOwners(this, null);
                break;
                
            case Affiliation:
                topForm = new Affiliations(this);
                break;
                
            case Building:
                topForm = new Buildings(this);
                break;
                
            case Attendant:
                topForm = new AttListForm(this, loginID, loginPW, isManager);
                break;
                
            case Settings:
                topForm = new Settings_System(this);
                break;
                
            case Arrivals:
                topForm = new CarArrivals(this);
                break;
                
            default:
                break;
        }
        
        return topForm;
    }

    /**
     * @return the statPan
     */
    public DisplayStatFrame getStatPan() {
        return statPan;
    }

    /**
     * @param statPan the statPan to set
     */
    public void setStatPan(DisplayStatFrame statPan) {
        this.statPan = statPan;
    }
    
//    private CarArrivals arrivals = null;

//    /**
//     * @return the arrivals
//     */
//    public CarArrivals getArrivals() {
//        return arrivals;
//    }

//    /**
//     * @param arrivals the arrivals to set
//     */
//    public void setArrivals(CarArrivals arrivals) {
//        this.arrivals = arrivals;
//    }
    
    class ManageArrivalList extends Thread {
        public void run() {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            displayTopForm(Arrivals);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="-- Form Control Items ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AffiliationItem;
    private javax.swing.JMenuItem AttendantListItem;
    private javax.swing.JMenuItem BuildingItem;
    private javax.swing.JButton CarIOListButton;
    private javax.swing.JLabel ClockLabel;
    private javax.swing.JMenuItem CloseProgramItem;
    private javax.swing.JMenu CommandMenu;
    private javax.swing.JMenuItem DriverListItem;
    private javax.swing.JMenuItem EntryRecordItem;
    private javax.swing.JMenu IsManagerLabelMenu;
    private javax.swing.JLabel LeftSide_Label;
    private javax.swing.JMenu LogInOutMenu;
    private javax.swing.JMenuItem LoginRecordItem;
    private javax.swing.JMenuItem LoginUser;
    private javax.swing.JMenuItem LogoutUser;
    private javax.swing.JScrollPane MainScrollPane;
    private javax.swing.JToolBar MainToolBar;
    private javax.swing.JTextArea MessageTextArea;
    private javax.swing.JLabel PID_Label;
    private javax.swing.JPanel PanelMainTop;
    private javax.swing.JPanel Panel_MainMsgList;
    private javax.swing.JButton Quit;
    private javax.swing.JMenu RecordsMenu;
    private javax.swing.JMenuItem RunRecordItem;
    private javax.swing.JMenuItem SettingsItem;
    private javax.swing.JPanel Status_Panel;
    private javax.swing.JMenu UserIDLabelMenu;
    private javax.swing.JButton UsersButton;
    private javax.swing.JMenuItem VehicleListItem;
    private javax.swing.JMenu VehicleMenu;
    private javax.swing.JButton VehiclesButton;
    private javax.swing.JPanel WholePanel;
    private javax.swing.JCheckBox autoGateOpenCheckBox;
    private javax.swing.JButton autoOpenButton;
    private javax.swing.JButton carEntryButton;
    private javax.swing.JPanel connStatusPanel;
    private javax.swing.JPanel debugPanel;
    private javax.swing.JPanel entryPanel;
    private javax.swing.JButton errDecButton;
    private javax.swing.JButton errIncButton;
    public javax.swing.JCheckBox errorCheckBox;
    private javax.swing.JLabel errorLabel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler24;
    private javax.swing.Box.Filler filler25;
    private javax.swing.Box.Filler filler26;
    private javax.swing.Box.Filler filler27;
    private javax.swing.Box.Filler filler28;
    private javax.swing.Box.Filler filler29;
    private javax.swing.Box.Filler filler30;
    private javax.swing.Box.Filler filler31;
    private javax.swing.Box.Filler filler32;
    private javax.swing.Box.Filler filler33;
    private javax.swing.Box.Filler filler34;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.Box.Filler fillerLeft;
    private javax.swing.JPanel fullPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelBar1;
    private javax.swing.JLabel labelBar2;
    private javax.swing.JLabel labelBar3;
    private javax.swing.JLabel labelBar4;
    private javax.swing.JLabel labelCamera1;
    private javax.swing.JLabel labelCamera2;
    private javax.swing.JLabel labelCamera3;
    private javax.swing.JLabel labelCamera4;
    private javax.swing.JLabel labelE_Board1;
    private javax.swing.JLabel labelE_Board2;
    private javax.swing.JLabel labelE_Board3;
    private javax.swing.JLabel labelE_Board4;
    private javax.swing.JMenuItem licenseMenuItem;
    private javax.swing.Box.Filler manager_ID_gap;
    private javax.swing.JLabel myMetaKeyLabel;
    private javax.swing.JLabel row1Heading;
    private javax.swing.JLabel row2Heading;
    private javax.swing.JLabel row3Heading;
    private javax.swing.JLabel row4Heading;
    public javax.swing.JButton showStatisticsBtn;
    private javax.swing.JPanel statusPanelGate1;
    private javax.swing.JPanel statusPanelGate2;
    private javax.swing.JPanel statusPanelGate3;
    private javax.swing.JPanel statusPanelGate4;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JPanel statusTextPanel;
    private javax.swing.JMenuBar visibleMenuBar;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
    
    JLabel managerLabel = new JLabel(MANAGER_MANU.getContent()); 
    JLabel ID_Label = new JLabel(ID_LABEL_STR.getContent()); 

    // JongbumPark's declaration
    private static GatePanel gatePanel;
    
    public Timer formClockTimer = null;

    private void startClock() {
        formClockTimer = new Timer("ospFormClockTimer"); 
            // 1000, new ActionListener() {
        TimerTask displayTime = new TimerTask() {
            @Override
            
            public void run() {
                ClockLabel.setText(new SimpleDateFormat ("hh:mm:ss a").format(new Date( )));
            }            
        };  
        formClockTimer.schedule(displayTime, 0, 1000);
    }

    public void stopRunningTheProgram() {

        // 전광판 기본문구 표시
        for (int gateNo = 1; gateNo <= gateCount; gateNo++) { 
            IE_Board ebdMan = (IE_Board) deviceManagers[E_Board.ordinal()][gateNo];
            ebdMan.showDefaultMessage();
        }
        

        setSHUT_DOWN(true);

        // Cancel timer here
        for (int gateNo = 1; gateNo <= gateCount; gateNo++) { 
            if (openGateCmdTimer[gateNo] == null) {
                continue;
            }
            openGateCmdTimer[gateNo].cancelTask();
            openGateCmdTimer[gateNo].cancel();
            openGateCmdTimer[gateNo].purge();
            openGateCmdTimer[gateNo] = null;
        }

        for (DeviceType type: DeviceType.values()) {
            for (byte gateNo = 1; gateNo <= gateCount; gateNo++) {
                if (connectDeviceTimer[type.ordinal()][gateNo] != null) {
                    connectDeviceTimer[type.ordinal()][gateNo].cancelTask();
                    connectDeviceTimer[type.ordinal()][gateNo].cancel();
                    connectDeviceTimer[type.ordinal()][gateNo].purge();
                    connectDeviceTimer[type.ordinal()][gateNo] = null;
                }
                // Handle some specific real hardware
                if (type == E_Board &&
                        Globals.gateDeviceTypes[gateNo].eBoardType == E_BoardType.LEDnotice) 
                {
                    getSendEBDmsgTimer()[gateNo][EBD_Row.TOP.ordinal()].reRunOnce(
                        new FinishLEDnoticeIntrTask(this, gateNo, EBD_Row.TOP), 0);                        
                }
                if (deviceManagers[type.ordinal()][gateNo] != null) {
                    deviceManagers[type.ordinal()][gateNo].stopOperation("System shutdown");
                }
            }
        }
        
        periodicallyCheckSystemTimer.cancel();
        periodicallyCheckSystemTimer.purge();

        if (formClockTimer != null) {
            formClockTimer.cancel(); 
            formClockTimer.purge();
        }
          
        if (DEBUG)
            closeIDLogFile();
        
        recordSystemStop();
        saveMessageLines();            
        System.exit(0);
    }

    private void AttendantTask_setEnabled(boolean b) {
        AttendantListItem.setEnabled(b);
        VehicleListItem.setEnabled(b);
        EntryRecordItem.setEnabled(b);        
        DriverListItem.setEnabled(b);
        AffiliationItem.setEnabled(b);
    }

    private void MenuItems_setEnabled(boolean loggedIn) {
        CarIOListButton.setEnabled(loggedIn);
        LoginUser.setEnabled(!loggedIn);
        LogoutUser.setEnabled(loggedIn);
        VehiclesButton.setEnabled(loggedIn);  
        UsersButton.setEnabled(loggedIn);  
        SettingsItem.setEnabled(loggedIn && isAdmin);
    }
    
    private int recordSystemStart() {
        int result = tryToUpdateStopRecord();
        if (result != 1) {
            // Failed to update system stop event and just store system booting time
            Connection conn = null;
            Statement recordOperation = null;
            String sql = "insert into systemRun(startTm) values (curtime())";
            result = -1;
            String excepMsg = "(System run record: booting-insert)";

            try {
                conn = JDBCMySQL.getConnection();
                recordOperation = conn.createStatement(); 
                result = recordOperation.executeUpdate(sql);
            } catch (SQLException ex) {
                logParkingException(Level.SEVERE, ex, excepMsg);
                System.out.println("ex:" + ex.getMessage());
            } finally {
                closeDBstuff(conn, recordOperation, null, excepMsg);
            }             
        }
        
        return result;
    }

    private int tryToUpdateStopRecord() {
        Connection conn = null;
        PreparedStatement recordOperation = null;
        StringBuilder sb = new StringBuilder();
        sb.append("update systemrun set startTm = curtime() ");
        sb.append("where recNO in ");
        sb.append(    "(select recNo ");
        sb.append(    "from(select recNo, startTm ");
        sb.append(        " from systemrun ");
        sb.append(        " order by recNo desc limit 1 ) AS LastRowTable ");
        sb.append(        " where LastRowTable.startTm is null )");
        
        int result = -1;
        String excepMsg = "(System run record: booting-update)";
        
        try {
            conn = JDBCMySQL.getConnection();
            recordOperation = conn.prepareStatement(sb.toString());
            result = recordOperation.executeUpdate();
        } catch (SQLException ex) {
            logParkingExceptionStatus(Level.SEVERE, ex, excepMsg, getStatusTextField(), GENERAL_DEVICE);
        } finally {
            closeDBstuff(conn, recordOperation, null, excepMsg);
            return result;
        }          
    }
  
    private int recordSystemStop() {
        Connection conn = null;
        Statement stmt = null;
        int result = -1;
        String logMsg = "(System run record: shutdown-insert)";
        
        try {
            conn = JDBCMySQL.getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate("insert into SystemRun(stopTm) values (curtime())");
        } catch (SQLException ex) {
            logParkingException(Level.SEVERE, ex, logMsg);
        } finally {
            closeDBstuff(conn, stmt, null, logMsg);
            addMessageLine(MessageTextArea, SYSTEM_STOP.getContent());
            logParkingOperation(OpLogLevel.LogAlways, SYSTEM_STOP.getContent());

            return result;
        }
    }    

    private int recordLogin() {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = -1;
        String excepMsg = "(User activity: login[=insert])";
        
        try {
            int pIndex = 1;
            
            conn = JDBCMySQL.getConnection();
            stmt = conn.prepareStatement("insert into LoginRecord(userID, loginTS) values (?, curtime())");
            stmt.setString(pIndex++, loginID);
            result = stmt.executeUpdate();
        } catch (SQLException ex) {
            logParkingException(Level.SEVERE, ex, excepMsg);
        } finally {
            closeDBstuff(conn, stmt, null, excepMsg);
            return result;
        }              
    }

    private int tryToUpdateLoginRecord() {
        Connection conn = null;
        PreparedStatement recordOperation = null;
        String sql = "update loginrecord set logoutTS = curtime() " + 
                "where recNo in (select recNo from " + 
                "( select recNo, userID, logoutTS from loginRecord order by recNo desc limit 1) " +
                "as MaxRowTab " +
                "where MaxRowTab.userID = ? and MaxRowTab.logoutTS is null)";
        int result = -1;
        String excepMsg = "(User activity: logout[=update])";
        
        try {
            int pIndex = 1;
            conn = JDBCMySQL.getConnection();
            recordOperation = conn.prepareStatement(sql);
            recordOperation.setString(pIndex++, loginID);
            result = recordOperation.executeUpdate();
        } catch (SQLException ex) {
            logParkingException(Level.SEVERE, ex, excepMsg);
        } finally {
            closeDBstuff(conn, recordOperation, null, excepMsg);
            return result;
        }       
    }    
    
    private int recordLogout() {
        int result = tryToUpdateLoginRecord();
        if (result != 1) {
            // Failed to update login record. So, just stores logout time into the DB.
            Connection conn = null;
            PreparedStatement recordOperation = null;
            String sql = "insert into LoginRecord(userID, logoutTS) values (?, curtime())";
            result = -1;
            String excepMsg = "(User operation: Logout-record insertion)";

            try {
                int pIndex = 1;
                conn = JDBCMySQL.getConnection();
                recordOperation = conn.prepareStatement(sql);
                recordOperation.setString(pIndex++, loginID);
                result = recordOperation.executeUpdate();
            } catch (SQLException ex) {
                logParkingException(Level.SEVERE, ex, excepMsg);
            } finally {
                closeDBstuff(conn, recordOperation, null, excepMsg);
            }    
        }
        return result;
    }  

    private void processLogoutReally() {
        recordLogout();
        addMessageLine(MessageTextArea, Globals.loginID + " " + LOG_OUT.getContent());
        
        Globals.loginID = null;
        Globals.loginPW = null;
        Globals.isManager = false;
        Globals.DEBUG = false;
        Globals.RANDOM_ATTENDANT = false;
        changeUserID_etc();
        AttendantTask_setEnabled(false);
    }

    private void saveMessageLines() {
        if (getMessageTextArea().getText().length() > 0) {
            // save the text a text file in log directory by
            // 1. make sure 'log' directory exists
            File fPath = new File("log");
            fPath.mkdirs();
            FileWriter writer = null;
            try {
                // 2. create text file("MessageList.txt") to store list
                writer = new FileWriter("log" + File.separator + "MessageList.txt");

                // 3. write list contents into the file
                writer.write(getMessageTextArea().getText());
            } catch (IOException ioe) {
                logParkingException(Level.SEVERE, ioe, "(message list writer write)");
            } finally {
                // 4. close system resources
                if (writer != null) {
                    try {
                    writer.close();
                    } catch (IOException ioe) {
                        logParkingException(Level.SEVERE, ioe, "(message list writer close)");
                    }
                }
            }
        }
    }

    private void initMessagelLines() {
        try (BufferedReader br = new BufferedReader(new FileReader("log" + File.separator + "MessageList.txt"))) 
        {
            String line = null; 
            while ((line = br.readLine()) != null) {
                getMessageTextArea().append(line + System.getProperty("line.separator"));
            }

            int len = getMessageTextArea().getDocument().getLength();
            getMessageTextArea().setCaretPosition(len); // places the caret at the bottom of the display area
            
        }  catch (FileNotFoundException fe) {
            addMessageLine(MessageTextArea, FIRST_RUN_MSG.getContent());
            logParkingException(Level.SEVERE, fe, "First Run of Parking Lot Manager");            
        }  catch (IOException ie) {
            logParkingException(Level.SEVERE, ie, "(message list file IO exception)");
        }
    }

    static int changeCount = 0;
    
    public Component getComponentByName(HashMap compMap, String name) {
        if (compMap.containsKey(name)) {
            return (Component) compMap.get(name);
        }
        else {
            return null;
        }
    }

    private void setGatesAndRestPanel() {
        if (gateCount == 1) {
            gatePanel = new PanelFor1Gate();
        } else if (gateCount == 2) {
            gatePanel = new PanelFor2Gates();
        }
        // put titles to the gate panels
        for (int gateNo =1; gateNo <= gateCount; gateNo++)
        {            
            TitledBorder tBorder = (TitledBorder)getGatePanel().getPanel_Gate(gateNo).getBorder();

            tBorder.setTitleFont(new java.awt.Font(font_Type, font_Style, font_Size));
            tBorder.setTitle(gateNames[gateNo]);
        }
        WholePanel.add(getGatePanel(), java.awt.BorderLayout.CENTER);
    }

    /**
     * Process an arrival of a vehicle which is represented by the arriving car image.
     * 
     * @param cameraID ID number of camera, usually same as the gate number where the camera is
     * @param imageSN   unique sequence number of the passed image
     * @param tagRecognized car tag number extracted from the image by some image processing SW
     * @param image car tag image of the arriving vehicle
     */

    private void initCarEntryList() {
        listSelectionModel = new ListSelectionModel[gateCount + 1];
        for (int gateNo = 1; gateNo <= gateCount; gateNo++)
        {
            listSelectionModel[gateNo] = getGatePanel().getEntryList(gateNo).getSelectionModel();
            listSelectionModel[gateNo].addListSelectionListener(new ListSelectionChangeHandler(gateNo));
            try {
                KeyPressedEventHandler handler = new KeyPressedEventHandler (gateNo);
                getGatePanel().getEntryList(gateNo).addKeyListener(handler);
            } catch (Exception ex) {
                System.out.println("");
            }
            loadPreviousEntries(gateNo);
        }
    }

    private void loadPreviousEntries(int gateNo) {
        @SuppressWarnings("unchecked")
        DefaultListModel<CarAdmission> listModel 
                = (DefaultListModel<CarAdmission>)admissionListModel[gateNo];
        
        StringBuffer sb = new StringBuffer("Select arrseqno, concat ('-', ");
        sb.append("substr(arrivaltime, 9, 2), ");
        sb.append("substr(arrivaltime, 11), ' ') as tmDisplay, ");
        sb.append("TagEnteredAs, TagRecognized, BarOperation ");
        sb.append("from car_arrival ");
        sb.append("where gateno = ? ");
        sb.append("order by arrSeqno desc ");
        sb.append("limit ? ");
 
        Connection conn = null;
        PreparedStatement pStmt = null;    
        ResultSet rs = null;
        long arrSeqNo = 0;
        try {
            // create the java statement
            conn = JDBCMySQL.getConnection();
            pStmt = conn.prepareStatement(sb.toString());
            pStmt.setInt(1, gateNo);
            pStmt.setInt(2, RECENT_COUNT);
            rs = pStmt.executeQuery();
            
            String timePlusTag = "";
            String tagEnteredAs = "";
            String tagRecognized = "";
            BarOperation barOptn;
            int barOptnInt;
            PermissionType perm;
            
            while (rs.next()) {
                timePlusTag = "";
                timePlusTag += rs.getString("tmDisplay");
                tagEnteredAs = rs.getString("TagEnteredAs");
                tagRecognized = rs.getString("TagRecognized");
                if (tagEnteredAs == null) {
                    timePlusTag += tagRecognized;
                    perm = UNREGISTERED;
                } else {
                    timePlusTag += tagEnteredAs;
                    if (parkingPermitted(tagEnteredAs)) {
                        perm = ALLOWED;
                    } else {
                        perm = DISALLOWED;
                    }
                }
                barOptnInt = rs.getInt("BarOperation");
                barOptn = BarOperation.values()[barOptnInt];
                arrSeqNo = rs.getLong("arrseqno");
                increaseListHeightBeforehand(gateNo);
                
                String barOpSuffix = "";
                if (barOptn != REGISTERED_CAR_OPENED) {
                    barOpSuffix =  "-" + barOptn.getContent();
                }
                listModel.addElement(new CarAdmission(timePlusTag + "-" + 
                        perm.getContent() + barOpSuffix, arrSeqNo));
            }
        } catch (SQLException se) {
            logParkingException(Level.SEVERE, se, 
                    "(select car_recent for seqNo: " + String.valueOf(arrSeqNo));
        } finally {
            closeDBstuff(conn, pStmt, rs, "(Loading previous car arrival records)");
        }
    }

    public synchronized void updateMainForm(int gateNo, String tagRecognized,  
            long arrSeqNo, PermissionType permission, BarOperation barOptn) {
        try
        {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat displayFormat = new SimpleDateFormat("dd HH:mm:ss ");    
            
            Date currentTime = calendar.getTime();
            String tmDisplay = displayFormat.format(currentTime);
            
            @SuppressWarnings("unchecked")
            DefaultListModel<CarAdmission> listModel =
                    (DefaultListModel<CarAdmission>) admissionListModel[gateNo];
            // Increase box height
            increaseListHeightBeforehand(gateNo);
            // add a row to the recent entry list for the gate
            String entry = "-" + tmDisplay + tagRecognized + "-" + permission.getContent();
            if (permission != ALLOWED) {
                entry += "-" + barOptn.getContent();
            }
            listModel.add(0, new CarAdmission(entry, arrSeqNo));
            
            /**
             * Update car arrival list GUI if it were in display.
             */
            if (getTopForms()[Arrivals.ordinal()] != null) {
                ((CarArrivals)(topForms[Arrivals.ordinal()])).loadArrivalsListTable(true);
            }
            
            // display entry image on the label for the gate
            listSelectionModel[gateNo].setSelectionInterval(0, 0);
            if (barOptn == REGISTERED_CAR_OPENED || barOptn == AUTO_OPENED) {
                showImage(gateNo);
            }
            
            int lastIdx = listModel.getSize() - 1;
            while (lastIdx >= RECENT_COUNT)
            {
                listModel.remove(lastIdx);
                lastIdx = listModel.getSize() - 1;
            }  
        } catch (Exception e) {
            logParkingException(Level.SEVERE, e, "(update main form display)");
        } 
    }

    public long insertDBrecord(int gateNo, Date arrivalTm, String tagRecognized, String tagEnteredAs, 
            BufferedImage bufferedImage, int unitSeqNo, int l2No, String visitReason, BarOperation barOp) 
    {
        String arrivalTmStr = sdf.format(arrivalTm);

        StringBuffer sb = new StringBuffer(" Insert Into Car_arrival (GateNo, ArrivalTime, ");
        sb.append("TagRecognized, TagEnteredAs, ImageBlob, AttendantID, ");
        if (unitSeqNo != -1) { sb.append("unitSeqNo, "); }
        if (l2No != -1) { sb.append("L2_No, "); }
        if (visitReason != null) { sb.append("VisitReason, "); }
        sb.append("BarOperation) "); 
        sb.append("Values (?, ?, ?, ?, ?, ?, "); 
        if (unitSeqNo != -1) { sb.append("?, "); }
        if (l2No != -1) { sb.append("?, "); }
        if (visitReason != null) { sb.append("?, "); }
        sb.append("?) ");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long arrSeqNo = -1;

        try {
            conn = JDBCMySQL.getConnection();
            stmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            stmt.setInt(index++, gateNo);
            stmt.setString(index++, arrivalTmStr);
            stmt.setString(index++, tagRecognized);
            if (tagEnteredAs == null || tagEnteredAs.length() == 0) {
                stmt.setString(index++, null);
            } else {
                stmt.setString(index++, tagEnteredAs);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            InputStream imageInStream = new ByteArrayInputStream(baos.toByteArray());            
            stmt.setBlob(index++, imageInStream);
            closeInputStream(imageInStream, "closing input strream for DB record insertion");
            
            stmt.setString(index++, Globals.loginID);
            if (unitSeqNo != -1) { stmt.setInt(index++, unitSeqNo); }
            if (l2No != -1) { stmt.setInt(index++, l2No); }
            if (visitReason != null) { stmt.setString(index++, visitReason); }
            stmt.setInt(index++, barOp.ordinal());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                arrSeqNo = rs.getLong(1);
            }
        } catch (FileNotFoundException ex) {
            logParkingException(Level.SEVERE, ex, "image file not found");
        } catch (SQLException ex) {
            logParkingException(Level.SEVERE, ex, "error in car arrival insertion");
        } finally {
            closeDBstuff(conn, stmt, rs, "(insert car arrival tuple)");
            return arrSeqNo;
        }     
    }

    /**
     * send an open command to a gate bar.
     * It creates a Runnable object and use it to reschedule a timer which sends open command.
     * @param gateNo gate number where the bar exists
     * @param openCmdID unique ID assigned to the current open command
     * @param carPassingDelayMs duration to maintain the gate open while a car passes
     */
    public void openGate(int gateNo, int openCmdID, int carPassingDelayMs) {
        
        openCommandIssuedMs[gateNo] = System.currentTimeMillis();
        if (deviceType[GateBar.ordinal()][gateNo] == NaraBar.ordinal()) {
            NaraBarMan gateMan = (NaraBarMan) getDeviceManagers()[GateBar.ordinal()][gateNo];
            gateMan.getNaraBarMessages().add(new NaraMsgItem(Nara_MsgType.GateUp));
            // carPassingDelayMs ms 경과 후 차단기 닫는 명령을 add 하는 task run once 함
            gateMan.scheduleGateCloseAction(gateMan, carPassingDelayMs);
        } else {
            SendGateOpenTask sendOpenTask = 
                    new SendGateOpenTask(this, (byte) gateNo, openCmdID, carPassingDelayMs);
            getOpenGateCmdTimer()[gateNo].reschedule(sendOpenTask);
        }
    }    
    
    Object AckFileMutex = new Object();
    
    String[] columnsBar = new String[] {"Command ID", "(negative: random attendant)"};
    String[] columnsEBD = new String[] {"Inter' SN", ""};
    TableModel modelBar = new DefaultTableModel(null, columnsBar);    
    TableModel modelEBD = new DefaultTableModel(null, columnsEBD);    
    File[] odsFileBar = new File[1];    
    File[] odsFileEBD = new File[1];    
    /**
     * Gives a gate open command after initializing ACK flag and sender timer.
     * 
     * @param gateNo ID of the gate to open
     * @param openCommandID Unique ID assigned for this open command
     * @param carPassingDelayMs Duration to maintain the gate opened
     */
    public void raiseGateBar(byte gateNo, int openCommandID, int carPassingDelayMs) {
        // the increment operator generates a unique ID for each open command created.
        if (deviceManagers[GateBar.ordinal()][gateNo].isNeverConnected()) {
            return;
        }
        
        if (DEBUG) {
            /**
             * Save gate open command ID for a book keeping
             */
            checkOdsExistance("_GateBar_", gateNo, 
                    " Gate Bar", "is where the open command is sent to",
                    getStatusTextField(), odsFileBar, modelBar);
            appendOdsLine(odsFileBar[0], Integer.toString(openCommandID), 
                    getStatusTextField());                
        }
        ParkingTimer openCmdTimer = getOpenGateCmdTimer()[gateNo];
        synchronized (openCmdTimer) {
            if (openCommAcked[gateNo]) {
                openCommAcked[gateNo] = false; 
            } else {
                if (openCmdTimer.hasTask()) {
                    if (DEBUG) {
                        int resends = ((SendGateOpenTask)openCmdTimer.getParkingTask())
                                .getResendCount();
                        logParkingOperation(OpLogLevel.LogAlways, 
                                "Open ID: " + openCommandIDs[gateNo] 
                                + " cancelled after " + resends + " trials", gateNo);
                    }
                    openCmdTimer.cancelTask();
                }
            }
            openCommandIDs[gateNo] = openCommandID;
            openGate(gateNo, openCommandID, carPassingDelayMs);
        }
    }

    /**
     * Returns the text display area of the Main GUI.
     * 
     * @return the MessageTextArea
     */
    public javax.swing.JTextArea getMessageTextArea() {
        MessageTextArea.getCaret().setVisible(true);
        return MessageTextArea;
    }

    /**
     * @return the statusTextField
     */
    @Override
    public javax.swing.JTextField getStatusTextField() {
        return statusTextField;
    }

    /**
     * @return the SHUT_DOWN
     */
    public boolean isSHUT_DOWN() {
        return SHUT_DOWN;
    }

    /**
     * @param SHUT_DOWN the SHUT_DOWN to set
     */
    public void setSHUT_DOWN(boolean SHUT_DOWN) {
        this.SHUT_DOWN = SHUT_DOWN;
    }

    /**
     * @return the loginDialog
     */
    public LoginDialog getLoginDialog() {
        if (loginDialog != null) {
//            loginDialog.getUserIDText().setText("admin");
            loginDialog.getPassword().setText("");
        }
        return loginDialog;
    }

    /**
     * @param loginDialog the loginDialog to set
     */
    public void setLoginDialog(LoginDialog loginDialog) {
        this.loginDialog = loginDialog;
    }

    public JLabel[][] getDeviceConnectionLEDs() {
        return deviceConnectionLabels;
    }

    /**
     */
    public SocketConnStat[][] getSockConnStat() {
        return sockConnStat;
    }

    /**
     * @return the openBarCmdTimer
     */
    public ParkingTimer[] getOpenGateCmdTimer() {
        return openGateCmdTimer;
    }

    /**
     * @return the socketMutex
     */
    public Object[][] getSocketMutex() {
        return socketMutex;
    }

    /**
     * @return the connectDeviceTimer
     */
    public ParkingTimer[][] getConnectDeviceTimer() {
        return connectDeviceTimer;
    }

    /**
     * @return the sendEBDmsgTimer
     */
    public ParkingTimer[][] getSendEBDmsgTimer() {
        return sendEBDmsgTimer;
    }

    private void closeIDLogFile() {
        for (DeviceType type : DeviceType.values()) {
            for (int gNo = 1; gNo <= gateCount; gNo++) {
                if (getIDLogFile()[gNo] != null) {
                    try {
                        getIDLogFile()[type.ordinal()][gNo].close();
                        IDLogFile[gNo] = null;
                    } catch (IOException e) {}
                }
            }
        }
    }        

    /**
     * @return the IDLogFile
     */
    public FileWriter[][] getIDLogFile() {
        return IDLogFile;
    }

    @Override
    public IDevice.IManager[][] getDeviceManagers() {
        return deviceManagers;
    }

    @Override
    public JLabel[][] getDeviceConnectionLabels() {
        return deviceConnectionLabels;
    }

    @Override
    public ToleranceLevel getTolerance(DeviceType type, byte deviceID) {
        return this.tolerance[type.ordinal()][deviceID];
    }

    @Override
    public void setTolerance(DeviceType type, byte deviceID, ToleranceLevel tolerance) {
        this.tolerance[type.ordinal()][deviceID] = tolerance;
    }

    Random rand = new Random();
    
    public synchronized void interruptEBoardDisplay(byte gateNo, String tagRecognized, 
            PermissionType permission, String remark, String tagRegistered, int imageSN, int carPassingDelayMs)
    {
        String tagNumber = tagRecognized;
        
        if (permission != UNREGISTERED)
            tagNumber = tagRegistered;
        
        interruptsAcked[gateNo] = false;
        IDevice.IManager eManager = deviceManagers[E_Board.ordinal()][gateNo];
        if (eManager == null) {
            statusTextField.setText("E-Board #" + gateNo + " manager isn't alive");
        } else {
            if (IDevice.isConnected(eManager, E_Board, gateNo))
            {
                long currTimeMs = System.currentTimeMillis();

                //<editor-fold desc="-- Init debug information">
                if (DEBUG) {
                    eBoardMsgSentMs[gateNo][EBD_Row.TOP.ordinal()] = currTimeMs;
                    eBoardMsgSentMs[gateNo][EBD_Row.BOTTOM.ordinal()] = currTimeMs;
                }
                //</editor-fold>

                // check E-Board type and process accordingly
                switch (Globals.gateDeviceTypes[gateNo].eBoardType) {
                    case LEDnotice:
                        //<editor-fold desc="-- Car arrival interrupt message for the LEDnotice hardware">
                        LEDnoticeManager manager = (LEDnoticeManager)deviceManagers[E_Board.ordinal()][gateNo];
                        manager.sendCarArrival_interruptMessage(
                                ledNoticeSettings[CAR_ENTRY_TOP_ROW.ordinal()],
                                ledNoticeSettings[CAR_ENTRY_BOTTOM_ROW.ordinal()],
                                gateNo, tagNumber, permission, remark, 
                                carPassingDelayMs);
                        //</editor-fold>
                        break;

                    default:
                        //<editor-fold desc="-- Car arrival interrupt message for the e-board simulator">
                        getSendEBDmsgTimer()[gateNo][EBD_Row.TOP.ordinal()].reschedule(
                                new SendEBDMessageTask(
                                        this, gateNo, EBD_INTERRUPT1, 
                                        getIntMessage(permission, tagNumber, gateNo, EBD_Row.TOP, 
                                                imageSN * 2 + EBD_Row.TOP.ordinal(), carPassingDelayMs), 
                                        imageSN * 2 + EBD_Row.TOP.ordinal()
                                )
                        );

                        getSendEBDmsgTimer()[gateNo][EBD_Row.BOTTOM.ordinal()].reschedule(
                                new SendEBDMessageTask(
                                        this, gateNo, EBD_INTERRUPT2, 
                                        getIntMessage(permission, tagNumber, gateNo, EBD_Row.BOTTOM, 
                                                imageSN * 2 + EBD_Row.BOTTOM.ordinal(), carPassingDelayMs), 
                                        imageSN * 2 + EBD_Row.BOTTOM.ordinal()
                                )
                        );
                        //</editor-fold>
                        break;
                }

                //<editor-fold desc="-- Save debug information">
                if (DEBUG) 
                {
                    /**
                     * Save EBD interrupt message serial numbers for book keeping
                     */
                    checkOdsExistance("_E_Board_", gateNo, 
                            " E-Board", "is the interrupt target",
                            getStatusTextField(), odsFileEBD, modelEBD);
                    appendOdsLine(odsFileEBD[0], 
                            Integer.toString(imageSN * 2 + EBD_Row.TOP.ordinal()), 
                            getStatusTextField());                       
                    appendOdsLine(odsFileEBD[0], 
                            Integer.toString(imageSN * 2 + EBD_Row.BOTTOM.ordinal()), 
                            getStatusTextField());                       
                    
                }
                //</editor-fold>
            }
        }
    }
    
    byte[] getIntMessage(PermissionType permission, String tagRecogedAs, 
            byte deviceNo, EBD_Row row, int msgSN, int delay) 
    {
        EBD_DisplaySetting setting = null;
        
        setting = EBD_DisplaySettings[row == EBD_Row.TOP ? 
                CAR_ENTRY_TOP_ROW.ordinal() : CAR_ENTRY_BOTTOM_ROW.ordinal()];
            
        String displayText = null;
        //<editor-fold desc="-- determind display text using e-board settings value like contentType">
        switch (setting.contentType) {
            case VERBATIM:
                displayText = setting.verbatimContent;
                break;
                
            case VEHICLE_TAG:
            case REGISTRATION_STAT:
                // fetch vehicle registration status from DB
                if (setting.contentType == VEHICLE_TAG) {
                    displayText = tagRecogedAs;
                } else { // REGISTRATION_STAT
                    if (permission == ALLOWED)
                        displayText = EDM_REGISTERED.toString();
                    else if (permission == DISALLOWED ) {
                        String remark = getDisallowReason(tagRecogedAs);
                        
                        if (remark == null || remark.length() == 0) {
                            remark = NO_DATA.toString();
                        }
                        displayText = EDM_DISALLOWED + "(" + remark + ")";
                    } else if (permission == UNREGISTERED) {
                        displayText = EDM_VISITOR.toString();
                    }
                }
                break;
            case GATE_NAME:
                displayText = gateNames[deviceNo];
                break;
                
            default:
                displayText = "";
                break;
        }
        //</editor-fold>
        
        byte[] displayTextBytes = null;
        try {
            displayTextBytes = displayText. getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            logParkingException(Level.SEVERE, ex, "getting byte array for : " + displayText, deviceNo);  
        }
        int displayTextLength = displayTextBytes.length;
        
        // <code:1><length:2><row:1><msgSN:4><text:?><type:1><color:1><font:1><pattern:1><cycle:4>
        // <delay:4><check:2>
        byte code = (byte) (row == EBD_Row.TOP ? EBD_INTERRUPT1.ordinal() : EBD_INTERRUPT2.ordinal());
        short wholeMessageLen // length of 10 fields from <length> to <check>
                = (short)(displayTextLength + 21); // 21 == sum of 10 fields == 11 fields except <text>
        byte[] lenBytes //  {--Len[1], --Len[0]}
                = {(byte)((wholeMessageLen >> 8) & 0xff), (byte)(wholeMessageLen & 0xff)}; 
        byte[] wholeMessageBytes = new byte[wholeMessageLen + 1];
        
        formMessageExceptCheckShort(code, lenBytes, row, msgSN, displayTextBytes, setting, delay, 
                wholeMessageBytes);
        
        //<editor-fold desc="complete making message byte array by assigning 2 check bytes">
        // calculate 2 check bytes by adding all bytes in the of 9 fields: from <code:1> to <delay:4>
        byte[] check = new byte[2];
        addUpBytes(wholeMessageBytes, check);
        
        int idx = wholeMessageBytes.length - 2;
        wholeMessageBytes[idx++] = check[0];
        wholeMessageBytes[idx++] = check[1];
        //</editor-fold>
        
        return wholeMessageBytes;
    }    

    /**
     * @return the passingDelayStat
     */
    public PassingDelayStat[] getPassingDelayStat() {
        return passingDelayStat;
    }

    private boolean processLogOut(boolean forced) {
        boolean isLoggedOut = false;
        try {
            int result = 0;
            if (forced) {
                JOptionPane.showMessageDialog(this, Globals.loginID + AUTO_LOGOUT.getContent());
                result = JOptionPane.YES_OPTION;
            } else {
                result = JOptionPane.showConfirmDialog(this, 
                    Globals.loginID + ASK_LOGOUT.getContent() + " ",
                    CONFIRM_LOGOUT.getContent(), JOptionPane.YES_NO_OPTION);
            }
            if (result == JOptionPane.YES_OPTION) {
                processLogoutReally();
                enableManagerItem(false);
                isLoggedOut = true;
                if (debugPanel.isVisible()) {
                    removeDebugPanel();
                }
                
                for (int i = 0; i < ControlEnums.TopForms.values().length; i++) {
                    if (getTopForms()[i] != null) {
                        getTopForms()[i].dispose();
                        topForms[i] = null;
                    }
                }                
            }
        } catch (Exception ex) {
            com.osparking.global.Globals.logParkingException(Level.SEVERE, ex, "(Process User Logout)");
        }    
        return isLoggedOut;
    }

    private void fetchPassingDelay() {
        Connection conn = null;
        Statement selectStmt = null;
        ResultSet rs = null;
        
        for (int gateID = 1; gateID <= gateCount; gateID++) 
        {
            try {
                conn = JDBCMySQL.getConnection();
                selectStmt = conn.createStatement();
                rs = selectStmt.executeQuery("SELECT passingDelayPreviousAverageMs, "
                        + "passingDelayPreviousPopulation, passingDelayCalculationTime " 
                        + "FROM gatedevices WHERE gateid = " + gateID);
                while (rs.next() ) {
                    float average = rs.getFloat("passingDelayPreviousAverageMs");
                    getPassingDelayStat()[gateID]
                            .setPassingDelayPreviousAverageMs((average == 0 ? -1.0f : average));
                    getPassingDelayStat()[gateID]
                            .setPassingDelayPreviousPopulation(rs.getInt("passingDelayPreviousPopulation"));
                    getPassingDelayStat()[gateID]
                            .setPassingDelayCalculationTime(rs.getTimestamp("passingDelayCalculationTime"));
                }
            } catch (SQLException ex) {
                logParkingException(Level.SEVERE, ex, "(Loading passing delay records)");
            } finally {
                closeDBstuff(conn, selectStmt, rs, "(Loading passing delay records)");
            }
        }
    }

    /**
     * @param configureSettingsForm the configureSettingsForm to set
     */
//    public void setConfigureSettingsForm(Settings_System configureSettingsForm) {
//        this.configureSettingsForm = configureSettingsForm;
//    }
//
//    @Override
//    public void clearAttendantManageForm() {
//        attendantsListForm = null;
//    }

    private static class ListSelectionChangeHandler implements ListSelectionListener {
        int gateNo = 0;
        
        private ListSelectionChangeHandler(int gateNo) {
            this.gateNo = gateNo;
        }
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting())
            {
                return;
            }
            final ListSelectionModel lsm = (ListSelectionModel)e.getSource();  
            showImage( gateNo);
        }
    }

    public static void showImage(final int gateNo) {
        if (getGatePanel().getEntryList(gateNo).getSelectedIndex() > -1) 
        {
            getShownImageRow()[gateNo] = getGatePanel().getEntryList(gateNo).getSelectedIndex();

            JLabel picLabel = getGatePanel().getCarPicLabels()[gateNo];
            CarAdmission carEntry = (CarAdmission)getGatePanel().getEntryList(gateNo).getSelectedValue();

            String sql = new String( "Select imageblob from car_arrival where ArrSeqNo = ?");
            Connection conn = null;
            PreparedStatement pStmt = null;    
            ResultSet rs = null;
            try {
                // <editor-fold defaultstate="collapsed" desc="-- Fetch image from the DB and show it ">
                conn = JDBCMySQL.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setLong(1, carEntry.getArrSeqNo());
                rs = pStmt.executeQuery();
                if (rs.next()) {
                    try {
                        InputStream imageInStream = rs.getBinaryStream("ImageBlob");

                        if (imageInStream == null) {
                            picLabel.setIcon(null);
                            picLabel.setText("No Image Exists");
                        } else {
                            BufferedImage imageRead = ImageIO.read(imageInStream);
                            closeInputStream(imageInStream, "(image loading from DB)");
                            
                            showBufferedImage(gateNo, picLabel, imageRead);
                            picLabel.setText(null);
                            picLabel.setIcon(createStretchedIcon(picLabel.getSize(), imageRead, false));
                            originalImgWidth[gateNo] = imageRead.getWidth(); 
                            gatePanel.setGateImage((byte)gateNo, imageRead);
                        }
                    } catch (IOException ex) {
                        logParkingException(Level.SEVERE, ex, "(image loading from DB)");
                    }
                }
                // </editor-fold>
            }
            catch(Exception e) {
                logParkingException(Level.SEVERE, e, "(in car arrival image display routine)");
            }
            finally {
                closeDBstuff(conn, pStmt, rs, "(in car arrival image resource )");
            }
        }
    }

    public void insertDebugPanel() {
        debugPanel.setVisible(true);
        Dimension debugDim = debugPanel.getPreferredSize();
        int h = debugDim.height + connStatusPanel.getPreferredSize().height;
                
        Status_Panel.setPreferredSize(new Dimension(debugDim.width, h));
    }
    
    public void removeDebugPanel() {
        debugPanel.setVisible(false);
        Status_Panel.setPreferredSize(connStatusPanel.getPreferredSize());
    }
    
    javax.swing.Timer clearStatus = new javax.swing.Timer(BOTTOM_DISPLAY_MS, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            getStatusTextField().setText(null);
        }
    });
            
    public void displayStatus(String message) {
        synchronized(getStatusTextField()) {
            getStatusTextField().setText(message); 
            clearStatus.restart();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    javax.swing.UIManager.getLookAndFeelDefaults().put("ScrollBar.minimumThumbSize", new Dimension(30, 30)); 
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            logParkingException(Level.SEVERE, ex, "(ClassNotFoundException)");
        } catch (InstantiationException ex) {
            logParkingException(Level.SEVERE, ex, "(InstantiationException)");
        } catch (IllegalAccessException ex) {
            logParkingException(Level.SEVERE, ex, "(IllegalAccessException)");
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            logParkingException(Level.SEVERE, ex, "(UnsupportedLookAndFeelException)");
        }
        //</editor-fold>

        initializeLoggers();
        checkOptions(args);
        
        readSettings();
        if (listFileNotFound()) {
            language = Locale.KOREAN;
            IS_FIRST_FUN = true;            
            initSystemSettings();
            initEBoardSettings();
        }

        EBD_DisplaySettings = readEBoardSettings();
        Thread.currentThread().setPriority((Thread.MAX_PRIORITY));
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                testUniqueness("ParkingLotManager", "OsParking");
                
                ControlGUI mainForm = new ControlGUI(false);
                
                if (!DEBUG) {
                    Globals.shortLicenseDialog(null);
                    /**
                     * If single gate and car entry button is being used but 
                     * debug mode is not on, then give warning that something 
                     * must be wrong!
                     */
                    if (gateCount == 1) {
                        int camTypeIdx = DB_Access.deviceType[Camera.ordinal()][1];
                        if (camTypeIdx == CarButton.ordinal()) {
                            JOptionPane.showMessageDialog(null, 
                                    "To enable ["+ CAR_ARRIVAL_BTN.getContent() + "], use" +
                                            System.lineSeparator() +
                                            "'-debug' command line option and login as" +
                                            System.lineSeparator() +
                                            "'admin'.  Otherwise, no camera simulator!",
                                    "Debug Mode Needed", WARNING_MESSAGE);                            
                        }
                    }
                }                
                mainForm.recordSystemStart();
                mainForm.setVisible(true);
            }
        });
    }
}
