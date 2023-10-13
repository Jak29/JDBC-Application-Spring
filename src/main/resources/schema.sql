CREATE TABLE salons (
    salon_id INT PRIMARY KEY,
    salon_name VARCHAR(255) NOT NULL,
    salon_address VARCHAR(255) NOT NULL,
    salon_phone FLOAT NOT NULL,
    salon_days INT NOT NULL
);

CREATE TABLE stylists (
    stylist_id INT PRIMARY KEY,
    stylist_name VARCHAR(255) NOT NULL,
    stylist_phone INT NOT NULL,
    stylist_salary INT NOT NULL,
    salon_id INT,
    FOREIGN KEY(salon_id) REFERENCES salons(salon_id) on delete cascade
);
