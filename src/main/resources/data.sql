CREATE TABLE IF NOT EXISTS question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(255) NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_option CHAR(100) NOT NULL
);

INSERT INTO question (question_text, option_a, option_b, option_c, option_d, correct_option)
VALUES 
('What is 2 + 2?', '3', '4', '5', '6', 'optionB'),
('What is the capital of France?', 'Berlin', 'Madrid', 'Paris', 'Rome', 'optionC'),
('Which language runs on the JVM?', 'C++', 'Python', 'Java', 'Ruby', 'C'),
('Which indian airport is the first airport to operate completely on solar panel','Cochin','Patna','Mumbai','Raipur','optionA'),
('What is the largest planet in our solar system?', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'optionC'),
('Who wrote "To Kill a Mockingbird"?', 'Harper Lee', 'Mark Twain', 'Ernest Hemingway', 'F. Scott Fitzgerald', 'optionA'),
('What is the boiling point of water?', '90°C', '100°C', '110°C', '120°C', 'optionB'),
('Which element has the chemical symbol "O"?', 'Oxygen', 'Gold', 'Silver', 'Iron', 'optionA'),
('What is the capital of Japan?', 'Seoul', 'Beijing', 'Tokyo', 'Bangkok', 'optionC'),
('Who painted the Mona Lisa?', 'Vincent van Gogh', 'Pablo Picasso', 'Leonardo da Vinci', 'Claude Monet', 'optionC');

