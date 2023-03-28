package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Libretto {

	private List<Voto> voti;

	public Libretto() {
		this.voti=new ArrayList<Voto>();
	}
	
	/**
	 * Aggiungi un nuovo Voto al Libretto
	 * @param v il Voto da aggiungere
	 * @return true
	 */
	public boolean add(Voto v) {
		if(this.esisteVotoConflitto(v) || this.esisteVotoDuplicato(v))
          throw new IllegalArgumentException("Voto errato: "+v);
		return this.voti.add(v);
	} 
	
	public void stampa() {
		for(Voto v : this.voti) {
			System.out.println(v);
		}
	}
	
	public void stampaPuntiUguali(int valore) {
		for(Voto v : this.voti) {
			if(v.getPunti()==valore)
				System.out.println(v);
		}
	}
	
	public Voto cercaVotoPerNome(String corso) {
		for(Voto v:this.voti) {
//			if(v.getCorso().compareTo(corso)==0)
			if(v.getCorso().equals(corso))
				return v;
		}
		return null;
//		throw new RuntimeException("Voto non trovato");
	}
	
	public boolean esisteVotoDuplicato(Voto nuovo) {
		for(Voto v:voti) {
			if(v.isDuplicato(nuovo))
				return true;
		}
		return false;
	}
	
	public boolean esisteVotoConflitto(Voto nuovo) {
		for(Voto v:voti) {
			if(v.isConflitto(nuovo))
				return true;
		}
		return false;
	}
	
	/**
	 * metodo 'factory' per creare un nuovo libretto con i voti migliorati
	 * @return
	 */
	public Libretto librettoMigliorato() {
		Libretto migliore=new Libretto();
//      migliore.voti=new ArrayList<>();
		for(Voto v:this.voti) {
        	migliore.voti.add(v.clone());
//        	migliore.voti.add(new Voto(v));
        }
		for(Voto v:migliore.voti) {
			if(v.getPunti()>=18 && v.getPunti()<24) {
				v.setPunti(v.getPunti()+1);
			}else
				if(v.getPunti()>=24 && v.getPunti()<29) {
					v.setPunti(v.getPunti()+2);
				}else
					if(v.getPunti()==29) {
						v.setPunti(v.getPunti()+1);
					}	
		}
		return migliore;
	}
	
// non si modifica(aggiungere o eliminare elementi) una lista mentre si sta iterando su di essa!
	public void eliminaVotiInferiori(int punti) {
		List<Voto> daCancellare=new ArrayList<Voto>();
		for(Voto v:this.voti) {
			if(v.getPunti()<punti) {
				daCancellare.add(v);
			}
		}
//      this.voti.removeAll(daCancellare);
		for(Voto v1:daCancellare) {
			this.voti.remove(v1);
		}
	}
	
	public Libretto librettoOrdinatoAlfabeticamente() {
		Libretto ordinato=new Libretto();
		ordinato.voti=new ArrayList<>(this.voti);
		ordinato.voti.sort(new ComparatorByName());
		return ordinato;
	}
	
	public Libretto librettoOrdinatoPerVoto() {
		Libretto ordinato=new Libretto();
		ordinato.voti=new ArrayList<>(this.voti);
		ordinato.voti.sort(new Comparator<Voto>() {
			@Override
			public int compare(Voto o1, Voto o2) {
				// TODO Auto-generated method stub
				return o2.getPunti()-o1.getPunti();
			}
			});
		return ordinato;
	}
}
