ALTER TABLE users
ADD COLUMN manager_id INT DEFAULT NULL,
ADD CONSTRAINT fk_manager
    FOREIGN KEY (manager_id)
    REFERENCES users(id) ON DELETE SET NULL;