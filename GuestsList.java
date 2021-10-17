package p_01;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
 
public class GuestsList implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int max_participants;
	private ArrayList<Guest> guestsList;
	private ArrayList<Guest> waitList;
	private Guest auxGuest = new Guest();
	private static Scanner sc = new Scanner(System.in);
	
	public ArrayList<Guest> getGuestsList() {
		return guestsList;
	}

	public ArrayList<Guest> getWaitList() {
		return waitList;
	}
	
	public GuestsList(int max_participants) throws EOFException, IOException, NullPointerException {
		GuestsList.max_participants = max_participants;
		guestsList = new ArrayList<Guest>(max_participants);
		waitList = new ArrayList<Guest>();
		try {
			this.readFromBinaryFile(this.guestsList, "C:\\Users\\rotar\\MyWorkspace\\Guestlist.dat");
			this.readFromBinaryFile(this.waitList, "C:\\Users\\rotar\\\\MyWorkspace\\Waitlist.dat");
		} catch (EOFException e) {
			System.out.println("EOFException thrown: ");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			System.out.println("IOException thrown: ");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException thrown: ");
			e.printStackTrace();
		} 
	}
 
	public int getGuestsNo() {
		return guestsList.size();
	}
 
	public int getWaitlistNo() {
		return waitList.size();
	}
 
	public int getSubscribeNo() {
		return guestsList.size() + waitList.size();
	}
	
	public int getAvailableSeats() {
		return max_participants - guestsList.size();
	}
	
	public void printGuestsList() {
		for (int i = 0; i < guestsList.size(); i++) {
			System.out.println(i + 1 + ". "+ guestsList.get(i).toString());
		}
	}
 
	public void printWaitlist() {
		for (int i = 0; i < waitList.size(); i++) {
			System.out.println(i + 1 + ". "+ waitList.get(i).toString());
		}
	}
	
	public Guest createGuest() {
		Guest newGuest = new Guest();
		String nume, prenume, email, nrTlf;
		System.out.println("Nume:");
		nume = sc.next();
		newGuest.setLastName(nume);
		System.out.println("Prenume:");
		prenume = sc.next();
		newGuest.setFirstName(prenume);
		System.out.println("email:");
		email = sc.next();
		newGuest.setEmail(email);
		System.out.println("Numar de telefon:");
		nrTlf = sc.next();
		newGuest.setPhoneNumber(nrTlf);
		
		return newGuest;
	}
	
	public int addGuest(Object guest) {
		if (isOnGuestsList(guest) != null || isOnWaitList(guest) != null) {
			System.out.println("Eroare: aceasta persoana a fost deja inregistrata.");
			return -1;
		}
		Guest auxGuest = (Guest) guest;
		if (this.getGuestsNo() >= max_participants) {
			waitList.add(auxGuest);
			System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit "
					+ "numarul de ordine " + getWaitlistNo() + ".\nTe vom notifica daca un "
					+ "loc devine disponibil");
			return this.getWaitlistNo();
		}
		  
		guestsList.add(auxGuest);
		System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!.");
		return 0;		
	}
	
	public Guest isOnGuestsList(Object guest) {
		for (Guest i : guestsList) {
			if (i.equals(guest)) {
				return i;
			}
		}
		return null;
	}
	
	public Guest isOnWaitList(Object guest) {
		for (Guest i : waitList) {
			if (i.equals(guest)) {
				return i;
			}
		}
		return null;
	}
 
	public Guest check() {
		String lastName, firstName, email, phoneNr;
		System.out.println("Alege optiunea dupa care vrei sa faci cautarea:\n"
				+ "1. Nume si prenume\n"
				+ "2. email\n"
				+ "3. Numar de telefon");
		Guest.compCase = sc.nextInt();
		
		try {
			switch(Guest.compCase) {
				case 1:
					System.out.println("Nume: ");
					lastName = sc.next();
					auxGuest.setLastName(lastName);
					System.out.println("Prenume");
					firstName = sc.next();
					auxGuest.setFirstName(firstName);
					break;
				case 2:
					System.out.println("email: ");
					email = sc.next();
					auxGuest.setEmail(email);
					break;
				case 3:
					System.out.println("Numar de telefon: ");
					phoneNr = sc.next();
					auxGuest.setPhoneNumber(phoneNr);
					break;
				default:
					System.out.println("Comanda nedefinita.");
				}
		} catch (NoSuchElementException e) {
			System.out.println("Introducerea datelor a fost anulata. Aplicatia se inchide...");
	    	return null;
		}
		
		if (isOnGuestsList(auxGuest) != null) {
			System.out.println("Aceasta persoana este inscris/a la eveniment.");
			return isOnGuestsList(auxGuest);
		} else if (isOnWaitList(auxGuest) != null) {
			System.out.println("Aceasta persoana este inscris/a pe lista de asteptare.");
			return isOnWaitList(auxGuest);
		} else {
			System.out.println("Aceasta persoana nu este inregistrata.");
		}
		auxGuest = new Guest();
		return null;
	}
	
	public boolean removeGuest() {
		auxGuest = new Guest();
		auxGuest = check();
		
		if (isOnWaitList(auxGuest) != null) {
			waitList.remove(isOnWaitList(auxGuest));
			System.out.println("Datele acestei persoane au fost sterse.");
		}
		
		if (isOnGuestsList(auxGuest) != null) {
			guestsList.remove(isOnGuestsList(auxGuest));
			System.out.println("Datele acestei persoane au fost sterse.");
			if (guestsList.size() != 0 && guestsList.size() < max_participants &&
					waitList.size() > 0) {
				System.out.println(waitList.get(0).toString() 
						+ " a fost trecut/a pe lista de invitati.");
				guestsList.add(waitList.get(0));
				waitList.remove(0);
			}
		}
		return false;
	}
	
	public void update() {
		auxGuest = new Guest();
		auxGuest = check();
		if (auxGuest != null) {
			System.out.println("Alege campul pe care vrei sa il actualizezi:\n"
					+ "1. Nume si prenume\n"
					+ "2. email\n"
					+ "3. Numar de telefon");
			switch(sc.nextInt()) {
				case 1:
					System.out.println("Nume nou:");
					auxGuest.setLastName(sc.next());
					System.out.println("Prenume nou:");
					auxGuest.setFirstName(sc.next());
					System.out.println("Numele si prenumele au fost salvate.");
					break;
				case 2:
					System.out.println("email nou:");
					auxGuest.setEmail(sc.next());
					System.out.println("Adresa de email a fost salvata.");
					break;
				case 3:
					System.out.println("Numar de telefon nou:");
					auxGuest.setPhoneNumber(sc.next());
					System.out.println("Numarul de telefon a fost salvat.");
					break;
				default:
					System.out.println("Comanda nedefinita.");
			}
		}
	}
	
	public void search() {
		String keyword = "";
		System.out.println("Introdu un sir de caractere dupa care vrei sa faci cautarea:");
		try {
			keyword = sc.next();
		} catch (NoSuchElementException e) {
			System.out.println("Introducerea datelor a fost anulata. Aplicatia se inchide...");
		}
		
		int j = 1;
		for (Guest i : guestsList) {
			if (i.contains(keyword) != null) {
				System.out.println("Contactul " + j + " contine: " + i.contains(keyword));
			}
			j++;
		}
		j = 1;
		for (Guest i : waitList) {
			if (i.contains(keyword) != null) {
				System.out.println("Contactul " + j + " contine: " + i.contains(keyword));
			}
			j++;
		}
	}
	
	public void writeToBinaryFile(ArrayList<Guest> persons, String address) throws FileNotFoundException, 
									IOException {
		try (ObjectOutputStream binaryFileOut = new ObjectOutputStream(new BufferedOutputStream
				(new FileOutputStream(address)))) {
			for (Guest person : persons) {
				binaryFileOut.writeObject(person);
			}
		} 
	}
	 
	 public void readFromBinaryFile(ArrayList<Guest> persons, String address) throws IOException,
	 								FileNotFoundException {
		 try (ObjectInputStream guestBinaryFileIn = new ObjectInputStream(new FileInputStream(address))) {
			while (true) {
				try {
					Guest newGuest = (Guest) guestBinaryFileIn.readObject();
					if (newGuest != null) {
						persons.add(newGuest);
					}
				} catch (EOFException e) {
					break;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		 }
	 }
	
	 public static void helpInput() {
		 GuestsList.printDivider();
		 System.out.println("help \t\t- Afiseaza aceasta lista de comenzi\n" + 
				"add \t\t- Adauga o noua persoana (inscriere)\r\n" + 
				"check \t\t- Verifica daca o persoana este inscrisa la eveniment\r\n" + 
				"remove \t\t- Sterge o persoana existenta din lista\r\n" + 
				"update \t\t- Actualizeaza detaliile unei persoane\r\n" + 
				"guests \t\t- Lista de persoane care participa la eveniment\r\n" + 
				"waitlist \t- Persoanele din lista de asteptare\r\n" + 
				"available \t- Numarul de locuri libere\r\n" + 
				"guests_no \t- Numarul de persoane care participa la eveniment\r\n" + 
				"waitlist_no \t- Numarul de persoane din lista de asteptare\r\n" + 
				"subscribe_no \t- Numarul total de persoane inscrise\r\n" + 
				"search \t\t- Cauta toti invitatii conform sirului de caractere introdus\r\n" + 
				"quit \t\t- Inchide aplicatia");
		 GuestsList.printDivider();
	 }
	 
	public static void printDivider() {
		System.out.println("\n====================================================\n");
	}
	 
}
