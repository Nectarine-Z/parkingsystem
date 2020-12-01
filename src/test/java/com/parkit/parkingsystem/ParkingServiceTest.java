package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    private static ParkingService parkingService;

    @Mock
    private static InputReaderUtil inputReaderUtil;
    @Mock
    private static ParkingSpotDAO parkingSpotDAO;
    @Mock
    private static TicketDAO ticketDAO;

    @BeforeEach
    private void setUpPerTest() {
        try {
            when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");

            ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);// Créer un objet de la  classe ParkingSpot 
            Ticket ticket = new Ticket();// Création d'un objet de la classe Ticket
            ticket.setInTime(new Date(System.currentTimeMillis() - (60*60*1000)));
            ticket.setParkingSpot(parkingSpot);
            ticket.setVehicleRegNumber("ABCDEF");
            when(ticketDAO.getTicket(anyString())).thenReturn(ticket);// Lorsque l'objet ticketDAO appelle la méthode getTicket() et quelque soit le paramètre (anyString())), on renvoie l'objet ticket qu'on a créé 
            when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);// lorsque l'objet ticketDAO appelle la méthode updateTicket()et quelque soit le paramètre introduit, on renvoie la valeur true

            when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);// lorsque l'objet parkingSpotDAO appelle la méthode updateParking avec n'importe quel paramètre (any(ParkingSpot)), on renvoie true

            parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);// initialisation de l'objet parkingService
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
    }

    @Test
    public void processExitingVehicleTest(){
        parkingService.processExitingVehicle();// execution de la méthode processExistingVehicle
        /*lorsqu'on executé la méthode processExistingVehicle() */
        verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));// 
    }

}
