package tests.data;


public enum addresses {
    Ижевск("г. Ижевск, ул. Карла Маркса, д. 300А"),
    Казань("г. Казань, ул. Зинина, д. 9/23");

    public final String shopAdress;

    addresses(String shopAdress) {
        this.shopAdress = shopAdress;
    }
}
