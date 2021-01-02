/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.util;

public class IDGenerator {

    public enum TableName {
        SUPPLIER,
        CUSTOMER,
        LOCATION,
        ITEM,
        PURCHASESTOCK,
        BATCH,
        SUPPLIERPAYMENT,
        CUSTOMERORDER,
        CUSTOMERPAYMENT
    }

    private TableName tableName;
    private String prifix;
    private String column;

    public IDGenerator(TableName tableName) {
        switch (tableName) {
            case SUPPLIER:
                this.tableName = tableName;
                this.column = "supplierId";
                this.prifix = "S";
                break;
            case CUSTOMER:
                this.tableName = tableName;
                this.column = "customerId";
                this.prifix = "C";
                break;
            case ITEM:
                this.tableName = tableName;
                this.column = "itemCode";
                this.prifix = "IT";
                break;
            case LOCATION:
                this.tableName = tableName;
                this.column = "locationId";
                this.prifix = "L";
                break;
            case PURCHASESTOCK:
                this.tableName = tableName;
                this.column = "purchaseId";
                this.prifix = "PS";
                break;
            case BATCH:
                this.tableName = tableName;
                this.column = "batchNo";
                this.prifix = "B";
                break;
            case SUPPLIERPAYMENT:
                this.tableName = tableName;
                this.column = "paymentId";
                this.prifix = "S/P/";
                break;
            case CUSTOMERORDER:
                this.tableName = tableName;
                this.column = "orderId";
                this.prifix = "C/O/";
                break;
            case CUSTOMERPAYMENT:
                this.tableName = tableName;
                this.column = "paymentId";
                this.prifix = "C/P/";
                break;
        }
    }

    public String getNextID() throws Exception {
        return makeNextID(IDGeneratorController.getLastID(String.valueOf(tableName), column));
    }

    private String makeNextID(String lastID) {
        if (lastID != null) {
            String[] arr = lastID.split(prifix);
            int i = Integer.parseInt(arr[1]) + 1;
            if (i < 10) {
                return prifix + "00" + i;
            } else if (i < 100) {
                return prifix + "0" + i;
            } else {
                return prifix + i;
            }
        } else {
            return prifix + "001";
        }
    }

}
