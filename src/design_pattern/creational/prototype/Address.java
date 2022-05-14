package design_pattern.creational.prototype;

public class Address {
    private String street, district, province;

    public Address(String street, String district, String province) {
        super();
        this.street = street;
        this.district = district;
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", district=" + district +
                ", province=" + province + "]";
    }
}
