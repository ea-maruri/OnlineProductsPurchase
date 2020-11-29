package beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;


@Named("user_session")
@SessionScoped
public class UserManager implements Serializable {
    private ProductsBean productsBean;
    FacesContext fCtx = FacesContext.getCurrentInstance();
    private int rowsOnPage;
    private ArrayList<Products> purchasedProducts = new ArrayList<>();
    private ArrayList<Products> filteredProducts = new ArrayList<>();

    public ArrayList<ArrayList<Products>> getPurchases() {
        return purchases;
    }

    private ArrayList<ArrayList<Products>> purchases = new ArrayList<>();
    private String errorString = "";

    public ArrayList<Products> getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(ArrayList<Products> lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    private ArrayList<Products> lastPurchase = new ArrayList<>();


    public UserManager(){
        productsBean = ProductsBean.getSingleton();
        rowsOnPage = 5;

        setPurchasedProducts(productsBean.getProductCopy());
        setFilteredProducts(new ArrayList<>(purchasedProducts));

    }


    public void setPurchasedProducts(ArrayList<Products> purchasedProducts) {
        this.purchasedProducts = new ArrayList<>(purchasedProducts);
    }

    public void setFilteredProducts(ArrayList<Products> newFiltered) {
        filteredProducts.clear();
        filteredProducts.addAll(newFiltered);
    }

    public double getLastPurchaseTotalPrice(){
        double total = 0;
        for(Products p: lastPurchase){
            total += p.getTotalPrice();
        }
        return (double) Math.round(total * 100d) / 100d;
    }
}
