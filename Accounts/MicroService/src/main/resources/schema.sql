CREATE TABLE IF NOT EXISTS customer(
   customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(255) NOT NULL,
    created_at date ,
    created_by varchar(50) ,
    updated_at date ,
    updated_by varchar(50)
    
);

---- Insert some sample data
--INSERT INTO users (username, email, password) VALUES
--('john_doe', 'john@example.com', 'password123'),
--('jane_smith', 'jane@example.com', 'letmein');


CREATE TABLE IF NOT EXISTS accounts (
   customer_id INT NOT NULL,
   account_number int  PRIMARY KEY,
    account_type VARCHAR(255) NOT NULL,
    branch_address VARCHAR(255) NOT NULL,
    created_at date ,
    created_by varchar(50) ,
    updated_at date ,
    updated_by varchar(50)
    
);