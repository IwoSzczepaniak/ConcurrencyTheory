package old_ideas.zad6;

public class DiningRoom {
    private int counter = 0;
    private int seats_available;


    public DiningRoom(int seats_available){
        this.seats_available = seats_available;
    }

    public synchronized void seatInTable(){
        counter++;
    }

    public synchronized void standOutOfTable(){
        counter--;
    }

    public synchronized boolean canSbSeat(){
        return this.counter < this.seats_available;
    }
}
