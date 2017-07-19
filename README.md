# _Hair Salon Management Dashboard_

#### _Allow hair salon manager/owner to update clients and stylists, July 14, 2017_

#### By _**Mick Sexton**_

## Description

_This application can be used by salon owners & managers to add and delete stylists as well as clients. Users can update stylist information like name and hourly wage. Users can update client information like name, age, special requirements, and the stylist that they are assigned to._

## Setup/Installation Requirements

* _Download the hair_salon folder_
* _Setup Up Your Database in psql called: hair_salon (hair_salon_test for automated testing purposes)_
* _Create a database table for stylists with name varchar and wage int_
* _Create a database table for clients with name varchar, age int, specialReq varchar, and stylistId int_
* _type in "gradle run" in your command line_
* _open a browser and go to localhost:4567_

## Code Specs

|Behavior - Plain English|Input|Output|
|---|---|---|
|User sees a list of stylists they can click on for more information, as well as a button option to Add a new stylist.|User selects a stylist|User goes to the stylist page for that stylist|
|User sees a stylist page with their list of their clients, information about that stylist including name and hourly wage, a button to add a new client to this stylist, a button to update the stylists's information, a button to delete this stylist, and a button to navigate back to Home page.|User selects a client|User is directed the client page for that client|
|User sees a client page with their list of information including the client's name, age, special requirements, and stylist they are assigned to. They also see option buttons to update the client's info, delete the client, navigate back to the stylist page for that client, or navigate back to home page|User clicks on Update Client button|User is directed to the Update Client Form page.|
|User sees a client update page with fields populated with previously saved info about client.|User changes a value in one of the fields like Special Requirements or selects a new stylist to assign them to from the select box and clicks update button.|User is then directed back o the client details page with updated information.|
|User sees a delete client page after clicking on delete button from the client details page.|User clicks delete again to confirm they want to remove client from system.|User is directed back to the stylist page for that former client.|
|User sees an add client form after selecting add client from the stylist details page.|User fills out name, age, special requirements for the new client and submits form.|User is then directed to a new client details page with that client's information.|
|User sees a stylist update page with fields populated with previously saved information on the stylist that is editable.|User changes information like the stylist's hourly wage and submits the update.|User is directed back to that stylist's details page with the updated information now showing.|
|User sees a delete stylist form after selecting delete stylist from the stylist details page. If that stylist still has clients assigned to them, it will not all the user to delete that stylist until those clients have been moved to another stylist. If the stylist has had all clients reassigned, there will be a delete button to confirm the delete decision.|User clicks the delete button again.|User is directed back to home page with list of stylists that no longer shows that deleted stylist.|
|User sees an add stylist form after selecting add stylist from the home page.|User fills in the stylist name and hourly wage fields and clicks submit.|User is then taken to a new stylist details page for that new stylist that they have just added.|


## Known Bugs

_No known issues or bugs at this time_

## Support and contact details

_Please contact Mick Sexton at lacrookedbeat@hotmail.com for any questions, feedback, or concerns._

## Technologies Used

_Technologies used include Java, Atom, Git, Gradle, GitHub, Spark, CSS, J-Unit, Postgres, SQL database, and Velocity Template Engine_

### License

*This software operates under the MIT license.*

Copyright (c) 2017 **_Mick Sexton_**
