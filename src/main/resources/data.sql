drop table if exists message;
drop table if exists account;
create table account (
    accountId int primary key auto_increment,
    username varchar(255) unique,
    password varchar(255)
);
create table message (
    message_id int primary key auto_increment,
    posted_by int,
    message_text varchar(255),
    time_posted_epoch bigint,
    foreign key (posted_by) references  account(account_id)
);


--add cascade delete/change later

-- Starting test values with ids of 9999 to avoid test issues
--insert into reimbursement values (9999, 'testuser1', 'password');
--insert into reimbursement values (9998, 'testuser2', 'password');
--insert into reimbursement values (9997, 'testuser3', 'password');
--insert into reimbursement values (9996, 'testuser4', 'password');

--insert into user values (9999, 9999,'test user 1',1669947792);
--insert into user values (9997, 9997,'test user 2',1669947792);
--insert into user values (9996, 9996,'test user 3',1669947792);

