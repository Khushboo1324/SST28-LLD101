# Ex7 — ISP: Smart Classroom Devices Interface

## 1. Context
A smart classroom controller manages devices: projector, lights, AC, attendance scanner.

## 2. Current behavior
- There is one large interface `SmartClassroomDevice` containing many methods
- Each device implements methods it does not need using dummy logic
- Controller calls only some methods depending on device type

## 3. What’s wrong (at least 5 issues)
1. Fat interface forces irrelevant methods on devices.
2. Dummy implementations hide bugs and create misleading behavior.
3. Controller is tempted to call methods that some devices don’t meaningfully support.
4. Adding a new device forces implementing many unrelated methods.
5. Device capabilities are unclear; interface does not model reality.

## 4. Your task
- Split the fat interface into smaller capability-based interfaces.
- Update controller and devices to depend only on what they use.
- Preserve console output.

## 5. Constraints
- Preserve output for `Main`.
- Keep device class names unchanged.
- No external libs.

## 6. Acceptance criteria
- No device implements methods irrelevant to it.
- Controller depends only on specific capability interfaces.

## 7. How to run
```bash
cd SOLID/Ex7/src
javac *.java
java Main
```

## 8. Sample output
```text
=== Smart Classroom ===
Projector ON (HDMI-1)
Lights set to 60%
AC set to 24C
Attendance scanned: present=3
Shutdown sequence:
Projector OFF
Lights OFF
AC OFF
```

## 9. Hints (OOP-only)
- Capabilities: power control, brightness control, temperature control, scanning.
- Keep composition: registry can return devices by capability rather than by concrete class.

## 10. Stretch goals
- Add a “smart board” device without implementing unrelated methods.


Answer :
7.Initially all every device is forced to implement ALL 6 methods, even if they make no sense for that device. In the SmartClassroomDevice
Interface , Projector class which implements SmartClassrommDevice 4 out of 6 methods are dummies A projector cannot set brightness or temperature or scan attendance , but the interface forces it to pretend it can, LightPanel class which implements SmartClassrommDevice 3 out of 6 methods are dummies ,Airconditior 3 are dummies and similarly with AttendanceScanner The scanner only scans  yet it has to fake 5 other methods! The ClassroomController gets back a SmartClassroomDevice — which means legally it could call pj.setTemperatureC(24) on a projector and the code would compile fine! It just silently does nothing. This is dangerous and misleading.

The fix is to break SmartClassroomDevice into small, focused capability interfaces, one per ability:
Switchable       → powerOn(), powerOff()
Dimmable         → setBrightness(int pct)
TemperatureCtrl  → setTemperatureC(int c)
InputConnectable → connectInput(String port)
AttendanceSensor → scanAttendance()
Then each device class implements only the interfaces relevant to it:
Projector        → Switchable, InputConnectable
LightPanel       → Switchable, Dimmable
AirConditioner   → Switchable, TemperatureCtrl
AttendanceScanner → Switchable, AttendanceSensor
The ClassroomController can depend on the specific capabilities it needs for each device, rather than the whole fat interface. This way, there are no dummy methods, and the design accurately models the real capabilities of each device. Adding a new device is also easier, as it only needs to implement the relevant interfaces without being forced to provide meaningless implementations.