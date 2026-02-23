import java.util.List;

public interface StudentRepository {
    public void save(StudentRecord r);
        public int count();
        public List<StudentRecord> all();
}
