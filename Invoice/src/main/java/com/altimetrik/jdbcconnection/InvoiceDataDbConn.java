package com.altimetrik.jdbcconnection;

public interface InvoiceDataDbConn {
public void insertInvoiceData(String InvoiceNo,String InvoiceDate,String CustomerPO,String SoldTo,String ShipTo,String RemitTo,String TotalInvoice,String Status) throws Exception;
}
