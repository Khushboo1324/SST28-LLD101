# Ex8 — ISP: Student Club Management Admin Tools

## 1. Context
Clubs have different roles: treasurer, secretary, event lead. The admin tool interface currently combines everything.

## 2. Current behavior
- One interface `ClubAdminTools` includes finance, minutes, and event operations
- Each role tool implements methods it does not use (dummy / exceptions)
- `ClubConsole` calls only the relevant subset per role

## 3. What’s wrong (at least 5 issues)
1. Fat interface forces irrelevant methods.
2. Dummy implementations cause hidden failures later.
3. Clients depend on methods they don’t need.
4. New role tools become harder to implement safely.
5. Capabilities are unclear.

## 4. Your task
- Split interface into smaller role/capability interfaces.
- Ensure each tool depends only on the methods it uses.
- Preserve output.

## 5. Constraints
- Preserve output and command order.
- Keep class names unchanged.

## 6. Acceptance criteria
- No dummy/no-op implementations for irrelevant methods.
- `ClubConsole` depends on minimal interfaces.

## 7. How to run
```bash
cd SOLID/Ex8/src
javac *.java
java Main
```

## 8. Sample output
```text
=== Club Admin ===
Ledger: +5000 (sponsor)
Minutes added: "Meeting at 5pm"
Event created: HackNight (budget=2000)
Summary: ledgerBalance=5000, minutes=1, events=1
```

## 9. Hints (OOP-only)
- Identify client groups: finance client, minutes client, events client.
- Split by what callers actually need.

## 10. Stretch goals
- Add “publicity lead” without implementing finance methods.

Answer:
initially , the `ClubAdminTools` interface is a fat interface that includes methods for finance, minutes, and event operations. This violates the Interface Segregation Principle (ISP) because clients (like `ClubConsole`) are forced to depend on methods they do not use. To refactor this, we can split the `ClubAdminTools` interface into smaller, more specific interfaces that correspond to the different roles in the club.
like in TreasureTool class which is implements all the methods but  3 are dummies like irrelavent to the role of Treasurer, similarly in SectaryTool 4 dummuies The secretary only takes notes — yet has to fake all financial and event methods. and EventLeadTool 3 dummies classes . ClubConsole could legally call treasurer.createEvent(...) or secretary.addIncome(...) — the compiler won't stop it. It would silently do nothing (dummy), which is dangerous.

Break ClubAdminTools into 3 small, role-based interfaces:
like FinanceTools ,MinutesTools, EventTools. Each role tool implements only the relevant interface(s). For example:
```java
interface FinanceTools {
    void addIncome(int amount);
    void addExpense(int amount);
}    
interface MinutesTools {
    void addMinutes(String minutes);
}
interface EventTools { 
    void createEvent(String name, int budget);
}