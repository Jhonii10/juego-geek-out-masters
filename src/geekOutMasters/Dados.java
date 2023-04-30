package geekOutMasters;

import java.util.Random;

/***
 * this class was created by
 * @autor jhoni ipia jhonier.ipia@correounivalle.edu.co
 * @version v.1.0.0
 * @date: april 29, 2023
 */

/**
 * class Dados this class generate a ramdom value between 1 and 6
 */
public class Dados {

    private int cara , caraOpuesta;

    public int getCara() {
        return cara;
    }

    public void setCara(int newCara) {
        this.cara = cara;
    }


    public int newCara(){
        Random aletorio = new Random();
        cara =aletorio.nextInt(6)+1;
        setCara(cara);
        return cara;
    }

    public int getCaraOpuesta() {

        /* switch (cara){
            case 1: caraOpuesta = 2;
                break;
            case 2: caraOpuesta = 1;
                break;
            case 3: caraOpuesta = 5;
                break;
            case 4: caraOpuesta = 6;
                break;
            case 5: caraOpuesta = 3;
                break;
            case 6: caraOpuesta = 4;
                break;
        } */

        caraOpuesta = (cara + 3 ) % 6 + 1;
        setCara(caraOpuesta);
        return caraOpuesta;
    }

    public void setCaraOpuesta(int caraOpuesta) {
        this.caraOpuesta = caraOpuesta;
    }
}
