package com.gestionBBDDSqlite;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorDepartamento {

    Connection cn = null;

    public ControladorDepartamento(String nombreFichero) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        cn = DriverManager.getConnection("jdbc:sqlite:" + nombreFichero);
        Statement st = cn.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS Departamento("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre VARCHAR(100),"
                + "responsable VARCHAR(100),"
                + "empleados INTEGER,"
                + "nPlanta INTEGER);");
    }

    public void insertarDepartamento(Departamento d) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("insert into Departamento (nombre, responsable, empleados, nPlanta) values ( ? , ? , ? , ? );");
        pst.setString(1, d.getNombre());
        pst.setString(2, d.getResponsable());
        pst.setInt(3, d.getEmpleados());
        pst.setInt(4, d.getnPlanta());
        pst.execute();
    }

    public void borrarDepartamento(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("DELETE FROM Departamento WHERE id = ?");
        pst.setInt(1, id);
        pst.execute();
    }

    public void modificarDepartamento(int id, Departamento d) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE Departamento SET nombre = ?, responsable = ?, empleados = ?, nPlanta = ? WHERE id = ? ");
        pst.setString(1, d.getNombre());
        pst.setString(2, d.getResponsable());
        pst.setInt(3, d.getEmpleados());
        pst.setInt(4, d.getnPlanta());
        pst.setInt(5, id);
        pst.execute();
    }

    public Departamento consultarDepartamento(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * from Departamento WHERE id = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        return recogerDepartamento(rs);
    }

    public ArrayList<Departamento> consultarTodosDepartamentos() throws SQLException {
        ArrayList<Departamento> al = new ArrayList<>();
        Departamento d = null;
        Statement st;
        st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from Departamento");
        while (rs.next()) {
            al.add(recogerDepartamento(rs));
        }
        return al;
    }

    public Departamento recogerDepartamento(ResultSet rs) throws SQLException {
        Departamento d = null;
        int ident = rs.getInt("id");
        String n = rs.getString("nombre");
        String r = rs.getString("responsable");
        int emp = rs.getInt("empleados");
        int planta = rs.getInt("nPlanta");
        d = new Departamento(ident, n, r, emp, planta);
        return d;
    }
}
