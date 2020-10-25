package br.senai.sc.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import br.senai.sc.models.Pessoa;

public class DateServices {

	public static String dateToStringDDMMyyyy(Date dataAtual) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(dataAtual);
	}
	
	public static Date stringToDate(String texto) throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return dateFormat.parse(texto);        
	}
	
	public static boolean isNotFuture(Date data) {		
		return data.before(Date.from(Instant.now())) || data.equals(Date.from(Instant.now()))?true:false;
	}
	
}
