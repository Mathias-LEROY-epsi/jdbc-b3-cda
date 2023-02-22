package fr.epsi.b3.dal.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epsi.b3.bo.Fournisseur;
import fr.epsi.b3.dal.FournisseurDAO;

public class FournisseurXMLDAO implements FournisseurDAO {
    @Override
    public List<Fournisseur> select() throws SQLException {
        //TODO à coder
        System.out.println("XML extraire");
        return new ArrayList<>();
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        //TODO à coder
        System.out.println("XML insert");
    }

    @Override
    public void update(String ancienNom, String nouveauNom) {
        //TODO à coder
    }

    @Override
    public void delete(Fournisseur fournisseur) {
        //TODO à coder
    }
}
