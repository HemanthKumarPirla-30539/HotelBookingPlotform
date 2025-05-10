package com.example.HotelBooking;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/hotel")
public class userController {

	@Autowired
	UserRepository ur;
	
	@PostMapping("/insert")
	public String insert(@RequestBody User u) {
		ur.save(u);
		return "Booked Suucessfully";
	} 
	
	@GetMapping("/retrieve")
	public List<User> getAll() {
		return ur.findAll();
		
	}
	
	
	@PutMapping("/update/{ano}")
	public String updateOne(@PathVariable("ano") String ano, @RequestBody User u) {
	    Optional<User> existingUser = ur.findById(ano);
	    
	    if (existingUser.isPresent()) {
	        User updatedUser = existingUser.get();
	        updatedUser.setUserName(u.getUserName());
	        updatedUser.setUserAdhar(u.getUserAdhar());  
	        updatedUser.setBedShare(u.getBedShare());
	        updatedUser.setLocation(u.getLocation());
	        updatedUser.setRoomType(u.getRoomType());
	        
	        ur.save(updatedUser);
	        return "Updated Successfully";
	    } else {
	        return "User not found with Aadhar: " + ano;
	    }
	}
	 @PostMapping("/signup")
	    public ResponseEntity<String> signup(@RequestBody User u) {
	        if (ur.findByUserAdhar(u.getUserAdhar()).isPresent()) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists with this Aadhar");
	        }
	        ur.save(u);
	        return ResponseEntity.ok("Signup successful");
	    }

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody User u) {
	        Optional<User> user = ur.findByUserAdhar(u.getUserAdhar());
	        if (user.isPresent() && user.get().getUserName().equals(u.getUserName())) {
	            return ResponseEntity.ok("Login successful");
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Aadhar or Name");
	    }
	    
	    @GetMapping("/user/{adhar}")
	    public ResponseEntity<User> getUserByAdhar(@PathVariable String adhar) {
	        Optional<User> u = ur.findByUserAdhar(adhar);
	        return u.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    @PostMapping("/book")
	    public String bookHotel(@RequestBody Map<String, String> data) {
	        User user = new User();
	        user.setUserName(data.get("userName"));
	        user.setUserAdhar(data.get("userAdhar"));
	        user.setLocation(data.get("location"));
	        user.setRoomType(data.get("roomType"));
	        user.setBedShare(data.get("bedShare"));
	        user.setHotelName(data.get("hotelName")); // You must add hotelName field to User entity

	        ur.save(user);
	        return "Hotel booked successfully!";
	    }

	
	}
	

