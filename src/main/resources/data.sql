CREATE TABLE IF NOT EXISTS question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(255) NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_option CHAR(1) NOT NULL
);

INSERT INTO question (question_text, option_a, option_b, option_c, option_d, correct_option)
VALUES 
('What is 2 + 2?', '3', '4', '5', '6', 'B'),
('What is the capital of France?', 'Berlin', 'Madrid', 'Paris', 'Rome', 'C'),
('Which language runs on the JVM?', 'C++', 'Python', 'Java', 'Ruby', 'C'),
('Which indian airport is the first airport to operate completely on solar panel','Cochin','Patna','Mumbai','Raipur','A');
