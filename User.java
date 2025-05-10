package com.example.HotelBooking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	String userAdhar;
	
	String userName;
String roomType;
String location;
String bedShare;

String hotelName;

public String getHotelName() {
    return hotelName;
}

public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
}

public String getBedShare() {
	return bedShare;
}
public void setBedShare(String bedShare) {
	this.bedShare = bedShare;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserAdhar() {
	return userAdhar;
}
public void setUserAdhar(String userAdhar) {
	this.userAdhar = userAdhar;
}
public String getRoomType() {
	return roomType;
}
public void setRoomType(String roomType) {
	this.roomType = roomType;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
@Override
public String toString() {
	return "User [UserAdhar=" + userAdhar + ", UserName=" + userName + ", RoomType=" + roomType + ", Location="
			+ location + "BedShare"+ bedShare +"]";
}

}
