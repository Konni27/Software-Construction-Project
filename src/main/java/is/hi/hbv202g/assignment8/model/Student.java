package is.hi.hbv202g.assignment8.model;

public class Student extends User{
    private boolean feePaid;

    /**
     * Student.
     */
    public Student(String name, boolean feePaid){
        super(name);
        this.feePaid = feePaid;

    }

    /**
     * Has Student paid fee.
     */
    public boolean isFeePaid(){
        return feePaid;
    }

    /**
     * Set fee Paid.
     */
    public void setFeePaid(boolean feePaid){
        this.feePaid = feePaid;
    }
}
