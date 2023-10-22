import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class PR450Producte {
    private int id;
    private String nom;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public PR450Producte(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        int oldId = this.id;
        this.id = id;
        pcs.firePropertyChange("producteId", oldId, id);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String oldNom = this.nom;
        this.nom = nom;
        pcs.firePropertyChange("producteName", oldNom, nom);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
