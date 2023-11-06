package old_ideas.zad5;

public class Referee {
    private int counter = 0;
    private int seats_available;

    public Referee(int seats_available){
        this.seats_available = seats_available;
    }


    public synchronized void seatInTable(){
        counter++;
        if (counter>seats_available) System.out.println("ZA DUŻO");
    }

    public synchronized void standOutOfTable(){
        counter--;
    }

    public synchronized boolean canSbSeat(){
        return this.counter < this.seats_available;
    }
}
