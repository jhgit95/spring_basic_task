-- schedule 테이블 생성
CREATE TABLE schedule (
                          schedule_id INT AUTO_INCREMENT PRIMARY KEY,
                          assignee VARCHAR(20),
                          pw VARCHAR(45),
                          content VARCHAR(200),
                          reg_date VARCHAR(45),
                          mod_date VARCHAR(45),
                          FOREIGN KEY (assignee) REFERENCES assignee(assignee_id)
);

