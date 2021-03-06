CREATE TABLE IF NOT EXISTS user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  phone VARCHAR(250) NOT NULL
);


CREATE TABLE IF NOT EXISTS expense (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  type VARCHAR(250) NOT NULL,
  amount DOUBLE NOT NULL,
  paid_by int NOT NULL,
  paid_to int NOT NULL,
  note VARCHAR(250) NOT NULL
);


INSERT INTO user (name,  phone) VALUES
  ('USERA', '11111'),
  ('USERB', '22222'),
  ('USERC', '33333');
  

/*ALTER TABLE expense DROP CONSTRAINT  notification_ak
ALTER TABLE expense DROP CONSTRAINT  notification_bk*/

  
ALTER TABLE expense ADD CONSTRAINT notification_ak FOREIGN KEY (paid_to) REFERENCES user(id);
ALTER TABLE expense ADD CONSTRAINT notification_bk FOREIGN KEY (paid_by) REFERENCES user(id);

