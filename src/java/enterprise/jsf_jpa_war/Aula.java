/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.jsf_jpa_war;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 *
 * @author valeriotanferna
 */
public class Aula {
    
    private long id;
    public String nomeaula; // nome dell'aula
    public int posti; // posti a sedere nell'aula
    private boolean occupata; // se l'aula e' occupata
    private boolean prof; // se occupata dal professore (campo valido solo se la variabile occupata è true)
    private String mail; // la mail di chi ha prenotato

    /**
         * @param id
	 * @param nomeaula
	 * @param posti
	 * @param occupata
	 * @param prof
	 * @param mail
	 */
    public Aula(long id, String nomeaula, int posti, boolean occupata, boolean prof, String mail){
        this.id = id;
        this.nomeaula = nomeaula;
        this.posti = posti;
        this.occupata = occupata;
        this.prof = prof;
        this.mail = mail;
    }
    
    public long getId(){
        return id;
    }
    
    public String getNomeaula(){
        return nomeaula;
    }
    
    public int getPosti(){
        return posti;
    }
    
    public boolean getOccupata(){
        return this.occupata;
    }
    
    public boolean getProf(){
        return prof;
    }
    
    public String getMail(){
        return mail;
    }
    
    public boolean prenota(String nomeaula, boolean prof, String mail){ //mi serve la mail del prof per discriminare
        // TODO utente vuole prenotare quella particolare aula
        // serve funzione per cercarla
        if(!this.occupata){ // se non è occupata
            this.occupata = true;
            this.prof = true;
            this.mail = mail;
            return true;
        }
        else if(this.occupata && !this.prof){ // se occupata, ma non da un professore, il professore la prenota
            this.occupata = true;
            this.prof = true;
            this.mail = mail;
            return true;
        }
        else return false;
    }
    
    public boolean cancella(String nomeaula, boolean prof, String mail){
        if(this.mail == mail && this.occupata ){ // se l'aula è prenotata da me
            this.occupata = false;
            this.mail = null;
            return true;
        }
        return false;
    }
}
