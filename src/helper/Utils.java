package helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {
	static SimpleDateFormat sdf = new SimpleDateFormat("ddd/MM/yyyy");
	static NumberFormat nf = new DecimalFormat("R$ #,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

	public static String dateParaString(Date data) {
		return Utils.sdf.format(data);
	}

	public static String doubleParaString(Double valor) {
		return Utils.nf.format(valor);

	}

	public static Double StringParaDouble(String valor) {
		try {
			return (Double) Utils.nf.parse(valor);
		} catch (ParseException e) {
			return null;

		}
	}

	public static void pausar(int segundos) {
		try {
			TimeUnit.SECONDS.sleep(segundos);
			// pausar mensagem para que o usuario possa ler
		} catch (InterruptedException e) {
			System.out.println("Erro ao pausar por " + segundos + " segundos.");
		}
	}
}
