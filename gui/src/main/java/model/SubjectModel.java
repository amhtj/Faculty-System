package model;

public class SubjectModel {

    private int id;
    private String name;
    private int teacherId;
    private String groups;

    public int getId() {return id;}

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherId() { return teacherId; }

    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }

    public String getGroups() { return groups; }

    public void setGroups(String groups) { this.groups = groups; }
}
