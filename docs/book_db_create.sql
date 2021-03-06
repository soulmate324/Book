use book;

CREATE TABLE search_history (
  account_id int(11) COMMENT '유저번호',
  query varchar(500) NOT NULL COMMENT '검색어',
  search_count int(11) DEFAULT 0 COMMENT '검색결과',
  created datetime DEFAULT now() COMMENT '검색일자'
) COMMENT='검색 히스토리';

CREATE TABLE member (
  user_idx int(11) NOT NULL AUTO_INCREMENT COMMENT '유저 순번',
  nick_name varchar(100) DEFAULT NULL COMMENT '별명',
  email varchar(200) DEFAULT NULL COMMENT '이메일',  
  passwd varchar(200) DEFAULT NULL COMMENT '비밀번호',  
  created datetime DEFAULT now() COMMENT '검색일자',
  PRIMARY KEY (user_idx)
) COMMENT='멤버 관리';

ALTER TABLE member ADD UNIQUE INDEX member_idx1 (email);


INSERT INTO member (nick_name, email, passwd) values ('정이','psme324@naver.com','');
commit;

SELECT account_id, nick_name FROM member WHERE email = '' AND passwd = '';

INSERT INTO search_history (account_id, query, search_count) values ();

SELECT query, search_count, created FROM search_history;