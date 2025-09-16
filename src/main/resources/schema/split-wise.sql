-- Users Table
CREATE TABLE Users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       phoneNumber VARCHAR(20) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL
);

-- Groups Table
CREATE TABLE Groups (
                        group_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description VARCHAR(255),
                        creationDate DATETIME NOT NULL,
                        created_user_id BIGINT NOT NULL,
                        FOREIGN KEY (created_user_id) REFERENCES Users(user_id),
                        INDEX idx_groups_created_user_id (created_user_id)
);

-- Expenses Table
CREATE TABLE Expenses (
                          expense_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description VARCHAR(255),
                          expenseType ENUM('expense','transaction') NOT NULL,
                          group_id BIGINT NOT NULL,
                          created_user_id BIGINT NOT NULL,
                          date DATETIME NOT NULL,
                          FOREIGN KEY (group_id) REFERENCES Groups(group_id),
                          FOREIGN KEY (created_user_id) REFERENCES Users(user_id),
                          INDEX idx_expenses_group_id (group_id),
                          INDEX idx_expenses_created_user_id (created_user_id)
);

-- UserExpense Table (Join table between Users and Expenses)
CREATE TABLE UserExpense (
                             user_id BIGINT NOT NULL,
                             expense_id BIGINT NOT NULL,
                             userExpenseType ENUM('owes','owned') NOT NULL,
                             amount DOUBLE NOT NULL,
                             PRIMARY KEY (user_id, expense_id),
                             FOREIGN KEY (user_id) REFERENCES Users(user_id),
                             FOREIGN KEY (expense_id) REFERENCES Expenses(expense_id),
                             INDEX idx_user_expense_user_id (user_id),
                             INDEX idx_user_expense_expense_id (expense_id)
);

-- users_groups Table (Join table between Users and Groups)
CREATE TABLE users_groups (
                              user_id BIGINT NOT NULL,
                              group_id BIGINT NOT NULL,
                              PRIMARY KEY (user_id, group_id),
                              FOREIGN KEY (user_id) REFERENCES Users(user_id),
                              FOREIGN KEY (group_id) REFERENCES Groups(group_id),
                              INDEX idx_users_groups_user_id (user_id),
                              INDEX idx_users_groups_group_id (group_id)
);