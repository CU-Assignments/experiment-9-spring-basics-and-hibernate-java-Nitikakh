#include <iostream>
#include <mysql_driver.h>
#include <mysql_connection.h>
#include <cppconn/statement.h>
#include <cppconn/prepared_statement.h>
#include <cppconn/resultset.h>

using namespace std;

int main() {
    try {
        // Connect to MySQL server
        sql::mysql::MySQL_Driver *driver;
        sql::Connection *conn;
        sql::Statement *stmt;
        sql::PreparedStatement *prep;
        sql::ResultSet *res;

        driver = sql::mysql::get_mysql_driver_instance();
        conn = driver->connect("tcp://127.0.0.1:3306", "root", "your_password");
        conn->setSchema("college");

        stmt = conn->createStatement();
        stmt->execute("CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, name VARCHAR(100), age INT)");

        // INSERT
        prep = conn->prepareStatement("INSERT INTO students(id, name, age) VALUES (?, ?, ?)");
        prep->setInt(1, 101);
        prep->setString(2, "Neha Sharma");
        prep->setInt(3, 21);
        prep->execute();
        cout << "Student inserted." << endl;

        // READ
        res = stmt->executeQuery("SELECT * FROM students");
        while (res->next()) {
            cout << "ID: " << res->getInt("id") 
                 << ", Name: " << res->getString("name") 
                 << ", Age: " << res->getInt("age") << endl;
        }

        // UPDATE
        stmt->execute("UPDATE students SET age = 22 WHERE id = 101");
        cout << "Student age updated." << endl;

        // DELETE
        stmt->execute("DELETE FROM students WHERE id = 101");
        cout << "Student deleted." << endl;

        // Cleanup
        delete res;
        delete prep;
        delete stmt;
        delete conn;

    } catch (sql::SQLException &e) {
        cout << "Error: " << e.what() << endl;
    }

    return 0;
}
