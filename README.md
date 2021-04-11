# JavaParkingProject
This is a project I made in my first year using Java. This programme allows the user to park or have their 
vehicle parked by an attendant in the appropriate zone. If the user decides to park the vehicle themselves 
the system will check if there is a free parking space in the correct zone (the zone is automatically chosen based on
the vehicle type) and if there is it provides the user with the receipt. When the user returns to the car park they have to type 
in receipt's number and pay the right amount of money for their stay. If the payment is accepted system activates a token and reminds 
the user where their vehicle is parked. The token gives them 15 minutes to leave the car park. User leaves the car park be re-entering the receipt number.

MCP deals with five kinds of vehicles:
1. Standard sized vehicles (up to 2 metres in height and less than 5 metres in length:
cars and small vans).
2. Higher vehicles (over 2 metres in height but less than 3 metres in height and less than
5 metres in length: tall short wheel-based vans).
3. Longer vehicles (less than 3 metres in height and between 5.1 and 6 metres in length:
long wheel-based vans).
4. Coaches (of any height up to 15 metres in length).
5. Motorbikes.

MCP supports five parking zones to accommodate these different vehicle types:
1. Zone 1: Outside, standard-sized zone: not in the multi-story building. This can only
accommodate standard sized vehicles and higher vehicles.
2. Zone 2: Outside, longer-sized zone: not in the multi-story building. This can
accommodate only longer vehicles, but not coaches.
3. Zone 3: Outside, coach zone; just for coaches.
4. Zone 4: Multi-story building, standard-sized zone. This can only accommodate
standard sized vehicles.
5. Zone 5: Multi-story building, motorbike zone. Motorbikes only

If the user(but not motorbike rider or coach driver) drop off their vehicle at the front
door and give it to a garage attendant, then the attendant driver will use the same app to
enter the relevant information and will provide the customer with the parking receipt.
Attendants though are able to roam the car park and pick free space, in the right zone, by its
id(available zones will print automatically based on the vehicle type). When the customer
returns to the car park, they need to enter the receipt number, pay the correct amount of
money and then they will be informed that the attendant will collect their vehicle. Payment
also activates the token that gives 15 minutes for the user to leave the car park.
In both situations, if the users take more than 15 minutes to leave they will be informed
that they took too long and will be asked to call help.
Disabled drivers and riders of all vehicles (except coaches) have the fee halved and have
free parking on Sundays. Free parking still requires the collection of a token.

