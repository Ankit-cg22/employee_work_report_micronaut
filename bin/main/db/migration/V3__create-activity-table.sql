CREATE TABLE activity_table(
    id INT PRIMARY KEY AUTO_INCREMENT,
    work_category ENUM('DEV', 'LEARN', 'DOCS', 'INTERVIEW', 'MEET', 'OTHERS') NOT NULL,
    hours INT NOT NULL CHECK (hours >= 0 AND hours <= 24),
    minutes INT NOT NULL CHECK (minutes >= 0 AND minutes <= 60),
    description TEXT ,
    form_id INT NOT NULL ,
    FOREIGN KEY (form_id) REFERENCES form_table(id) ON DELETE CASCADE
);