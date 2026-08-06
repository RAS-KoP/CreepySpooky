package io.github.ras_kop.creepyspooky.energy;


public class KekkaiSystemComponent {
    
    private int kekkai_tier;
    private int yoryoku_amount;
    private int yoryoku_capacity;

    public KekkaiSystemComponent(int tier, int capacity){
        this.kekkai_tier = tier;
        this.yoryoku_capacity = capacity;
    }


    public int getTier(){
        return kekkai_tier;
    }


    public int getEnergy(){
        return yoryoku_amount;
    }


    public int getCapacity(){
        return yoryoku_capacity;
    }


    public void setTier(int tier){
        this.kekkai_tier = tier;
    }


    public void setCapacity(int capacity){
        this.yoryoku_capacity = capacity;
    }


    public void setYoryoku(int energy){
        this.yoryoku_amount = energy;
    }


    public int receiveYoryoku(int amount){
        //容量に追加できたエネルギー量を返す
        int received = Math.min(yoryoku_capacity - yoryoku_amount, amount);
        yoryoku_amount += received;
        return received;
    }


    public int extractYoryoku(int amount){
        //出力できたエネルギー量を返す
        int extracted = Math.min(yoryoku_amount, amount);
        yoryoku_amount -= extracted;
        return extracted;
    }
}
