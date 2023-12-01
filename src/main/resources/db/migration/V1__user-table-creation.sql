CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    email_id VARCHAR(100) NOT NULL UNIQUE,
    work_hour_start TIME DEFAULT '09:00:00',
    work_hour_end TIME DEFAULT '17:00:00',
    password text not null ,
    CHECK (work_hour_start < work_hour_end)
);