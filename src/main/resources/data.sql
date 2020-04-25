
DELETE FROM moods WHERE  user_id = (select user_id from users where email='user1@test.pl');
DELETE FROM moods WHERE  user_id = (select user_id from users where email='user2@test.pl');
DELETE FROM moods WHERE  user_id = (select user_id from users where email='user3@test.pl');
DELETE FROM moods WHERE  user_id = (select user_id from users where email='user4@test.pl');

DELETE FROM moods WHERE  project_id = (select project_id from projects where title='Test Project1');
DELETE FROM moods WHERE  project_id = (select project_id from projects where title='Test Project2');
DELETE FROM moods WHERE  project_id = (select project_id from projects where title='Test Project3');
DELETE FROM moods WHERE  project_id = (select project_id from projects where title='Test Project4');
DELETE FROM moods WHERE  project_id = (select project_id from projects where title='Test Project5');
DELETE FROM moods WHERE  project_id = (select project_id from projects where title='Test Project6');

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


INSERT INTO projects( title, project_owner ) VALUES ('Test Project1',(select user_id from users where email='user1@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Project2',(select user_id from users where email='user1@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Project3',(select user_id from users where email='user2@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Project4',(select user_id from users where email='user3@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Project5',(select user_id from users where email='user4@test.pl'));
INSERT INTO projects( title, project_owner ) VALUES ('Test Project6',(select user_id from users where email='user4@test.pl'));


INSERT INTO project_user( joined_user, joined_project)
VALUES ((select user_id from users where email='user2@test.pl'),(select project_id from projects where title ='Test Project1'));
INSERT INTO project_user( joined_user, joined_project)
VALUES ((select user_id from users where email='user2@test.pl'),(select project_id from projects where title ='Test Project2'));
INSERT INTO project_user( joined_user, joined_project)
VALUES ((select user_id from users where email='user3@test.pl'),(select project_id from projects where title ='Test Project2'));
INSERT INTO project_user( joined_user, joined_project)
VALUES ((select user_id from users where email='user4@test.pl'),(select project_id from projects where title ='Test Project2'));