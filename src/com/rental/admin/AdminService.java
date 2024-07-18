package com.rental.admin;

import com.rental.DBConnection;
import com.rental.model.Booking;
import com.rental.model.User;
import com.rental.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                users.add(user);
            }
        }
        return users;
    }

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setVehicleId(rs.getInt("vehicle_id"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setLocation(rs.getString("location"));
                booking.setTotalAmount(rs.getDouble("total_amount"));
                bookings.add(booking);
            }
        }
        return bookings;
    }

    public double getTotalRevenue() throws SQLException {
        String query = "SELECT SUM(total_amount) AS total_revenue FROM bookings";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("total_revenue");
            }
        }
        return 0;
    }
}
