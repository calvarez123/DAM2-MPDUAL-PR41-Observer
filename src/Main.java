import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Main {
    public static void main(String[] args) {
        PR450Producte producte1 = new PR450Producte(1, "llibre");
        PR450Producte producte2 = new PR450Producte(2, "quadern");
        PR450Producte producte3 = new PR450Producte(3, "escriptori");
        PR450Producte producte4 = new PR450Producte(4, "ordinador");
        PR450Producte producte5 = new PR450Producte(5, "ratolí");

        PropertyChangeListener producteListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("Producte ha canviat el " + evt.getPropertyName() +
                        " de '" + evt.getOldValue() + "' a '" + evt.getNewValue() + "'");
            }
        };
        producte1.addPropertyChangeListener(producteListener);
        producte2.addPropertyChangeListener(producteListener);

        PR450Magatzem magatzem = new PR450Magatzem();
        PR450Entregues entregues = new PR450Entregues();

        PropertyChangeListener magatzemListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("magatzemAdd".equals(evt.getPropertyName())) {
                    System.out.println("S'ha afegit el producte amb id " + ((PR450Producte) evt.getNewValue()).getId() +
                            " al magatzem, capacitat: " + magatzem.getCapacitat());
                } else if ("magatzemRemove".equals(evt.getPropertyName())) {
                    System.out.println("S'ha esborrat el producte amb id " + ((PR450Producte) evt.getOldValue()).getId() +
                            " del magatzem, capacitat: " + magatzem.getCapacitat());
                } else if ("magatzemEntrega".equals(evt.getPropertyName())) {
                    System.out.println("S'ha mogut el producte amb id " + ((PR450Producte) evt.getNewValue()).getId() +
                            " del magatzem cap a les entregues");
                }
            }
        };
        magatzem.addPropertyChangeListener(magatzemListener);

        PropertyChangeListener entreguesListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("entreguesAdd".equals(evt.getPropertyName())) {
                    System.out.println("S'ha afegit el producte amb id " + ((PR450Producte) evt.getNewValue()).getId() +
                            " a la llista d'entregues");
                } else if ("entreguesRemove".equals(evt.getPropertyName())) {
                    System.out.println("S'ha entregat el producte amb id " + ((PR450Producte) evt.getOldValue()).getId());
                }
            }
        };
        entregues.addPropertyChangeListener(entreguesListener);

        magatzem.addProducte(producte1);
        magatzem.addProducte(producte2);
        magatzem.addProducte(producte3);
        magatzem.addProducte(producte4);
        magatzem.addProducte(producte5);

        producte1.setNom("llibreta");

        magatzem.removeProducte(2);
        magatzem.removeProducte(3);
        magatzem.removeProducte(4);

        System.out.println("Productes al magatzem: " + magatzem.toString());
        System.out.println("------------------------------------------------------");
        System.out.println("Productes en entregues: " + entregues.toString());
    }
}
