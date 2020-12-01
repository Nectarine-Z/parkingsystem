package com.parkit.parkingsystem.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

@ExtendWith(MockitoExtension.class)


public class ParkingSpotDAOTest {

    @Mock
    private static ParkingService parkingService, parkingService1, parkingService2,parkingService3 ;
    @Mock
    private static InputReaderUtil inputReaderUtil,inputReaderUtil1, inputReaderUtil2;
    @Mock
    private static ParkingSpotDAO parkingSpotDAO;
    @Mock
    private static TicketDAO ticketDAO,ticketDAO1;
    
    
    @Test 
    public void getNextAvailableSlotTest() {
    	parkingSpotDAO = new ParkingSpotDAO();
    	ParkingType parkingType = ParkingType.CAR;
		Assertions.assertNotNull(parkingSpotDAO.getNextAvailableSlot(parkingType));
    }
    @Test 
    public void updateParkingTest() {

    	parkingSpotDAO = new ParkingSpotDAO();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
    	Assertions.assertTrue(parkingSpotDAO.updateParking(parkingSpot));
    }



}
