/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.util;

import java.sql.Connection;
import java.sql.ResultSet;
import lk.buyingandselling.DBConnection.DBConnection;

/**
 *
 * @author SLR
 */
public class IDGeneratorController {
    public static String getLastID(String tableName,String columnName) throws Exception{
        String sql="SELECT "+columnName+" FROM "+tableName+" ORDER BY 1 DESC LIMIT 1";
        Connection con=DBConnection.getInstance().getConnection();
        ResultSet stm=con.createStatement().executeQuery(sql);
        if (stm.next()) {
            return stm.getString(1);
        }
        return null;
    }
}
