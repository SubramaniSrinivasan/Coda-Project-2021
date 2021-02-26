package com.utility;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import com.model.Bill;
import com.model.Item;
import com.model.User;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelGenerator {

	public static void execute(User user, Bill bill, List<Item> itemList, OutputStream out) {
		
		WritableWorkbook workbook = null;
		
		try {
			workbook = Workbook.createWorkbook(out);
			WritableSheet excelSheet = workbook.createSheet("Invoice", 0);
			
			HashMap<String, String> excelMap = new HashMap<String, String>();
			
			Integer totalPrice = 0;
		
			excelMap.put("Bill No", bill.getBillNo().toString());
			excelMap.put("Bill Date", bill.getDate().toString());
			excelMap.put("User Id", String.valueOf(user.getUid()));
			excelMap.put("User Name", user.getUname());
			
			int row = 0;
			Label label;
			
            for(String i : excelMap.keySet()) {
            	
            	label = new Label(0, row, i);
                excelSheet.addCell(label);
                
                label = new Label(1, row, excelMap.get(i));
                excelSheet.addCell(label);
                
                row++;
            	
        	}
            
            row++;
            
            label = new Label(0, row, "S.NO");
            excelSheet.addCell(label);
            
            label = new Label(1, row, "Item ID");
            excelSheet.addCell(label);
            
            label = new Label(2, row, "Item Name");
            excelSheet.addCell(label);
            
            label = new Label(3, row, "Unit");
            excelSheet.addCell(label);
            
            label = new Label(4, row, "Price");
            excelSheet.addCell(label);
            
            row++;
            
            for(Item item : itemList) {
            	totalPrice += item.getPrice();
            	String Data[] = {item.getItemID(), item.getItemDescription(), item.getUnit(), String.valueOf(item.getPrice())};
            	for(int i = 0; i < 5; i++) {
            		if(i == 0) {
            			label = new Label(i, row, String.valueOf(i + 1));
            		}
            		else {
            			label = new Label(i, row, Data[i-1]);
            		}
            		excelSheet.addCell(label);
            	}
            	row++;
            }
            
            row++;
            
            label = new Label(3, row, "Total Price");
            excelSheet.addCell(label);
            
            label = new Label(4, row, totalPrice.toString());
            excelSheet.addCell(label);
            
			workbook.write();
		}
		catch (Exception e) {
            e.printStackTrace();
        }
		finally {
            if(workbook != null) {
                try {
                	workbook.close();
                } catch(Exception e) {
                    e.printStackTrace();
                } 
            }
        }
	}
	
}
