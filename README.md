# MedicalProject
Medical Project for School Advanced Object Programming(PAO) laboratory.

This project provides a login/register for users. An user can be a doctor or a client. 
Both client and doctor has common fields when they sign up, so I created a class named User with that common fields and then Client's and Doctor's class inheritance from it.

For managing the user, client and doctor's abilities(for example, logging-in) I created DataUserCenter, DataClientCenter and DataDoctorCenter, in which I provided the most important functionalities.


Main class, in which I am using all queries, is called ClientsAndDoctorsStorageManipulator. Here I can register both as client or doctor. For registering, I check if the new user has already an account, even if it is a client or a doctor.

For storing data, I am using two types of files: JSON and CSV. 

For implementing the CSV files functionalities, first of all I created a Singleton class named FileManipulator. This class contains other 4 Singleton classes, names:
  - ClientFileReader
  - ClientFileWriter
  - DoctorFileReader
  - DoctorFileWriter
 

The FileManipulator's class provides help in reading and writing both doctors and client in local storage.
