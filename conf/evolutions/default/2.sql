# --- Adding default values

# --- !Ups

INSERT into "ACCOUNT"("EMAIL", "PASSWORD", "FIRST_NAME", "LAST_NAME", "AGE", "ROLE", "STATUS") values ('bunyodreal@gmail.com','2912600','Bunyod','Bobojonov',25,3,1);
INSERT into "ACCOUNT"("EMAIL", "PASSWORD", "FIRST_NAME", "LAST_NAME", "AGE", "ROLE", "STATUS") values ('farabdullaev@gmail.com','fara321','Farrux','Abdullaev',25,2,1);
INSERT into "ACCOUNT"("EMAIL", "PASSWORD", "FIRST_NAME", "LAST_NAME", "AGE", "ROLE", "STATUS") values ('respect.net@bk.ru','muza321','Muzaffar','Artikov',25,1,1);

INSERT into "HOTEL_TYPE"("NAME") values ('Hostel');
INSERT into "HOTEL_TYPE"("NAME") values ('Apartment');
INSERT into "HOTEL_TYPE"("NAME") values ('Bed and Breakfast');

INSERT into "ROOM_TYPE"("NAME", "DESCRIPTION") values ('Hotel', 'A room assigned to one person. May have one or more beds.');
INSERT into "ROOM_TYPE"("NAME", "DESCRIPTION") values ('Double', 'A room assigned to two people. May have one or more beds.');
INSERT into "ROOM_TYPE"("NAME", "DESCRIPTION") values ('Triple', 'A room assigned to three people. May have two or more beds.');
INSERT into "ROOM_TYPE"("NAME", "DESCRIPTION") values ('Quad', 'A room assigned to four people. May have two or more beds.');
INSERT into "ROOM_TYPE"("NAME", "DESCRIPTION") values ('Queen', 'A room with a queen sized bed. May be occupied by one or more people.');
INSERT into "ROOM_TYPE"("NAME", "DESCRIPTION") values ('King', 'A room with a king sized bed. May be occupied by one or more people.');
INSERT into "ROOM_TYPE"("NAME", "DESCRIPTION") values ('Twin', 'A room with two twin beds. May be occupied by one or more people.');
INSERT into "ROOM_TYPE"("NAME", "DESCRIPTION") values ('Double-double', 'A Room with two double ( or perhaps queen) beds. May be occupied by one or more person.');

INSERT into "CITY"("NAME", "LATITUDE", "LONGITUDE", "REGION_ID") values ('Urgench', 80.000, 180.000, 1);
INSERT into "PRICE_INTERVAL"("NAME", "BOTTOM", "TOP") values (1, 5.0, 100.0);