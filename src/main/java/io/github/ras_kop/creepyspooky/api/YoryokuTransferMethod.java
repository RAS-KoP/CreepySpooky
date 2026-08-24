package io.github.ras_kop.creepyspooky.api;

import io.github.ras_kop.creepyspooky.energy.YoryokuEnergyComponent;

public class YoryokuTransferMethod {
    
    public static boolean Transport(
        YoryokuEnergyComponent out_target,
        YoryokuEnergyComponent in_target,
        int speed
    ){
        int out_yoryoku = out_target.extractYoryoku(speed);
        int over_yoryoku = in_target.receiveYoryoku(out_yoryoku);
        out_target.receiveYoryoku(over_yoryoku);

        if(out_yoryoku != 0 && over_yoryoku == 0){
            return true;
        }
        return false;
    }
}
