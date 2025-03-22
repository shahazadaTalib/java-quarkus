package eagel.tailung.exception;

public class BussinessException extends Exception{
    int status;
    public BussinessException(){
    }
    public BussinessException(int status,String message){
        super(message);
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
