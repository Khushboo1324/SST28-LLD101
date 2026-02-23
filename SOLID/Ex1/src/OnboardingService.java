import java.util.*;

public class OnboardingService {
    private final StudentRepository studentRepository;

    public OnboardingService(StudentRepository studentRepository) { this.studentRepository = studentRepository; }
    
    private final StudentInputParser parser = new StudentInputParser();
    private final InputValidation validation = new InputValidation();
    private final Printing printing = new Printing();
    
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);
        // parser from StudentInputParser
        Map<String , String > kv = parser.parse(raw);

        // validation from InputValidation
        List<String> errors = validation.validate(kv);
        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");
        
        // ID generate from IdUtil
        String id = IdUtil.nextStudentId(studentRepository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        //Saving
        studentRepository.save(rec);

        // Printing by Priniting
        printing.sucess(id , rec, studentRepository.count());
    }
}
