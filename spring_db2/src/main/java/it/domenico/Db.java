package it.domenico;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Db {
	static String JDBC_DRIVER;
	static String DB_URL;
	
	static String USER;
	static String PASS;
	
	
	public  Db(String JDBC_DRIVER,String DB_URL,String USER,String PASS)
	{
		this.JDBC_DRIVER=JDBC_DRIVER;
		this.DB_URL=DB_URL;
		this.USER=USER;
		this.PASS=PASS;
		
		int scelta=0;
		Scanner in = new Scanner(System.in);
		
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName(JDBC_DRIVER);
	        conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        String sql;
	        ResultSet rs;
	        while(scelta!=5)
	        {
		        System.out.println("|1. STAMPA        |");
		        System.out.println("|2. INSERISCI     |");
		        System.out.println("|3. MODIFICA      |");
		        System.out.println("|4. CANCELLA      |");
		        System.out.println("|5. ESCI          |");
		        System.out.print("|Inserisci scelta :");
		        scelta=Integer.parseInt(in.nextLine());
	        
	        	switch(scelta)
		        {
	        		case 1:
	        			stmt = conn.createStatement();
	        	        sql = "SELECT * FROM modello";
	        	        rs = stmt.executeQuery(sql);

	        	        while (rs.next()) 
	        	        {
	        	        	String nome_modello = rs.getString("nome_modello");
	        	        	String marca = rs.getString("marca");
	        	        	String cod_modello = rs.getString("cod_modello");
	        	        	String alimentazione = rs.getString("alimentazione");
	        	        	
	        	        	System.out.println("nome modello: " + nome_modello);
	        	        	System.out.println("marca: " + marca);
	        	        	System.out.println("codice modello: " + cod_modello);
	        	        	System.out.println("alimentazione: " + alimentazione);
	        	        	System.out.println("");
	        	        }
	        	        rs.close();
	        	        stmt.close();
	        	        break;
	        	        
	        		case 2:
	        			stmt = conn.createStatement();
	        			sql = "INSERT INTO `modello`(`nome_modello`, `marca`, `cod_modello`, `alimentazione`) VALUES ('c1','citroen','c05','benzina')";
	        			stmt.execute(sql);
	        	        stmt.close();
	        	        break;
	        	        
	        		case 3:
	        			stmt = conn.createStatement();
	        			sql = "UPDATE `modello` SET `nome_modello`='c2',`alimentazione`='gpl' WHERE `cod_modello`='c05'";
	        			stmt.execute(sql);
	        	        stmt.close();
	        			break;
	        			
	        		case 4:
	        			stmt = conn.createStatement();
	        			sql = "DELETE FROM `modello` WHERE `cod_modello`='c05'";
	        			stmt.execute(sql);
	        	        stmt.close();
	        			break;
	        			
	        		case 5:
	        			System.out.println("ARRIVEDERCI");
	        			break;
	        		
		        	default: 
		        		System.out.println("Scelta non disponibile");
		        		break;
		        }
	        }
	        
			}
			catch (SQLException se) 
		    {
		        se.printStackTrace();
		    } 
		    catch (Exception e) 
		    {
		        e.printStackTrace();
		    } 
		    finally 
		    {
		        
		        try
		        {
		        	if (conn != null)
		            conn.close();
		        } 
		        catch (SQLException se) 
		        {
		          se.printStackTrace();
		        }
		    }

	}

}
