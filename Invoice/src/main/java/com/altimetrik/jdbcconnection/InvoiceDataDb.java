package com.altimetrik.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.tools.ant.taskdefs.SendEmail;
import org.checkerframework.common.reflection.qual.ForName;

import com.altimerik.constants.Constants;

public class InvoiceDataDb implements InvoiceDataDbConn {
	 Connection connection=null;
	public void insertInvoiceData(String InvoiceNo,String InvoiceDate,String CustomerPO,String SoldTo,String ShipTo,String RemitTo,
			String TotalInvoice,String Status) throws Exception {
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.getConnection(Constants.localhosturl,Constants.username,Constants.password);
			String sql="insert into INVOICEDATA values(?,?,?,?,?,?,?,?)";
			PreparedStatement stmt=connection.prepareStatement(sql);
			stmt.setString(1,InvoiceNo);
			stmt.setString(2,InvoiceDate);
			stmt.setString(3,CustomerPO);
			stmt.setString(4,SoldTo);
			stmt.setString(5,ShipTo);
			stmt.setString(6,RemitTo);
			stmt.setString(7,TotalInvoice);
			stmt.setString(8,"Notaprroved");
			int DataInserted=stmt.executeUpdate();
			System.out.println("Data Inserted Succesfully:"+DataInserted);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			connection.close();
		}
	}

	public void updateInvoiceData(){
		SendEmail sendmail=new SendEmail();
			Scanner in=new Scanner(System.in);
		String a=in.nextLine();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
				String sql="UPDATE InvoiceData set Status='Approved' WHERE id='a'";
				PreparedStatement stmt=con.prepareStatement(sql);
				int rs = stmt.executeUpdate(sql);
				System.out.println("Updated Sucessfully:"+rs);
				if(rs>=0){
					// sendmail.sendMailInteraction();
				}
				else{
					System.out.println("its not approved");
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
}
