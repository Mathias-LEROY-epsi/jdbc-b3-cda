package fr.epsi.b3.dal;

import java.sql.SQLException;
import java.util.List;

import fr.epsi.b3.bo.Fournisseur;

public interface FournisseurDAO {
    List<Fournisseur> select() throws SQLException;

    void insert(Fournisseur fournisseur) throws SQLException;

    void update(String nouveauNom, String ancienNom) throws SQLException;

    void delete(Fournisseur fournisseur) throws SQLException;
}
