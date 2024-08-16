-- schedule 테이블 생성
CREATE TABLE schedule (
                          schedule_id INT AUTO_INCREMENT PRIMARY KEY,
                          assignee VARCHAR(20) NOT NULL,
                          pw VARCHAR(45) NOT NULL,
                          content VARCHAR(200) NOT NULL,
                          reg_date VARCHAR(45) NOT NULL,
                          mod_date VARCHAR(45) NOT NULL
);

-- 가장 최근에 작성된 글을 조회
-- SELECT * FROM schedule ORDER BY schedule_id DESC LIMIT 1;

-- 모든 할 일 조회
-- SELECT * FROM schedule ORDER BY mod_date DESC;

-- 할 일 저장
-- INSERT INTO schedule (assignee, pw, content, reg_date, mod_date) VALUES (?, ?, ?, ?, ?);

-- id 값과 일치하는 일정 조회
-- SELECT * FROM schedule WHERE schedule_id = ?;

-- 수정일로 조회
-- SELECT * FROM schedule WHERE mod_date LIKE ? ORDER BY mod_date DESC;

-- 담당자명으로 조회
-- SELECT * FROM schedule WHERE assignee = ? ORDER BY mod_date DESC;

-- 담당자명과 수정일로 조회
-- SELECT * FROM schedule WHERE assignee = ? AND mod_date LIKE ? ORDER BY mod_date DESC;

-- 할 일 수정
-- UPDATE schedule SET mod_date = ?, content = ? WHERE schedule_id = ? AND pw = ?;

-- 담당자만 수정
-- UPDATE schedule SET mod_date = ?, assignee = ? WHERE schedule_id = ? AND pw = ?;

-- 할 일과 담당자 수정
-- UPDATE schedule SET mod_date = ?, assignee = ?, content = ? WHERE schedule_id = ? AND pw = ?;

-- 스케줄 id와 pw가 일치하는지 확인
-- SELECT COUNT(*) FROM schedule WHERE schedule_id = ? AND pw = ?;

-- 선택한 일정 삭제
-- DELETE FROM schedule WHERE schedule_id = ? AND pw = ?;

-- 페이지네이션
-- SELECT * FROM schedule ORDER BY schedule_id ASC LIMIT ? OFFSET ?;
