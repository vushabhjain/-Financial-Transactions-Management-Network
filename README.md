# -Financial-Transactions-Management-Network
The Financial Transactions Management Network is a Java-based banking management system built using the JDBC API. This project allows users to manage their financial activities securely and efficiently. The system includes essential features such as user registration, login, account creation, debit/credit money, money transfers, balance checks, and ensures data consistency throughout all operations.

Features
User Registration: New users can register by providing necessary details.
Login: Registered users can log in using their credentials.
Account Creation: Users can create new accounts linked to their profile.
Debit Money: Users can securely withdraw money from their account.
Credit Money: Users can add money to their account.
Money Transfer: Users can transfer funds between accounts.
Balance Check: Users can check the balance of their accounts.
Data Consistency: All operations are ensured to maintain data integrity and consistency across the system.
Technology Stack
Programming Language: Java
Database: MySQL (or any other supported by JDBC)
JDBC API: Used for database connectivity and operations
IDE: [IntelliJ IDEA, Eclipse, or your preferred IDE]
Version Control: Git
Setup and Installation
Prerequisites
Java Development Kit (JDK) 8 or higher
MySQL database (or compatible JDBC database)
JDBC Driver for MySQL
Git installed on your machine
Steps
Clone the Repository

bash
Copy code
git clone https://github.com/yourusername/Financial-Transactions-Management-Network.git
cd Financial-Transactions-Management-Network
Database Setup

Create a new MySQL database using the following command:
sql
Copy code
CREATE DATABASE banking_management;
Import the database schema provided in the schema.sql file:
bash
Copy code
mysql -u your_username -p banking_management < schema.sql
Configure Database Connection

Open the project in your IDE.
Modify the DBConnection.java file to include your database credentials (username, password, etc.).
Build and Run the Project

Compile and run the project using your IDE or via the command line:
bash
Copy code
javac Main.java
java Main
Usage
Register a new user.
Log in using your credentials.
Create a bank account.
Use the options to debit/credit money, transfer funds, and check your balance.
Project Structure
bash
Copy code
├── src
│   ├── Main.java                   # Entry point of the application
│   ├── UserRegistration.java        # Handles user registration logic
│   ├── UserLogin.java               # Handles user login logic
│   ├── AccountCreation.java         # Handles account creation logic
│   ├── Transaction.java             # Handles debit, credit, and money transfer logic
│   ├── BalanceCheck.java            # Handles balance check logic
│   ├── DBConnection.java            # Manages database connection
├── schema.sql                       # SQL script to set up the database schema
└── README.md                        # Project documentation
License
This project is licensed under the MIT License - see the LICENSE file for details.

Contact
If you have any questions or suggestions, feel free to reach out:

LinkedIn: (https://www.linkedin.com/in/vrushabh-awathankar-jain-148b95226/)

Email: vrushabhawathankar@gmail.com
