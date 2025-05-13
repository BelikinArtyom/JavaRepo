package json.models;

public class JaySon {

        private String name;
        private int sku;

        private ShipTo shipTo;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Integer getSku() {
                return sku;
        }

        public void setSku(Integer sku) {
                this.sku = sku;
        }

        public ShipTo getShipTo() {
                return shipTo;
        }

        public void setShipTo(ShipTo shipTo) {
                this.shipTo = shipTo;
        }
}
