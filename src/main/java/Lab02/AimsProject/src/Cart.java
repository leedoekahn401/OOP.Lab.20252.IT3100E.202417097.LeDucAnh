package Lab02.AimsProject.src;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    public Cart(DigitalVideoDisc... items){
        if(items.length > MAX_NUMBERS_ORDERED){
            throw new IllegalArgumentException("Maximum number of items is " + MAX_NUMBERS_ORDERED);
        }
        for(DigitalVideoDisc item : items){
            this.itemsOrdered[qtyOrdered] = item;
            this.qtyOrdered++;
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc item){
        if(qtyOrdered >= MAX_NUMBERS_ORDERED){
            System.err.println("Cart is full!");
            return;
        }
        itemsOrdered[qtyOrdered] = item;
        qtyOrdered++;
        System.out.println("The disc has been added to the cart!");

        if(qtyOrdered == MAX_NUMBERS_ORDERED){
            System.out.println("Cart is full!");
        }else if(qtyOrdered == MAX_NUMBERS_ORDERED - 1){
            System.out.println("Cart is almost full!");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc){
        boolean found = false;
        for(int i =0; i< itemsOrdered.length; i++){
            if(itemsOrdered[i].equals(disc)){
                found = true;
                for(int j = i+1; j < itemsOrdered.length; j++){
                    itemsOrdered[j] = itemsOrdered[j-1];
                }
            }
        }
        if(found){
            qtyOrdered--;
            System.out.println("The disc has been removed from the cart!");

        }else{
            System.out.println("The disc not found!");
        }
    }

    public double totalCost(){
        double total = 0;
        for(DigitalVideoDisc item : itemsOrdered){
            total += item.getCost();
        }
        return total;
    }

}
