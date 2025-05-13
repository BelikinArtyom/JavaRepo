package json.models;

public class ShipTo {


    private String shipTo;
    private String city;
    private Integer zip;

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }


    //    "shipTo" : {
//        "name" : "Jane Smith",
//                "address" : "123 Maple Street",
//                "city" : "Pretendville",
//                "state" : "NY",
//                "zip"   : 12345
//    }
//}
}
