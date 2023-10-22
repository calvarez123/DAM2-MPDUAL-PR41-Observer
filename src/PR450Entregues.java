import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PR450Entregues {
    private ArrayList<PR450Producte> productes = new ArrayList<>();
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public PR450Entregues() {
    }

    public void addProducte(PR450Producte producte) {
        productes.add(producte);
        pcs.firePropertyChange("entreguesAdd", null, producte);
    }

    public void removeProducte(int id) {
        PR450Producte producteToRemove = null;
        for (PR450Producte producte : productes) {
            if (producte.getId() == id) {
                producteToRemove = producte;
                break;
            }
        }
        if (producteToRemove != null) {
            productes.remove(producteToRemove);
            pcs.firePropertyChange("entreguesRemove", producteToRemove, null);
            pcs.firePropertyChange("entreguesEntregat", null, producteToRemove);
        }
    }

    public ArrayList<PR450Producte> getProductes() {
        return productes;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    @Override
    public String toString() {
    StringBuilder result = new StringBuilder("Entregues:\n");
    for (PR450Producte producte : productes) {
        result.append("Producte ID: ").append(producte.getId()).append(", Nom: ").append(producte.getNom()).append("\n");
    }
    return result.toString();
}

}
