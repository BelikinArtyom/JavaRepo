package tests.data;


public enum address {
    Izhevsk("г. Ижевск, ул. Карла Маркса, д. 300А"),
    SPB("г. Санкт-Петербург, ул. Марата, д. 53");

    private final String shopAdress;

    address(String shopAdress) {
        this.shopAdress = shopAdress;
    }
}
