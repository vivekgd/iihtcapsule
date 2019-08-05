Steps to Run The Application:

Pre-Requisite:
1. MySQL Server should be running in port 3306
2. Create Capsule Database
3. Import capsule-init-database-script.sql (found in resource folder of project)

Building & Running the Application:
1. Build Application Using Jenkins Job / maven clean install
2. Trigger the command java -jar taskmanager-1.0-SNAPSHOT.jar(Jar will be available in Target Directory)

Note: UI & Service Applications are integrated in same project. npm goal is added as execution step and UI build files will be compied in to static folder. Which will be included in the Build Jar


Application will be running in Port 8080. Access Application from http://localhost:8080