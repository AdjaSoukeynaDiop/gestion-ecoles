-- Insertion des niveaux
INSERT INTO level (name) VALUES ('PRIMARY'), ('SECONDARY'), ('HIGH_SCHOOL');

-- Insertion des utilisateurs
INSERT INTO users (name, email, password, role, phone, created_at, favorites) VALUES
                                                                        ('Aminata Diallo', 'aminata.diallo@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'USER', '+221 77 123 45 67', '2024-01-15 10:30:00', '1,2'),
                                                                        ('Moussa Fall', 'moussa.fall@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'USER', '+221 78 234 56 78', '2024-02-20 14:15:00', '1'),
                                                                        ('Fatou Seck', 'fatou.seck@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye', 'ADMIN', '+221 77 345 67 89', '2024-01-01 08:00:00', '');

-- Insertion des écoles (sans coordinates_id)
INSERT INTO school (id, name, type, address
                   , region, students, rating, phone, email, established, facilities, images) VALUES
                                                                                                                          (1, 'Lycée Lamine Guèye', 'PUBLIC', 'Avenue Lamine Guèye, Dakar', 'Dakar', 1200, 4.2, '+221 33 821 23 45', 'lycee.lamine.gueye@education.sn', 1950,
                                                                                                                           'Bibliothèque,Laboratoire de sciences,Terrain de sport,Cantine',
                                                                                                                           'school1_front.jpg,school1_classroom.jpg,school1_lab.jpg'),
                                                                                                                          (2, 'École Primaire Sainte-Marie', 'PRIVATE', 'Rue 10, Sicap Liberté, Dakar', 'Dakar', 350, 4.5, '+221 33 824 67 89', 'contact@saintemarie-dakar.sn', 1965,
                                                                                                                           'Cour de récréation,Salle informatique,Infirmerie,Cantine',
                                                                                                                           'school2_entrance.jpg,school2_playground.jpg'),
                                                                                                                          (3, 'Collège Moderne de Pikine', 'PUBLIC', 'Quartier Pikine, Dakar', 'Dakar', 800, 3.8, '+221 33 834 12 67', 'college.pikine@education.sn', 1978,
                                                                                                                           'Laboratoire,Bibliothèque,Terrain de basket',
                                                                                                                           'school3_building.jpg,school3_court.jpg');

-- Insertion des coordonnées
INSERT INTO coordinates (school_id, latitude, longitude) VALUES
                                                             (1, 14.6937, -17.4441),
                                                             (2, 14.7645, -17.3660),
                                                             (3, 14.8085, -16.9299);

-- Liaisons école ↔ niveaux
INSERT INTO school_level (school_id, level_id) VALUES
                                                   (1, 3),  -- HIGH_SCHOOL
                                                   (2, 1),  -- PRIMARY
                                                   (3, 2),  -- SECONDARY
                                                   (3, 3);  -- aussi HIGH_SCHOOL

-- Insertion des avis
INSERT INTO review (user_id, school_id, rating, comment, date) VALUES
                                                                              (1, 1, 4, 'Très bon établissement avec des professeurs compétents.', '2024-03-15 16:45:00'),
                                                                              (2, 1, 5, 'Excellente école, je la recommande vivement!', '2024-03-20 11:30:00'),
                                                                              (1, 2, 4, 'Belle école privée avec de bonnes infrastructures.', '2024-03-25 09:15:00');

-- Insertion des messages de chat
INSERT INTO chat_message (user_id, type, content, timestamp, suggestions) VALUES
                                                                              (1, 'USER', 'Bonjour, je cherche une école primaire dans la région de Dakar', '2024-04-01 10:00:00', ''),
                                                                              (1, 'BOT', 'Bonjour! Je peux vous aider à trouver des écoles primaires à Dakar.', '2024-04-01 10:00:05', 'École Primaire Sainte-Marie,École Primaire Jean Mermoz,École Primaire des Parcelles'),
                                                                              (2, 'USER', 'Quelles sont les meilleures écoles secondaires publiques?', '2024-04-02 14:30:00', ''),
                                                                              (2, 'BOT', 'Voici les meilleures écoles secondaires publiques selon les avis:', '2024-04-02 14:30:03', 'Collège Moderne de Pikine,Collège Kennedy,Collège Ahmadou Ndack Seck');
