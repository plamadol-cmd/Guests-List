# Guests_List

This application implements a back-end system responsible for managing the guests list for an event and consists of 3 parts:
1. Class "Guest" - which is used to create instances for every participant.
2. Class "GuestsList" - this is where the magic happens. This event has a limited number of attendees, so the user sets the maximum number of guests 
                        when starting the app. Two ArrayLists are used to store the guests(guestList) and the participants that are on the waitlist(waitList). 
                        Before exiting the app, the lists are stored in 2 binary files. Similarly, when starting the app, it will read the previously added 
                        participants from the .dat files. This class implements all of the commands from the Main file.
3. Class "Main" - this is the engine of the app. It reads the input from the user and prints the results. Available commands:
                      - help: show this commands list
				              - add: add a Guest
                      - check: check if the current guest has signed up
                      - remove: check if the guest exists on any of lists and delete the data
                      - update: update details of an existing guest
                      - guests: print the list of the attendees
                      - waitlist: print the people on the waiting list
                      - available: numbers of available seats
                      - guests_no: number of current attendees
                      - waitlist_no: number of people on the waiting list
                      - subscribe_no: the total number of people that signed up for an event
                      - search: search the "guestsList" and the "waitList" for matches from the input String
                      - quit: exit the app
