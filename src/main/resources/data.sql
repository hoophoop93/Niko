
DELETE FROM moods WHERE  user_id = (select user_id from users where email='user1@test.pl');
DELETE FROM moods WHERE  user_id = (select user_id from users where email='user2@test.pl');
DELETE FROM moods WHERE  user_id = (select user_id from users where email='user3@test.pl');
DELETE FROM moods WHERE  user_id = (select user_id from users where email='user4@test.pl');

DELETE FROM project_user WHERE joined_user = (select user_id from users where email='user1@test.pl');
DELETE FROM project_user WHERE joined_user = (select user_id from users where email='user2@test.pl');
DELETE FROM project_user WHERE joined_user = (select user_id from users where email='user3@test.pl');
DELETE FROM project_user WHERE joined_user = (select user_id from users where email='user4@test.pl');

DELETE FROM  projects WHERE project_owner = (select user_id from users where email='user1@test.pl');
DELETE FROM  projects WHERE project_owner = (select user_id from users where email='user2@test.pl');
DELETE FROM  projects WHERE project_owner = (select user_id from users where email='user3@test.pl');
DELETE FROM  projects WHERE project_owner = (select user_id from users where email='user4@test.pl');


INSERT INTO users(email, password_hash, display_name)
    VALUES ('user1@test.pl','$2a$10$DOBALodwib2ULPvHCHqr7ePZqj0XXy7sbOsCUALO0h4LpMRmoOeQS','Test User1');
INSERT INTO users(email, password_hash, display_name)
    VALUES ('user2@test.pl','$2a$10$DOBALodwib2ULPvHCHqr7ePZqj0XXy7sbOsCUALO0h4LpMRmoOeQS','Test User2');
INSERT INTO users(email, password_hash, display_name)
    VALUES ('user3@test.pl','$2a$10$DOBALodwib2ULPvHCHqr7ePZqj0XXy7sbOsCUALO0h4LpMRmoOeQS','Test User3');
INSERT INTO users(email, password_hash, display_name)
    VALUES ('user4@test.pl','$2a$10$DOBALodwib2ULPvHCHqr7ePZqj0XXy7sbOsCUALO0h4LpMRmoOeQS','Test User4');

delete from users where ctid NOT IN (select max(ctid) from users group by email);


INSERT INTO projects( title, project_owner ) VALUES ('Test Projekt1',(select user_id from users where email='user1@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Projekt2',(select user_id from users where email='user1@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Projekt3',(select user_id from users where email='user2@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Projekt4',(select user_id from users where email='user3@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Projekt5',(select user_id from users where email='user4@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Projekt6',(select user_id from users where email='user4@test.pl'));


INSERT INTO project_user( joined_user, joined_project)
VALUES ((select user_id from users where email='user2@test.pl'),(select project_id from projects where title ='Test Projekt1'));
INSERT INTO project_user( joined_user, joined_project)
VALUES ((select user_id from users where email='user2@test.pl'),(select project_id from projects where title ='Test Projekt2'));
INSERT INTO project_user( joined_user, joined_project)
VALUES ((select user_id from users where email='user3@test.pl'),(select project_id from projects where title ='Test Projekt2'));
INSERT INTO project_user( joined_user, joined_project)
VALUES ((select user_id from users where email='user4@test.pl'),(select project_id from projects where title ='Test Projekt2'));