CREATE TABLE form_table(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL ,
    date DATE NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    submitted BOOLEAN DEFAULT false ,
    CONSTRAINT unique_double UNIQUE (user_id, date)
);
