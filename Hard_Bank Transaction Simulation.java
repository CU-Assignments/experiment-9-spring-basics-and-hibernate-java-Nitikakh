#include <iostream>
#include <stdexcept>
using namespace std;

class Account {
public:
    string holderName;
    double balance;

    Account(string name, double initialBalance) {
        holderName = name;
        balance = initialBalance;
    }

    void withdraw(double amount) {
        if (amount > balance) {
            throw runtime_error("Insufficient balance for withdrawal");
        }
        balance -= amount;
    }

    void deposit(double amount) {
        balance += amount;
    }

    void showBalance() {
        cout << holderName << "'s Balance: ₹" << balance << endl;
    }
};

void transferMoney(Account &from, Account &to, double amount) {
    try {
        cout << "\nTransferring ₹" << amount << " from " << from.holderName 
             << " to " << to.holderName << "...\n";

        from.withdraw(amount);  // deduct from sender
        to.deposit(amount);     // add to receiver

        cout << "Transaction successful!\n";
    } catch (const exception &e) {
        cout << "Transaction failed: " << e.what() << endl;
    }
}

int main() {
    Account raj("Raj", 7000);
    Account simran("Simran", 3000);

    raj.showBalance();
    simran.showBalance();

    transferMoney(raj, simran, 2000);  // should succeed

    raj.showBalance();
    simran.showBalance();

    transferMoney(raj, simran, 6000);  // should fail

    raj.showBalance();
    simran.showBalance();

    return 0;
}
