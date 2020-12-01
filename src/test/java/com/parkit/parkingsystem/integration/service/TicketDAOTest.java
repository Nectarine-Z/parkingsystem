package com.parkit.parkingsystem.integration.service;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

//@ExtendWith(MockitoExtension.class)


public class TicketDAOTest {
    private TicketDAO ticketDAO;
    private Ticket ticket;
    
    @BeforeEach
    private void setUpPerTest() {
        try {
            ticketDAO = new TicketDAO();
            ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
            ticket = new Ticket();
            ticket.setInTime(new Date(System.currentTimeMillis() - (60*60*1000)));
            ticket.setParkingSpot(parkingSpot);
            ticket.setVehicleRegNumber("ABCDEF");
            

           // when(ticketDAO.checkRegNumber(anyString())).thenReturn(true);
          //  when(ticketDAO.saveTicket(ticket)).thenReturn(true);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
    }
    @Test 
    public void saveTicketTest() {
    	Assertions.assertFalse(ticketDAO.saveTicket(ticket));
    }
    @Test 
    public void getTicketTest() {
    	String vehicleRegNumber="ABCDEF";
		Assertions.assertNotNull(ticketDAO.getTicket(vehicleRegNumber));
    }
    @Test
    public void updateTicketTest() {   	
    	Assertions.assertFalse(ticketDAO.updateTicket(ticket));
    }
    @Test
    public void updateTicketInTimeTest() {
    	ticketDAO.updateTicketInTime(ticket);
    }
    @Test
    public void checkRegNumberTest() {
    	String regNumberPlate ="ABCDEF";
		ticketDAO.checkRegNumber(regNumberPlate );
    }



}
