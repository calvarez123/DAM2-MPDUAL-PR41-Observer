import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class PR450Magatzem {
    private ArrayList<PR450Producte> productes = new ArrayList<>();
    private int capacitat = 10;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public PR450Magatzem() {
    }

    public void addProducte(PR450Producte producte) {
        if (productes.size() < capacitat) {
            productes.add(producte);
            pcs.firePropertyChange("magatzemAdd", null, producte);
            capacitat--;
        }
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
            pcs.firePropertyChange("magatzemRemove", producteToRemove, null);
            capacitat++;
            pcs.firePropertyChange("magatzemEntrega", null, producteToRemove);
        }
    }


    public int getCapacitat() {
        return this.capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
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
    StringBuilder result = new StringBuilder("Magatzem:\n");
    for (PR450Producte producte : productes) {
        result.append("Producte ID: ").append(producte.getId()).append(", Nom: ").append(producte.getNom()).append("\n");
    }
    return result.toString();
}

}
