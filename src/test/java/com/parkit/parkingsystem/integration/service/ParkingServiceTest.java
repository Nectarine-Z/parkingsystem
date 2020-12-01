package com.parkit.parkingsystem.integration.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

@ExtendWith(MockitoExtension.class)


public class ParkingServiceTest {

    @Mock
    private static ParkingService parkingService, parkingService1, parkingService2,parkingService3 ;
    @Mock
    private static InputReaderUtil inputReaderUtil,inputReaderUtil1, inputReaderUtil2;
    @Mock
    private static ParkingSpotDAO parkingSpotDAO;
    @Mock
    private static TicketDAO ticketDAO,ticketDAO1;
    private Ticket ticket;
    private int input = 1;
    
    @BeforeEach
    private void setUpPerTest() {
        try {

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
/*lorsque l'objet parkingService appelle la méthode processIncomingVehicle(), elle a d'abord besoin d'exécuter la méthode getNextParkingNumberIfAvailable(). 
 * lorsque la méthode getNextParkingNumberIfAvailable() est appelée, elle appelle à son tour la méthode getVehicleType() pour récuperer le type de véhicule. A ce niveau là, 
 * nous utilisons la méthode  when(inputReaderUtil.readSelection()).thenReturn() pour lui donner à chaque fois une valeur (1, 2 ou default). Une fois le parkingType est récupéré, 
 * on passe à l'étape suivante qui est le parkingNumber, lorsque la méthode parkingSpotDAO.getNextAvailableSlot(parkingType) est appeléé, on renvoie automatiquement la valeur 42 
 * (  when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(42);). Ensuite, il controle si la valeur du parkingNumber est > 0 ou pas. Ici 42 > 0, donc 
 * il va executer le code à l'intérieur de if (ligne 74 de la classe parkingService.java).
 * */
    @Test 
    public void processIncomingVehicle1() {
    	input = 1;
    	when(inputReaderUtil.readSelection()).thenReturn(input);
    	Integer parkingNumber = 42;
		when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(parkingNumber );
    	Assertions.assertTrue(inputReaderUtil.readSelection() == 1);
        parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    	parkingService.processIncomingVehicle();
    }
    @Test 
    public void processIncomingVehicle2() {
    	input = 2;
    	when(inputReaderUtil.readSelection()).thenReturn(input);
    	Integer parkingNumber = 42;
		when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(parkingNumber );
    	Assertions.assertTrue(inputReaderUtil.readSelection() == 2);
        parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
    	parkingService.processIncomingVehicle();
    }
    @Test 
    public void processIncomingVehicleDefault() {
    	input = 3;
    	when(inputReaderUtil.readSelection()).thenReturn(input);
    	Assertions.assertTrue(inputReaderUtil.readSelection() == 3);
        parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        //Assertions.assertSame(IllegalArgumentException.class, parkingService.getNextParkingNumberIfAvailable(),"Error parsing user input for type of vehicle");
        parkingService.processIncomingVehicle();
      //Assertions.assertThrows(IllegalArgumentException.class,()-> parkingService.processIncomingVehicle());     
    }
    
    @Test
   public void getNextParkingNumberIfAvailable1() {
 
    	input = 1;
    	when(inputReaderUtil.readSelection()).thenReturn(input);
    	Integer parkingNumber = -1;
		when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(parkingNumber );
        parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        parkingService.getNextParkingNumberIfAvailable();
    }



}
