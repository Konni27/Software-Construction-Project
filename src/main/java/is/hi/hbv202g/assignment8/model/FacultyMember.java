package is.hi.hbv202g.assignment8.model;

public class FacultyMember extends User {
    private String department;

    /**
     * Faculty Member.
     */
    public FacultyMember(String name, String department) {
        super(name);
        this.department = department;
    }

    /**
     * Get department.
     */
    public String getDepartment(){
        return department;
    }

    /**
     * Set department.
     */
    public void setDepartment(String department){
        this.department = department;
    }
}
