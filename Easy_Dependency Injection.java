#include <iostream>
#include <string>
using namespace std;

// Simple Course class with name and duration
class Course {
public:
    string name;
    int durationMonths;

    Course(string courseName, int months) {
        name = courseName;
        durationMonths = months;
    }
};

// Student class depends on Course (kind of like DI)
class Student {
public:
    string fullName;
    Course* enrolledCourse;

    Student(string name, Course* course) {
        fullName = name;
        enrolledCourse = course;
    }

    void showDetails() {
        cout << "Student Name: " << fullName << endl;
        cout << "Course: " << enrolledCourse->name 
             << " (" << enrolledCourse->durationMonths << " months)" << endl;
    }
};

int main() {
    Course csCourse("Software Engineering", 36);
    Student student("Aarav Mehta", &csCourse);

    student.showDetails();

    return 0;
}
