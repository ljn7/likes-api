-- insert_data.sql
INSERT INTO users (user_id, name, created_at)
VALUES (111, 'John Doe', CURRENT_TIMESTAMP);

INSERT INTO users (user_id, name, created_at)
VALUES (222, 'Jane Smith', CURRENT_TIMESTAMP);

INSERT INTO users (user_id, name, created_at)
VALUES (333, 'Test User', CURRENT_TIMESTAMP);

INSERT INTO contents (content_id, total_likes, belongs_to, created_at)
VALUES (111, 0, 111, CURRENT_TIMESTAMP);

INSERT INTO contents (content_id, total_likes, belongs_to, created_at)
VALUES (222, 1, 222, CURRENT_TIMESTAMP);

INSERT INTO contents (content_id, total_likes, belongs_to, created_at)
VALUES (333, 2, 333, CURRENT_TIMESTAMP);

INSERT INTO likes (like_id, content_id, user_id, created_at)
VALUES (111, 222, 222, CURRENT_TIMESTAMP);

INSERT INTO likes (like_id, content_id, user_id, created_at)
VALUES (222, 222, 222, CURRENT_TIMESTAMP);

INSERT INTO likes (like_id, content_id, user_id, created_at)
VALUES (333, 333, 222, CURRENT_TIMESTAMP);

INSERT INTO likes (like_id, content_id, user_id, created_at)
VALUES (444, 333, 111, CURRENT_TIMESTAMP);

INSERT INTO notifications (notification_id, user_id, message, is_read, created_at)
VALUES (111, 111, 'Notification 1', false, CURRENT_TIMESTAMP);

INSERT INTO notifications (notification_id, user_id, message, is_read, created_at)
VALUES (222, 222, 'Notification 2', false, CURRENT_TIMESTAMP);

INSERT INTO notifications (notification_id, user_id, message, is_read, created_at)
VALUES (333, 333, 'Notification 3', false, CURRENT_TIMESTAMP);
