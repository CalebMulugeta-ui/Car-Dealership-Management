AutoPark GUI Project
A simple Java GUI project for managing and visualizing parking operations. The project is built in IntelliJ IDEA using pure Java and provides an interactive interface for simulating parking lot management.
✨ Features
Add and remove vehicles
Display current parking occupancy
Track total parked vehicles and available spots
Basic input validation
In-memory data storage (no external database)
🧱 Project Structure
AutoPark-GUI-Project/
├─ .idea/                     # IntelliJ project config
├─ src/                       # Java source files (GUI + logic)
├─ .gitignore
└─ AutoPark GUI Project.iml   # IntelliJ module file
▶️ Getting Started
Prerequisites
Java JDK 17+
IntelliJ IDEA (Community or Ultimate Edition)
Run in IntelliJ
Clone this repository:
git clone https://github.com/CalebMulugeta-ui/AutoPark-GUI-Project.git
Open the project folder in IntelliJ IDEA.
Locate the class with the public static void main(String[] args) method.
Right-click the file → Run.
Run via Command Line
cd AutoPark-GUI-Project/src
javac path/to/MainClass.java
java path.to.MainClass
(Replace MainClass with the name of your main class.)
🖥️ Tech Stack
Language: Java
IDE: IntelliJ IDEA
GUI Toolkit: Swing or JavaFX (depending on implementation)
If using JavaFX, make sure your JavaFX SDK is configured under
File → Project Structure → Libraries and that you include the following VM options when running:
--module-path "path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml
🔧 Configuration
Default lot size or rules can be updated within source files under src/.
IntelliJ automatically manages compilation and run configurations.
📦 Creating a Runnable JAR
Go to File → Project Structure → Artifacts → + → JAR → From modules with dependencies
Select your main class.
Build the artifact:
Build → Build Artifacts → Build
Run the generated JAR:
java -jar out/artifacts/AutoPark_GUI_Project/AutoPark_GUI_Project.jar
