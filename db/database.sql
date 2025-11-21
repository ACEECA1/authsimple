
-- @block
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    displayname VARCHAR(100) NOT NULL,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL
);

--@block
SELECT * FROM Users;

-- @block
INSERT INTO Users (displayname, Username, Email, Password) VALUES
('John Doe', 'johndoe', 'john.doe@example.com', 'password123'),
('Jane Smith', 'janesmith', 'jane.smith@example.com', 'password456'),
('Alice Johnson', 'alicej', 'alice.johnson@example.com', 'password789');
