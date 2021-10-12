package com.example.daoforutsproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.daoforutsproject.Model.BookDetail;
import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.User;

import java.util.List;
@Dao
public interface BookingDao {
    @Transaction
    @Query("SELECT * FROM book_detail")
    List<BookDetail> getAllBookings();

    @Insert
    void insertBooking(BookDetail bookDetail);

    @Update
    void updateBooking(BookDetail bookDetail);

    @Delete
    void deleteBooking(BookDetail bookDetail);

    //    mengambil user yang membuat Bokingan
    @Query("SELECT * FROM user where username = :username")
    User getUser(String username);

    //    mengambil kamar hotel di Booking
    @Query("SELECT * FROM hotel_room where room_id = :room_id")
    HotelRoom getHotelRoom(String room_id);
}
