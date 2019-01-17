package paquete_reportes;


import Conexion.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes {
    

//    public Reportes() {
//        try {
//		
//            Class.forName("com.mysql.jdbc.Driver");
//            //Creamos un enlace hacia la base de datos
//            String servidor = "jdbc:mysql://192.168.1.65:3306/controlinventario";
//            String usuarioDB="root";
//            String passwordDB="administrador";
//            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
//            instrucion = conexion.createStatement();
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, e, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
//        }
//    }

//metodo para exportar a pdf 
public void resportesPDF(String ruta, String archi) {
    
    Connection con;
        con = Conexion.GetConnection();
        try {
            
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(ruta, null, con);
            JasperExportManager.exportReportToPdfFile(informe, archi);
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("INFORME");
            ventanavisor.setVisible(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
        }
    } 


}
