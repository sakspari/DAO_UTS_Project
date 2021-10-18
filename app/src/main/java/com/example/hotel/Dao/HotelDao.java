package com.example.hotel.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomReview;

import java.util.List;

@Dao
public interface HotelDao {
    @Transaction
    @Query("SELECT * FROM hotel_room")
    List<HotelRoom> getAllRoom();

    @Insert
    void insertRoom(HotelRoom hotelRoom);

    @Update
    void updateRoom(HotelRoom hotelRoom);

    @Delete
    void deleteRoom(HotelRoom hotelRoom);

    // ambil review yang dibuat untuk kamar yang iodnya sama dengan room_id
    @Query("SELECT * FROM room_review where fk_room_id = :room_id")
    List<RoomReview> getRoomReview(String room_id);
}