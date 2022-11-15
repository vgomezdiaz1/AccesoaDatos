package com.gestionFicheroBBDD;

import com.gestionBBDDSqlite.Departamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ControladorBBDD {

    Connection cn = null;

    public ControladorBBDD(String nombreFichero) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        cn = DriverManager.getConnection("jdbc:sqlite:" + nombreFichero);
        Statement st = cn.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS Agente("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre VARCHAR(100),"
                + "eliminado BOOLEAN);");

        st.execute("CREATE TABLE IF NOT EXISTS Multa("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "localidad VARCHAR(100),"
                + "coste REAL,"
                + "pagada BOOLEAN,"
                + "eliminado BOOLEAN,"
                + "idAgente INTEGER,"
                + "FOREIGN KEY idAgente REFERENCES Agente(id));");

    }

    public void crearMulta(Multa m) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("insert into Multa (localidad, coste, pagada, eliminado, idAgente) values ( ? , ? , ? , ? , ? );");
        pst.setString(1, m.getLocalidad());
        pst.setDouble(2, m.getCoste());
        pst.setBoolean(3, m.isPagada());
        pst.setBoolean(4, m.isEliminado());
        pst.setInt(5, m.getIdAgente());
        pst.execute();
    }

    public void crearAgente(Agente a) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("insert into Agente (nombre, eliminado) values ( ? , ? );");
        pst.setString(1, a.getNombre());
        pst.setBoolean(2, a.isEliminado());
        pst.execute();
    }

    public void borrarMulta(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE Agente SET eliminado = ?  WHERE id = ? ");
        pst.setBoolean(1, true);
        pst.setInt(2, id);
        pst.execute();
    }

    public void borrarAgente(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("DELETE FROM Agente WHERE id = ?");
        pst.setInt(1, id);
        pst.execute();
    }

    public void modificarMulta(int id, Multa m) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE Departamento SET localidad = ?, coste = ?, pagada = ?, eliminado = ?, idAgente =?  WHERE id = ? ");
        pst.setString(1, m.getLocalidad());
        pst.setDouble(2, m.getCoste());
        pst.setBoolean(3, m.isPagada());
        pst.setBoolean(4, m.isEliminado());
        pst.setInt(5, m.getIdAgente());
        pst.setInt(6, id);
        pst.execute();
    }

    public void modificarAgente(int id, Agente a) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE Agente SET nombre = ?, eliminado = ?  WHERE id = ? ");
        pst.setString(1, a.getNombre());
        pst.setBoolean(2, a.isEliminado());
        pst.setInt(3, id);
        pst.execute();
    }

    public void pagarMulta(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("UPDATE Departamento SET pagada = ?,  WHERE id = ? ");
        pst.setBoolean(1, true);
        pst.setInt(2, id);
        pst.execute();
    }

    /**
     *
     * @param id multa
     * @return Multa completa solo si esta en activo
     * @throws SQLException
     */
    public Multa consultarMulta(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * from Multa WHERE id = ? and eliminado = false");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        return recogerMulta(rs);
    }

    /**
     *
     * @param id multa
     * @return Multa completa este eliminada o no
     * @throws SQLException
     */
    public Multa consultarMultaCompleta(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * from Multa WHERE id = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        return recogerMulta(rs);
    }

    /**
     *
     * @param id agente
     * @return Devuelve el agente que sigue en activo
     * @throws SQLException
     */
    public Agente consultarAgente(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * from Agente WHERE id = ? and eliminado = false");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        return recogerAgente(rs);
    }

    /**
     *
     * @param id agente
     * @return Devuelve el agente activo o no
     * @throws SQLException
     */
    public Agente consultarAgenteCompleto(int id) throws SQLException {
        PreparedStatement pst = (PreparedStatement) cn.prepareStatement("SELECT * from Agente WHERE id = ? ");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        return recogerAgente(rs);
    }
    /**
     * 
     * @return Devuelve todas las multas que siguen en activo
     * @throws SQLException 
     */
    public ArrayList<Multa> consultarTodasMulta() throws SQLException {
        ArrayList<Multa> al = new ArrayList<>();
        Departamento d = null;
        Statement st;
        st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from Multa where eliminado = false");
        while (rs.next()) {
            al.add(recogerMulta(rs));
        }
        return al;
    }

    public ArrayList<Agente> consultarTodasAgente() throws SQLException {
        ArrayList<Agente> al = new ArrayList<>();
        Departamento d = null;
        Statement st;
        st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from Agente WHERE eliminado = false");
        while (rs.next()) {
            al.add(recogerAgente(rs));
        }
        return al;
    }

    public Multa recogerMulta(ResultSet rs) throws SQLException {
        Multa d = null;
        int ident = rs.getInt("id");
        String l = rs.getString("localidad");
        double c = rs.getDouble("coste");
        boolean p = rs.getBoolean("pagada");
        boolean e = rs.getBoolean("eliminado");
        int idAgente = rs.getInt("idAgente");
        d = new Multa(ident, l, c, p, e, idAgente);
        return d;
    }

    public Agente recogerAgente(ResultSet rs) throws SQLException {
        Agente d = null;
        int ident = rs.getInt("id");
        String l = rs.getString("nombre");
        boolean e = rs.getBoolean("eliminado");
        d = new Agente(ident, l, e);
        return d;
    }
}
