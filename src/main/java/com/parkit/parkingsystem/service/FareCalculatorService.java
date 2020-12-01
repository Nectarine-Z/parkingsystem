package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

	public void calculateFare(Ticket ticket) {
		if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
			throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
		}

		double inHour = ticket.getInTime().getTime();
		double outHour = ticket.getOutTime().getTime();

		double duration = outHour - inHour;
		double factor = duration / 1000 / 60 / 60;
		if (factor <= 0.5) {
			ticket.setPrice(0);
		} else {
			TicketDAO ticketDAO  = new TicketDAO();
			double reduc = 1;
			if (ticketDAO.checkRegNumber(ticket.getVehicleRegNumber())) {
				reduc = 0.95;
			}
			switch (ticket.getParkingSpot().getParkingType()) {
			case CAR: {
				ticket.setPrice((factor * Fare.CAR_RATE_PER_HOUR)*reduc);
				break;
			}
			case BIKE: {
				ticket.setPrice((factor * Fare.BIKE_RATE_PER_HOUR)*reduc);
				break;
			}
			default:
				throw new IllegalArgumentException("Unkown Parking Type");
			}
		}

	}
}