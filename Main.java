package p_01;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
 
	public static void main(String[] args) throws EOFException, IOException, 
							NullPointerException, FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int max_participants = -1;
		String userInput;
		
		System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
		try {
			max_participants = sc.nextInt();
		} catch (InputMismatchException e) {
			sc.nextLine();
		    System.out.println("Nu ai introdus o valoare intreaga. Te rog sa reincerci.");
		} catch (NoSuchElementException e) {
			System.out.println("Introducerea datelor a fost anulata. Aplicatia se inchide.");
		} 
		
		GuestsList event = new GuestsList(max_participants);
		
		userInput = "";
		
		while (!userInput.equals("quit")) {
			try {
				System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
				userInput = sc.next().toLowerCase();
				switch(userInput) {
					case "help":
						GuestsList.helpInput();
						break;
					case "add":
						event.addGuest(event.createGuest());
						GuestsList.printDivider();
						break;
					case "check":
						event.check();
						GuestsList.printDivider();
						break;
					case "remove":
						event.removeGuest();
						break;
					case "update":
						event.update();
						GuestsList.printDivider();
						break;
					case "guests":
						event.printGuestsList();
						GuestsList.printDivider();
						break;
					case "waitlist":
						event.printWaitlist();
						GuestsList.printDivider();
						break;
					case "available":
						System.out.println(event.getAvailableSeats());
						GuestsList.printDivider();
						break;
					case "guests_no":
						System.out.println(event.getGuestsNo());
						GuestsList.printDivider();
						break;
					case "waitlist_no":
						System.out.println(event.getWaitlistNo());
						GuestsList.printDivider();
						break;
					case "subscribe_no":
						System.out.println(event.getSubscribeNo());
						GuestsList.printDivider();
						break;
					case "search":
						event.search();
						GuestsList.printDivider();
						break;
					case "quit":
						event.writeToBinaryFile(event.getGuestsList(), "C:\\Users\\rotar\\MyWorkspace\\Guestlist.dat");
						event.writeToBinaryFile(event.getWaitList(), "C:\\Users\\rotar\\\\MyWorkspace\\Waitlist.dat");
						System.out.println("La revedere!");
						GuestsList.printDivider();
						break;
					default:
						System.out.println("Comanda nedefinita. Reintroduceti: ");
				} 
			} catch (NoSuchElementException e) {
				System.out.println("Introducerea datelor a fost anulata. Aplicatia se inchide.");
		    	break;
			}
		}
		
		sc.close();
		
	}
 
}
