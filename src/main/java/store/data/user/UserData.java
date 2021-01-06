package store.data.user;

import store.model.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserData {

    @Email
    @NotEmpty
    private String email;
    private UserRole role;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") //min 8 characters, at least 1 letter, 1 number
    private String password;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String firstName;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String lastName;
    @NotEmpty
    @Pattern(regexp = "^[0-9+]*$")
    private String phoneNumber;
    private String addressId;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String country;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String city;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String county;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z -]*$")
    private String streetName;
    @NotEmpty
    @Pattern(regexp = "^[0-9]*$")
    private String streetNumber;
    @NotEmpty
    @Pattern(regexp = "^[0-9]*$")
    private String flatNumber;
    @NotEmpty
    @Pattern(regexp = "^[0-9]*$")
    private String buildingNumber;

    public UserData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
