package br.com.techunion.util;

public class Crypt {

	public static String crypt(String text){
        int Iaux;
        String[] tabasc = new String[text.length() + 1];
        String strwork = "";
        String[] posw = new String[text.length() + 1];
        int AuxLen = text.length();

        Iaux = AuxLen - 1;
        while (Iaux >= 0) {
            posw[Iaux] = text.substring(Iaux, text.length()); // Carregando a matriz letra a letra
            tabasc[Iaux] = String.valueOf((((int) posw[Iaux].charAt(0)) + text.length()));
            tabasc[Iaux] = String.format("%03d", Integer.valueOf(tabasc[Iaux])).substring(0, 3);
            strwork = strwork + tabasc[Iaux];
            Iaux -= 1;
        }
        return strwork + String.format("%04d", text.length()).replace(" ", "0");
	}
	
	public static String decrypt(String text){
        int Iaux;
        String palavra = "";
        int STRpos;
        String STRPalavra;
        int wtam;

        Iaux = Integer.parseInt(text.substring(text.length() - 4, text.length()));
        wtam = Iaux;
        while (Iaux > 0) {
            STRpos = Integer.parseInt((text.substring(0, 3))) - wtam;
            STRPalavra = String.valueOf((char) STRpos);
            text = text.substring(3, text.length());
            palavra = STRPalavra + palavra;
            Iaux -= 1;
        }
        if (palavra.equals("")) {
            return "N/A";
        } else {
            return palavra;
        }

	}
}
