package Process;

public class Organization {
    private String ID;
    private String Name;
    private String Province;
    private String District;
    private String Town;
    private String Street;
    private String Note;
    private int avaiScheds;
    private int totalScheds;

    public Organization() {
    }

    public Organization(String ID, String Name, String Province, String District, String Town, String Street, String Note,
                        int avaiScheds, int totalScheds) {
        this.ID = ID;
        this.Name = Name;
        this.Province = Province;
        this.District = District;
        this.Town = Town;
        this.Street = Street;
        this.Note = Note;
        this.avaiScheds = avaiScheds;
        this.totalScheds = totalScheds;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String Town) {
        this.Town = Town;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public int getAvaiScheds() {
        return avaiScheds;
    }

    public void setAvaiScheds(int avaiScheds) {
        this.avaiScheds = avaiScheds;
    }

    public int getTotalScheds() {
        return totalScheds;
    }

    public void setTotalScheds(int totalScheds) {
        this.totalScheds = totalScheds;
    }
}