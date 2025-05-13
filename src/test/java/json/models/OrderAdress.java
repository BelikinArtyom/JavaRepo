package json.models;

public class OrderAdress {


    public String shipTo;
    public String city;
    public Integer zip;
    public String address;
    public String state;
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


//        "shipTo" : {
//        "name" : "Jane Smith",
//                "address" : "123 Maple Street",
//                "city" : "Pretendville",
//                "state" : "NY",
//                "zip"   : 12345
//
//

}
