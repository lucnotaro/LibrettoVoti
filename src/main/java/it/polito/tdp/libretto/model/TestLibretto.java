package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	public static void main(String[] args) {
		
		Libretto lib=new Libretto();
		
		lib.add(new Voto("Analisi 1", 29, LocalDate.of(2021, 2, 15)));
		lib.add(new Voto("Fisica 2", 25, LocalDate.of(2022, 6, 10)));
		lib.add(new Voto("Informatica", 27, LocalDate.of(2022, 12, 1)));
		lib.add(new Voto("Chimica", 21, LocalDate.of(2021, 7, 19)));
		
		
		lib.stampaPuntiUguali(25);
		Voto v=lib.cercaVotoPerNome("Analisi 1");
		System.out.println(v);
		v=lib.cercaVotoPerNome("Fisica 1");
		System.out.println(v);
		
		Voto a1bis=new Voto("Analisi 1", 29, LocalDate.of(2025, 2, 15));
		Voto a1ter=new Voto("Analisi 1", 30, LocalDate.of(2025, 2, 15));
		
		System.out.println(a1bis+" è duplicato : "+lib.esisteVotoDuplicato(a1bis));
		System.out.println(a1ter+" c'è conflitto : "+lib.esisteVotoConflitto(a1ter));
		
		try {
		lib.add(new Voto("Chimica", 21, LocalDate.of(2019, 7, 19)));
		}catch(IllegalArgumentException iae) {
			System.out.println("Errore nell'inserimento voto");
			System.out.println(iae.getMessage());
		}
		
		System.out.println("\nLibretto originale:");
		lib.stampa();
		Libretto migliore=lib.librettoMigliorato();
		System.out.println("\nLibretto migliorato:");
		migliore.stampa();
		System.out.println("\nLibretto originale:");
		lib.stampa();
		
    	lib.eliminaVotiInferiori(24);
		System.out.println("\nLibretto originale >24:");
		lib.stampa();
		
		System.out.println("\nOrdine alfabetico:");
		lib.librettoOrdinatoAlfabeticamente().stampa();
		
		System.out.println("\nOrdine di voto:");
		lib.librettoOrdinatoPerVoto().stampa();
	}

}
