package json.models;

public class OrderDetails {

    public String name;
    public Integer sku;
    public double price;

    public OrderAdress shipTo;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderAdress getShipTo() {
        return shipTo;
    }

    public void setShipTo(OrderAdress shipTo) {
        this.shipTo = shipTo;
    }
}
