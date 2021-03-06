/*
 * Copyright (C) 2016 Open Source Parking, Inc.(www.osparking.com)
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
package com.osparking.vehicle;

import static com.osparking.global.CommonData.ODS_FILEPATH;
import static com.osparking.global.CommonData.getStringWidth;
import static com.osparking.global.Globals.getNumericDigitCount;
import static com.osparking.global.Globals.isManager;
import com.osparking.global.names.ControlEnums;
import static com.osparking.global.names.ControlEnums.FormMode.CreateMode;
import static com.osparking.global.names.ControlEnums.FormMode.NormalMode;
import static com.osparking.global.names.ControlEnums.FormMode.UpdateMode;
import java.awt.Component;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Open Source Parking, Inc.(www.osparking.com)
 */
public class CommonData {
    /**
     * Vehicle table column width weight(=ratio) constants.
     */
    public static final int vRowNoWidth = 60; // row number(sequence number)
    public static final int vPlateNoWidth = 70; // 
    public static final int vDriverNmWidth = 65; // 
    public static final int vAffiliWidth = 100; // 
    public static final int vBuildingWidth = 75; // 
    public static final int vOtherWidth = 60; // 
    public static final int vCauseWidth = 80; // 
    
    public static final int DTC_MARGIN = 5; // Driver Table Column Margin
    public static final int DTCW_MAX = 3; // Driver Table Column Margin
    public static final int DTCW_RN = 80;
    public static final int DTCW_DN = 100;
    public static final int DTCW_CP = 130;
    public static final int DTCW_LL = 120;
    public static final int DTCW_L1 = 110;
    public static final int DTCW_L2 = 110;
    public static final int DTCW_BN = 110;
    public static final int DTCW_UN = 110;
    public static final int CABW_NORM = 90; // Car Arrival Box Width Normal
    public static final int CABW_WIDE = 120; // Car Arrival Box Width Wide
    public static final int CABH_NORM = 28; // Car Arrival Box Height Normal
        
    static int count = 0;

    /**
     * Fix enable/disable affiliation management buttons in one place.
     *      Contitions to enable these buttons -
     *        Button : Insert     Update     Delete     Cancel
     *                 -----------------------------------------
     *      Form Mod : !update    !insert    normal     !normal  
     *      Row sel' :   N/A      selected   selected   selected
     *    User level : Manager    Manager    Manager    Manager
     */
    public static void fixButtonEnabled(JTable table, ControlEnums.FormMode formMode, 
                JButton insertSaveButton, JButton updateSaveButton, 
                JButton deleteButton, JButton cancelButton, JButton closeFormButton) 
    {
        if (isManager) {
            /**
             * Insert Button
             */
            if (formMode == UpdateMode) {
                insertSaveButton.setEnabled(false);
            } else {
                insertSaveButton.setEnabled(true);
            }
            
            boolean rowSelected = table.getSelectedRow() != -1;

            /**
             * Update button
             */
            if (formMode != CreateMode && rowSelected) {
                updateSaveButton.setEnabled(true);
            } else {
                updateSaveButton.setEnabled(false);
            }
            
            /**
             * Delete button
             */
            if (formMode == NormalMode && rowSelected) {
                deleteButton.setEnabled(true);
            } else {
                deleteButton.setEnabled(false);
            }
            
            /**
             * Cancel button
             */
            if (formMode != NormalMode && rowSelected) {
                cancelButton.setEnabled(true);
                closeFormButton.setEnabled(false);
            } else {
                cancelButton.setEnabled(false);
                closeFormButton.setEnabled(true);
            }
        } else {
            /**
             * Disable each button for a non-manager user.
             */
            insertSaveButton.setEnabled(false);
            updateSaveButton.setEnabled(false);
            deleteButton.setEnabled(false);
            cancelButton.setEnabled(false);
        }
    }    
    
    public static void tableColumnLanguage(JTable table, int i, ControlEnums.OsPaLang lang) {
        table.getColumnModel().getColumn(i).setCellEditor(
                new TableCellEditorKor(lang, table.getFont()));
    }    
    
    public static void adjustColumnWidth(JTable lev_Table, int numRows) {
        String countStr = Integer.toString(numRows);
        int numWidthFont = getStringWidth(countStr, lev_Table.getFont());
        int width = numWidthFont + 40;
        TableColumn column = lev_Table.getColumnModel().getColumn(0);

        if (column.getPreferredWidth() < width) {
            column.setPreferredWidth(width); 
            column.setMinWidth(width); 
        }
    }    
    
    public static String prependEscape(String searchKey) {
        return searchKey.replace("!", "!!")
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }
    
    public static void attachLikeCondition(StringBuffer cond, String column, int strLen) {
        if (strLen > 0) {
            if (cond.length() > 0)
                cond.append(" and ");
            else 
                cond.append(" ");
            cond.append(column + " like ?  ESCAPE '!' ");
        }  
    }    
    
    public static boolean invalidName(String name) {
        if (name.length() <= 1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Check if car driver cellular phone number provided.
     * @param cell
     * @return 
     */
    public static boolean invalidCell(String cell) {
        // Driver cell phone number must be privided at any rate.
        if (getNumericDigitCount(cell) < 10) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean invalidPhone(String phone) {
        if (0 < getNumericDigitCount(phone) && getNumericDigitCount(phone) < 4) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void setHelpDialogLoc(JButton odsHelpButton, JDialog helpDialog) {
        Point buttonPoint = odsHelpButton.getLocationOnScreen();
        int dialogWidth = helpDialog.getSize().width;
        int dialogHeight = helpDialog.getSize().height;
        
        int helpDialogX = buttonPoint.x - dialogWidth / 2;
        
        if (helpDialogX < 0) {
            helpDialogX = 0;
        }
        
        int helpDialogY = buttonPoint.y - dialogHeight - 10;
        if (helpDialogY < 0) {
            helpDialogY = 0;
        }
        helpDialog.setLocation(helpDialogX, helpDialogY);
    }   
    
    public static boolean wantToSaveFile(Component aFrame, 
            JFileChooser saveFileChooser, StringBuffer fullPath, String filename)
    {
        String filePath = ODS_FILEPATH +  File.separator + filename;
        File defFile = new File(filePath);
        
        saveFileChooser.setSelectedFile(defFile);
        
        if (saveFileChooser.showSaveDialog(aFrame) == JFileChooser.APPROVE_OPTION) {
            fullPath.append(saveFileChooser.getSelectedFile().getAbsolutePath());
            return true;
        } else {
            return false;
        }
    }    
    
    public static void copyFileUsingFileChannels(File source, File dest)
                    throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
                inputChannel = new FileInputStream(source).getChannel();
                outputChannel = new FileOutputStream(dest).getChannel();
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
                inputChannel.close();
                outputChannel.close();
        }
    }       
}
