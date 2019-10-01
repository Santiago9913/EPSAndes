package it.controller;

import java.util.Scanner;

import it.view.View;

public class Controller {

	private View view; 

	public Controller() {
		view = new View();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false; 

		while(!fin) {
			view.printMenu();

			int option = sc.nextInt();

			switch(option) {

			case 0: 
				break;



			}
		}
	}

}
