# FlightBookingApplication
Flight inventory and booking management systems

Problem Statement: Design an application for flight inventory and booking management systems. 
Where every day multiple flights are flying on different sectors and flights can have multiple 
fares and for each fare there will be a list of seats. Assumptions: . There is a supplier that
is providing flight data, Same flight can fly for two different sectors for the same day but time
cannot be the same Flights can have multiple fare Type and for each fare Type airline is providing 
an available seat list. If a seat is already booked, other users can't book the same seat. Take today's
date as 1. For departDate simple number is provided like - 1, 2, 3, 4, etc For time, assume its 24 hour
format and will be a decimal number. User fund will be a decimal number only.

Features: . AddUser(userld, name, funds) Return user details Output: <u1, name, funds> 
            SearchFlight(from, to, depar Date, paxCount) Return available flights for given request in below format.
            (for the same date and if all available seats are equal or more than provided pax count).4
            Output-<flightNumber, airline, from, to departDate, depart Time, price, fare Type List<seat>>
            Book flight(userld, flightNumber, departDate, fare Type, list<seat>) 
            Book flight if all seats are available with the same fare Type and the user has enough funds in his/her wallet.
            Deduct funds from userAccount for booking and return bookingld. If it fails, then return a proper error message
 

