//I am sorry ahead of time
package ddcalc;

import java.util.Scanner;

public class DoomsdayCalculator {

	// Main Method
	public static void main(String[] args) {
		// Init. variables
		Scanner kb = new Scanner(System.in);
		String inputDate = "";
		int month = 00;
		int day = 00;
		int year = 0000;
		int doomsday = 0;
		int doomsdate = 0;
		String output = ""; //I like variables :)

		// Get input
		System.out.println("Enter the date requested in MM/DD/YYYY format:");
		inputDate = kb.nextLine();
		kb.close();

		// Convert to ints.
		inputDate = inputDate.trim();
		month = Integer.parseInt(inputDate.substring(0, 2));
		day = Integer.parseInt(inputDate.substring(3, 5));
		year = Integer.parseInt(inputDate.substring(6));

		//Call methods
		doomsday = findYearAnchor(year);
		doomsdate = findDoomsdate(month, year);
		output = calcuDate(doomsdate, doomsday, day);
		
		//Print!!!!!!!!!!!!!!!!!!!!!!
		System.out.println(inputDate + " is a " + output + ".");

	}

	public static int findCenturyAnchor(int year) {
		// convert year to century

		int century = year / 100;
		int anchor = -1;
		century *= 100;
		century %= 400;

		// Check century anchor and return
		switch (century) {

		case 0:
			anchor = 2;
			break;
		case 100:
			anchor = 0;
			break;
		case 200:
			anchor = 5;
			break;
		case 300:
			anchor = 3;
			break;
		}

		return anchor;
	}

	public static int findYearAnchor(int year) {
		int yy = 0;
		int sum = 0;

		// Get last two numbers
		String convertMe = Integer.toString(year);
		convertMe = convertMe.substring(convertMe.length() - 2);
		yy = Integer.parseInt(convertMe);

		// Tha Math ;)
		sum = (((yy / 12) + yy % 12 + ((yy % 12) / 4) + findCenturyAnchor(year)) % 7);
		return sum;
	}

	public static boolean isLeapYear(int year) {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				}
			}
			else {
				return true;
			}
		}
		return false;
	}

	public static int findDoomsdate(int month, int year) {
		int doomsdate = -1;

		switch (month) {
		case 1:
			if (isLeapYear(year)) {
				doomsdate = 4;
			} else {
				doomsdate = 3;
			}
			
			break;

		case 2:
			if (isLeapYear(year)) {
				doomsdate = 29;
			} else {
				doomsdate = 28;
			}
			break;

		case 3:
			doomsdate = 14;
			break;

		case 4:
			doomsdate = 4;
			break;

		case 5:
			doomsdate = 9;
			break;

		case 6:
			doomsdate = 6;
			break;

		case 7:
			doomsdate = 11;
			break;

		case 8:
			doomsdate = 8;
			break;

		case 9:
			doomsdate = 5;
			break;

		case 10:
			doomsdate = 10;
			break;

		case 11:
			doomsdate = 7;
			break;

		case 12:
			doomsdate = 12;
			break;
		}
		return doomsdate;
	}

	public static String calcuDate(int doomsdate, int doomsday, int day) {
		int count = 0;
		String[] daysOfTheWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

		// day counts up/down to doomsdate
		// doomsday = what doomsdates mean for the year (i.e. 0, 1, 2, etc)
		if (doomsdate > day) {
			while (doomsdate > day) {
				day++;
				count--;
			}
		} 
		else if (doomsdate < day) {
			while (doomsdate < day) {
				day--;
				count++;
			}
		} 

		day = (doomsday + count);

		// FUCK
		while (day < 0) {
			day += 7;
		}
		day %= 7;

		// Apply date
		return daysOfTheWeek[day];
	}

}