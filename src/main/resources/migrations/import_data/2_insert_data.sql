SET search_path TO app;

INSERT INTO tasks (title, parent_task, max_xp) VALUES
('C3_SimpleBashUtils', NULL, 350),
('C2_s21_stringplus', 'C3_SimpleBashUtils', 750),
('C4_s21_math', 'C3_SimpleBashUtils', 300),
('C5_s21_decimal', 'C3_SimpleBashUtils', 350),
('C6_s21_matrix', 'C5_s21_decimal', 200),
('C7_SmartCalc_v1.0', 'C6_s21_matrix', 650),
('C8_3DViewer_v1.0', 'C7_SmartCalc_v1.0', 1043),
('DO1_Linux', 'C2_s21_stringplus', 350),
('DO2_LinuxNetwork', 'DO1_Linux', 350),
('DO3_LinuxMonitoring_v1.0', 'DO2_LinuxNetwork', 350),
('DO4_LinuxMonitoring_v2.0', 'DO3_LinuxMonitoring_v1.0', 501),
('DO5_SimpleDocker', 'DO3_LinuxMonitoring_v1.0', 300),
('DO6_CICD', 'DO5_SimpleDocker', 402),
('SQL2_Info21_v1.0', 'C8_3DViewer_v1.0', 600),
('SQL3_RetailAnalytics_v1.0', 'SQL2_Info21_v1.0', 600),
('CPP1_s21_matrixplus', 'C8_3DViewer_v1.0', 300),
('CPP2_s21_containers', 'CPP1_s21_matrixplus', 501),
('CPP3_SmartCalc_v2.0', 'CPP2_s21_containers', 750),
('CPP4_3DViewer_v2.0', 'CPP3_SmartCalc_v2.0', 1043),
('A1_Maze', 'CPP4_3DViewer_v2.0', 300),
('A2_SimpleNavigator_v1.0', 'A1_Maze', 400),
('A3_Parallels', 'A2_SimpleNavigator_v1.0', 300);


INSERT INTO peers (nickname, birthday) VALUES
('Aboba', '2000-01-01'),
('Baboba', '1995-02-02'),
('Cagoga', '1990-03-03'),
('Derevo', '1985-04-04'),
('Egogo', '2003-05-05');


INSERT INTO checks (peer, task, date) VALUES
('Aboba', 'C3_SimpleBashUtils', '2023-09-24'),
('Aboba', 'C2_s21_stringplus', '2023-09-26'),
('Baboba', 'DO1_Linux', '2023-09-25'),
('Derevo', 'SQL2_Info21_v1.0', '2023-09-26'),
('Egogo', 'CPP1_s21_matrixplus', '2023-09-27'),
('Cagoga', 'A1_Maze', '2023-09-26'),
('Baboba', 'DO1_Linux', '2023-09-30'),
('Aboba', 'DO1_Linux', '2023-09-30');


INSERT INTO p2p ("check", checking_peer, state, time) VALUES
(1, 'Baboba', 'Start', '13:00'),
(1, 'Baboba', 'Success', '13:24'),
(3, 'Cagoga', 'Start', '18:22'),
(3, 'Cagoga', 'Success', '18:36'),
(2, 'Cagoga', 'Start', '14:44'),
(2, 'Cagoga', 'Success', '15:15'),
(4, 'Egogo', 'Start', '20:54'),
(4, 'Egogo', 'Failure', '21:10'),
(5, 'Derevo', 'Start', '11:20'),
(5, 'Derevo', 'Success', '11:33'),
(6, 'Derevo', 'Start', '10:55'),
(6, 'Derevo', 'Success', '11:15'),
(7, 'Cagoga', 'Start', '10:00'),
(7, 'Cagoga', 'Success', '10:15'),
(8, 'Cagoga', 'Start', '10:30'),
(8, 'Cagoga', 'Success', '10:40');


INSERT INTO verter ("check", state, time) VALUES
(3, 'Start', '18:36'),
(3, 'Failure', '18:37'),
(7, 'Start', '10:15'),
(7, 'Success', '10:16'),
(8, 'Start', '10:41'),
(8, 'Success', '10:41');

INSERT INTO transferred_points (checking_peer, checked_peer, points_amount)
VALUES
('Baboba', 'Aboba', 1),
('Cagoga', 'Baboba', 1),
('Cagoga', 'Aboba', 1),
('Egogo', 'Derevo', 1),
('Derevo', 'Cagoga', 1),
('Derevo', 'Egogo', 1);


INSERT INTO friends (peer_1, peer_2)
VALUES
('Aboba', 'Baboba'),
('Egogo', 'Derevo'),
('Aboba', 'Cagoga'),
('Baboba', 'Derevo'),
('Derevo', 'Cagoga');


INSERT INTO recommendations (peer, recommended_peer)
VALUES
('Aboba', 'Cagoga'),
('Cagoga', 'Derevo'),
('Egogo', 'Derevo'),
('Derevo', 'Egogo'),
('Baboba', 'Cagoga');


INSERT INTO time_tracking (peer, date, time, state)
VALUES
('Aboba', TO_DATE('2023-09-24', 'YYYY-MM-DD'), '10:00', 1),
('Aboba', TO_DATE('2023-09-24', 'YYYY-MM-DD'), '20:00', 2),
('Baboba', TO_DATE('2023-09-24', 'YYYY-MM-DD'), '12:10', 1),
('Baboba', TO_DATE('2023-09-24', 'YYYY-MM-DD'), '16:33', 2),
('Cagoga', TO_DATE('2023-09-25', 'YYYY-MM-DD'), '06:00', 1),
('Cagoga', TO_DATE('2023-09-25', 'YYYY-MM-DD'), '09:00', 2);


INSERT INTO xp ("check", xp_amount)
VALUES
(1, 350),
(2, 700),
(5, 300),
(6, 285),
(7, 350),
(8, 340);

