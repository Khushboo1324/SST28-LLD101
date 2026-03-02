import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket original = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + original);

        IncidentTicket assigned = service.assign(original, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);

        System.out.println("\nAfter assign (new object): " + assigned);
        System.out.println("After escalate (new object): " + escalated);
        System.out.println("Original still unchanged  : " + original);

        // These lines should not compile after immutability refactor:
        // original.setPriority("CRITICAL");
        // original.setAssigneeEmail("x@y.com");

        // External mutation should not affect ticket tags
        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTags list is immutable from outside.");
        }
        System.out.println("After outside attempt      : " + escalated);

        System.out.println("\nObject identities:");
        System.out.println("original : " + System.identityHashCode(original));
        System.out.println("assigned : " + System.identityHashCode(assigned));
        System.out.println("escalated: " + System.identityHashCode(escalated));
    }
}
