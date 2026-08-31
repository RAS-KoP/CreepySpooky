package io.github.ras_kop.creepyspooky.api;


public class YoryokuTransferMethod {
    
    public static boolean Transport(
        IYoryokuHolder out_target,
        IYoryokuHolder in_target,
        int speed
    ){
        int out_yoryoku = out_target.extractYoryoku(speed);
        int over_yoryoku = out_yoryoku - in_target.receiveYoryoku(out_yoryoku);
        out_target.receiveYoryoku(over_yoryoku);

        if(out_yoryoku != 0 && over_yoryoku == 0){
            return false;
        }
        return true;
    }
}
