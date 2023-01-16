package com.project.ms_server_connection.Connection;

public class ConnectionClass {


    public static String ip = "192.168.112.244";
    public static String port = "1433";
    public static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    public static String db = "TestDB";
    public static String un = "Test";
    public static String pass = "12345";
    public static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+db;
}
