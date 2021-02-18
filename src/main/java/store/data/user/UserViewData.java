package store.data.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserViewData {

    @NotBlank(message = "Requested")
    @Pattern(regexp = "^[A-Za-z0-9+ -]*$")
    private String firstName;
    @NotBlank(message = "Requested")
    @Pattern(regexp = "^[A-Za-z0-9+ -]*$")
    private String lastName;
    @NotNull(message = "Requested")
    @Pattern(regexp = "^[0-9+]*$")
    private String phoneNumber;
    private String addressId;
    @NotBlank(message = "Requested")
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String country;
    @NotBlank(message = "Requested")
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String city;
    @NotBlank(message = "Requested")
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String county;
    @NotBlank(message = "Requested")
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String streetName;
    @NotBlank(message = "Requested")
    @Pattern(regexp = "^[0-9]*$")
    private String streetNumber;
    @NotNull(message = "Requested")
    @Pattern(regexp = "^[0-9]*$")
    private String flatNumber;
    @NotNull(message = "Requested")
    @Pattern(regexp = "^[0-9]*$")
    private String buildingNumber;

    public UserViewData() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
