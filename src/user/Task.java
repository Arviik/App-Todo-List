package user;

import BDD.BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Task {
    private int id_tache = 0;
    private String libelle;
    private String description;
    private int difficulte;
    private String date_debut;
    private String date_fin;
    private String date_butoir;
    private int ref_type;
    private int ref_etat;
    private int ref_compte;
    private int ref_tache;

    public Task(String libelle, String description, int difficulte, String date_debut, String date_fin, String date_butoir,BDD BDD) throws SQLException {
    setLibelle(libelle);
    setDate_butoir(date_butoir);
    setDate_debut(date_debut);
    setDate_fin(date_fin);
    setDescription(description);
    setDifficulte(difficulte);
    Ajout_tache(BDD);

    }
    public Task(int ref_compte){
        setRef_compte(ref_compte);
    }

    public Task(int ref_tache,int ref_compte){
        setRef_compte(ref_compte);
        setRef_compte(ref_tache);
    }
    public void Ajout_tache(BDD BDD) throws SQLException {
        PreparedStatement req = BDD.getCnx().prepareStatement("INSERT INTO tache(libelle,description, difficulte, date_debut, date_fin,date_butoir) VALUES (?,?,?,?,?,?)");
        req.setString(1,libelle);
        req.setString(2,description);
        req.setInt(3,difficulte);
        req.setString(4,date_debut);
        req.setString(5,date_fin);
        req.setString(6,date_butoir);
        req.executeUpdate();
    }

    public boolean Affiche(BDD BDD) throws SQLException {
        PreparedStatement req = BDD.getCnx().prepareStatement("SELECT * FROM tache INNER JOIN gere ON tache.id_tache = gere.ref_tache INNER JOIN compte ON gere.ref_compte = compte.id_compte WHERE ref_compte= ?");
        req.setInt(1,ref_compte);
        ResultSet monResulat = req.executeQuery();
        if (monResulat.next()){
            setId_tache(monResulat.getInt(1));
            setLibelle(monResulat.getString(2));
            setDescription(monResulat.getString(3));
            setDifficulte(monResulat.getInt(4));
            setDate_debut(monResulat.getString(5));
            setDate_fin(monResulat.getString(6));
            setDate_butoir(monResulat.getString(7));
            return true;
        } else {
            return false;
        }
    }

    public boolean Affiche_All_Tache(BDD BDD) throws SQLException {
        PreparedStatement req = BDD.getCnx().prepareStatement("SELECT * FROM tache LEFT JOIN gere ON tache.id_tache = gere.ref_tache LEFT JOIN compte ON gere.ref_compte = compte.id_compte");
        ResultSet monResulat = req.executeQuery();
        if (monResulat.next()){
            setId_tache(monResulat.getInt(1));
            setLibelle(monResulat.getString(2));
            setDescription(monResulat.getString(3));
            setDifficulte(monResulat.getInt(4));
            setDate_debut(monResulat.getString(5));
            setDate_fin(monResulat.getString(6));
            setDate_butoir(monResulat.getString(7));
            setRef_compte(monResulat.getInt(8));
            return true;
        } else {
            return false;
        }
    }

    public void Assigner_tache(BDD BDD) throws SQLException {
        PreparedStatement req = BDD.getCnx().prepareStatement("INSERT INTO gere(ref_tache,ref_compte) VALUES (?,?)");
        req.setInt(1,ref_tache);
        req.setInt(2,ref_compte);
        req.executeUpdate();
    }



    public int getId_tache() {
        return id_tache;
    }

    public void setId_tache(int id_tache) {
        this.id_tache = id_tache;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getDate_butoir() {
        return date_butoir;
    }

    public void setDate_butoir(String date_butoir) {
        this.date_butoir = date_butoir;
    }

    public int getRef_type() {
        return ref_type;
    }

    public void setRef_type(int ref_type) {
        this.ref_type = ref_type;
    }

    public int getRef_etat() {
        return ref_etat;
    }

    public void setRef_etat(int ref_etat) {
        this.ref_etat = ref_etat;
    }

    public int getRef_compte() {
        return ref_compte;
    }

    public void setRef_compte(int ref_compte) {
        this.ref_compte = ref_compte;
    }

    public int getRef_tache() {
        return ref_tache;
    }

    public void setRef_tache(int ref_tache) {
        this.ref_tache = ref_tache;
    }
}
