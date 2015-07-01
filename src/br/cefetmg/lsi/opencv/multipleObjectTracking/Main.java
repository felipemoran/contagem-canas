package br.cefetmg.lsi.opencv.multipleObjectTracking;

import br.cefetmg.lsi.opencv.multipleObjectTracking.processing.MultipleObjectTracking;

/**
 * Created by felipemoran on 6/23/15.
 */
public class Main {
    public static void main(String[] args) {
        MultipleObjectTracking coisa = new MultipleObjectTracking();
        try {
            coisa.startTracking();
        } catch(Exception erro) {
            System.out.println(erro.getMessage());
        }
    }
}
