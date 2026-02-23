
public class Printing {
    public void sucess(String id , StudentRecord rec,int count){
        System.out.println("OK: created student " + id);
        System.out.println("Saved. Total students: " + count);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
